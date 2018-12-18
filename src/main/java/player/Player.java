package board;


import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.application.Application.launch;

public interface Player {
  /**
   * Checking if move is valid (following game rules).
   * @param fromX X coordinate of field when pawn starts move
   * @param fromY Y coordinate of field when pawn starts move
   * @param toX X coordinate of field when pawn ends move
   * @param toY Y coordinate of field when pawn ends move
   * @return
   */
  static boolean isMoveValid(double fromX, double fromY, double toX, double toY){
    return true; //in future - asking server if is valid
  };

  int getMyId();
  int getBoardSize();


  static void main (String[] args) { launch(args); }



}
