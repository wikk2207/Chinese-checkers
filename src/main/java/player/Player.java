package player;


import board.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.application.Application.launch;

public interface Player {

  void setId(int id);
  void setBoardSize(int size);
  void setMyTurn(boolean myTurn);
  Scene getScene();
  void addPlayer(int playerId);
  void createBoard();
  void uploadMove(int playerId, int fromX, int fromY, int toX, int toY);
  int getId();
}