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
