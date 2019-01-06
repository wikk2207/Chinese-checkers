package tp.chinesecheckers.Server.GameBoard;

/**
 * Dwuwymiarowa plansza.
 */
public class GameBoard {
  /**
   * -1 pole wyłączone z gry
   * 0 pole puste
   * 1-6 pole z pionkiem gracza.
   */

  /**
   * Konstruktor tworzacy pusta tablice bedaca podstawa planszy.
   */
  private int [][]board;

  public GameBoard(int height, int width) {
    board = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = 0;
      }
    }
  }

  /**
   * Metoda dodaje pionek do planszy.
   * Używana tylko przy tworzeniu planszy.
   * @param x pierwsza wspolrzedna punktu umieszczenia pionka
   * @param y druga wspolrzedna punktu umieszczenia pionka
   * @param player gracz do ktorego pionek bedzie nalezec (nr 1-6)
   */
  public void addPawn(int x, int y, int player) {
    board[x][y] = player;
  }

  /**
   * Metoda przemieszcza pionek.
   * @param pawnX pierwsza wspolrzedna lokalizacji pionka przemieszczanego
   * @param pawnY druga wspolrzedna lokalizacji pionka przemieszczanego
   * @param newX pierwsza wspolrzedna punktu docelowego przemieszczenia
   * @param newY druga wspolrzedna punktu docelowego przemieszczenia
   * @throws CantRemovePawnException wyrzucany gdy chcemy przemiescic cos z lub do puntu wylaczonego z gry
   */
  public void movePawn(int pawnX, int pawnY, int newX, int newY) throws CantRemovePawnException {

    if (board[newX][newY] == -1 || board[pawnX][pawnY] == -1) {
      throw new CantRemovePawnException();
    }
    board[newX][newY] = board[pawnX][pawnY];
    board[pawnX][pawnY] = 0;

    //TODO rysowanie planszy serwer
    System.out.println("Widok serwera");
    for (int j = 16; j > -1; j--){
      for ( int i = 0; i < 17; i++) {
        if(board[i][j] == -1){
          System.out.print("  ");
        } else {
          System.out.print(board[i][j] + " ");
        }
      }
      System.out.println();
    }
  }

  /**
   * Metoda zwaca informacje czy pole jest zajete przez pionek.
   * @param x pierwsza wspolrzedna sprawdzanego punktu
   * @param y druga wspolrzedna sprawdzanego punktu
   * @return boolean czy zajety
   */
  public boolean isFree(int x, int y) {
    return board[x][y] == 0;
  }

  public boolean isPlayer(int x, int y, int playerID) {
    return board[x][y] == playerID;
  }


}
