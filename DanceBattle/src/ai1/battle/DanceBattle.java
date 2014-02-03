package ai1.battle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** This predicts the winner of a dance battle game where moves cannot be repeated. */
public class DanceBattle {

	/** Initial game state. */
	private GameState initState;
	
	/** Debug flag. */
	private boolean debug;
	
	/** Constructor. */
	public DanceBattle(String inputFileName, boolean debug) {
		this.debug = debug;
		initialize(inputFileName);
	}

	/** Read input file and initialize game state. */
	private void initialize(String inputFileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
			
			// Read number of moves.
			int numMoves = Integer.parseInt(reader.readLine());
			// Read number of previous turns.
			int numTurns = Integer.parseInt(reader.readLine());
			// Make space to save moves made on previous turns.
			int[][] prevTurns = new int[numTurns][2];
			int ix = 0;
			String row;
			// Loop through & read the moves made on previous turns, store in array. 
			while ((row = reader.readLine()) != null && ix < numTurns) {
				// Split line by any whitespace.
				String[] split = row.split("\\s+");
				prevTurns[ix][0] = Integer.parseInt(split[0]);
				prevTurns[ix][1] = Integer.parseInt(split[1]);
				ix++;
			}
			// Make sure number of input moves matches configured number.
			if (ix != numTurns) {
				System.out.println("Mismatch between number of turns and length of list.");
				return;
			}
			
			// Output startup configuration.
			if (debug) {
				System.out.println("# Moves: " + numMoves);
				System.out.println("# Turns: " + numTurns);
				for (ix = 0; ix < numTurns; ix++) {
					System.out.println("" + prevTurns[ix][0] + ' ' + prevTurns[ix][1]);
				}
				System.out.println();
			}
			
			// Create initial game state move. Will be max or min move based on # prevTurns.
			initState = new GameState(numMoves, numTurns, prevTurns, (numTurns % 2) == 0);
			// Build the whole state tree down.
			initState.buildDown();
			// Set debug flag.
			GameState.setDebug(debug);
			
		} catch (FileNotFoundException e) {
			System.out.println(inputFileName);
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.out.println(inputFileName);
			e.printStackTrace();
			return;
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (IOException e) {}
			}
		}		
	}
	
	/** Play through the dance game and determine the winner assuming optimal moves. */
	public void play() {
		// Call recursive function to evaluate winner.
		boolean maxWins = initState.evaluateMinMax();
		System.out.println("\nMAX:");
		System.out.println((maxWins) ? "Win" : "Lose");
	}

	/** Main Program. Process command line parms & create DanceBattle with input file. */
	public static void main(String[] args) {
		boolean debug = false;
		String inputFileName = null;
		
		switch(args.length) {
		case 2:
			debug = true;
		case 1:
			inputFileName = args[0];
			break;
		default:
			System.out.println("Command line error. Usage:\njava ai1.battle.DanceBattle <input filename> [-debug]");
			System.exit(1);
		}
		
		// Create & play dance battle.
		DanceBattle danceBattle = new DanceBattle(inputFileName, debug);
		danceBattle.play();
	}

}
