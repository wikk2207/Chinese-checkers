package tp.chinesecheckers.Server.GameBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RealPlayer extends Thread implements Player {
  private final String SET_ID = "ID";
  private final String SET_OPPONENTS = "OPPONENTS";
  private final String SET_NUMBER_OF_PLAYERS = "CREATE_GAME";
  private final String OPPONENT_MOVED = "OPPONENT_MOVED";
  private final String YOUR_TURN = "YOUR_TURN";
  private final String TURN_END = "TURN_END";
  private final String START_GAME = "START_GAME";
  private final String WRONG_MOVE = "WRONG_MOVE";
  private final String CORRECT_MOVE = "CORRECT_MOVE";
  private final String JOINED_TO_GAME = "JOIN_GAME";
  private final String WON = "WON";

  public final String NUM_OF_PLAYERS = "PLAYERS";//<allPlayersNum> <bootNum>
  public final String MOVED = "MOVE";
  public final String END_MOVE = "END_MOVE";


  private int id;
  private Game game;
  private Socket socket;
  private BufferedReader input;
  private PrintWriter output;

  private boolean won;

  /**
   *
   * @param game
   * @param socket
   * @param id
   */
  public RealPlayer(Game game, Socket socket, int id) {
    this.game = game;
    this.socket = socket;
    this.id = id;
    won = false;
    try {
      input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      output = new PrintWriter(socket.getOutputStream(), true);
      output.println(SET_ID + " " + id);
      if(id == 1) {

        System.out.println("polaczono z klientem");
        output.println(SET_NUMBER_OF_PLAYERS);
        System.out.println("Wyslano prosbe o utowrzenie gry");

        while (true) {
          String command = input.readLine();
          System.out.println("Got message");
          if (command.startsWith(NUM_OF_PLAYERS)) {
            String[] arguments = command.split(" ");
            game.setRules(arguments[1], arguments[2]);
            System.out.println(command);
            break;
          }
        }
      } else {
        output.println(JOINED_TO_GAME);
      }
    } catch (IOException e) {
      System.err.println("Player is dead:" + e);
    }
  }

  @Override
  public void wrongMove() {
    output.println(WRONG_MOVE);
  }

  @Override
  public void correctMove() {
    output.println(CORRECT_MOVE);
  }

  @Override
  public void otherPlayerMoved(int opponent, int begX, int begY, int endX, int endY) {
    output.println(OPPONENT_MOVED + " " + opponent + " " + begX + " " + begY + " " + endX
        + " " + endY);
  }

  @Override
  public void yourTurn() {
    output.println(YOUR_TURN);
  }

  @Override
  public void setOpponetsNum(int players) {
    output.println(SET_OPPONENTS + " " + players);
  }

  @Override
  public void turnEnd() {
    if (won) {
      turnEnd();
    } else {
      output.println(TURN_END);
    }
  }

  /**
   *
   */
  public void run() {
    try {
      output.println(START_GAME);
      if (id == 1) {
        yourTurn();
      }

      while (true) {
        String command = input.readLine();
        if (command.startsWith(MOVED)) {
          String[] arguments = command.substring(MOVED.length() + 1).split(" ");
          int[] cordinates = new int[4];
          for (int i = 0; i < 4; i++) {
            try {
              cordinates[i] = Integer.parseInt(arguments[i]);
            } catch (NumberFormatException e) {
              System.err.println("Zły format liczby");
            }
          }
          game.move(id, cordinates[0], cordinates[1], cordinates[2], cordinates[3]);
        } else if (command.equals(END_MOVE)) {
          game.endMove(id);
        }
      }
    } catch (IOException e) {
      System.err.println("Player is dead: " + e);
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        System.err.println("Can't close socket" + e);
      }
    }
  }

  @Override
  public void won() {
    output.println(WON);
    won = true;
  }
}
