package ilsa.multidim;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import ilsa.multidim.models.Square;

/**
 * Magic Squares making use of multi-dimensional arrays
 *
 */
public class App {
	public static void main(String[] args) {
		final URL FILE_LUNA = App.class.getClassLoader().getResource("Luna.txt");
		final URL FILE_MERCURY = App.class.getClassLoader().getResource("Mercury.txt");

		// tellen horizontaal en verticaal
		int numberOfTokensLuna = countTokens(FILE_LUNA);
		System.out.println(numberOfTokensLuna);
		
		int numberOfLinesLuna = countLines(FILE_LUNA);
		System.out.println(numberOfLinesLuna);

		// multidimensional arrays aanmaken
		Square squareLuna = new Square(numberOfTokensLuna, numberOfLinesLuna);
//		Square squareMercury = Square(numberOfTokens, numberOfLines);
		

//		Square squareMercury = new Square();
//		Square squareLuna = new Square();
//
//		readFile(FILE_MERCURY, squareMercury);
//
//		// check that all rows sum to the same constant.
//		boolean equalHorMercury = squareMercury.eachLineEqualSum();
//		System.out.printf("Do all rows sum to the same constant? %b\n", equalHorMercury);
//
//		// check that all columns sum to the same constant.
//		boolean equalVerMercury = squareMercury.eachColumnEqualSum();
//		System.out.printf("Do all columns sum to the same constant? %b\n", equalVerMercury);
//
//		// check that all diagonals sum to the same constant.
//		boolean equalDiaMercury = squareMercury.eachDiagonalEqualSum();
//		System.out.printf("Do all diagonals sum to the same constant? %b\n", equalDiaMercury);
//
//		readFile(FILE_LUNA, squareLuna);
//
//		boolean equalSumLuna = squareLuna.eachLineEqualSum();
//		System.out.printf("Do all rows sum to the same constant? %b\n", equalSumLuna);
//
//		boolean equalVerluna = squareLuna.eachColumnEqualSum();
//		System.out.printf("Do all columns sum to the same constant? %b\n", equalVerluna);
//
//		boolean equalDiaLuna = squareLuna.eachDiagonalEqualSum();
//		System.out.printf("Do all diagonals sum to the same constant? %b\n", equalDiaLuna);

	}

//	private static void readFile(URL fileName, Square square) {
//		int numberOfItems = countLines(fileName);
//		try (Scanner readLine = new Scanner(new FileReader(fileName.getFile()))) {
//
//			while (readLine.hasNextLine()) {
//				Line line = new Line();
//
//				for (int i = 0; i < numberOfItems; i++) {
//					int temp = readLine.nextInt();
//					line.getLine().add(temp);
//				}
//				square.getLines().add(line);
//			}
//
//		} catch (FileNotFoundException e) {
//			System.out.println("\nFile not found!\n");
//		} catch (NoSuchElementException e) {
//			System.out.println("\nEnd of file has been reached\n");
//		}
//
//		System.out.println(square.toString());
//
//	}

	private static int countLines(URL fileName) {
		int count = 0;
		try (Scanner readFile = new Scanner(new FileReader(fileName.getFile()))) {
			
			while (readFile.nextLine() != null) {
				count ++;
				readFile.nextLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Reached end of file\n");
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
			System.out.println("No line was found, or empty line\n");
		}

		return count;
	}

}
