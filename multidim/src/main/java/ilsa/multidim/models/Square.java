package ilsa.multidim.models;

public class Square {
	private int[][] square;
	private int side;
	private boolean equalRowSum;
	private boolean equalColumnSum;
	private boolean equalDiaSum;
	private boolean magicSquare;
	private int rowSum;
	private int columnSum;
	private int diaSum;

	public Square(int side) {
		this.side = side;
		square = new int[side][side];

	}

	public int[][] getSquare() {
		return square;
	}
	
	public void checkSquare() {
		equalRowSum();
		equalColumnSum();
		equalDiaSum();
		checkMagicSquare();
	}

	private void equalRowSum() {
		int firstTotal = 0;

		for (int column = 0; column < square[0].length; column++) {
			firstTotal += square[0][column];
		}

		for (int row = 1; row < square.length; row++) {
			int totalOfThisRow = 0;
			for (int column = 0; column < square[row].length; column++) {
				totalOfThisRow += square[row][column];
			}

			if (totalOfThisRow != firstTotal) {
				equalRowSum = false;
			}
		}
		
		equalRowSum = true;
		
		if (equalRowSum) {
			rowSum = firstTotal;
		}
		
	}

	private void equalColumnSum() {
		int firstTotal = 0;

		for (int row = 0; row < square.length; row++) {
			firstTotal += square[row][0];
		}

		for (int column = 1; column < square[0].length; column++) {
			int totalOfThisColumn = 0;
			for (int row = 0; row < square.length; row++) {
				totalOfThisColumn += square[row][column];
			}

			if (firstTotal != totalOfThisColumn) {
				equalColumnSum = false;
			}
		}
		equalColumnSum = true;

		if (equalColumnSum) {
			columnSum = firstTotal;
		}
		
	}

	private void equalDiaSum() {
		int totalUpDown = 0;
		int totalDownUp = 0;

		for (int row = 0; row < square.length; row++) {
			totalUpDown += square[row][row];
		}

		for (int row = square.length - 1; row >= 0; row--) {
			totalDownUp += square[row][row];
		}
		
		equalDiaSum = (totalUpDown == totalDownUp);
		
		if (equalDiaSum) {
			diaSum = totalUpDown;
		}

	}
	
	
	private void checkMagicSquare() {
		magicSquare = this.equalRowSum && this.equalColumnSum && this.equalDiaSum;
	}
	
	public void printMagicDetails() {
		System.out.printf("Do all rows sum to the same constant of %d? %b\n", this.rowSum, this.equalRowSum);
		System.out.printf("Do all columns sum to the same constant of %d? %b\n", this.columnSum, this.equalColumnSum);
		System.out.printf("Do all diagonals sum to the same constant of %d? %b\n\n", this.diaSum, this.equalDiaSum);
		System.out.printf("Is this square a magic square? %b\n\n", this.magicSquare);
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
		System.out.printf("\nThis square has a side of %d.\n\n", this.side);
		return squareString.toString();

	}


}
