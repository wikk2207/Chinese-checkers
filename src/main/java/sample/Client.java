package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

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

  private Socket socket;
  private BufferedReader input;
  private PrintWriter output;
  private Counter counter;

  boolean run;
  boolean first_set = false;

  private static final int PORT = 9931;
  private static final String HOST = "localhost";

  public Client(Counter counter) {
    String response;
    this.counter = counter;
    run = true;
    try {
      socket = new Socket(HOST, PORT);
      input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      output = new PrintWriter(socket.getOutputStream(), true);

      while(!first_set) {
        response = input.readLine();
        if (response.startsWith(SET_ID)) {
          try {
            counter.setId(Integer.parseInt(response.substring(SET_ID.length() + 1)));
            System.out.println(response);
          } catch (NumberFormatException e) {
            System.err.println("Zły format ID");
          }
        } else if (response.equals(SET_NUMBER_OF_PLAYERS)) {
          System.out.println(response);
          counter.setFirst(true);
          first_set = true;
        } else if (response.startsWith(SET_OPPONENTS)) {
          System.out.println(response);
          try {
            counter.setNumberOfPlayers(
                Integer.parseInt(response.substring(SET_OPPONENTS.length() + 1))
            );
          } catch (NumberFormatException e) {
            System.err.println("Zły format SET_OPPONENTS");
          }
          first_set = true;
        }
      }
    } catch (IOException e) {
      System.err.println("Nie znaleziono serwera");
      System.exit(1);
    }
  }

  /**
   * Metoda uruchamiana w chwili uruchomienia aplikacji gracza
   */
  public void run() {
    String response;
    try {
      while (run) {
        response = input.readLine();
        //Do zweryfikowania przez Wiktorię
        if (response.startsWith(OPPONENT_MOVED)) {
          System.out.println(response);
          String[] arguments = response.split(" ");
          try {
            counter.uploadMove(
                Integer.parseInt(arguments[1]),
                Integer.parseInt(arguments[2]),
                Integer.parseInt(arguments[3]),
                Integer.parseInt(arguments[4]),
                Integer.parseInt(arguments[5])
            );
          } catch (NumberFormatException e) {
            System.err.println("Zły format SET_OPPONENTS");
          }
        } else if (response.equals(YOUR_TURN)) {
          counter.yourTurn();
          System.out.println(response);
        } else if (response.equals(TURN_END)) {
          System.out.println(response);
          counter.turnEnd();
        } else if (response.equals(START_GAME)) {
          System.out.println(response);
          counter.startGame();
        } else if (response.equals(WRONG_MOVE)) {
          System.out.println(response);
          counter.wrongMove();
        } else if (response.equals(CORRECT_MOVE)) {
          System.out.println(response);
          counter.correctMove();
        } else if (response.equals(CORRECT_MOVE)) {
          System.out.println(response);
          //TODO reakcja na zwycięstwo
          //counter.youWon(); czy coś XD
        }
      }
    } catch (IOException e) {
      System.err.println("Lost conection with server");
      System.exit(1);
    }
  }

  /**
   * Metoda uruchamiana przez pierwszego gracza, informuje server o liczbie graczy oraz bootow
   * @param players Liczba wszystkich graczy
   * @param boots Liczba bootów
   */
  public void setNumOfPlayers(int players, int boots) {
    output.println(NUM_OF_PLAYERS + " " + (players - boots) + " " + boots);
    System.out.println(NUM_OF_PLAYERS + " " + (players - boots) + " " + boots);
  }

  /**
   * Metoda wykonywana przez gracza w chwili przemieszczania pionka
   * @param begX Pierwsza wspolrzedna początku ruchu
   * @param begY Druga wspolrzedna początku ruchu
   * @param endX Pierwsza wspolrzedna konca ruchu
   * @param endY Druga wspolrzedna konca ruchu
   */
  public void move(int begX, int begY, int endX, int endY) {
    output.println(MOVED + " " + begX + " " + begY + " " + endX + " " + endY);
    System.out.println(MOVED + " " + begX + " " + begY + " " + endX + " " + endY);
  }

  /**
   * Metoda uruchamiana przez gracza w chwili zakonczenia ruchu
   */
  public void endMove() {
    output.println(END_MOVE);
  }






}
