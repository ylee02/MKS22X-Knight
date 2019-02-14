public class KnightBoard {
  private int[][] board;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
  }

  public int findDigits(int num) {
    if (num < 10) {
      return 0;
    }
    return 1+ findDigits(num / 10);
  }


  public String toString() {
    String ans = "";
    for (int i = 0; i < board.length; i++) {
      for (int y = 0; y < board[0].length; y++) {
        if (board[i][y] / 10 == 0) {
          ans += " " + board[i][y] + " ";
        }
        else {
          ans += board[i][y] + " ";
        }
      }
      ans += "\n";
    }
    return ans;
  }

  public int[][] getBoard() {
    return board;
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  //or out of bounds.
  public boolean solve(int startingRow, int startingCol){
    return solveH(startingRow, startingCol, 0);
  }

  public boolean addKnight(int r, int c, int level) {
    if (r >= board.length || c >= board[0].length || r < 0 || c < 0 || board[r][c] != 0) {
      return false;
    }
    board[r][c] = level;
    return true;

  }

  public void removeKnight(int r, int c) {
    if (r >= board.length || c >= board[0].length || r < 0 || c < 0) {
      return;
    }
    board[r][c] = 0;
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  //or out of bounds.
  public int countSolutions(int startingRow, int startingCol){
    return 0;
  }

  private boolean solveH(int row ,int col, int level) {
    if (level == board.length * board[0].length && level != 0) {
      return true;
    }

    if (addKnight(row, col, level)) {
      System.out.println(toString());
      if (solveH(row - 2, col + 1, level + 1)) {
        System.out.println("a");
        return true;
      }
      if (solveH(row - 2, col - 1, level + 1)) {
        System.out.println("b");
        return true;
      }
      if (solveH(row - 1, col - 2, level + 1)) {
        System.out.println("c");
        return true;
      }
      if (solveH(row - 1, col + 2, level + 1)) {
        System.out.println("d");
        return true;
      }
      if (solveH(row + 1, col - 2, level + 1)) {
        System.out.println("e");
        return true;
      }
      if (solveH(row + 1, col + 2, level + 1)) {
        System.out.println("f");
        return true;
      }
      if (solveH(row + 2, col + 1, level + 1)) {
        System.out.println("g");
        return true;
      }
      if (solveH(row + 2, col - 1, level + 1)) {
        System.out.println("h");
        return true;
      }

    }
    removeKnight(row,col);
    level -= 1;
    return false;

  }
  // level is the # of the knight


}
