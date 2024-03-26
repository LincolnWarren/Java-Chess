/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: To create and run a chess game using the FEN string. Uses a frame to display and get inputs froms the user.
*/

import javax.swing.JFrame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.util.ArrayList;

class Main {
    private static Team white = new Team();
    private static Team black = new Team();

    private static Board board = new Board();
    //true when it is white's turn to move
    private static boolean currentTurn = true;

    private static int halfmoveClock = 0;
    private static int fullmoveClock = 0;

    private static String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";

  private static JFrame frame = new JFrame();
  private static Panel panel = new Panel(white, black);
  public static void main(String[] args)throws IOException {

    white.setOtherTeam(black);
    black.setOtherTeam(white);

    //Takes the jpeg of pieces and puts them into an array
    BufferedImage all = ImageIO.read(new File("ChessPieces.png"));
    Image[] images = new Image[12];
    for (int y = 0; y<400; y+=200){
      int i = 6*y/200;
      for (int x = 0; x<1200; x+=200){
        images[i] = all.getSubimage(x,y,200,200).getScaledInstance(64,64, BufferedImage.SCALE_SMOOTH);
        i++;
      }
    }
    Panel.setImages(images);
    ClickListener.setImages(images);

    //Takes a FEN string and converts it into a board position with given rules
    int numSpaces = 0;
    int numSlashes = 0;
    int boardPosition = 57;
    for (int i =0; i<fen.length(); i++){
      char currentChar = fen.charAt(i);
      if (currentChar == ' '){
        numSpaces++;
        continue;
      }
      if (numSpaces == 0){
        if (currentChar == 'k'){
          new King(boardPosition, false, board, black, images[6]);
        } else if (currentChar == 'K'){
          new King(boardPosition, true, board, white, images[0]);
        } else if (currentChar == 'q'){
          new Queen(boardPosition, false, board, black, images[7]);
        } else if (currentChar == 'Q'){
          new Queen(boardPosition, true, board, white, images[1]);
        } else if (currentChar == 'b'){
          new Bishop(boardPosition, false, board, black, images[8]);
        } else if (currentChar == 'B'){
          new Bishop(boardPosition, true, board, white, images[2]);
        } else if (currentChar == 'n'){
          new Knight(boardPosition, false, board, black, images[9]);
        } else if (currentChar == 'N'){
          new Knight(boardPosition, true, board, white, images[3]);
        } else if (currentChar == 'r'){
          new Rook(boardPosition, false, board, black, images[10]);
        } else if (currentChar == 'R'){
          new Rook(boardPosition, true, board, white, images[4]);
        } else if (currentChar == 'p'){
          new Pawn(boardPosition, false, board, black, images[11]);
        } else if (currentChar == 'P'){
          new Pawn(boardPosition, true, board, white, images[5]);
        } else if (currentChar == '/'){
          numSlashes++;
          boardPosition=57-8*numSlashes;
          continue;
        } else {
          boardPosition +=Character.getNumericValue(currentChar);
          continue;
        }
        boardPosition++;
      }

       else if (numSpaces == 1){
        if (currentChar == 'b'){
          currentTurn = false;
        } else {
          currentTurn = true;
        }
      }

      else if (numSpaces == 2){
        if (currentChar == '-'){
          white.setKingsideCastle(false);
          white.setQueensideCastle(false);
          black.setKingsideCastle(false);
          black.setQueensideCastle(false);
        }
        else if (currentChar == 'K'){
          white.setKingsideCastle(true);
        }
        else if (currentChar == 'Q'){
          white.setQueensideCastle(true);
        }
        else if (currentChar == 'k'){
          black.setKingsideCastle(true);
        }
        else if (currentChar == 'q'){
          black.setQueensideCastle(true);
        }
      }

      else if (numSpaces == 3){
        if (currentChar == '-'){
          continue;
        }
        if (currentChar>=97){
          boardPosition = 1;
          boardPosition+=currentChar-97;
        } else{
          boardPosition += 8*(Character.getNumericValue(currentChar-1));
          board.getSquare(boardPosition).setEnPassant(true);
        }
      }

      else if (numSpaces == 4){
        halfmoveClock = halfmoveClock*10 + Character.getNumericValue(currentChar);
      }

      else if (numSpaces == 5){
        fullmoveClock = fullmoveClock *10 + Character.getNumericValue(currentChar);
      }
    }

    //sets up the frame
    frame.setBounds(0,0,512,512);
    frame.setUndecorated(true);
    JButton[] pieceButtons = new JButton[64];
    frame.add(panel);
    frame.setVisible(true);

    //adds all the buttons to the frame
    for (int y = 448; y>=0; y-=64){
      int i = (448-y)/8;
      for (int x = 0; x<512; x+=64){
        final int ind = i;
        pieceButtons[i] = new JButton();
        pieceButtons[i].setSize(64, 64);
        pieceButtons[i].setLocation(x, y);
        pieceButtons[i].setOpaque(false);
        pieceButtons[i].setContentAreaFilled(false);
        pieceButtons[i].setBorderPainted(false);


        ClickListener click = new ClickListener(i+1, panel, frame, board);
        ClickListener.setCurrentTurn(currentTurn);
        pieceButtons[i].addActionListener(click);
        frame.add(pieceButtons[i]);
        i++;
      }
    }
  }
}
