public class KnightBoard {
  private int[][] board;
  private int[] moves = {1,2,-1,2,1,-2,-1,-2,2,1,-2,1,2,-1,-2,-1};
  private int level;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) throws IllegalArgumentException{
    if (startingRows < 0 || startingCols < 0) {
		throw new IllegalArgumentException("Rows and/or columns cannot be negative.");
	}
	board = new int[startingRows][startingCols];
	level = 1;
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
  
  public void checkBoard() {
	  for (int i = 0; i < board.length; i++) {
		  for (int y = 0; y< board[0].length; y++) {
			  if (board[i][y] < 0) {
				  throw new IllegalStateException("Board cannot contain negative values");
			  }
		  }
	  }
  }	  

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  //or out of bounds.
  public boolean solve(int startingRow, int startingCol){
	if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
		throw new IllegalArgumentException("Knight out of bounds");
	}
	checkBoard();
	level = 1;
    return solveH(startingRow, startingCol);
  }

  public boolean addKnight(int r, int c) {
    if (r >= board.length || c >= board[0].length || r < 0 || c < 0 || board[r][c] != 0) {
      return false;
    }
	if (board[r][c] == 0) {
		board[r][c] = level;
		level++;
		return true;
	}
	return false;
    

  }

  public void removeKnight(int r, int c) {
    if (r >= board.length || c >= board[0].length || r < 0 || c < 0) {
      return;
    }
    board[r][c] = 0;
	level -= 1;
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  //or out of bounds.
  public int countSolutions(int startingRow, int startingCol){
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
		throw new IllegalArgumentException("Knight out of bounds");
	}
	checkBoard();
	level=1;
	return countH(startingRow, startingCol) / 8;
  }
  
  public int countH(int row, int col) {
	  //solveH()
	  if (level == board.length * board[0].length + 1) {
		  System.out.println(toString());
		  return 1;
	  }
	  int ans = 0;
	  for (int i = 0; i < 15; i+=2) {
		  if (addKnight(row, col)) {
			ans += countH(row + moves[i], col + moves[i+1]);
			removeKnight(row,col);
		  }
	  }
	  return ans;
  }
		  

  private boolean solveH(int row ,int col) {
	  if (level > board.length * board[0].length) {
		  return true;
	  }
	  for (int i = 0; i < 15; i += 2) {
		  if (addKnight(row, col)){
			  if (solveH(row + moves[i], col + moves[i + 1])) {
				  return true;
			  }
			  removeKnight(row, col);
		  }
	  }
	  return false;
	  
  }
    
  // level is the # of the knight


}
