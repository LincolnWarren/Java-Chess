/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Takes the users input from the frame and moves the pieces based on that input
*/
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.Image;
public class ClickListener implements ActionListener{
  private static boolean turn;
  private static Piece firstPiece;
  private int first;
  private Panel panel;
  private JFrame frame;
  private Board board;
  private static Image[] images;
  public ClickListener(int i, Panel p, JFrame f, Board b){
    first = i;
    panel = p;
    frame  = f;
    board = b;
  }

  public void actionPerformed(ActionEvent e){
    //promotes a pawn if it is at the end of the board
    if (panel.getPromoting()){
      if (!promote()){
        return;
      }
      Piece p = firstPiece;
      firstPiece = null;
      p.remove();
      panel.setPromoting(false);
      panel.setMoves(new ArrayList<Integer>());
      frame.repaint();
      turn = !turn;
    }

    //sets the clicked piece to the current piece and shows all its legal moves
    else if (board.getSquare(first).getPiece() != null && board.getSquare(first).getPiece().isWhite() == turn){
      firstPiece = board.getSquare(first).getPiece();
      ArrayList<Integer> m = firstPiece.showLegalMoves();
      ArrayList<Integer> move = new ArrayList<Integer>();

      for (int i: firstPiece.showLegalMoves(m)){
        move.add(i);
      }
      panel.setMoves(move);
      frame.repaint();
    }

    //Checks if the clicked square is a legal move for the current piece
    else if (firstPiece!=null){
      for (int p : panel.getMoves()){
        if (first == p){
          firstPiece.move(p);
          if (firstPiece.getValue() == 1 && (p <9 || p >56)){
            panel.setPromoting(true);
            if (p>56){
              ArrayList<Integer> m = new ArrayList<Integer>();
              m.add(p-8);
              m.add(p-16);
              m.add(p-24);
              m.add(p-32);
              panel.setMoves(m);
            } else{
              ArrayList<Integer> m = new ArrayList<Integer>();
              m.add(p+8);
              m.add(p+16);
              m.add(p+24);
              m.add(p+32);
              panel.setMoves(m);
            }
            frame.repaint();
            break;
          }
          frame.repaint();
          turn = !turn;
          break;
        }
      }
      if (!panel.getPromoting()){
        panel.setMoves(new ArrayList<Integer>());
        frame.repaint();
        firstPiece = null;
      }
    }
  }
  //changes the pawn to a new piece
  public boolean promote(){
    if (firstPiece.getPosition()-8 == first){
      new Queen(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[1]);
    } else if (firstPiece.getPosition()-16 == first){
      new Bishop(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[2]);
    } else if (firstPiece.getPosition()-24 == first){
      new Knight(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[3]);
    } else if (firstPiece.getPosition()-32 == first){
      new Rook(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[4]);
    } else if (firstPiece.getPosition()+8 == first){
      new Queen(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[7]);
    } else if (firstPiece.getPosition()+16 == first){
      new Bishop(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[8]);
    } else if (firstPiece.getPosition()+24 == first){
      new Knight(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[9]);
    } else if (firstPiece.getPosition()+32 == first){
      new Rook(firstPiece.getPosition(), firstPiece.isWhite(), board, firstPiece.getTeam(), images[10]);
    } else{
      return false;
    }
    return true;
  }



  public static boolean getCurrentTurn(){
    return turn;
  }
  public static void setCurrentTurn(boolean b){
    turn = b;
  }

  public static Piece getFirstPiece(){
    return firstPiece;
  }
  public static void setFirstPiece(Piece p){
    firstPiece = p;
  }
  public static void setImages(Image[] i){
    images = i;
  }
}
