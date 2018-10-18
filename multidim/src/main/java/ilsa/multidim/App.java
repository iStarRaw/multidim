package ilsa.multidim;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import ilsa.multidim.models.Square;

/**
 * Magic Squares, making use of multi-dimensional arrays
 *
 */
public class App {
	public static void main(String[] args) {
		
		final URL FILE_LUNA = App.class.getClassLoader().getResource("Luna.txt");
		final URL FILE_MERCURY = App.class.getClassLoader().getResource("Mercury.txt");

		int numberOfLinesLuna = countLines(FILE_LUNA);
		int numberOfTokensLuna = countTokens(FILE_LUNA);
		System.out.printf("Luna has %d columns and %d rows.\n", numberOfLinesLuna, numberOfTokensLuna);
		
		Square squareLuna = new Square(numberOfTokensLuna, numberOfLinesLuna);
		
		readFile(FILE_LUNA, squareLuna);
		
		// check that all rows sum to the same constant
		boolean equalSumLuna = squareLuna.equalRowSum();
		System.out.printf("Do all rows sum to the same constant? %b\n", equalSumLuna);

		// check that all columns sum to the same constant.
		boolean equalVerluna = squareLuna.equalColumnSum();
		System.out.printf("Do all columns sum to the same constant? %b\n", equalVerluna);

		// check that all diagonals sum to the same constant.
//		boolean equalDiaLuna = squareLuna.equalDiagonalSum();
//		System.out.printf("Do all diagonals sum to the same constant? %b\n", equalDiaLuna);
		
		
		
		
		int numberOfLinesMercury = countLines(FILE_MERCURY);
		int numberOfTokensMercury = countTokens(FILE_MERCURY);
		System.out.printf("Mercury has %d columns and %d rows.\n", numberOfLinesMercury, numberOfTokensMercury);
		
		Square squareMercury = new Square(numberOfTokensMercury, numberOfLinesMercury);
		
		readFile(FILE_MERCURY, squareMercury);

		
//		boolean equalHorMercury = squareMercury.equalRowSum();
//		System.out.printf("Do all rows sum to the same constant? %b\n", equalHorMercury);
		
//		boolean equalVerMercury = squareMercury.equalColumnSum();
//		System.out.printf("Do all columns sum to the same constant? %b\n", equalVerMercury);

//		boolean equalDiaMercury = squareMercury.equalDiagonalSum();
//		System.out.printf("Do all diagonals sum to the same constant? %b\n", equalDiaMercury);
//


	}

	private static void readFile(URL fileName, Square square) {
		try (Scanner readLine = new Scanner(new FileReader(fileName.getFile()))) {

			while (readLine.hasNextLine()) {

				for (int row = 0; row < square.getSquare().length; row++) {
					for (int column = 0; column < square.getSquare()[row].length; column++) {
						square.getSquare()[row][column] = readLine.nextInt();
					}
				}
				
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found!\n");
		} catch (NoSuchElementException e) {
			System.out.println("End of file has been reached.\n");
		}

		System.out.println(square.toString());

	}

	
	private static int countLines(URL fileName) {
		int count = 0;
		try (Scanner readFile = new Scanner(new FileReader(fileName.getFile()))) {

			while (readFile.nextLine() != null) {
				count++;
				readFile.nextLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Reached end of file.\n");
		}
		return count;
	}

	
	
	private static int countTokens(URL fileName) {
		int count = 0;
		try (Scanner readFile = new Scanner(new FileReader(fileName.getFile()))) {

			StringTokenizer line = new StringTokenizer(readFile.nextLine());
			count = line.countTokens();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("No line was found, or line is empty.\n");
		}

		return count;
	}

}
