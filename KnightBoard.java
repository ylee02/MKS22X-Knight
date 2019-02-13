public class KnightBoard {
  private int[][] board;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
  }


  public String toString() {

  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  //or out of bounds.
  public boolean solve(int startingRow, int startingCol){
    return solveH(startingRow, startingCol, 0);
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  //or out of bounds.
  public int countSolutions(int startingRow, int startingCol){}

  private boolean solveH(int row ,int col, int level) {
    if (level == row * col) {
      return true;
    }
    try {
      board[row][col] = level;
      if (solveH(row - 2, row + 1, level + 1)) {
        return true;
      }
      if (solveH(row - 2, row - 1, level + 1)) {
        return true;
      }
      if (solveH(row - 1, row - 2, level + 1)) {
        return true;
      }
      if (solveH(row - 1, row + 2, level + 1)) {
        return true;
      }
      if (solveH(row + 1, row - 2, level + 1)) {
        return true;
      }
      if (solveH(row + 1, row + 2, level + 1)) {
        return true;
      }
      if (solveH(row + 2, row + 1, level + 1)) {
        return true;
      }
      if (solveH(row + 2, row - 1, level + 1)) {
        return true;
      }
      return false;
    }catch(Exception e) {
      return false;
    }

  }
  // level is the # of the knight


}
