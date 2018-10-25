package ilsa.multidim;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ilsa.multidim.models.Square;

/**
 * Magic Squares, making use of multi-dimensional array.
 *
 */
public class App {

	private static final Logger logger = Logger.getLogger(App.class.getName());
	
	static boolean onlyDigits = true;

	public static void main(String[] args) {
		final URL FILE_LUNA = App.class.getClassLoader().getResource("Luna.txt");
//		final URL FILE_MERCURY = App.class.getClassLoader().getResource("Mercury.txt");
//		final URL FILE_WEN = App.class.getClassLoader().getResource("wen.txt");

		List<URL> fileBook = new ArrayList<>();

		fileBook.add(FILE_LUNA);
//		fileBook.add(FILE_MERCURY);
//		fileBook.add(FILE_WEN);
		
		FileHandler handler = null;
		
		for (URL fileName : fileBook) {
			try {
				handler = new FileHandler("mylog.xml");
				logger.addHandler(handler);
				
				logger.log(Level.INFO, "About to create a square");
				Square square = createSquare(fileName);

				if (onlyDigits) {
					System.out.println(square.toString());
					square.printMagicDetails();
				}
			
			} catch (NullPointerException e) {
				logger.log(Level.INFO, "Check your spelling, can't find your file", e);
			} catch (FileNotFoundException e) {
				logger.log(Level.WARNING, "Not able to find your file.", fileName.toString());
			} catch (InputMismatchException e) {
				logger.log(Level.WARNING, "File does not contain a square", fileName.toString());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

	private static Square createSquare(URL fileName) throws FileNotFoundException, InputMismatchException {
		boolean squareAdmitted = isSquare(fileName);

		if (!squareAdmitted) {
			throw new InputMismatchException();
		}

		Square mySquare = new Square(countLines(fileName));

		try (Scanner readLine = new Scanner(new FileReader(fileName.getFile()))) {
			while (readLine.hasNextLine()) {

				for (int row = 0; row < mySquare.getSquare().length; row++) {
					for (int column = 0; column < mySquare.getSquare()[row].length; column++) {
						if (readLine.hasNextInt()) {
						mySquare.getSquare()[row][column] = readLine.nextInt();
						}
					}
				}
			}
			mySquare.checkSquare();
		} catch (InputMismatchException e) {
			logger.log(Level.WARNING, "Please provide a file with only digits!");
			onlyDigits = false;
		}
		return mySquare;

	}

	private static boolean isSquare(URL fileName) throws FileNotFoundException, InputMismatchException {
		int numberOfLines = countLines(fileName);
		int numberOfTokens = countTokens(fileName);

		return numberOfTokens != 0 || numberOfTokens == numberOfLines;
	}

	private static int countLines(URL fileName) throws FileNotFoundException {
		int count = 0;
		try (Scanner readFile = new Scanner(new FileReader(fileName.getFile()))) {

			while (readFile.nextLine() != null) {
				count++;
				if (!readFile.hasNextLine()) {
					break;
				}
				readFile.nextLine();
			}

		} catch (NoSuchElementException e) {
			logger.log(Level.INFO, "No more lines found");
		}
		return count;
	}

	private static int countTokens(URL fileName) throws FileNotFoundException, InputMismatchException {
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

				if (!readFile.hasNextLine()) {
					break;
				}
			}

		}
		return count;
	}

}