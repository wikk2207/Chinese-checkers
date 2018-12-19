package tp.chinesecheckers.Server.GameBoard;

public interface Player {
  void start();

  void otherPlayerMoved(int opponent, int begX, int begY, int endX, int endY);

  void yourTurn();

  void turnEnd();
}
