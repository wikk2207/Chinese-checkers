package tp.chinesecheckers.Server.GameBoard;

import java.net.ServerSocket;

public class GameServer {
  public static void main(String[] args) throws Exception {
    ServerSocket listener = new ServerSocket(9931);
    System.out.println("Server is running");
    try {
      Game game = new Game();
      game.setFirst(listener.accept());
      System.out.println("Player conected");
      while (!game.isRulesSet()) {}
      for (int i = 1; i < game.howManyPlayers(); i++) {
        game.addPlayer(listener.accept());
        System.out.println("Player conected");
      }
      game.runGame();
    } finally {
      listener.close();
    }
  }
}
