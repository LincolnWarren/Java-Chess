/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Defines the bishop piece.
*/
import java.awt.Image;
import java.util.ArrayList;
public class Bishop extends Piece{
  public Bishop(int p, boolean c, Board b, Team t, Image i){
    super(3, p, c, b, t, i);
  }

  public ArrayList<Integer> showLegalMoves(){
    return generateSliding();
  }
}