package ai1.battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** This maintains the game state and provides successor moves. */
public class GameState {

	/** Static flag for debugging output. */
	private static boolean debug = false;
	
	/** Number of moves in the game. */
	private int numMoves;
	
	/** Move taken at this state. */
	private int move[] = new int[2];
	
	/** Moves used. */
	private boolean movesUsed[][];
	
	/** Successor states in game. */
	private List<GameState> nextStates = null;
	
	/** Min or Max optimized state. max = true, min = false */
	private boolean minMax;

	/** Constructor. */
	public GameState(int numMoves, int numTurns, int[][] prevTurns, boolean minMax) {
		this.numMoves = numMoves;
		this.minMax = minMax;
		// Account for moves already made so far.
		movesUsed = new boolean[numMoves][numMoves];
		for (int ix = 0; ix < numTurns; ix++) {
			recordMove(prevTurns[ix][0], prevTurns[ix][1]);
		}
		if (numTurns > 0) {
			// Record last move as this state.
			makeMove(prevTurns[numTurns-1][0], prevTurns[numTurns-1][1]);
		}
		else {
			// Flag empty start state.
			move[1] = -1;
		}
	}
	
	/** Copy Constructor. */
	public GameState(GameState gameState) {
		numMoves = gameState.numMoves;
		minMax = gameState.minMax;
		move = new int[2];
		move[0] = gameState.move[0];
		move[1] = gameState.move[1];
		movesUsed = new boolean[numMoves][numMoves];
		for (int ix = 0; ix < numMoves; ix++) {
			for (int iy = 0; iy < numMoves; iy++) {
				movesUsed[ix][iy] = gameState.movesUsed[ix][iy];
			}			
		}
	}
	
	/** Record the input move in the matrix of used moves. */
	private void recordMove(int first, int second) {
		movesUsed[first][second] = true;
		movesUsed[second][first] = true;
	}
	
	/** Make the move and record it. */
	private void makeMove(int first, int second) {
		move[0] = first;
		move[1] = second;
		recordMove(first, second);
	}
	
	/** Build the tree of successor states recursively down from here. */
	public void buildDown() {
		nextStates = getNextStates();
		// Recursively build states.
		for (GameState nextState : nextStates) {
			nextState.buildDown();
		}
	}
	
	/** Return list of successor moves. */
	public List<GameState> getNextStates() {
		List<GameState> nextStates = new ArrayList<GameState>();
		// Set up indexes for start move loop. 
		// Handle case where battle just started & also where there was previous move.
		int start = (move[1] < 0) ? 0 : move[1];
		int end = (move[1] < 0) ? numMoves : move[1] + 1;
		for (int first = start; first < end; first++) {
			for (int second = 0; second < numMoves; second++) {
				if (!movesUsed[first][second]) {
					// Copy parent state.
					GameState newState = new GameState(this);
					// Make move that got here.
					newState.makeMove(first, second);
					// Next MinMax level.
					newState.flipMinMax();
					nextStates.add(newState);
				}
			}
		}
		
		return nextStates;
	}
	
	/** Recursively determine winning player, assuming optimal moves. */
	public boolean evaluateMinMax() {
		// Any moves remaining from here?
		if (nextStates.isEmpty()) {
			// Nope, loss for this side.
			if (debug) {
				System.out.println(((minMax) ? "MAX: " : "MIN: ") + " from [" + move[0] + ',' + move[1] + "] no moves left for: " + ((minMax) ? "MAX" : "MIN"));
			}
			return !minMax;
		}
		
		// Loop through next states & recursively evaluate them.
		// As soon as encounter winning result for this side, return it.
		for (GameState nextState : nextStates) {
			if (nextState.evaluateMinMax() == minMax) {
				if (debug) {
					System.out.println(((minMax) ? "MAX: " : "MIN: ") + " from [" + move[0] + ',' + move[1] + "] move to [" + nextState.move[0] + ',' + nextState.move[1] + "] win for: " + ((minMax) ? "MAX" : "MIN"));
				}
				return minMax;
			}
		}
		
		// No winning results, return loss for this side.
		if (debug) {
			System.out.println(((minMax) ? "MAX: " : "MIN: ") + " from [" + move[0] + ',' + move[1] + "] no winning results for: " + ((minMax) ? "MAX" : "MIN"));
		}
		return !minMax;
	}
	
	/** Mutator. */
	public void flipMinMax() {
		minMax = !minMax;
	}

	/** Accessor. */
	public boolean isMax() {
		return minMax;
	}
	
	/** Accessor. */
	public boolean isMin() {
		return !minMax;
	}

	/** Static Mutator. */ 
	public static void setDebug(boolean debug) {
		GameState.debug = debug;
	}

	/** To string. */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int ix = 0 ; ix < movesUsed.length ; ix++) {
			for (int iy = 0 ; iy < movesUsed[ix].length ; iy++) {
				builder.append(movesUsed[ix][iy]).append(',');
			}
			builder.append('\n');
		}
		return "GameState [minMax=" + minMax + ", move=" + Arrays.toString(move) + ", \nmovesUsed=\n"
				+ builder.toString() + "]";
	}
		
}
