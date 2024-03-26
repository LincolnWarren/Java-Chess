/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Paints the board, pieces, and moves onto the frame
*/
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
public class Panel extends JPanel{
  private ArrayList<Integer> moves = new ArrayList<Integer>();
  private Team white;
  private Team black;
  private boolean promoting;
  private static Image[] images;

  public Panel(Team w, Team b){
    white = w;
    black = b;
    promoting = false;
  }
  public static void setImages(Image[] i){
    images = i;
  }

  //paints the board based on the rules of the game.
  public void paint(Graphics g){
    for (int x = 0; x < 8; x++){
      for (int y = 7; y>=0; y--){
        boolean isPromotingSquare = false;
        boolean isRed = false;
        for (int i : moves){
          if ((8*(7-y)+x+1) == i){
            if (promoting){
              g.setColor(Color.orange);
              isPromotingSquare = true;
            } else {
              g.setColor(Color.red);
              if ((x+y)%2 == 1){
                g.setColor(Color.red.darker());
              }   
              isRed = true;
            }
            g.fillRect(x*64, y*64, 64, 64);
            break;
          }
        }
        if (isRed){
          continue;
        } if (isPromotingSquare){
          continue;
        }
        if ((x+y)%2 == 0){
          g.setColor(Color.white);
        } else {
          g.setColor(Color.lightGray);
        }
        g.fillRect(x*64, y*64, 64, 64);
      }
    }
    if (promoting){
      int i =1;
      if (!ClickListener.getFirstPiece().isWhite()){
        i = 7;
      }
      for (int m : moves){
        int xPos = ((m-1)%(8))*64;
        int yPos = ((64-m)/8)*64;
        g.drawImage(images[i], xPos,yPos, this);
        i++;
      }
    }
  for (Piece p: white.getTeam()){
      int xPos = ((p.getPosition()-1)%(8))*64;
      int yPos = ((64-p.getPosition())/8)*64;
      if (promoting){
        if (p.getPosition() == moves.get(0)){
          continue;
        }
        if (p.getPosition() == moves.get(1)){
          continue;
        }
        if (p.getPosition() == moves.get(2)){
          continue;
        }
        if (p.getPosition() == moves.get(3)){
          continue;
        }
      }
      g.drawImage(p.getImage(),xPos,yPos, this);
    }
    for (Piece p: black.getTeam()){
      int xPos = ((p.getPosition()-1)%(8))*64;
      int yPos = ((64-p.getPosition())/8)*64;
      if (promoting){
        if (p.getPosition() == moves.get(0)){
          continue;
        }
        if (p.getPosition() == moves.get(1)){
          continue;
        }
        if (p.getPosition() == moves.get(2)){
          continue;
        }
        if (p.getPosition() == moves.get(3)){
          continue;
        }
      }
      g.drawImage(p.getImage(),xPos,yPos, this);
    }  
  }



  
  public boolean getPromoting(){
    return promoting;
  }
  public void setPromoting(boolean b){
    promoting = b;
  }

  public void setMoves(ArrayList<Integer> m){
    moves = new ArrayList<Integer>();
    for (int move:m){
      moves.add(move);
    }
  }
  public ArrayList<Integer> getMoves(){
    return moves;
  }

  
}