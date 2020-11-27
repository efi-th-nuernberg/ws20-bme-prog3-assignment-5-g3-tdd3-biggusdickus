public class Life implements ILife {
  private int[][] gameBoardCurrent;
  private int[][] gameBoardNextGen;
  private int board_width;
  private int board_height;
  
  public static void main(String[] args) {
    Life l = new Life(new String[] {  " *   ",
                                      " *   ",
                                      " *   ",
                                      "     ",
                                      "     " });
    l.printBoard();
    l = (Life) l.nextGeneration();
    l.printBoard();
    l = (Life) l.nextGeneration();
    l.printBoard();
    l = (Life) l.nextGeneration();
    l.printBoard();
    l = (Life) l.nextGeneration();
    l.printBoard();
    l = (Life) l.nextGeneration();
    l.printBoard();
  }

  public Life() {
    board_width = 5;
    board_height = 5;
    gameBoardCurrent = new int[board_width][board_height];
    gameBoardNextGen = createArrayCopy(gameBoardCurrent);
  }

  public Life(String[] setup) {
    board_width = setup[0].length();
    board_height = setup.length;
    gameBoardCurrent = new int[board_width][board_height];
    gameBoardNextGen = createArrayCopy(gameBoardCurrent);
    for (int y = 0; y < board_height; y++) {
      for (int x = 0; x < board_width; x++) {
        if (setup[y].charAt(x) != ' ') {
          setAlive(x, y);
        }
      }
    }
  }

  public int[][] createArrayCopy(int[][] matrix) {
    int [][] copy = new int[matrix.length][matrix[0].length];
      for(int i = 0; i< matrix.length; i++){
          for (int j = 0; j < matrix[i].length; j++){
              copy[i][j] = matrix[i][j];
          }
      }
      return copy;
  }

  public void printBoard() {
    for(int y = 0; y < board_height; y++) {
      for(int x = 0; x < board_width; x++) {
        if(isAlive(x,y)) System.out.print("*"); 
        else System.out.print(" ");
      }
      System.out.print("\n");
    }
  }

  @Override
  public void nukeAll() {
    for(int y = 0; y < board_height; y++) {
      for(int x = 0; x < board_width; x++) {
        setDead(x,y);
      }
    }
  }

  @Override
  public void setAlive(int x, int y) {
    gameBoardCurrent[x][y] = 1;
    gameBoardNextGen[x][y] = 1;
  }

  @Override
  public void setDead(int x, int y) {
    gameBoardCurrent[x][y] = 0;
    gameBoardNextGen[x][y] = 0;
  }

  @Override
  public boolean isAlive(int x, int y) {
    if((x >= 0) && (x < board_width) && (y >= 0) && (y < board_height)) {
      if(gameBoardCurrent[x][y] == 1) return true;
    }
    return false;
  }

  @Override
  public ILife nextGeneration() {
    applyRules();
    gameBoardCurrent = createArrayCopy(gameBoardNextGen);
    return this;
  }

  public void applyRules() {
    for(int y = 0; y < board_height; y++) {
      for(int x = 0; x < board_width; x++) {
        if(amountOfNeighbors(x, y) == 3) {
          gameBoardNextGen[x][y] = 1;
        }
        if(amountOfNeighbors(x, y) < 2) {
          gameBoardNextGen[x][y] = 0;
        }
        if(amountOfNeighbors(x, y) > 3) {
          gameBoardNextGen[x][y] = 0;
        }
      }
    }
  }

  public int amountOfNeighbors(int x, int y) {
    int neighbors = checkLeftCell(x, y) + checkRightCell(x, y)
    + checkTopLeftCell(x, y) + checkTopRightCell(x, y) 
    + checkBottomLeftCell(x, y) + checkBottomRightCell(x, y)
    + checkTopCell(x, y) + checkBottomCell(x, y); 
    return neighbors;
  }

  // LINKS
  public int checkLeftCell(int x, int y) {
    if(isAlive(x-1, y)) return 1;
    return 0;
  }
  
  // RECHTS
  public int checkRightCell(int x, int y) {
    if(isAlive(x+1, y)) return 1;
    return 0;
  }
  
  // OBEN LINKS
  public int checkTopLeftCell(int x, int y) {
    if(isAlive(x-1, y-1)) return 1;
    return 0;
  }

  // OBEN RECHTS
  public int checkTopRightCell(int x, int y) {
    if(isAlive(x+1, y-1)) return 1;
    return 0;
  }

  // UNTEN LINKS
  public int checkBottomLeftCell(int x, int y) {
    if(isAlive(x-1, y+1)) return 1;
    return 0;
  }
  
  // UNTEN RECHTS
  public int checkBottomRightCell(int x, int y) {
    if(isAlive(x+1, y+1)) return 1;
    return 0;
  }

  // UNTEN
  public int checkBottomCell(int x, int y) {
    if(isAlive(x, y+1)) return 1;
    return 0;
  }

  // OBEN
  public int checkTopCell(int x, int y) {
    if(isAlive(x, y-1)) return 1;
    return 0;
  }
}