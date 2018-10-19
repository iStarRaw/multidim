package ilsa.multidim;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
		final URL FILE_WEN = App.class.getClassLoader().getResource("wen.txt");

		List<URL> fileBook = new ArrayList<>();

		fileBook.add(FILE_LUNA);
		fileBook.add(FILE_MERCURY);
		fileBook.add(FILE_WEN);

		for (URL fileName : fileBook) {
			Square square = createSquare(fileName);

			readFile(fileName, square);
			System.out.println(square.toString());
			square.printMagicDetails();
		}

	}

//	private static Square createSquare(URL fileName) throws InputMismatchException {
//		int numberOfLines = countLines(fileName);
//		int numberOfTokens = countTokens(fileName);
//
//		// TODO check if input is a square (numberOfLines & numberOfTokens are same)
//		if (numberOfLines != numberOfTokens) {
//			new InputMismatchException();
//		}
//		
//		
//		return new Square(numberOfTokens);
//
//	}

	private static Square createSquare(URL fileName) {

		readFile(fileName);

	}

	private static void readFile(URL fileName, Square square) {

		isSquare(fileName);

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
			System.out.println("Reached end of file.\n");
		}
	}

	private static boolean isSquare(URL fileName) {
		int numberOfLines = countLines(fileName);
		int numberOfTokens = countTokens(fileName);

		return numberOfTokens != 0 || numberOfTokens == numberOfLines;
	}

	private static int countLines(URL fileName) {
		int count = 0;
		try (Scanner readFile = new Scanner(new FileReader(fileName.getFile()))) {

			while (readFile.nextLine() != null) {
				count++;
				readFile.nextLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found!\n");
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

			while (readFile.hasNextLine()) {
				StringTokenizer followingLine = new StringTokenizer(readFile.nextLine());
				int temp = followingLine.countTokens();

				if (temp != count) {
					count = 0;
					break;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found!\n");
		} catch (NoSuchElementException e) {
			System.out.println("No line was found, or line is empty.\n");
		}

		return count;
	}

}
