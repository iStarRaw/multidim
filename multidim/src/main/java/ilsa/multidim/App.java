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
	
	static boolean onlyDigits = true;
	
	public static void main(String[] args) {
		final URL FILE_LUNA = App.class.getClassLoader().getResource("Luna.txt");
//		final URL FILE_MERCURY = App.class.getClassLoader().getResource("Mercury.txt");
//		final URL FILE_WEN = App.class.getClassLoader().getResource("wen.txt");

		List<URL> fileBook = new ArrayList<>();

		fileBook.add(FILE_LUNA);
//		fileBook.add(FILE_MERCURY);
//		fileBook.add(FILE_WEN);

		for (URL fileName : fileBook) {
			Square square = createSquare(fileName);
			
			if (onlyDigits) {
			System.out.println(square.toString());
			square.printMagicDetails();
			}
		}

		
		
	}

	private static Square createSquare(URL fileName) throws InputMismatchException {
		boolean squareAdmitted = isSquare(fileName);

		if (!squareAdmitted) {
			throw new InputMismatchException();
		}

		Square mySquare = new Square(countLines(fileName));

		try (Scanner readLine = new Scanner(new FileReader(fileName.getFile()))) {
			while (readLine.hasNextLine()) {

				for (int row = 0; row < mySquare.getSquare().length; row++) {
					for (int column = 0; column < mySquare.getSquare()[row].length; column++) {
						mySquare.getSquare()[row][column] = readLine.nextInt();
					}
				}
			}
			mySquare.checkSquare();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!\n");
		} catch (InputMismatchException e) {
			System.out.println("Please provide a file with only digits!\n");
			onlyDigits = false;
		} catch (NoSuchElementException e) {
			System.out.println("Reached end of file.");
		}
		return mySquare;

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
			System.out.println("Reached end of file.");
		}
		return count;
	}

	private static int countTokens(URL fileName) {
		int count = 0;

		try (Scanner readFile = new Scanner(new FileReader(fileName.getFile()))) {

			StringTokenizer line = new StringTokenizer(readFile.nextLine());
			count = line.countTokens();

			while (readFile.nextLine() != null) {
				StringTokenizer followingLine = new StringTokenizer(readFile.nextLine());
				int temp = followingLine.countTokens();

				if (temp != count) {
					count = 0;
					break;
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found!\n");
		} catch (InputMismatchException e) {
			System.out.printf("%s does not contain a square\n", fileName.toString());
		} catch (NoSuchElementException e) {
			System.out.println("Reached end of file.");
		}
		return count;
	}

}