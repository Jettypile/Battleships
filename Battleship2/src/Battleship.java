//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            BP
// Files:            (list of source files)
// Semester:         CS 200 Fall 2017
//
// Author:           Omar kurosu jalil
// Email:            kurosujalil@wisc.edu
// CS Login:         kurosu-jalil
// Lecturer's Name:  Marc Renault
// Lab Section:      038
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, roommates 
// strangers, etc do.
//
// Persons:          (identify each person and describe their help in detail)
// Online Sources:   (identify each URL and describe its assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
import java.util.Random;

public class Battleship {

    /**
     * This method converts a String representing a base (or radix) 26 number into a decimal (or 
     * base 10) number. The String representation of the base 26 number uses the letters of the 
     * Latin alphabet to represent the 26 digits. That is, A represents 0, B represents 1, C 
     * represents 2, ..., Y represents 24,  and Z represents 25.
     *
     * A couple of examples:
     * BAAA = 1 * 26^3 + 0 * 26^2 + 0 * 26^1 + 0 * 26^0 = 17576
     * ZERTY = 25 * 26^4 + 4 * 26^3 + 17 * 26^2 + 19 * 26^1 + 24 * 26^0 = 11506714
     *
     * For this method:
     *   - use Math.pow to calculate the powers of 26.
     *   - don't assume that the input is in any particular case; use toUpperCase().
     *   - don't check that the input is only 'A' to 'Z'.
     *   - calculate the value of each digit relative to 'A'.
     *   - start from either the first or last character, and calculate the exponent based on the
     *     index of each character.
     *
     * @param coord The coordinate value in base 26 as described above. 
     * @return The numeric representation of the coordinate.
     */
    public static int coordAlphaToNum(String coord) {
        int i;
        int valueOfChar = 0;
        int power26 = 0;
        int numChar = 0;
        coord = coord.toUpperCase(); 
        for (i = 0; i <= (coord.length() - 1); ++i) {
    			power26 = (int) Math.pow(26, ((coord.length() - 1) - i)); 
    			numChar = coord.charAt(i) - 'A'; 
    			valueOfChar += power26 * numChar; //converts coordinate Letter to Number
        }
        return valueOfChar; 
    }

    /**
     * This method converts an int value into a base (or radix) 26 number, where the digits are 
     * represented by the 26 letters of the Latin alphabet. That is, A represents 0, B represents 1,
     * C represents 2, ..., Y represents 24,  and Z represents 25. 
     * A couple of examples: 17576 is BAAA, 11506714 is ZERTY.
     *
     * The algorithm to convert an int to a String representing these base 26 numbers is as follows:
     * - Initialize res to the input integer
     * - The next digit is determined by calculating the remainder of res with respect to 26
     * - Convert this next digit to a letter based on 'A'
     * - Set res to the integer division of res and 26
     * - Repeat until res is 0
     *
     * @param coord The integer value to covert into an alpha coordinate.
     * @return The alpha coordinate in base 26 as described above. If coord is negative, an empty 
     *         string is returned.
     */
    public static String coordNumToAlpha(int coord) {
    		if (coord < 0) {
			return "";
		}
        int res = coord;
        String finalString = "";
    		while (res >= 1) {
    			int conversion = res % 26;
    			char converter = (char) (conversion + 'A'); //converts coordinate number to letter
    			res = res / 26;
    			finalString = converter + finalString;
    		}
    		if (coord == 0) {
    			finalString = "A";
    		}
        return finalString;
    }

    /**
     * Prompts the user for an integer value, displaying the following:
     *     "Enter the valName (min to max): "
     * Note: There should not be a new line terminating the prompt. valName should contain the 
     * contents of the String referenced by the parameter valName. min and max should be the values 
     * passed in the respective parameters.
     *
     * After prompting the user, the method will read an int from the console and consume an entire
     * line of input. If the value read is between min and max (inclusive), that value is returned.
     * Otherwise, "Invalid value." terminated by a new line is output and the user is prompted 
     * again. 
     *
     * @param sc The Scanner instance to read from System.in.
     * @param valName The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param min The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String valName, int min, int max) {
    		System.out.print("Enter the " + valName + " (" + min + " to "+ max + "): "); //prompts user for int value between max and min
		int boardDimension = sc.nextInt();
		while (boardDimension < min || boardDimension > max) { //if value is not between max and min prompts again
			System.out.println("Invalid value.");
			System.out.print("Enter the " + valName + " (" + min + " to "+ max + "): ");
			boardDimension = sc.nextInt();
		}
        return boardDimension;
    }

    /**
     * Prompts the user for an String value, displaying the following:
     *     "Enter the valName (min to max): "
     * Note: There should not be a new line terminating the prompt. valName should contain the 
     * contents of the String referenced by the parameter valName. min and max should be the values 
     * passed in the respective parameters.
     *
     * After prompting the user, the method will read an entire line of input, trimming any trailing 
     * or leading whitespace. If the value read is (lexicographically ignoring case) between min and
     * max (inclusive), that value is returned. Otherwise, "Invalid value." terminated by a new line 
     * is output and the user is prompted again. 
     *
     * @param sc The Scanner instance to read from System.in.
     * @param valName The name of the value for which the user is prompted.
     * @param min The minimum acceptable String value (inclusive).
     * @param min The maximum acceptable String value (inclusive).
     * @return Returns the value read from the user.
     */
    public static String promptStr(Scanner sc, String valName, String min, String max) {
    			System.out.print("Enter the " + valName + " (" + min + " to "+ max + "): "); //prompts user for string value between max and min
    			String userInput = sc.next();
    			userInput = userInput.trim(); 
    			userInput = userInput.toUpperCase();
    			if (userInput.compareTo(max) > 0) { //if value is not between max and min prompts again
    				System.out.println("Invalid value.");
    				System.out.print("Enter the " + valName + " (" + min + " to "+ max + "): ");
    				userInput = sc.next();
    				userInput = userInput.trim();
    			}
    			if (userInput.compareTo(min) < 0) { //if value is not between max and min prompts again
    				System.out.println("Invalid value.");
    				System.out.print("Enter the " + valName + " (" + min + " to "+ max + "): ");
    				userInput = sc.next();
    				userInput = userInput.trim();
    			}
    			return userInput;
    }

    /**
     * Prompts the user for an char value. The prompt displayed is the contents of the String 
     * referenced by the prompt parameter. 
     * Note: There should not be a new line terminating the prompt. 
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character in lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        System.out.print(prompt); //prompts user for char value 
    		String ignoreWhitespace = sc.next();
    		char userAnswer = '\u0000';
    		char userNull = '\u0000';
    		ignoreWhitespace = ignoreWhitespace.toLowerCase();
    			if (ignoreWhitespace.length() > 0) { //ignores the whitespace for the input and gets first character
    				ignoreWhitespace = ignoreWhitespace.trim();
    				userAnswer = ignoreWhitespace.charAt(0);
    			}
    			else {
    				userAnswer = userNull;
    				}
    		return userAnswer;
		}
    			
    /**
     * Initializes a game board so that all the entries are Config.WATER_CHAR.
     *
     * @param board The game board to initialize.
     */
    public static void initBoard(char board[][]) {
    		for (int i = 0; i < board.length; i++) { //initializes game board for Battleship
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = Config.WATER_CHAR;
			}
		}
    }

    /**
     * Prints the game boards as viewed by the user. This method is used to print the 
     * game boards as the user is placing their ships and during the game play. 
     *
     * Some notes on the display:
     *   - Each column printed will have a width of Config.MAX_COL_WIDTH.
     *   - Each row is followed by an empty line.
     *   - The values in the headers and cells are to be right justified.
     *
     * @param board The board to print.
     * @param caption The board caption.
     */
    public static void printBoard(char board[][], String caption) {
    		char columnName = 'A';
		System.out.println(caption);
		System.out.print("    ");

		for (int i = 0; i < board[0].length; i++) { //prints the column headers 
			if (i == board[0].length - 1) { 
				System.out.print(" " + columnName);
			}
			else {
				System.out.print(" " + columnName + " ");
			}
			columnName++;
		}
		System.out.println();
		
		for(int i = 0; i < board.length ; i++) { //prints the row headers 
			System.out.print("  ");
			System.out.print(i);
			System.out.print(" ");
			for(int j = 0; j < board[0].length; j++) { //fills the game board with water
				if (j == board[0].length - 1) {
					System.out.print(" " + board[i][j]);
				}
				else {
				System.out.print(" " + board[i][j] + " ");
				}
			}
			System.out.println();			
			System.out.println();
		}
    }
    
    /**
     * Determines if a sequence of cells of length len in a game board is clear or not. This is used
     * to determine if a ship will fit on a given game board. The x and y coordinates passed in as 
     * parameters represent the top-left cell of the ship when considering the grid.
     * 
     * @param board The game board to search.
     * @param xcoord The x-coordinate of the top-left cell of the ship.
     * @param ycoord The y-coordinate of the top-left cell of the ship.
     * @param len The length of the ship.
     * @param dir true if the ship will be vertical, otherwise horizontal
     * @return 1 if the cells to be occupied by the ship are all Config.WATER_CHAR, 
     *         -1 if the cells to be occupied are not Config.WATER_CHAR, and
     *         -2 if the ship would go out-of-bounds of the board.
     */
    public static int checkWater(char board[][], int xcoord, int ycoord, int len, boolean dir) {
    		int shipFits = 1;
		if (dir) { //checks if board fits in the vertical direction
			if (ycoord + len > board.length) { 
				shipFits = -2;
			} 
			else { //makes sure the ship fits on the board
				for (int i = ycoord; i < ycoord + len; i++) {
					if (board[i][xcoord] != Config.WATER_CHAR) {
						shipFits = -1;
					}
				}
			}
		} 
		else { //checks if board fits in the horizontal direction
			if (xcoord + len > board[0].length) {
				shipFits = -2;
			} 
			else { //makes sure the ship fits on the board
				for (int i = xcoord; i < xcoord + len; i++) {
					if (board[ycoord][i] != Config.WATER_CHAR) {
						shipFits = -1;
					}
				}
			}

		}
		return shipFits;
    }

    /**
     * Checks the cells of the game board to determine if all the ships have been sunk.
     * 
     * @param board The game board to check.
     * @return true if all the ships have been sunk, false otherwise.
     */
    public static boolean checkLost(char board[][]) { //check if the user wins or the computer wins
        boolean check = true; 
        for (int i = 0; i < board.length ;i++) {
        		for (int j = 0 ; j < board[0].length ; j++) {
        			if (board[i][j] == Config.HIT_CHAR) { //checks for hit characters
        				continue;
        			}
        			if (board[i][j] == Config.WATER_CHAR) { //checks for water characters
        				continue;
        			}
        			if (board[i][j] == Config.MISS_CHAR) { //checks for miss characters
        				continue;
        			}
        			else {
        				check = false; 
        				break;
        			}
        		}
        }
        return check;
    } 

    /**
     * Places a ship into a game board. The coordinate passed in the parameters xcoord and ycoord
     * represent the top-left coordinate of the ship. The ship is represented on the game board by
     * the Character representation of the ship id. (For this method, you can assume that the id
     * parameter will only be values 1 through 9.)
     *
     * @param board The game board to search.
     * @param xcoord The x-coordinate of the top-left cell of the ship.
     * @param ycoord The y-coordinate of the top-left cell of the ship.
     * @param len The length of the ship.
     * @param dir true if the ship will be vertical, otherwise horizontal.
     * @param id The ship id, assumed to be 1 to 9.
     * @return false if the ship goes out-of-bounds of the board, true otherwise.
     */
    public static boolean placeShip(char board[][], int xcoord, int ycoord, int len, boolean dir,
                                    int id) {
    		boolean placedShip = false;
		if (dir) { //places the vertical direction ship
			if (len > board.length) {
				placedShip = false;
			}
			if (xcoord < board[0].length && ycoord <= board.length - len) { //makes sure the ship is not out of bounds
				for (int i = 0; i < len; i++) {
					board[ycoord + i][xcoord] = (char) (id + 48);
				}
				placedShip = true;
			}
		}
		else { //places the horizontal direction ship
			if (len > board[0].length) { 
				placedShip = false;
			}
			if (xcoord <= board[0].length - len && ycoord < board.length) { //makes sure the ship is not out of bounds
				for (int i = 0; i < len; i++) {
					board[ycoord][xcoord + i] = (char) (id + 48);
				}
				placedShip = true;
			}
		}
		return placedShip;
    }

    /**
     * Randomly attempts to place a ship into a game board. The random process is as follows:
     *   1 - Pick a random boolean, using rand. True represents vertical, false horizontal.
     *   2 - Pick a random integer, using rand, for the x-coordinate of the top-left cell of the 
     *       ship. The number of integers to choose from should be calculated based on the width of
     *       the board and length of the ship such that the placement of the ship won't be 
     *       out-of-bounds.
     *   3 - Pick a random integer, using rand, for the y-coordinate of the top-left cell of the 
     *       ship. The number of integers to choose from should be calculated based on the height of
     *       the board and length of the ship such that the placement of the ship won't be 
     *       out-of-bounds.
     *   4 - Verify that this random location can fit the ship without intersecting another ship 
     *       (checkWater method). If so, place the ship with the placeShip method.
     *
     * It is possible for the configuration of a board to be such that a ship of a given length may
     * not fit. So, the random process will be attempted at most Config.RAND_SHIP_TRIES times.
     * 
     * @param board The game board to search.
     * @param len The length of the ship.
     * @param id The ship id, assumed to be 1 to 9..
     * @param rand The Random object.
     * @return true if the ship is placed successfully, false otherwise.
     */
    public static boolean placeRandomShip(char board[][], int len, int id, Random rand) {
    		int xCoordinate = 0;
		int yCoordinate = 0;
		boolean placedCorrect = false;
		int minFail = 0;

		while (minFail < Config.RAND_SHIP_TRIES) { 
			boolean dir = rand.nextBoolean();
			
			if (!dir) { //places a random ship in the vertical direction
				if (board[0].length > len) {
					xCoordinate = rand.nextInt((board[0].length - len) + 1);
				} 
				else {
					xCoordinate = rand.nextInt(1);
				}
			} 
			else { 
				xCoordinate = rand.nextInt(board[0].length);
			}
			
			if (dir) { //places a random ship in the horizontal direction
				if (board.length > len) {
					yCoordinate = rand.nextInt((board.length - len) + 1);
				} 
				else {
					yCoordinate = rand.nextInt(1);
				}
			} 
			else {
				yCoordinate = rand.nextInt(board.length);
			}
			
			if (checkWater(board, xCoordinate, yCoordinate, len, dir) == 1) { //makes sure the ship can be placed on the board
				placedCorrect = placeShip(board, xCoordinate, yCoordinate, len, dir, id);
				return placedCorrect;
			} 
			else {
				minFail++;
			}
		}
		return placedCorrect;
    }
        			

    /**
     * This method interacts with the user to place a ship on the game board of the human player and
     * the computer opponent. The process is as follows:
     *   1 - Print the user primary board, using the printBoard.
     *   2 - Using the promptChar method, prompt the user with "Vertical or horizontal? (v/h) ".
     *       A response of v is interpreted as vertical. Anything else is assumed to be horizontal.
     *   3 - Using the promptInt method, prompt the user for an integer representing the 
     *       "ship length", where the minimum ship length is Config.MIN_SHIP_LEN and the maximum 
     *       ship length is width or height of the game board, depending on the input of the user 
     *       from step 1.
     *   4 - Using the promptStr method, prompt the user for the "x-coord". The maximum value
     *       should be calculated based on the width of the board and the length of the ship. You 
     *       will need to use the coordAlphaToNum and coordNumToAlpha methods to covert between int
     *       and String values of coordinates.
     *   5 - Using the promptInt method, prompt the user for the "y-coord". The maximum value
     *       should be calculated based on the width of the board and the length of the ship.
     *   6 - Check if there is space on the board to place the ship. 
     *     6a - If so:
     *             - Place the ship on the board using placeShip. 
     *             - Then, call placeRandomShip to place the opponents ships of the same length.
     *             - If placeRandomShip fails, print out the error message (terminated by a new 
     *               line): "Unable to place opponent ship: id", where id is the ship id, and 
     *               return false.
     *     6b - If not:
     *             - Using promptChar, prompt the user with "No room for ship. Try again? (y/n): "
     *             - If the user enters a 'y', restart the process at Step 1. 
     *             - Otherwise, return false.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param boardPrime The human player board.
     * @param boardOpp The opponent board. 
     * @param id The ship id, assumed to be 1 to 9.
     * @param rand The Random object.
     * @return true if ship placed successfully by player and computer opponent, false otherwise.
     */
    public static boolean addShip(Scanner sc, char boardPrime[][], char boardOpp[][], int id,
                                  Random rand) {
    		int shipLength = 0;
    		int xCoordinate = 0;
    		int yCoordinate = 0;
    		char tryAgain;
    
    		boolean noError = true;
    			
    		boolean correctResponse = true;
    		boolean enoughSpace;
    		boolean randomShipError;
    		int checkWaterAnswer = 0; 
    		printBoard(boardPrime, "My Ships:");
    		char vertOrHoriz = promptChar(sc, "Vertical or horizontal? (v/h): ");
    			if (vertOrHoriz == 'v') { //add ship preparation in vertical direction
    				enoughSpace = true;
    				shipLength = promptInt(sc, "ship length", Config.MIN_SHIP_LEN, boardPrime.length);
    				xCoordinate = coordAlphaToNum(promptStr(sc, "x-coord", "A", coordNumToAlpha(boardPrime[0].length - 1)));
    				yCoordinate = promptInt(sc, "y-coord", 0, boardPrime.length - shipLength);
    				checkWaterAnswer = checkWater(boardPrime, xCoordinate, yCoordinate, shipLength, enoughSpace);
    				if (checkWaterAnswer == 1) { //makes sure the ship can be put on the board
    					placeShip(boardPrime, xCoordinate, yCoordinate, shipLength, enoughSpace, id);
    					randomShipError = placeRandomShip(boardOpp, shipLength, id, rand);
    					if (randomShipError == false) {
    						System.out.println("Unable to print ship: " + id);
    						noError = false;
    					}
    					else {
    						noError = true;
    					}
    				}
    				else { //prompts user to try again if ship doesn't fit
    					tryAgain = promptChar(sc, "No room for ship. Try again? (y/n): ");
    					if (tryAgain == 'y') {
    						noError = false;
    					}
    					else {
    						noError = false;
    					}
    				}
    			}
    			else { //add ship preparation in the horizontal direction
    				enoughSpace = false;
    				shipLength = promptInt(sc, "ship length", Config.MIN_SHIP_LEN, boardPrime[0].length);
    				xCoordinate = coordAlphaToNum(promptStr(sc, "x-coord", "A", coordNumToAlpha(boardPrime[0].length - shipLength)));
    				yCoordinate = promptInt(sc, "y-coord", 0, boardPrime.length - 1);
    				checkWaterAnswer = checkWater(boardPrime, xCoordinate, yCoordinate, shipLength, enoughSpace);
   				if (checkWaterAnswer == 1) { //makes sure the ship can be put on the board
    					placeShip(boardPrime, xCoordinate, yCoordinate, shipLength, enoughSpace, id);
    					randomShipError = placeRandomShip(boardOpp, shipLength, id, rand);
    					if (randomShipError == false) {
    						System.out.println("Unable to print ship: " + id);
  						noError = false;
    					}
    					else {
    						noError = true;
    					}
    				}
    				else { //prompts user to try again if ship doesn't fit
    					tryAgain = promptChar(sc, "No room for ship. Try again? (y/n): ");
    					if (tryAgain == 'y') { 
    						noError = false;
    					}
    					else {
    						noError = false;
    					}
    				}
    			}	
    		return noError;
    }

    /**
     * Checks the state of a targeted cell on the game board. This method does not change the 
     * contents of the game board.
     *
     * @return  3 if the cell was previously targeted.
     *          2 if the shot would be a miss.
     *          1 if the shot would be a hit.
     *         -1 if the shot is out-of-bounds.
     */
    public static int takeShot(char[][] board, int x, int y) { //coordinate directions for ship shooting
        int shotTaken = 0;
    		if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) { //shot is out of bound
        		shotTaken = -1;
        	}
    		else if (board[y][x] == Config.HIT_CHAR || board[y][x] ==Config.MISS_CHAR) { //shot occupied
        		shotTaken = 3;
        	}
    		else if (board[y][x] == Config.WATER_CHAR) { //shot missed
        		shotTaken = 2;
        } 
        else { //shot hit 
        		shotTaken = 1;
        	}
        return shotTaken;
    }
       

    /**
     * Interacts with the user to take a shot. The procedure is as follows:
     *   1 - Using the promptStr method, prompt the user for the "x-coord shot". The maximum value
     *       should be based on the width of the board. You will need to use the coordAlphaToNum 
     *       and coordNumToAlpha methods to covert between int and String values of coordinates.
     *   2 - Using the promptInt method, prompt the user for the "y-coord shot". The maximum value
     *       should be calculated based on the width of the board.
     *   3 - Check the shot, using the takeShot method. If it returns:
     *        -1: Print out an error message "Coordinates out-of-bounds!", terminated by a new line.
     *         3: Print out an error message "Shot location previously targeted!", terminated by a
     *            new line.
     *         1 or 2: Update the cells in board and boardTrack with Config.HIT_CHAR or 
     *                 Config.MISS_CHAR accordingly.
     *   This process should repeat until the takeShot method returns 1 or 2.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param board The computer opponent board (containing the ship placements).
     * @param boardTrack The human player tracking board.
     */
    public static void shootPlayer(Scanner sc, char[][] board, char[][] boardTrack) {
        int x = 0;
        int y = 0;
        int takeShotResult = 0;
    		while (takeShotResult != 1 || takeShotResult != 2) { //shoots the computer board
    			x = coordAlphaToNum(promptStr(sc, "x-coord shot", "A", coordNumToAlpha(board[0].length - 1)));
    			y = promptInt(sc, "y-coord shot", 0, board.length - 1);
    			takeShotResult = takeShot(board, x, y);
    			if (takeShotResult == -1) { //out of bounds
    				System.out.println("Coordinates out-of-bounds!");
    				return;
    			}
    			else if (takeShotResult == 3) { //location occupied
    				System.out.println("Shot location previously targeted!");
    				return;
    			}
    			else if (takeShotResult == 2) { //miss
    				boardTrack[y][x] = Config.MISS_CHAR; 
    				board[y][x] = Config.MISS_CHAR;
    				return;
    			}
    			else { //hit
    				boardTrack[y][x] = Config.HIT_CHAR;
    				board[y][x] = Config.HIT_CHAR;
    				return;
    			}
    		} 
    }

    /**
     * Takes a random shot on the game board. The random process works as follows:
     *   1 - Pick a random valid x-coordinate
     *   2 - Pick a random valid y-coordinate
     *   3 - Check the shot, using the takeShot method. 
     *   This process should repeat until the takeShot method returns 1 or 2, then update the cells
     *   in board with Config.HIT_CHAR or Config.MISS_CHAR accordingly.
     *
     * Note: Unlike the placeRandomShip method, this method continues until it is successful. This
     * may seem risky, but in this case the random process will terminate (find an untargeted cell)
     * fairly quickly. For more details, see the appendix of the Big Program 1 subject.
     *
     * @param rand The Random object.
     * @param board The human player game board.
     */
    public static void shootComputer(Random rand, char[][] board) { //shoots the player board
    		int takeShotResult = 0;
    		
    		while (takeShotResult != 1 || takeShotResult != 2) { //continues to iterate until hits or misses
    			int x = rand.nextInt(board[0].length);
    			int y = rand.nextInt(board.length);
    			takeShotResult = takeShot(board, x, y);
    			
    			if (takeShotResult == 1) { //hit 
    				board[y][x] = Config.HIT_CHAR; 
    				return;
    			}
    			else if (takeShotResult == 2) { //miss 
    				board[y][x] = Config.MISS_CHAR; 
    				return;
    			}
    			else {
    			}
    		}
    		return;
    }

    /**
     * This is the main method for the Battleship game. It consists of the main game and play again
     * loops with calls to the various supporting methods. When the program launches (prior to the 
     * play again loop), a message of "Welcome to Battleship!", terminated by a newline, is 
     * displayed. After the play again loop terminiates, a message of "Thanks for playing!", 
     * terminated by a newline, is displayed.
     *
     * The Scanner object to read from System.in and the Random object with a seed of Config.SEED 
     * will be created in the main method and used as arguments for the supporting methods as 
     * required.
     *
     * Also, the main method will require 3 game boards to track the play:
     * - One for tracking the ship placement of the user and the shots of the computer, called the
     *   primary board with a caption of "My Ship". 
     * - One for displaying the shots (hits and misses) taken by the user, called the tracking board
     *   with a caption of "My Shots"; and one for tracking the ship placement of the computer and 
     *   the shots of the user. 
     * - The last board is never displayed, but is the primary board for the computer and is used to
     *   determine when a hit or a miss occurs and when all the ships of the computer have been 
     *   sunk. 
     * Notes:
     *   - The size of the game boards are determined by the user input.  
     *   - The game boards are 2d arrays that are to be viewed as row-major order. This means that 
     *     the first dimension represents the y-coordinate of the game board (the rows) and the 
     *     second dimension represents the x-coordinate (the columns).
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
    		Scanner scn = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        int i = 0;
        System.out.println("Welcome to Battleship!");
        
        boolean playerContinue = true;
        while (playerContinue == true) { //will continue playing until user prompts no
        	
        		boolean userWish = true;
        		while (userWish) { //will continue until user prompts no
        			int boardHeight = promptInt(scn, "board height", Config.MIN_HEIGHT, Config.MAX_HEIGHT ); //initializes both game boards
        			int boardWidth = promptInt(scn, "board width", Config.MIN_WIDTH, Config.MAX_WIDTH);
        			
        			System.out.println("");
        		
        			//creates and initializes game board
        			char boardUser[][] = new char [boardHeight][boardWidth];
        			initBoard(boardUser);
        			char boardComputer[][] = new char [boardHeight][boardWidth];
        			initBoard(boardComputer);
        			char boardShots[][] = new char [boardHeight][boardWidth];
        			initBoard(boardShots);
        		
        		
        			int numShips = promptInt(scn, "number of ships", Config.MIN_SHIPS, Config.MAX_SHIPS);
        			char userRestart;
        			boolean addShipFail = false;
        			for (int shipNum = 1; shipNum <= numShips; ++shipNum) { //continues board placement until all ships are placed
        				addShipFail = addShip(scn, boardUser, boardComputer, shipNum, rand); //begins the addShip method and process of adding ships
        				if (addShipFail == false) { //if one ship doesn't get placed prompts user to start again
        					userRestart = promptChar(scn, "Error adding ships. Restart game? (y/n): ");
        					if (userRestart == 'y') {
        						userWish = true;
        						break;
        					}
        					else {
        						userWish = false;
        						break;
        					}
        				}
        				else {
        					userWish = false;
        				}
        			}
        			boolean playingGame = true;
        			while (playingGame) { //begins the process of shooting the ships and playing the game
        				printBoard(boardUser, "My Ships:");
        				printBoard(boardShots, "My Shots:");
        				shootPlayer(scn, boardComputer, boardShots); //begins the process of shooting the computer ships
        				
        				boolean win = checkLost(boardComputer); //used to check if player has one after each shot
        				if (win) {
        						System.out.println("Congratulations, you sunk all the computer's ships!");
        						printBoard(boardUser, "My Ships:");
        						printBoard(boardShots, "My Shots:");
        						break;
        				}
        				else {
        					shootComputer(rand, boardUser); //used to check if the computer has won
        					boolean lose = checkLost(boardUser);
        					if (lose == true) { //if the player loses  
        						System.out.println("Oh no! The computer sunk all your ships!");
        						printBoard(boardUser, "My Ships:");
        						printBoard(boardShots, "My Shots:");
        						break;
        					}
        					else {
        						continue;
        					} 
        				}
        			}	
        		}
        		char userAnswer = promptChar(scn, "Would you like to play again? (y/n): "); //asks the player if they want to play again
        		if (userAnswer == 'y') {
        			playerContinue = true;
        		}
        		else {
        			playerContinue = false; 
        		}
        }
        System.out.println("Thanks for playing!");
    }
}