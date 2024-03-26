/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Creates a square on the boaord that can store properties allowing the game to work properly
*/
public class Square{
  private boolean canEnPassant;
  private Piece current;

  public Square(){
    canEnPassant = false;
  }

  public void setPiece(Piece p){
    current = p;
  }
  public Piece getPiece(){
    return current;
  }
  public void movePiece(){
    current = null;
  }

  public void setEnPassant(boolean p){
    canEnPassant = p;
  }
  public boolean getEnPassant(){
    return canEnPassant;
  }
}