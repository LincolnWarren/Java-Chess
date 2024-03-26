/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: creates pieces that can do their specific type of move, checks for check, and sets the game to the rules of chess ie. castling and en passant.
*/
import java.awt.Image;
import java.util.ArrayList;
public class Piece{
  private int value;
  private int position;
  private boolean isWhitePiece;
  private Board board;
  private Team team;
  private Image image;
  

  public Piece(int v, int p, boolean c, Board b, Team t, Image i){
    value = v;
    position = p;
    isWhitePiece = c;
    board = b;
    team = t;
    image = i;
    t.addPiece(this);
    board.getSquare(position).setPiece(this);
  }

  //moves the piece and changes the state of the game if needed
  public void move(int p){
    //checks if the piece is a king and disables castling
    if (value == 999){
      team.setKingsideCastle(false);
      team.setQueensideCastle(false);
    }
    if (isWhitePiece){
      if (position == 8 && team.getKingsideCastle()){
        team.setKingsideCastle(false);
      } else if (position == 1 && team.getQueensideCastle()){
        team.setQueensideCastle(false);
      } else if (p == 64 && team.getOtherTeam().getKingsideCastle()){
        team.getOtherTeam().setKingsideCastle(false);
      } else if (p == 57 && team.getOtherTeam().getKingsideCastle()){
        team.getOtherTeam().setQueensideCastle(false);
      } else if (value == 1 && board.getSquare(p).getEnPassant()){
        board.getSquare(p-8).getPiece().remove();
      }
      for (int i =41; i<49; i++){
        board.getSquare(i).setEnPassant(false);
      }
    }

    else{
      if (position == 64 && team.getKingsideCastle()){
        team.setKingsideCastle(false);
      } else if (position == 57 && team.getQueensideCastle()){
        team.setQueensideCastle(false);
      } else if (p == 8 && team.getOtherTeam().getKingsideCastle()){
        team.getOtherTeam().setKingsideCastle(false);
      } else if (p == 1 && team.getOtherTeam().getKingsideCastle()){
        team.getOtherTeam().setQueensideCastle(false);
      } else if (value == 1 && board.getSquare(p).getEnPassant()){
        board.getSquare(p+8).getPiece().remove();
      }
      for (int i =17; i<25; i++){
        board.getSquare(i).setEnPassant(false);
      }
    }

    
    if (board.getSquare(p).getPiece() != null && board.getSquare(p).getPiece().isWhite() != isWhitePiece){
      board.getSquare(p).getPiece().remove();
    }
    
    if (value == 1){
      if (position+16 == p){
        board.getSquare(position+8).setEnPassant(true);
      } else if (position-16 == p){
        board.getSquare(position-8).setEnPassant(true);
      }
    }

    if (value == 999){
      if (p-position == 2){
        board.getSquare(p+1).getPiece().move(p-1);
      }
      if (p-position == -2){
        board.getSquare(p-2).getPiece().move(p+1);
      }
    }
    
    board.getSquare(position).movePiece();
    board.getSquare(p).setPiece(this);
    position = p;
  }


  
  public void remove(){
    team.getTeam().remove(this);
    value = 0;
  }

  
  public void add(){
    team.getTeam().add(this);
  }

  public ArrayList<Integer> showLegalMoves(){
    return new ArrayList<Integer>();
  }

  //Checks all the moves and makes sure they are all legal and if they aren't, they are removed.
  public ArrayList<Integer> showLegalMoves(ArrayList<Integer> mov){
    ArrayList<Integer> moves = new ArrayList<Integer>();
    for (int m :mov){
      moves.add(m);
    }
    int temp;
    Piece tempPiece;
    int tempValue = 0;
    
    if (value == 999){
      moves.add(0, position);
      moves.add(1, position+1);
      moves.add(2, position-1);
    }
    
    boolean kingCastling = true;
    boolean queenCastling = true;
    
    for (int i = 0; i<moves.size(); i++){
      temp = moves.get(i);
      
      if (value == 999){
        if (!queenCastling && position -2 == temp){
          moves.remove(i);
          i--;
          continue;
        }
          if (!kingCastling && position +2 == temp){
          moves.remove(i);
          i--;
          continue;
        }
      }
      if (board.getSquare(temp).getEnPassant()){
        if (isWhitePiece){
          tempPiece = board.getSquare(temp-8).getPiece();
        } else{
          tempPiece = board.getSquare(temp+8).getPiece();
        }
      } else if (board.getSquare(temp).getPiece() != null && temp != position){
        tempPiece = board.getSquare(temp).getPiece();
      } else{
        tempPiece = null;
      }
      if (tempPiece!=null){
        tempValue = tempPiece.getValue();
        if (board.getSquare(temp).getEnPassant()){
          if (isWhitePiece){
            board.getSquare(temp-8).movePiece();
          } else{
            board.getSquare(temp+8).movePiece();
          }
        } else{
          board.getSquare(temp).movePiece();
        }
        tempPiece.remove();
        
      }
      tempMove(temp, position);
      
      ArrayList<Integer> otherMoves = team.getOtherTeam().getAllLegalMoves();
      for (int oMove : otherMoves){
        if (board.getSquare(oMove).getPiece()!=null){
          if (board.getSquare(oMove).getPiece().getValue() == 999){
            if (i == 0 && value == 999){
              
              kingCastling = false;
              queenCastling = false;
              break;
            }else if (i ==1 && value == 999){
              kingCastling = false;
              break;
            } else if (i == 2 && value == 999){
              queenCastling = false;
              break;
            }
            moves.remove(i);
            i--;
            break;
          }
        }
      }
      
      tempMove(position, temp);
      if (tempPiece != null){
        tempPiece.setValue(tempValue);
        tempPiece.add();
        if (board.getSquare(temp).getEnPassant()){
          if (isWhitePiece){
            tempPiece.tempMove(temp-8, temp-8);
          } else{
            tempPiece.tempMove(temp+8, temp+8);
          }
        } else{
          tempPiece.tempMove(temp, temp);
        }
        
      }
    }
    if (value == 999){
      moves.remove(0);
      moves.remove(0);
      moves.remove(0);
    }
    return moves;
  }

  public void tempMove(int p1, int p2){
    board.getSquare(p2).movePiece();
    board.getSquare(p1).setPiece(this);
  }

  //generates the moves for bishops, rooks, and queens
  public ArrayList<Integer> generateSliding(){
    ArrayList<Integer> moves= new ArrayList<Integer>();
    int[] vectors = {-1, 1, -8, 8, -7, 7, -9, 9};
    int start = 0;
    int end = 8;
    if (value == 3){
      start = 4;
    } if (value == 5){
      end = 4;
    }
    for (int i = start; i<end; i++){
      for (int pos = position + vectors[i]; pos<65 && pos>0; pos+=vectors[i]){
        if ((pos-1)%8>(position-1)%8 && (vectors[i]==7 || vectors[i] == -9 || vectors[i] == -1)){
          break;
        }
        if ((pos-1)%8<(position-1)%8 && (vectors[i] == 9 || vectors[i] == -7 || vectors[i] == 1)){
          break;
        }
        if (board.getSquare(pos).getPiece() != null){
          if (board.getSquare(pos).getPiece().isWhite() == isWhitePiece){
            
            break;
          } else{
            
            moves.add(pos);
            break;
          }
        }
        
        moves.add(pos);
      }
    }
    return moves;
  }

  public int getPosition(){
    return position;
  }
  public boolean isWhite(){
    return isWhitePiece;
  }
  public void setValue(int v){
    value = v;
  }
  public int getValue(){
    return value;
  }
  public Board getBoard(){
    return board;
  }
  public Team getTeam(){
    return team;
  }
  public void setTeam(Team t){
    team = t;
  }
  public void setPosition(int p){
    position = p;
  }
  

  public Image getImage(){
    return image;
  }
}