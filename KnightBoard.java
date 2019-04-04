public class KnightBoard {
  private int[][] board;
  //private int[][] solutionBoard;
  private int[] moves = {1,2,-1,2,1,-2,-1,-2,2,1,-2,1,2,-1,-2,-1};

  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) throws IllegalArgumentException{
    if (startingRows < 0 || startingCols < 0) {
		throw new IllegalArgumentException("Rows and/or columns cannot be negative.");
  	}
  	board = new int[startingRows][startingCols];
    //solutionBoard = new int[startingRows][startingCols];

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

  public void fillBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int y = 0; y < board.length; y++) {
        for (int x = 0; x < moves.length; x+=2) {
          try {
            board[i + moves[x]][y + moves[x+1]] = board[i + moves[x]][y + moves[x+1]];
            board[i][y]++;
          }catch (IndexOutOfBoundsException e) {
          }
        }
      }
    }
  }

  public boolean solve(int row, int col) {
    //fillBoard();
    return solveH(row, col, 1);
  }

  public boolean solveH(int row, int col, int level) {
    /*if (level == board.length * board[0].length && level != 0) {
      return true;
    }
    //int temprow = row;
    //int tempcol = col;
    int tem = board[row][col];
    int[] temp = findMin(row, col);
    board[row][col] = -1;
    solutionBoard[row][col] = level;
    level++;
    for (int i = 0; i < moves.length; i+=2) {
      try {
        board[row + moves[i]][col +moves[i+1]] -=1;
      }catch (IndexOutOfBoundsException e) {
      }
    }

    if (solveH(temp[1], temp[2])) {
      return true;
    }

    for (int i = 0; i < moves.length; i+=2) {
      try {
        board[row + moves[i]][col +moves[i+1]] +=1;
      }catch (IndexOutOfBoundsException e) {
      }
    }
    board[row][col] = tem;
    solutionBoard[row][col] = 0;
    level--;
    board[temp[1]][temp[2]] = -1;
    return solveH(row, col);
	*/
	if (level >= board.length * board[0].length) {
		return true;
	}
	
	if (addKnight(row, col, level)) {
		for (int i = 0; i < moves.length; i+=2) {
			if (solveH(row + moves[i], col + moves[i + 1], level+1)) {
				return true;
			}
			removeKnight(row + moves[i], col + moves[i + 1]);
		}
	}
	return false;
	
	



  }

  public int[] findMin(int row, int col) {
    int[] ans = new int[3];
    for (int i = 2; i < moves.length; i+=2 ) {
      try {
        if ((board[row + moves[i]][col +moves[i+1]] < ans[0] && board[row + moves[i]][col +moves[i+1]] <= -1) || board[row + moves[i]][col +moves[i+1]] == 0) {
          ans[0] = board[row + moves[i]][col + moves[i + 1]];
          ans[1] = row+  moves[i];
          ans[2] = col + moves[i + 1];
        }
      }catch(IndexOutOfBoundsException e) {
      }
    }
    return ans;
  }

  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  //or out of bounds.
  /*public boolean solve(int startingRow, int startingCol){
	if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
		throw new IllegalArgumentException("Knight out of bounds");
	}
	checkBoard();
	level = 1;
    return solveH(startingRow, startingCol);
  }*/

  public boolean addKnight(int r, int c, int level) {
    if (r >= board.length || c >= board[0].length || r < 0 || c < 0 || board[r][c] != 0) {
      return false;
    }
	if (board[r][c] == 0) {
		board[r][c] = level;
		return true;
	}
	return false;


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
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
		throw new IllegalArgumentException("Knight out of bounds");
	  }
  	checkBoard();
  	return countH(startingRow, startingCol, 1) / 8;
  }

  public int countH(int row, int col, int level) {
	  //solveH()
	  if (level == board.length * board[0].length + 1) {
		  //System.out.println(toString());
		  return 1;
	  }
	  int ans = 0;
	  for (int i = 0; i < 15; i+=2) {
		  if (addKnight(row, col, level)) {
			ans += countH(row + moves[i], col + moves[i+1], level+1);
			removeKnight(row,col);
		  }
	  }
	  return ans;
  }
  /*private boolean solveH(int row ,int col) {
    if (level == board.length * board[0].length && level != 0) {
      return true;
    }

    if (addKnight(row, col)) {
      level++;
      if (solveH(row - 2, col + 1)) {
        //System.out.println("a");
        return true;
      }
      if (solveH(row - 2, col - 1)) {
        //System.out.println("b");
        return true;
      }
      if (solveH(row - 1, col - 2)) {
        //System.out.println("c");
        return true;
      }
      if (solveH(row - 1, col + 2)) {
        //System.out.println("d");
        return true;
      }
      if (solveH(row + 1, col - 2)) {
        //System.out.println("e");
        return true;
      }
      if (solveH(row + 1, col + 2)) {
      //  System.out.println("f");
        return true;
      }
      if (solveH(row + 2, col + 1)) {
        //System.out.println("g");
        return true;
      }
      if (solveH(row + 2, col - 1)) {
        //System.out.println("h");
        return true;
      }
    }
    removeKnight(row,col);
    level -= 1;
    return false;
  }*/



  /*private boolean solveH(int row ,int col) {
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
  */

  // level is the # of the knight


}
