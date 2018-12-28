package player;

import board.Board;
import board.BoardException;
import board.Hexagon;
import board.Pawn;
import javafx.scene.Scene;
import sample.Counter;

import java.util.ArrayList;

public class RealPlayer implements Player {
  private Board board;
  private int size;
  private int playerId;
  private boolean myTurn;
  private int pawns;
  private ArrayList<ArrayList<Hexagon>> rows;
  Counter counter;

  public RealPlayer(Counter counter) {
    myTurn = false;
    this.counter=counter;
  }

  /**
   * Create own board for the concrete player.
   */
  public void createBoard() {
    try {
      board = new Board(size, playerId, this);
      rows = board.getRows();
    } catch (BoardException x) {
      System.out.println(x.getMessage());
      System.exit(-1);
    }
  }
  /**
   * Adding another player's pawns to this player's board.
   * @param id Id of another player
   */

  @SuppressWarnings("Duplicates")
  //!!!
  public void addPlayer(int id) {
    int idd;
    int a = playerId - 1;

    if(id > playerId) {
      idd = id - a;
    } else {
      idd = id + 6 - a;
    }

    ArrayList<Pawn> pawnsList = new ArrayList<>();
    int x;
    int y;
    int i;
    int j;
    double xx;
    double yy;
    Pawn newPawn;
    switch (idd) {
      case 1: {
        for (i = 0, y = size - pawns; i < pawns; i++) {
          for (j = 0, x = pawns; j < pawns - i; j++) {
            newPawn = new Pawn(y + i, x + j, id);
            xx = rows.get(y + i).get(x + j).getCenterX();
            yy = rows.get(y + i).get(x + j).getCenterY();
            newPawn.setCenterX(xx);
            newPawn.setCenterY(yy);
            rows.get(y + i).get(x + j).setPawn(newPawn);
            pawnsList.add(newPawn);
          }
        }

        break;
      }
      case 2: {
        for (i = 0, y = size - pawns - 1; i < pawns;i++) {
          for (x = pawns - 1, j = 0; j < pawns - i; j++) {
            newPawn = new Pawn(x - j, y - i, id);
            xx = rows.get(y - i).get(x - j).getCenterX();
            yy = rows.get(y - i).get(x - j).getCenterY();
            newPawn.setCenterY(yy);
            newPawn.setCenterX(xx);
            rows.get(y - i).get(x - j).setPawn(newPawn);
            pawnsList.add(newPawn);
          }
        }
        break;
      }
      case 3:
        for (y = pawns, i = 0; i < pawns;i++) {
          for (x = pawns, j = 0; j < pawns - i; j++) {
            newPawn = new Pawn(x + j, y + i, id);
            xx = rows.get(y + i).get(x + j).getCenterX();
            yy = rows.get(y + i).get(x + j).getCenterY();
            newPawn.setCenterX(xx);
            newPawn.setCenterY(yy);
            rows.get(y + i).get(x + j).setPawn(newPawn);
            pawnsList.add(newPawn);
          }
        }
        break;

      case 4: {
        for (y = pawns - 1, i = 0; i < pawns; i++) {
          for (x = size - pawns - 1, j = 0; j < pawns - i; j++) {
            newPawn = new Pawn(y - i, x - j, id);
            xx = rows.get(y - i).get(x - j).getCenterX();
            yy = rows.get(y - i).get(x - j).getCenterY();
            newPawn.setCenterX(xx);
            newPawn.setCenterY(yy);
            rows.get(y - i).get(x - j).setPawn(newPawn);
            pawnsList.add(newPawn);

          }
        }
        break;
      }
      case 5: {
        for (y = pawns, i = 0; i < pawns; i++) {
          for (x = size - pawns, j = 0; j < pawns - i; j++) {
            newPawn = new Pawn(y + i, x + j, id);
            xx = rows.get(y + i).get(x + j).getCenterX();
            yy = rows.get(y + i).get(x + j).getCenterY();
            newPawn.setCenterX(xx);
            newPawn.setCenterY(yy);
            rows.get(y + i).get(x + j).setPawn(newPawn);
            pawnsList.add(newPawn);
          }
        }
        break;
      }
      case 6: {

        for (y = size - pawns - 1, i = 0; i < pawns; i++) {
          for (x = size - pawns - 1, j = 0; j < pawns - i; j++) {
            newPawn = new Pawn(y - i, x - j, id);
            xx = rows.get(y - i).get(x - j).getCenterX();
            yy = rows.get(y - i).get(x - j).getCenterY();
            newPawn.setCenterX(xx);
            newPawn.setCenterY(yy);
            rows.get(y - i).get(x - j).setPawn(newPawn);
            pawnsList.add(newPawn);
          }
        }
        break;
      }
      default:
    }
    board.addPawns(pawnsList);
  }

  public void setId(int id) {
    this.playerId = id;
  }

  public void setBoardSize(int size) {
    this.size = size;
    pawns = (size - 1) / 4;
  }

  public void setMyTurn(boolean myTurn) {
    this.myTurn = myTurn;
    board.setMyTurn(myTurn);
  }

  public Scene getScene() {
    return board.getScene();
  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY) {
    board.uploadMove(playerId, fromX, fromY, toX, toY);
  }
  public boolean isMovePermitted (int fromX, int fromY, int toX, int toY) {
    return counter.isMovePermitted(fromX,fromY,toX,toY);
  }

  public int getId() {
    return playerId;
  }
}
