/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Defines the queen piece.
*/
import java.awt.Image;
import java.util.ArrayList;
public class Queen extends Piece{
  public Queen(int p, boolean c, Board b, Team t, Image i){
    super (9, p, c, b, t, i);
  }

  public ArrayList<Integer> showLegalMoves(){
    return generateSliding();
  }
}