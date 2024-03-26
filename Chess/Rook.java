/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Defines the rook piece.
*/
import java.awt.Image;
import java.util.ArrayList;
public class Rook extends Piece{
  public Rook(int p, boolean c, Board b, Team t, Image i){
    super(5, p, c, b, t, i);
  }
  public ArrayList<Integer> showLegalMoves(){
    return generateSliding();
  }
}