/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Defines the king piece.
*/
import java.awt.Image;
import java.util.ArrayList;
public class King extends Piece{
  
  public King(int p, boolean c, Board b, Team t, Image i){
    super (999, p, c, b, t, i);
  }

  public ArrayList<Integer> showLegalMoves(){
    //adds all the moves directly next to the king
    ArrayList<Integer> moves = new ArrayList<Integer>();
    for (int x = -1; x<=1; x++){
      for (int y = -1; y<=1; y++){
        int pos = super.getPosition();
        if (x == 0 && y==0){
          continue;
        }
        pos+= x + y*8;
        if (pos<1 || pos>64){
          continue;
        }
        if (Math.abs(((super.getPosition()-1)%8)-((pos-1)%8)) > 1){
          continue;
        }
        if (super.getBoard().getSquare(pos).getPiece()!=null && super.getBoard().getSquare(pos).getPiece().isWhite() == super.isWhite()){
        continue;
        }
        moves.add(pos);
      }
    }

    //adds the moves for castling
    if (super.getTeam().getKingsideCastle() && super.getBoard().getSquare(super.getPosition()+1).getPiece() == null && super.getBoard().getSquare(super.getPosition()+2).getPiece() == null && super.getBoard().getSquare(super.getPosition()+3).getPiece() != null && super.getBoard().getSquare(super.getPosition()+3).getPiece().getValue() == 5){
      moves.add(super.getPosition()+2);
    }
    if (super.getTeam().getQueensideCastle() && super.getBoard().getSquare(super.getPosition()-1).getPiece() == null && super.getBoard().getSquare(super.getPosition()-2).getPiece() == null && super.getBoard().getSquare(super.getPosition()-3).getPiece() == null && super.getBoard().getSquare(super.getPosition()-4).getPiece() != null && super.getBoard().getSquare(super.getPosition()-4).getPiece().getValue() == 5){
      moves.add(super.getPosition()-2);
    }
    return moves;
  }
}