/* Author: Lincoln Warren
 * Date: 5/17/2021
 * Purpose: Creates a team of pieces.
*/
import java.util.ArrayList;
public class Team{
  private ArrayList<Piece> team;
  private boolean kingsideCastle;
  private boolean queensideCastle;
  private Team otherTeam;
  
  public Team(){
    team = new ArrayList<Piece>();
  }

  public void addPiece(Piece p){
    team.add(p);
  }
  public ArrayList<Piece> getTeam(){
    return team;
  }

  public void setKingsideCastle(boolean t){
    kingsideCastle = t;
  }
  public void setQueensideCastle(boolean t){
    queensideCastle = t;
  }
  public boolean getKingsideCastle(){
    return kingsideCastle;
  }
  public boolean getQueensideCastle(){
     return queensideCastle;
  }

  public void setOtherTeam(Team t){
    otherTeam = t;
  }
  public Team getOtherTeam(){
    return otherTeam;
  }

  //returns every move for each piece
  public ArrayList<Integer> getAllLegalMoves(){
    ArrayList<Integer> moves = new ArrayList<Integer>();
    for (Piece piece : team){
      for (int i : piece.showLegalMoves()){
        moves.add(i);
      }
    }
    return moves;
  }
}