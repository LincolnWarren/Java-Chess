/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Defines the knight piece.
*/
import java.awt.Image;
import java.util.ArrayList;
public class Knight extends Piece{
  public Knight(int p, boolean c, Board b, Team t, Image i){
    super(3, p, c, b, t, i);
  }

  public ArrayList<Integer> showLegalMoves(){
    ArrayList<Integer> moves= new ArrayList<Integer>();
    int[] vals = {15,17,6,10,-6,-10,-15,-17};
    for (int val:vals){
      int pos= val+super.getPosition();
      if (Math.abs(((super.getPosition()-1)%8)-((pos-1)%8)) > 2){
        continue;
      }
      if (pos<1 || pos>64){
        continue;
      }
      if (super.getBoard().getSquare(pos).getPiece()!=null && super.getBoard().getSquare(pos).getPiece().isWhite() == super.isWhite()){
        continue;
      }
      moves.add(pos);
    }
    return moves;
  }
}