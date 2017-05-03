/**
 * Assignment 1 - Boggle and Blob game
 * Andrew Pai
 * October 26, 2015
 * Java 1.7
 * ------------------------------------
 * Problem Definition - 1. Create a boggle game. Generate a a board of random letters (5x5). Use recursion to locate the users word
 * 2. Create a battleship game. Create a 20x25 board containing 3 blobs. When the user selects a point on a blob, use recursion to delete only that one blob
 * Input - Any words the user finds on the board, and coordinates they choose to attack.
 * Output - Whether the word they chose is on the board, and a battleship board showing the coordinates the user has attacked
 * Process - Using recursion to search around a certain location until either the entire word is found or all the blobs have been replaced
 * ------------------------------------
 * Identifiers:
 * BOARD - Static constant of the boggle board dimension <type int>
 * LENGTH - Static constant of the length of the battleship board <type int>
 * WIDTH - Static constant of the width of the battleship board <type int>
 */
import java.io.*;

public class Assignment1 {
	final static int BOARD = 5, LENGTH = 25, WIDTH = 20;

	/**
	 * main method
	 * 
	 * This procedural method is called automatically and is used to organize
	 * the calling of other methods defined in the class
	 * 
	 * Local Variables: 
	 * 
	 * ass - Object used to gain access to non-static methods defined in the class <type Assignment1>
	 * bggle - Object used to gain access to methods in Boggle class <type Boggle>
	 * blb - Object used to gain access to methods in Blob class <type Blob>
	 * br - Object used to get user input <type BufferedReader>
	 * usedSpace - 2D array to keep track of which letters on the boggle board have been used <type boolean>
	 * userBoard - User's battleship board that keeps track of the coordinates they have attacked <type String>
	 * EXIT - Constant that allows the user to exit by entering "exit" <type String>
	 * userWord - String value of the users input <type String>
	 * start - Current time when user started the boggle game <type double>
	 * end - Current time when user had found 5 words in boggle <type double>
	 * elapsedTime - Amount of time the user took to find 5 words in boggle <type double>
	 * cordX - x-coordinate of the battle ship board <type int>
	 * cordY - y-coordinate of the battleship board <type int>
	 * counter - Number of words the user has found in boggle <type int>
	 * 
	 * @param args
	 *            <type String>
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {

		Assignment1 ass = new Assignment1();
		Boggle bggle = new Boggle();
		Blob blb = new Blob();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String userBoard[][] = new String[WIDTH][LENGTH];
		final String EXIT = "Exit";
		String userWord;
		double start, end, elapsedTime;
		int cordX, cordY, counter;
		System.out
				.println("                      Welcome to Andrew's Games of Skill!");
		System.out
				.println("                    This Amazing Program Contains TWO Games!");
		ass.lineCreator("-", 85);
		System.out.println("The 2 games available are:");
		Thread.sleep(20);
		System.err
				.println("1. Boggle Word Game - Test your intelligence here!");
		System.err.println("2. BattleShip - Test your luck here!");
		Thread.sleep(20);
		System.out
				.println("Enter the number of the game you wish to play. (Enter 'exit' to quit the program ;_;)");
		userWord = ass.userInput(br, 0);
		while (!userWord.equalsIgnoreCase(EXIT)) {
			if (userWord.equals("1")) {
				ass.lineCreator("-", 85);
				System.out.println("Welcome to the Boggle Game");
				System.out.println();
				Thread.sleep(20);
				System.err.println("Rules");
				Thread.sleep(20);
				ass.lineCreator("~", 85);

				System.out.println("1. Find words formed on the board.");
				System.out
						.println("2. The letters used must be adjacent to the previous letter used.");
				System.out
						.println("3. Each letter on the board can only be used once.");
				System.out
						.println("4. Try to find 5 words as fast as you can.");
				System.out
						.println("5. You can Enter '!exit' at anytime to return to the menu");
				ass.lineCreator("~", 85);
				System.out.println("Are you ready?");
				Thread.sleep(20);
				System.err.println("1. Yes");
				System.err.println("2. No");
				userWord = ass.userInput(br, 3);
				if (userWord.equalsIgnoreCase("1")) {
					counter=0;
					ass.lineCreator("-", 85);
					start = System.currentTimeMillis();
					ass.bogglePrinter(bggle.getBoggle());
					Thread.sleep(20);
					System.err.println("Enter a word (!Exit to return to the menu):");
					System.err.println((5-counter)+" words to go.");
					Thread.sleep(20);
					userWord = ass.userInput(br, 1);
					while (!userWord.equalsIgnoreCase("!" + EXIT)) {

						if (ass.wordSearch(bggle.getBoggle(), userWord) == true) {
							System.out
									.println("Good job! That is a valid word");
							counter++;
						} else {
							Thread.sleep(50);
							System.err.println("Sorry, that word is not found");
							Thread.sleep(50);
						}
						ass.lineCreator("~", 85);

						if (counter == 5) {
							end = System.currentTimeMillis();
							elapsedTime = end - start;
							System.out.println("It took you "
									+ Math.round(elapsedTime / 10.0) / 100.0
									+ " seconds to find 5 words");
							System.out
									.println("Would you like to continue playing?");
							Thread.sleep(20);
							System.err.println("1. Yes");
							System.err.println("2. No");
							userWord = ass.userInput(br, 3);
							if (userWord.equals("1")) {
								System.out
										.println("Would you like to continue using this board?");
								Thread.sleep(20);
								System.err.println("1. Yes");
								System.err.println("2. No");
								userWord = ass.userInput(br, 3);

								counter = 0;
								if (userWord.equals("2"))
									bggle.setBoggle();
								ass.lineCreator("~", 85);
								System.out.println("Are you ready?");
								Thread.sleep(20);
								System.err.println("1. Yes");
								System.err.println("2. No");
								userWord = ass.userInput(br, 3);

							} else
								break;

						}
						ass.bogglePrinter(bggle.getBoggle());
						Thread.sleep(20);
						System.err.println("Enter a word (!Exit to return to the menu):");
						System.err.println((5-counter)+" words to go.");
						Thread.sleep(20);
						userWord = ass.userInput(br, 1);
					}
				}
				System.out.println("Thank you for playing boggle!");
				bggle.setBoggle();
			} else if (userWord.equals("2")) {
				ass.lineCreator("-", 85);
				System.out.println("Welcome to the BattleShip Game");
				System.out.println();
				Thread.sleep(20);
				System.err.println("Rules");
				ass.lineCreator("~", 85);

				System.out
						.println("1. There are 3 ships hidden on a 20x25 Board.");
				System.out
						.println("2. The objective of this game is to hit each ship at least once.");
				System.out
						.println("3. To choose the coordinate, enter the letter first followed by the number of the coordinate.");
				System.out.println("(Ex. E20)");
				System.out
						.println("5. You can Enter 'exit' at anytime to return to the menu");

				ass.lineCreator("~", 85);
				System.out.println("Are you ready?");
				Thread.sleep(20);
				System.err.println("1. Yes");
				System.err.println("2. No");
				userWord = ass.userInput(br, 3);

				if (userWord.equalsIgnoreCase("1")) {
					System.out.println();
					ass.lineCreator("-", 85);
					userBoard = ass.boardReset(userBoard);
					ass.blobPrinter(userBoard);
					System.out
							.println("Which coordinate would you like to choose? (ex. A6)");
					System.out
							.println("(Enter 'BOARD' to give up and see where the ships are hidden)");
					userWord = ass.userInput(br, 2);
					while (!userWord.equalsIgnoreCase(EXIT)) {
						ass.lineCreator("-", 85);
						if (userWord.equalsIgnoreCase("BOARD")) {

							ass.blobPrinter(blb.getGrid());
							System.out
									.println("Was this game too difficult for you?");
							System.out.println("Better luck next time!");
							break;
						} else {
							cordX = (int) Character.toUpperCase(userWord
									.charAt(0)) - 65;
							// Assigns the letter portion of the coordinate to a
							// numerical value (A=0, B=1, C=2, etc.)

							cordY = Integer.parseInt(userWord.substring(1)) - 1;

							userBoard = ass.blobChecker(blb.getGrid(),
									userBoard, cordX, cordY);

							ass.blobPrinter(userBoard);

							if (ass.blobFinished(userBoard) == true) {
								System.out.println("You had a "
										+ ass.blobAccuracy(userBoard)
										+ "% accuracy rate.");
								break;
							}
						}
						System.out
								.println("Which coordinate would you like to choose? (ex. A6)");
						System.out
								.println("(Enter 'BOARD' to give up and see where the ships are hidden)");
						userWord = ass.userInput(br, 2);
					}
				}
				System.out.println("Thanks for playing BattleShip!");
				ass.boardReset(userBoard);

			} else {
				System.out.println("Invalid option.");

			}
			ass.lineCreator("-", 85);
			System.out.println("The 2 games available are:");
			Thread.sleep(20);
			System.err.println("1. Boggle Word Game");
			System.err.println("2. BattleShip");
			Thread.sleep(20);
			System.out
					.println("Enter the number of the game to play the game. (Enter 'exit' to quit the program ;_;)");
			userWord = ass.userInput(br, 0);

		}
		ass.lineCreator("=", 85);
		System.out.println("Thank you for using this program!");
		Thread.sleep(50);
		System.err.println("Hope you had lots of fun!");
		Thread.sleep(20);

	}

	/**
	 * userInput method
	 * 
	 * This functional method reads the users input, determines if it's a valid
	 * input and returns the value
	 * 
	 * List of Identifiers:
	 * 
	 * input - The value entered by the user
	 * 
	 * @param br
	 *            - a BufferedReader object used for keyboard input <type
	 *            BufferedReader>
	 * @param type
	 *            - What type of input is needed <type int>
	 * @return input - The users input <type String>
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String userInput(BufferedReader br, int type) throws IOException,
			InterruptedException {
		String input;
		while (true) {
			input = br.readLine();
			if (input.equalsIgnoreCase("EXIT")
					|| input.equalsIgnoreCase("!EXIT"))
				return input;
			if (type == 0) {
				// Gets input for selection menu
				if (input.matches("[1-2]") || input.equalsIgnoreCase("EXIT"))
					return input;
				else {
					lineCreator("-", 85);
					System.out.println("Invalid input. Please enter 1 or 2.");
					System.out.println("The 2 games available are:");
					Thread.sleep(20);
					System.err.println("1. Boggle Word Game");
					System.err.println("2. BattleShip");
					Thread.sleep(20);
					System.out
							.println("Enter the number of the game to play the game. (Enter 'exit' to quit the program ;_;)");
				}
			} else if (type == 1) {
				// Gets input for boggle game
				if (!input.matches("[a-zA-Z]+"))
					// Checks to make sure the input only contains letters
					System.out
							.println("Invalid input. Please enter only letters.");
				else
					return input;

			} else if (type == 2) {
				// Gets input for blob games
				if (input.equalsIgnoreCase("BOARD"))
					return input;
				else if (!String.valueOf(input.charAt(0)).matches("[a-yA-Y]+")
						|| !input.substring(1).matches("0?[1-9]|1[0-9]|20")
						|| input.length() > 3 || input.length() < 2)
					// Checks to make sure the first character is a letter
					// Checks everything after the letter is a number from 1-20
					// Also checks to make sure the coordinate is of appropriate
					// length
					System.out
							.println("Invalid coordinate. Please enter a letter first, followed by a number");
				else
					return input;
			} else {
				if (input.equalsIgnoreCase("1")
						|| input.equalsIgnoreCase("Yes")
						|| input.equalsIgnoreCase("Y"))
					return "1";
				else if (input.equalsIgnoreCase("2")
						|| input.equalsIgnoreCase("No")
						|| input.equalsIgnoreCase("N"))
					return "2";
				else
					System.out
							.println("That is not a valid option. Please try again.");

			}

		}

	}

	/**
	 * lineCreater method:
	 * 
	 * This procedural method recursively creates a line to space out the text
	 * 
	 * @param line
	 *            - What string to print <type String>
	 * @param amount
	 *            - How many times the string is to be printed <type int>
	 */
	public void lineCreator(String line, int amount) {
		if (amount != 0) {
			System.out.print(line);
			lineCreator(line, amount - 1);
		} else
			System.out.println();
	}

	/**
	 * boardReset method
	 * 
	 * This functional method resets the users board back to empty spaces
	 * 
	 * @param userBoard
	 *            - The users battleship board that needs to be reset <type
	 *            String>
	 * @return userBoard - The reseted board <type String>
	 */
	public String[][] boardReset(String[][] userBoard) {
		// Sets all the values in the array to a space
		for (int y = 0; y < WIDTH; y++) {
			for (int x = 0; x < LENGTH; x++) {
				userBoard[y][x] = " ";
			}
		}
		return userBoard;
	}

	/**
	 * blobPrinter method
	 * 
	 * This procedural method prints out the battleship board
	 * 
	 * @param Grid
	 *            - The board to be printed out <type String>
	 */
	public void blobPrinter(String Grid[][]) {

		for (int y = WIDTH - 1; y >= 0; y--) {
			System.out.printf("%3s", y + 1 + "|");
			for (int x = 0; x < LENGTH; x++) {
				System.out.print(Grid[y][x]);
			}
			System.out.println();
		}
		// Prints so that bottom left is [0][0] and top right is [19][24]
		lineCreator("-", 29);
		System.out.print("   ");
		for (int x = 65; x < 90; x++) {
			System.out.print(String.valueOf((char) x));
		}
		System.out.println();
	}

	/**
	 * blobChecker method
	 * 
	 * This functional method checks the users inputed coordinate to see whether
	 * they hit or missed the ship and to call another method to mark the rest
	 * of the ship as hit
	 * 
	 * @param blobGrid
	 *            - Battleship board containing the hidden ships <type String>
	 * @param userBoard
	 *            - User's battleship board <type String>
	 * @param cordX
	 *            - x-coordinate inputed by the user <type int>
	 * @param cordY
	 *            - y-coordinate inputed by the user <type int>
	 * @return userBoard - User's updated board with new coordinate <type
	 *         String>
	 */
	public String[][] blobChecker(String blobGrid[][], String[][] userBoard,
			int cordX, int cordY) {
		if (!userBoard[cordY][cordX].equals(" "))
			System.out.println("Sorry, that coordinate has already been hit");
		else if (blobGrid[cordY][cordX].equals("*")) {
			System.out.println("Good Job! You hit a ship");
			userBoard = blobDeleter(blobGrid, userBoard, cordX, cordY);

		} else
			userBoard[cordY][cordX] = "O";
		return userBoard;
	}

	/**
	 * blobDeleter method
	 * 
	 * This functional method recursively searches around the users coordinate
	 * to reveal the rest of the hidden ship they hit
	 * 
	 * @param blobGrid
	 *            - Battleship Board containing the hidden ships <type String>
	 * @param userBoard
	 *            - User's battleship board <type String>
	 * @param cordX
	 *            - x-coordinate inputed by the user <type int>
	 * @param cordY
	 *            y-coordinate inputed by the user <type int>
	 * @return userBoard - User's updated board with the hit ship revealed <type
	 *         String>
	 */
	public String[][] blobDeleter(String[][] blobGrid, String[][] userBoard,
			int cordX, int cordY) {
		if (cordX < 0 || cordY < 0 || cordX == LENGTH || cordY == WIDTH)
			return userBoard;
		if (!userBoard[cordY][cordX].equals(" "))
			return userBoard;
		if (blobGrid[cordY][cordX].equals("*")) {

			userBoard[cordY][cordX] = "X";
			for (int y = -1; y <= 1; y++) {
				for (int x = -1; x <= 1; x++) {
					userBoard = blobDeleter(blobGrid, userBoard, cordX + x,
							cordY + y);
				}
			}
		}
		return userBoard;
	}

	/**
	 * blobFinished method
	 * 
	 * This functional method checks the coordinates of the 3 ships to see if
	 * the user has hit all of them
	 * 
	 * @param userBoard
	 *            - User's battleship board <type String>
	 * @return true - If all the ships have been hit <type boolean>
	 * @return false - If all the ships haven't been hit <type boolean>
	 */
	public boolean blobFinished(String[][] userBoard) {
		if (userBoard[1][6].equalsIgnoreCase("X")
				&& userBoard[18][6].equalsIgnoreCase("X")
				&& userBoard[14][15].equalsIgnoreCase("X")) {
			System.out.println("Good job! You hit all the targets!");
			return true;
		}

		return false;
	}

	/**
	 * blobAccuracy method
	 * 
	 * This functional method calculates the users guessing accuracy
	 * 
	 * List of identifiers:
	 * 
	 * counter - Number of coordinates the user has attacked <type double>
	 * accuracy - The accuracy of the user <type double>
	 * 
	 * @param userBoard
	 *            - User's battleship board <type String>
	 * @return accuracy - The users guessing accuracy (3 divided by (number of
	 *         missed attacks + 3) <type double>
	 */
	public double blobAccuracy(String[][] userBoard) {
		double counter = 3.0, accuracy;
		for (int y = 0; y < WIDTH; y++) {
			for (int x = 0; x < LENGTH; x++) {
				if (userBoard[y][x].equalsIgnoreCase("O"))
					counter++;
			}
		}
		accuracy = Math.round((3 / counter)*10000) / 100.0;
		return accuracy;

	}

	/**
	 * bogglePrinter method
	 * 
	 * This procedural method prints the boggle board
	 * 
	 * @param boggleWords
	 *            - The boggle board to be printed <type String>
	 */
	public void bogglePrinter(String boggleWords[][]) {
		for (int x = 0; x < BOARD; x++) {
			for (int y = 0; y < BOARD; y++) {
				// Prints words in tabular format
				System.out.printf("%-3s", boggleWords[x][y]);
			}
			System.out.println();
		}
	}

	/**
	 * wordSearch method
	 * 
	 * This functional method searches the boggle board for the first letter of
	 * the word inputed by the user. If the first letter is found, the
	 * adjacentLetter method is called to find the rest of the word.
	 * 
	 * List of Identifiers:
	 * 
	 * valid - Whether the word inputed by the user is found on the board <type
	 * boolean>
	 * 
	 * usedSpace - Used to check if the current letter has already been used
	 * <type boolean>
	 * 
	 * @param boggleWords
	 *            - The boggle board to search<type String>
	 * @param word
	 *            - The word inputed by the user <type String>
	 * @return valid - Whether the word is found on the board <type boolean>
	 */
	public boolean wordSearch(String boggleWords[][], String word) {
		boolean usedSpace[][] = new boolean[BOARD][BOARD];
		boolean valid = false;
		for (int x = 0; x < BOARD; x++) {
			for (int y = 0; y < BOARD; y++) {
				if (boggleWords[x][y].equalsIgnoreCase(String.valueOf(word
						.charAt(0)))) {
					valid = adjacentLetter(boggleWords, usedSpace, word, 0, x,
							y, false);
					usedReset(usedSpace);
					if (valid == true)
						return valid;

				}
			}
		}
		return valid;
	}

	/**
	 * adjacentLetter method
	 * 
	 * This functional method recursively searched around a letter to see if the
	 * users word is found on the board
	 * 
	 * @param boggleWords
	 *            - The boggle board to search for the word in <type String>
	 * @param usedSpace
	 *            - Which letters have already been used <type boolean>
	 * @param word
	 *            - The word to search for <type String>
	 * @param pos
	 *            - The current letter of the word to search for <type int>
	 * @param cordX
	 *            - The x-coordinate of the letter <type int>
	 * @param cordY
	 *            - The y-coordinate of the letter <type int>
	 * @param solved
	 *            - If the word has been found <type boolean>
	 * @return solved - Returns true if the word has been found, otherwise
	 *         returns false <type boolean>
	 */
	public boolean adjacentLetter(String boggleWords[][],
			boolean usedSpace[][], String word, int pos, int cordX, int cordY,
			boolean solved) {
		if (cordX < 0 || cordX == BOARD || cordY < 0 || cordY == BOARD)
			return false;
		// Checks if coordinates are out of bounds

		if (!boggleWords[cordX][cordY].equalsIgnoreCase(String.valueOf(word
				.charAt(pos))))
			return false;
		// Checks if the current letter is the right letter
		if (usedSpace[cordX][cordY] == true)
			return false;
		// Checks if the current letter has already been used
		if (pos == word.length() - 1) {
			return true;
		}

		for (int y = -1; y <= 1; y++) {
			for (int x = -1; x <= 1; x++) {
				usedSpace[cordX][cordY] = true;
				solved = adjacentLetter(boggleWords, usedSpace, word, pos + 1,
						cordX + x, cordY + y, solved);
				if (solved == true)
					return true;
				// Returns true if the word has been found so the program stops
				// searching
				if (cordX + x >= 0 && cordX + x < BOARD && cordY + y >= 0
						&& cordY + y < BOARD)
					usedSpace[cordX + x][cordY + y] = false;
				// If the letter wasn't found at a certain coordinate, reset it
				// back to the unused state
			}
		}
		// Loop to check around the current letter
		return solved;
	}

	/**
	 * usedReset method
	 * 
	 * This functional method resets all the letters in the boggle board to the
	 * unused state
	 * 
	 * @param usedSpot
	 *            - Which letters have been used <type boolean>
	 * @return usedSpot - Which letters have been used <type boolean>
	 */
	public boolean[][] usedReset(boolean usedSpot[][]) {// Resets all the
		// letters to the
		// unused state
		for (int x = 0; x < BOARD; x++) {
			for (int y = 0; y < BOARD; y++) {
				usedSpot[x][y] = Boolean.FALSE;
			}
		}
		return usedSpot;
	}
}

/**
 * Blob class
 * 
 * This class contains the battle ship board with the hidden ships
 * 
 * List of Identifiers: LENGTH - Length of the battle ship board <type int>
 * 
 * WIDTH - Width of the battle ship board <type int>
 * 
 * blobGrid - Static 2D array containing the battle ship board <type String>
 * 
 */
class Blob {
	final static int LENGTH = 25, WIDTH = 20;
	private String blobGrid[][] = new String[WIDTH][LENGTH];

	/**
	 * Blob method
	 * 
	 * This default constructor method initializes the battle ship board
	 * 
	 * List of IDentifiers: input - Get input from text file, "Blob.txt" <type
	 * BufferedReader>
	 * 
	 * line - One line from the text file <type String>
	 * 
	 * @throws IOException
	 */
	public Blob() throws IOException {
		// Reads from a text file to make the board
		BufferedReader input = new BufferedReader(new FileReader("Blob.txt"));
		String line;
		for (int y = WIDTH - 1; y >= 0; y--) {
			line = input.readLine();
			// System.out.println(line);
			for (int x = 0; x < LENGTH; x++) {
				blobGrid[y][x] = String.valueOf(line.charAt(x));
			}
		}
		input.close();
	}

	/**
	 * getGrid method
	 * 
	 * This accessor method allows access to the battle ship board, blobGrid.
	 * 
	 * @return blobGrid - The battle ship board
	 */
	public String[][] getGrid() {
		return blobGrid;
	}

}

/**
 * Boggle class
 * 
 * This class contains the boggle board
 * 
 * List of Identifiers:BOARD - dimension of the boggle board <type int>
 * boggleWords - the boggle board the user uses to play the game <type String>
 * 
 */
class Boggle {
	final static int BOARD = 5;
	private String boggleWords[][] = new String[BOARD][BOARD];

	/**
	 * Boggle method
	 * 
	 * Default constructor that initializes boggleWords
	 */
	public Boggle() {
		setBoggle();
	}

	/**
	 * setBoggle method
	 * 
	 * This mutator method creates a randomly generated boggle board
	 * 
	 */
	public void setBoggle() {
		for (int x = 0; x < BOARD; x++) {
			for (int y = 0; y < BOARD; y++) {
				boggleWords[x][y] = String.valueOf((char) Math.round((Math
						.random() * 25) + 65));
				// Chooses random letter
			}
		}
	}

	/**
	 * getBOggle method
	 * 
	 * This accessor method returns the boggle board
	 * 
	 * @return boggleWords - the boggle board <type String>
	 */
	public String[][] getBoggle() {
		return boggleWords;
	}
}
