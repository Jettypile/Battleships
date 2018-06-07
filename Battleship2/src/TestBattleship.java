
/**

 * This file contains testing methods for the Battleship project. These methods are intended to 

 * provide an example of a way to incrementally test your code, and to provide example method calls

 * for the Battleship methods

 *

 * Toward these objectives, the expectation is that part of the grade for the Battleship project is 

 * to write some tests and write header comments summarizing the tests that have been written. 

 * Specific places are noted with FIXME but add any other comments you feel would be useful.

 */

import java.util.Random;

import java.util.Scanner;

/**
 * 
 * This class contains a few methods for testing methods in the Battleship
 * 
 * class as they are developed. These methods are all private as they are only
 * 
 * intended for use within this class.
 * 
 * 
 * 
 * @author Marc Renault
 * 
 * @author FIXME add your name here when you add test
 *
 * 
 * 
 */

public class TestBattleship {

	/**
	 * 
	 * This is the main method that runs the various tests. Uncomment the tests
	 * when
	 * 
	 * you are ready for them to run.
	 * 
	 * 
	 * 
	 * @param args
	 *            (unused)
	 * 
	 */

	public static void main(String[] args) {

		// Milestone 1

		testCoordAlphaToNum();

		testCoordNumToAlpha();

		// Milestone 2

		testCheckWater();

		testPlaceShip();

		// Milestone 3

		testTakeShot();

		testCheckLost();

	}

	private static void testCoordAlphaToNum() {

		int numTests = 6;

		int passed = numTests;

		int res;

		if ((res = Battleship.coordAlphaToNum("BAAA")) != 17576) {

			System.out.println("FAILED: Battleship.coordAlphaToNum(\"BAAA\") != 17576, but " + res);

			passed--;

		}

		if ((res = Battleship.coordAlphaToNum("ZERTY")) != 11506714) {

			System.out.println("FAILED: Battleship.coordAlphaToNum(\"ZERTY\") != 11506714, but " + res);

			passed--;

		}

		if ((res = Battleship.coordAlphaToNum("zerty")) != 11506714) {

			System.out.println("FAILED: Battleship.coordAlphaToNum(\"zerty\") != 11506714, but " + res);

			passed--;

		}

		if ((res = Battleship.coordAlphaToNum("&é\"")) != -14747) {

			System.out.println("FAILED: Battleship.coordAlphaToNum(\"&é\\\"\") != -14747, but " + res);

			passed--;

		}

		if ((res = Battleship.coordAlphaToNum("AaRON")) != 11869) {

			System.out.println("FAILED: Battleship.coordAlphaToNum(\"AaRON\") != 11869, but " + res);

			passed--;

		}

		if ((res = Battleship.coordAlphaToNum("OmArJaLiL")) != 206103805) {

			System.out.println("FAILED: Battleship.coordAlphaToNum(\"OmArJaLiL\") != 206103805, but " + res);

			passed--;

		}

		System.out.println("testCoordAlphatoNum: Passed " + passed + " of " + numTests + " tests.");

	}

	private static void testCoordNumToAlpha() {

		int numTests = 4;

		int passed = numTests;

		String res;

		if ((res = Battleship.coordNumToAlpha(17576)) != "BAAA") {

			System.out.println("FAILED: Battleship.coordAlphaToNum(17576) != \"BAAA\", but " + res);

			passed--;

		}

		if ((res = Battleship.coordNumToAlpha(11506714)) != "ZERTY") {

			System.out.println("FAILED: Battleship.coordAlphaToNum(11506714) != \"ZERTY\", but " + res);

			passed--;

		}

		if ((res = Battleship.coordNumToAlpha(11869)) != "AaRON") {

			System.out.println("FAILED: Battleship.coordAlphaToNum(11869) != \"AaRON\", but " + res);

			passed--;

		}

		if ((res = Battleship.coordNumToAlpha(11506714)) != "zerty") {

			System.out.println("FAILED: Battleship.coordAlphaToNum(11506714) != \"zerty\", but " + res);

			passed--;

		}

		System.out.println("testCoordNumtoAlpha: Passed " + passed + " of " + numTests + " tests.");

	}

	private static void testCheckWater() {
		int numTests = 3;
		int passed = numTests;
		int res;

		char[][] board = new char[5][5];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Config.WATER_CHAR;
			}
		}

		board[2][0] = 'a';

		if ((res = (Battleship.checkWater(board, 1, 2, 3, true))) != 1) {
			System.out.println("FAILED: Battleship.checkWater(board, 1, 1, 3, true) != \"1\", but " + res);
			passed--;

		}
		if ((res = (Battleship.checkWater(board, 2, 3, 7, false))) != -2) {
			System.out.println("FAILED: Battleship.checkWater(board, 2, 3, 4, false) != \"-2\", but " + res);
			passed--;

		}
		if ((res = (Battleship.checkWater(board, 1, 1, 3, true))) != -1) {
			System.out.println("FAILED: Battleship.checkWater(board, 4, 5, 2, true) != \"-1\", but " + res);
			passed--;

		}

		System.out.println("testcheckWater: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testPlaceShip() {
		int numTests = 3;
		int passed = numTests;
		boolean res = true;

		char[][] board = new char[3][3];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Config.WATER_CHAR;
			}
		}

		if ((res == (Battleship.placeShip(board, 0, 0, 3, true, 1))) != true) {
			System.out.println("FAILED: Battleship.placeShip(board, 0, 0, 3, true, 1) != \"true\", but " + res);
			passed--;
		}
		if ((res == (Battleship.placeShip(board, 0, 2, 1, true, 1))) != true) {
			System.out.println("FAILED: Battleship.placeShip(board, 2, 0, 1, false, 1) != \"true\", but " + res);
			passed--;
		}
		if ((res == (Battleship.placeShip(board, 0, 0, 4, true, 1))) != false) {
			System.out.println("FAILED: Battleship.placeShip(board, 0, 0, 4, true, 1) != \"false\", but " + res);
			passed--;
		}

		System.out.println("testPlaceShip: Passed " + passed + " of " + numTests + " tests.");

	}

	private static void testTakeShot() {

		int numTests = 2;
        int passed = numTests;
        int res = 0;

        char[][] board = new char[3][3];
        board[1][2] = Config.WATER_CHAR;
        board[2][3] = Config.HIT_CHAR;
        if ((res = Battleship.takeShot(board,1,2)) != 2) {
            System.out.println(
                "FAILED: takeShot(board,1,2) != 3, but " + res);
            passed--;
        }
        if ((res = Battleship.takeShot(board,2,3)) != 3) {
            System.out.println(
                "FAILED: takeShot(board,2,3) != 3, but " + res);
            passed--;
        }
        System.out.println("testTakeShot: Passed " + passed + " of " + numTests + " tests.");

	}

	private static void testCheckLost() {

		int numTests = 2;
		int passed = numTests;
		boolean res = true;

		char[][] board = new char[3][3];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Config.WATER_CHAR;
			}
		}
		board[2][2] = Config.HIT_CHAR;
		board [1][2] = Config.MISS_CHAR;

		if (((res == Battleship.checkLost(board)) != true)) {
			System.out.println("FAILED: checkLost(board) != \"true\", but " + res);
			passed--;  
		}
		board[2][2] = 2;
		board [1][2] = 1;
		if ((res == Battleship.checkLost(board) == true)) {
			System.out.println("FAILED: checkLost(board) = \"true\", but " + res);
			passed--;
		}
		

		System.out.println("testCheckLost: Passed " + passed + " of " + numTests + " tests.");

	}

}
