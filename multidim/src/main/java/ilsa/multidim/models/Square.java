package ilsa.multidim.models;

public class Square {
	private int[][] square;
	private int columnSize;
	private int rowSize;

	public Square(int columnSize, int rowSize) {
		this.columnSize = columnSize;
		this.rowSize = rowSize;
		square = new int[columnSize][rowSize];

	}

	public int[][] getSquare() {
		return square;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public int getRowSize() {
		return rowSize;
	}

	public boolean equalRowSum() {
		int firstTotal = 0;

		for (int column = 0; column < square[0].length; column++) {
			firstTotal += square[0][column];
		}

		for (int row = 1; row < square.length; row++) {
			int totalOfThisRow = 0;
			for (int column = 0; column < square[row].length; column++)
				totalOfThisRow += square[row][column];

			if (totalOfThisRow != firstTotal) {
				return false;
			}
		}

		return true;
	}

	public boolean equalColumnSum() {
		int firstTotal = 0;

		for (int row = 0; row < square.length; row++) {
			firstTotal += square[row][0];
		}
//		System.out.printf("Sum of the 1st column is %d.\n", firstTotal);

		for (int column = 1; column < square[0].length; column++) {
			int totalOfThisColumn = 0;
			for (int row = 0; row < square.length; row++) {
				totalOfThisColumn += square[row][column];
			}

//			System.out.println("Sum for column " + (column + 1) + " is " + totalOfThisColumn);
			if (firstTotal != totalOfThisColumn) {
				return false;
			}
		}
		return true;

	}

	// TODO afmaken
	public boolean equalDiagonalSum() {

		
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder squareString = new StringBuilder();

		for (int row = 0; row < square.length; row++) {
			for (int column = 0; column < square[row].length; column++) {
				squareString.append(square[row][column] + " ");
			}
			squareString.append("\n");
		}
		return squareString.toString();

	}

}
