/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Creates the chess board.
*/
public class Board{
  private Square[] board;

  public Board(){
    board = new Square[64];
    for (int i = 0; i< 64; i++){
      board[i] = new Square();
    }
  }

  public Square getSquare(int position){
    return board[position - 1];
  }
}