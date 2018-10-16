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

}
