package tp.chinesecheckers.Server.GameBoard;

public class Boot implements Player {
  private boolean correctMove;

  GameBoard gameBoard;


  Boot(int boardSize) {
    gameBoard = new GameBoard(boardSize,boardSize);
  }


  @Override
  public void start() {

  }

  @Override
  public void otherPlayerMoved(int opponent, int begX, int begY, int endX, int endY) {

  }

  @Override
  public void yourTurn() {

  }

  @Override
  public void turnEnd() {

  }

  @Override
  public void setOpponetsNum(int players) {
    switch(players) {

    }
  }

  @Override
  public void wrongMove() {
    this.correctMove=false;
  }

  @Override
  public void correctMove() {
    this.correctMove=true;
  }



}
