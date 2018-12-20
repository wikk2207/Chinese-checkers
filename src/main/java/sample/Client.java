package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

  private final String SET_ID = "ID";
  private final String SET_OPPONENTS = "OPPONENTS";
  private final String SET_NUMBER_OF_PLAYERS = "CREATE_GAME";
  private final String OPPONENT_MOVED = "OPPONENT_MOVED";
  private final String YOUR_TURN = "YOUR_TURN";
  private final String TURN_END = "TURN_END";
  private final String START_GAME = "START_GAME";
  private final String WRONG_MOVE = "WRONG_MOVE";
  private final String CORRECT_MOVE = "CORRECT_MOVE";

  public final String NUM_OF_PLAYERS = "PLAYERS";//<realPlayersNum> <bootNum>
  public final String MOVED = "MOVE";
  public final String END_MOVE = "END_MOVE";

  private Socket socket;
  private BufferedReader input;
  private PrintWriter output;
  private Counter couter;

  boolean run;

  private static final int PORT = 9931;
  private static final String HOST = "localhost";

  public Client(Counter counter) {
    this.couter = counter;
    run = true;
    try {
      socket = new Socket(HOST, PORT);
      input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      output = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      System.err.println("Nie znaleziono serwera");
      System.exit(1);
    }
  }

  public void play() {
    String response;
    try {
      while (run) {
        System.out.println("READY TO READ");
        response = input.readLine();
        if (response.startsWith(SET_ID)) {
          //TODO wywołanie metody counter
        } else if (response.startsWith(SET_OPPONENTS)) {
          //TODO wywołanie metody counter
        } else if (response.equals(SET_NUMBER_OF_PLAYERS)) {
          //TODO wywołanie metody counter
        } else if (response.startsWith(OPPONENT_MOVED)) {
          //TODO wywołanie metody counter
        } else if (response.equals(YOUR_TURN)) {
          //TODO wywołanie metody counter
        } else if (response.equals(TURN_END)) {
          //TODO wywołanie metody counter
        } else if (response.equals(START_GAME)) {
          //TODO wywołanie metody counter
        } else if (response.equals(WRONG_MOVE)) {
          //TODO wywołanie metody counter
        } else if (response.equals(CORRECT_MOVE)) {
          //TODO wywołanie metody counter
        }
      }
    } catch (IOException e) {
      System.err.println("Lost conection with server");
      System.exit(1);
    }
  }

  public void setNumOfPlayers(int players, int boots) {
    output.println(NUM_OF_PLAYERS + " " + (players - boots) + " " + boots);
    System.out.println(NUM_OF_PLAYERS + " " + (players - boots) + " " + boots);
  }

  public void move(int begX, int begY, int endX, int endY) {
    output.println(MOVED + " " + begX + " " + begY + " " + endX + " " + endY);
    System.out.println(MOVED + " " + begX + " " + begY + " " + endX + " " + endY);
  }

  public void endMove() {
    output.println(END_MOVE);
  }






}
