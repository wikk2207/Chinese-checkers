package tp.chinesecheckers.Server.GameBoard;
//TODO Napsiać klasę gra która będzie przechowywać graczy i rozpoczynać grę

import java.net.Socket;

/**
 * Przechowywana przez gameMastera, tworzona przez server
 */
public class Game {
  //FLAG
  private boolean jump;
  private boolean setRules;

  private int currentPlayer;
  private int realPlayerNum;
  private int bootNum;
  private int iterator;
  private int begX;
  private int begY;

  private GameMaster master;
  private Player first;
  private Player[] players;

  /**
   *
   */
  public Game() {
    currentPlayer = 1;
    jump = false;
    setRules = false;
    master = GameMaster.getInstance();
    iterator = 1;
  }

  /**
   *
   * @param socket
   */
  public void setFirst(Socket socket) {
    this.first = new RealPlayer(this, socket, 1);
  }

  /**
   *
   * @return
   */
  public boolean isRulesSet() {
    return setRules;
  }

  /**
   *
   * @param players
   * @param boots
   */
  public void setRules(String players, String boots) {
    try {
      realPlayerNum = Integer.parseInt(players);
      bootNum = Integer.parseInt(boots);
      this.players = new Player[bootNum + realPlayerNum];
      this.players[0] = first;
      setRules = true;
      master.setGameMode(1);
      master.setPlayerNumber(bootNum + realPlayerNum);
    } catch (NumberFormatException e) {
      System.err.println("Wrong player number");
    }
  }

  /**
   *
   * @return
   */
  public int howManyPlayers() {
    return realPlayerNum;
  }

  /**
   *
   * @param socket
   */
  public void addPlayer(Socket socket) {
    players[iterator] = new RealPlayer(this, socket, iterator);
    iterator++;
  }

  /* TODO po stworzeniu boota
  private void addBoot(parametry potrzebne do stworzenia boota) {
    players[iterator] = new Boot(<nw>);
    iterator++;
  }
  */

  /**
   *
   */
  public void runGame() {
    /*
    while(iterator < realPlayerNum + bootNum) {
      addBoot();
    }
    */
    for(int i = 0; i < players.length; i++) {
      players[i].start();
    }
  }

  private void doneMove(int endX, int endY) {
    for (int i = 0; i < players.length; i++) {
      if (i != currentPlayer) {
        int[] cordinates = CordinateTranslator.serverToPlayer(currentPlayer, begX, begY, endX, endY);
        players[i].otherPlayerMoved(currentPlayer, cordinates[0],
            cordinates[1], cordinates[2], cordinates[3]);
      }
    }
  }

  private void nextPlayer() {
    players[currentPlayer].turnEnd();
    currentPlayer++;
    if (currentPlayer == players.length) {
      currentPlayer = 0;
    }
    players[currentPlayer].yourTurn();
    jump = false;
  }

  /**
   *
   * @param playerId ID gracza wywołującego metodę
   * @param beginX pierwsza wspołrzędna punktu początku
   * @param beginY druga wspołrzędna punktu początku
   * @param endX pierwsza wspołrzędna punktu końca
   * @param endY druga wspołrzędna punktu końca
   * @return
   */
  public boolean move(int playerId, int beginX, int beginY, int endX, int endY) {
    if (playerId != currentPlayer) {
      return false;
    }
    int[] cordinates = CordinateTranslator.playerToServer(playerId, beginX, beginY, endX, endY);
    int result = master.movePawn(jump, beginX, beginY, endX, endY);
    if (result == 2) {
      return false;
    } else if (result == 1) {
      if (!jump) {
        this.begX = beginX;
        this.begY = beginY;
      }
      jump = true;
      return true;
    } else {
      if (!jump) {
        this.begX = beginX;
        this.begY = beginY;
      }
      doneMove(endX, endY);
      nextPlayer();
      return true;
    }
  }

  public void endMove(int playerID) {
    if (playerID == currentPlayer) {
      nextPlayer();
    }
  }
}
