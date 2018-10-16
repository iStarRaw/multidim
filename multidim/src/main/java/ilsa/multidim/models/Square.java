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

	//TODO afmaken
	private boolean equalRowSum() {
		for (int row = 0; row < square.length; row++) {

		}

		return false;
	}

	
	//TODO afmaken
	private boolean equalColumnSum() {

		for (int column = 0; column < square[0].length; column++) {
			int total = 0;
			for (int row = 0; row < square.length; row++)
				total += square[row][column];
			System.out.println("Sum for column " + column + " is " + total);
		}

		return false;
	}

	
	//TODO afmaken
	private boolean equalDiagonalSum() {

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
