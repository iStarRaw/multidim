package ilsa.multidim;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import ilsa.multidim.models.Square;

/**
 * Magic Squares, making use of multi-dimensional array.
 *
 */
public class App {
	public static void main(String[] args) {
		final URL FILE_LUNA = App.class.getClassLoader().getResource("Luna.txt");
		final URL FILE_MERCURY = App.class.getClassLoader().getResource("Mercury.txt");
		
		List<URL> fileBook = new ArrayList<>();
		
		fileBook.add(FILE_LUNA);
		fileBook.add(FILE_MERCURY);
		
		
		for (URL fileName: fileBook) {
			Square square;
			square = createSquare(fileName);
			
			readFile(fileName, square);		
			System.out.println(square.toString());
			printMagicDetails(square);
			
		}


		
	}
	
	private static Square createSquare(URL fileName) {
		int numberOfLines = countLines(fileName);
		int numberOfTokens = countTokens(fileName);
		
		return new Square(numberOfTokens, numberOfLines);
		
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
	
	private static void printMagicDetails(Square square) {
		System.out.printf("Do all rows sum to the same constant? %b\n", square.isEqualRowSum());
		System.out.printf("Do all columns sum to the same constant? %b\n", square.isEqualColumnSum());
		System.out.printf("Do all diagonals sum to the same constant? %b\n\n", square.isEqualDiaSum());
		System.out.printf("Is this square a magic square? %b\n\n", square.isMagicSquare());
	}


}
