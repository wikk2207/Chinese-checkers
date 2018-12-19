package tp.chinesecheckers.Server.GameBoard;


public  class CordinateTranslator {

  private static int[] translateVectorToPlayer(int player, int x, int y) {
    int[] translatedVector = new int[2];
    switch (player) {
      case 1 :
        translatedVector[0] = x;
        translatedVector[1] = y;
        break;

      case 2:
        translatedVector[0] = x - y;
        translatedVector[1] = x;
        break;

      case 3:
        translatedVector[0] = -y;
        translatedVector[1] = x - y;
        break;

      case 4:
        translatedVector[0] = -x;
        translatedVector[1] = -y;
        break;

      case 5:
        translatedVector[0] = y - x;
        translatedVector[1] = -x;
        break;

      case 6:
        translatedVector[0] = y;
        translatedVector[1] = y - x;
        break;

      default:
        translatedVector[0] = -1;
        translatedVector[1] = -1;
        break;

    }
    return translatedVector;
  }

  private static int[] translateVectorToServer(int player, int x, int y) {
    int[] translatedVector = new int[2];
    switch (player) {
      case 1 :
        translatedVector[0] = x;
        translatedVector[1] = y;
        break;

      case 2:
        translatedVector[0] = y;
        translatedVector[1] = y - x;
        break;

      case 3:
        translatedVector[0] = -x + y;
        translatedVector[1] = -x;
        break;

      case 4:
        translatedVector[0] = -x;
        translatedVector[1] = -y;
        break;

      case 5:
        translatedVector[0] = -y;
        translatedVector[1] = x - y;
        break;

      case 6:
        translatedVector[0] = x - y;
        translatedVector[1] = x;
        break;

      default:
        translatedVector[0] = -1;
        translatedVector[1] = -1;
        break;

    }
    return translatedVector;
  }

  /**
   * Metoda statyczna przeksztalcajaca wspolrzedne wzgledne gracza na bezwzgledne serwera.
   * [0] - poczatkowy X
   * [1] - poczatkowy Y
   * [2] - koncowy X
   * [3] - koncowy Y
   * @param player Gracz dla ktorego nastepuje przeksztalcenie
   * @param begX piersza wspolrzedna punktu poczatkowego
   * @param begY druga wspolrzedna punktu poczatkowego
   * @param endX pierwsza wspolrzedna punktu koncowego
   * @param endY druga wspolrzedna punktu koncowego
   * @return tablica wspolrzednych po przeksztalceniu
   */
  public static int[] playerToServer(int player, int begX, int begY, int endX, int endY) {
    int[] zeroPoint = new int[2];
    int[] translatedVector1 = translateVectorToServer(player, begX - 4, begY - 0);
    int[] translatedVector2 = translateVectorToServer(player, endX - begX, endY - begY);
    int[] translatedCordinates = new int[4];

    switch (player) {
      case 1 :
        zeroPoint[0] = 4;
        zeroPoint[1] = 0;
        break;

      case 2:
        zeroPoint[0] = 0;
        zeroPoint[1] = 4;
        break;

      case 3:
        zeroPoint[0] = 4;
        zeroPoint[1] = 12;
        break;

      case 4:
        zeroPoint[0] = 12;
        zeroPoint[1] = 16;
        break;

      case 5:
        zeroPoint[0] = 16;
        zeroPoint[1] = 12;
        break;

      case 6:
        zeroPoint[0] = 12;
        zeroPoint[1] = 4;
        break;

    }

    translatedCordinates[0] = zeroPoint[0] + translatedVector1[0];
    translatedCordinates[1] = zeroPoint[1] + translatedVector1[1];
    translatedCordinates[2] = translatedCordinates[0] + translatedVector2[0];
    translatedCordinates[3] = translatedCordinates[1] + translatedVector2[1];
    return translatedCordinates;
  }

  /**
   * Metoda statyczna przeksztalcajaca wspolrzedne wzgledne gracza na bezwzgledne serwera.
   * [0] - poczatkowy X
   * [1] - poczatkowy Y
   * [2] - koncowy X
   * [3] - koncowy Y
   * @param player Gracz dla ktorego nastepuje przeksztalcenie
   * @param begX piersza wspolrzedna punktu poczatkowego
   * @param begY druga wspolrzedna punktu poczatkowego
   * @param endX pierwsza wspolrzedna punktu koncowego
   * @param endY druga wspolrzedna punktu koncowego
   * @return tablica wspolrzednych po przeksztalceniu
   */
  public static int[] serverToPlayer(int player, int begX, int begY, int endX, int endY) {
    int[] zeroPoint = new int[2];
    int[] translatedVector1;
    int[] translatedVector2 = translateVectorToPlayer(player, endX - begX, endY - begY);
    int[] translatedCordinates = new int[4];
    zeroPoint[0] = 4;
    zeroPoint[1] = 0;

    switch (player) {
      case 1 :
        translatedVector1 = translateVectorToPlayer(player, begX - 4, begY - 0);
        break;

      case 2:
        translatedVector1 = translateVectorToPlayer(player, begX - 0, begY - 4);
        break;

      case 3:
        translatedVector1 = translateVectorToPlayer(player, begX - 4, begY - 12);
        break;

      case 4:
        translatedVector1 = translateVectorToPlayer(player, begX - 12, begY - 16);
        break;

      case 5:
        translatedVector1 = translateVectorToPlayer(player, begX - 16, begY - 12);
        break;

      case 6:
        translatedVector1 = translateVectorToPlayer(player, begX - 12, begY - 4);
        break;

      default:
        translatedVector1 = new int[2];
        break;

    }

    translatedCordinates[0] = zeroPoint[0] + translatedVector1[0];
    translatedCordinates[1] = zeroPoint[1] + translatedVector1[1];
    translatedCordinates[2] = translatedCordinates[0] + translatedVector2[0];
    translatedCordinates[3] = translatedCordinates[1] + translatedVector2[1];
    return translatedCordinates;
  }



}
