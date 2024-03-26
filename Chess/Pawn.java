/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Defines the pawn piece.
*/
import java.awt.Image;
import java.util.ArrayList;
public class Pawn extends Piece{
  public Pawn(int p, boolean c, Board b, Team t, Image i){
    super (1, p, c, b, t, i);
  }
  
  //adds the specific pawn moves
  public ArrayList<Integer> showLegalMoves(){
    int position = super.getPosition();
    Board board = super.getBoard();
    ArrayList<Integer> moves = new ArrayList<Integer>();

    if (super.isWhite()){
      
      if (position<57 && board.getSquare(position+8).getPiece() == null){
        moves.add(position+8);
        if (position <17 && board.getSquare(position+16).getPiece() == null){
          moves.add(position+16);
        }
      }
      
      if (position<57 && (position-1)%8!=0 && board.getSquare(position+7).getPiece() != null && !board.getSquare(position+7).getPiece().isWhite()){
        moves.add(position+7);
      }
      else if(position<41 && board.getSquare(position+7).getEnPassant() && board.getSquare(position+7).getPiece() == null){
        moves.add(position+7);
      }
      
      if (position<57 && position%8!=0 && board.getSquare(position+9).getPiece() != null && !board.getSquare(position+9).getPiece().isWhite()){
        moves.add(position+9);
      }
      else if(position<41 && board.getSquare(position+9).getEnPassant()){
        moves.add(position+9);
      }
    }
    

    else{ 
      
    
    
      if (board.getSquare(position-8).getPiece() == null){
        moves.add(position-8);
        if (position > 48 && board.getSquare(position-16).getPiece() == null){
          moves.add(position-16);
        }
      }
    

      if (position>8 && position%8!=0 && board.getSquare(position-7).getPiece() != null && board.getSquare(position-7).getPiece().isWhite()){
        moves.add(position-7);
      }
      else if(position>17 && board.getSquare(position-7).getEnPassant()){
        moves.add(position-7);
      }

      
      if (position>8 && (position-1)%8!=0 && board.getSquare(position-9).getPiece() != null && board.getSquare(position-9).getPiece().isWhite()){
        moves.add(position-9);
      }
      else if(position>17 && board.getSquare(position-9).getEnPassant()){
        moves.add(position-9);
      }
    }
    return moves;
  }
  
}