package player;

import board.Board;
import board.Hexagon;
import javafx.scene.Scene;
import player.Player;

import java.util.ArrayList;

public class Boot implements Player {
  private Board board;
  private int size;
  private int playerId;
  private boolean myTurn;
  private int pawns;
  private ArrayList<ArrayList<Integer>> rows;

  public Boot() {
    myTurn = false;
    pawns = (size - 1) / 4;
  }

  public void setId(int id) {
    this.playerId=id;
  }
  public void setBoardSize(int size) {
    this.size = size;
  }
  public void setMyTurn(boolean myTurn) {
    this.myTurn=myTurn;
  }

  @Override
  public Scene getScene() {
    return null;
  }

  public void addPlayer(int playerID) {

  }

  @Override
  public void createBoard() {

  }

  public void uploadMove(int playerId, int fromX, int fromY, int toX, int toY){

  }


}
