package tp.chinesecheckers.Server.GameBoard;

import java.net.Socket;

/**
 * Przechowywana przez gameMastera, tworzona przez server
 */
public class Game {
  //Flaga używana do zapisania informacji czy gracz wykonał skok
  private boolean jump;
  //Wspołrzędne końca pionka którym został wykonany skok
  private int previousX;
  private int previousY;

  //Flaga informująca czy pierwszy rgacz już ustawił tryb i zasady gry
  private boolean setRules;

  private int currentPlayer;
  private int allPlayerNum;
  private int bootNum;
  private int iterator;
  private int begX;
  private int begY;

  private GameMaster master;
  private Player first;
  private Player[] players;
  private int[] IDs;

  /**
   *
   */
  public Game() {
    currentPlayer = 0;
    jump = false;
    setRules = false;
    master = GameMaster.getInstance();
    iterator = 0;
  }

  /**
   * Metoda tworzy pierwszego gracza wybierającego ilość graczy i tryb gry
   * @param socket Wtyczka pierwszego gracza
   */
  public void setFirst(Socket socket) {
    this.first = new RealPlayer(this, socket, 1);
    iterator++;
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
   * @param num
   */

  private void setIDs(int num) {
    IDs = new int[num];
    switch (num) {
      case 2:
        IDs[0] = 1;
        IDs[1] = 4;
        break;

      case 3:
        IDs[0] = 1;
        IDs[1] = 3;
        IDs[2] = 5;
        break;

      case 4:
        IDs[0] = 1;
        IDs[1] = 2;
        IDs[2] = 4;
        IDs[3] = 5;
        break;

      case 6:
        IDs[0] = 1;
        IDs[1] = 2;
        IDs[2] = 3;
        IDs[3] = 4;
        IDs[4] = 5;
        IDs[5] = 6;
    }
  }

  public void setRules(String players, String boots) {
    try {
      allPlayerNum = Integer.parseInt(players);
      bootNum = Integer.parseInt(boots);
      this.players = new Player[allPlayerNum];
      setIDs(allPlayerNum);
      this.players[0] = first;
      setRules = true;
      master.setGameMode(1);
      master.setPlayerNumber(allPlayerNum);
    } catch (NumberFormatException e) {
      System.err.println("Wrong player number");
    }
  }

  /**
   *
   * @return
   */
  public int howManyPlayers() {
    return allPlayerNum - bootNum;
  }

  /**
   *
   * @param socket
   */
  public void addPlayer(Socket socket) {
    players[iterator] = new RealPlayer(this, socket, IDs[iterator]);
    players[iterator].setOpponetsNum(allPlayerNum);
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
    while(iterator < allPlayerNum) {
      addBoot();
    }
    */
    players[0] = first;
    for(int i = 0; i < players.length; i++) {
      players[i].start();
    }
  }

  private void doneMove(int endX, int endY) {
    for (int i = 0; i < players.length; i++) {
      if (i != currentPlayer) {
        System.out.println("DONE MOVE " + i + " " + begX + " " + begY + " " + endX + " " + endY);
        int[] cordinates = CordinateTranslator.serverToPlayer(IDs[i], begX, begY, endX, endY);
        players[i].otherPlayerMoved(IDs[currentPlayer], cordinates[0],
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
    System.out.println("Current player: " + currentPlayer);
    jump = false;
    players[currentPlayer].yourTurn();
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
  public void move(int playerId, int beginX, int beginY, int endX, int endY) {
    if (playerId != IDs[currentPlayer]) {
      players[currentPlayer].wrongMove();
      return;
    }
    int[] cordinates = CordinateTranslator.playerToServer(playerId, beginX, beginY, endX, endY);
    int result = master.movePawn(jump, cordinates[0], cordinates[1], cordinates[2], cordinates[3]);
    if (result == 2) {
      players[currentPlayer].wrongMove();
      System.out.println(cordinates[0] + " " + cordinates[1] + " " + cordinates[2] + " " + cordinates[3]);
      System.out.println("Zły ruch " + jump);
      return;
    } else if (result == 1) {
      if (!jump) {
        this.begX = cordinates[0];
        this.begY = cordinates[1];
      } else {
        if (cordinates[0] != previousX || cordinates[1] != previousY) { //TODO dopiero dodane, może wywoływać błędy
          players[currentPlayer].wrongMove();
          return;
        }
      }
      previousX = cordinates[2];
      previousY = cordinates[3];
      players[currentPlayer].correctMove();
      jump = true;
      System.out.println("IS JUMP: " + jump);
      return;
    } else {
      this.begX = cordinates[0];
      this.begY = cordinates[1];
      players[currentPlayer].correctMove();
      doneMove(cordinates[2], cordinates[3]);
      nextPlayer();
      System.out.println("SINGLE MOVE  +  IS JUMP: " + jump);
      return;
    }
  }

  public void endMove(int playerID) {
    if (playerID == IDs[currentPlayer]) {
      if (jump) {
        doneMove(previousX, previousY);
      }
      nextPlayer();

    }
  }
}
