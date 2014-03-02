

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ai1.shakey.AtProposition;
import ai1.shakey.BoxProposition;
import ai1.shakey.GoAction;
import ai1.shakey.InProposition;
import ai1.shakey.LocationProposition;
import ai1.shakey.PushAction;
import ai1.shakey.RoomProposition;
import ai1.shakey.ShakeysWorldPddl;
import ai1.strips.Action;
import ai1.strips.Pddl;
import ai1.strips.Proposition;
import ai1.strips.Strips;
import ai1.tower.ClearProposition;
import ai1.tower.MoveAction;
import ai1.tower.OnProposition;
import ai1.tower.SmallerProposition;
import ai1.tower.TowerOfHanoiPddl;

public class Controller {

	public static void main(String[] args) {
		printMenu();
		Pddl problemPddl = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			switch (input.charAt(0)) {
			case 'a':
			case 'A':
				problemPddl = buildTowerOfHanoiPddl();
				break;
			case 'b':
			case 'B':
				problemPddl = buildShakeysWorldPddl();
				break;
			default:
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Strips strips = new Strips(problemPddl);
		List<String> solutionSteps = strips.solve();
		for (String solutionStep : solutionSteps) {
			System.out.println(solutionStep);
		}
	}

	private static void printMenu() {
		System.out.println("A) Tower of Hanoi");
		System.out.println("B) Shakey's World ");
		System.out.println("C) Key-In-Box");
		System.out.println("D) Air cargo transport");
		System.out.println("E) Exit");
	}
	
	private static Pddl buildTowerOfHanoiPddl() {
		System.out.println("Enter height (1-9) of the tower:");
		int height;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			height = Integer.parseInt("" + input);
			if (height < 1 || height > 9) {
				System.out.println("Input error, defaulting to level 4 tower.");
				height = 4;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Input error, defaulting to level 4 tower.");
			height = 4;
		}

		// Initial state where tower starts.
		List<Proposition> initialState = buildTowerOn(height, "A");
		// Add smaller predicates.
		List<Proposition> smallerPropositions = buildSmallerPropositions(height);
		initialState.addAll(smallerPropositions);
		// Add clear predicates.
		initialState.addAll(buildClearPropositions("A"));
		
		// Goal state for use in heuristic.
		List<Proposition> goalStateHeuristic = buildTowerOn(height, "B");
		
		// Goal state 
		List<Proposition> goalState = new ArrayList<Proposition>();
		// Add tower destination.
		goalState.addAll(goalStateHeuristic);
		// Add smaller predicates.
		goalState.addAll(smallerPropositions);
		// Add clear predicates.
		goalState.addAll(buildClearPropositions("B"));

		// Actions.
		List<Action> actions = new ArrayList<Action>();
		actions.add(MoveAction.initMoveAction());
		
		return new TowerOfHanoiPddl(initialState, goalState, actions, goalStateHeuristic);
	}
	
	private static List<Proposition> buildTowerOn(int height, String peg) {
		List<Proposition> towerPropositions = new ArrayList<Proposition>();
		towerPropositions.add(OnProposition.initOnProposition("" + height, peg, height));
		for (int ix = 1; ix < height; ix++) {
			towerPropositions.add(OnProposition.initOnProposition("" + ix, "" + (ix+1), ix));
		}
		
		return towerPropositions;
	}
	
	private static List<Proposition> buildSmallerPropositions(int height) {
		List<Proposition> smallerPropositions = new ArrayList<Proposition>();
		for (int ix = 1; ix <= height; ix++) {
			// Each disk smaller than all pegs.
			smallerPropositions.add(SmallerProposition.initSmallerProposition("" + ix, "A"));
			smallerPropositions.add(SmallerProposition.initSmallerProposition("" + ix, "B"));
			smallerPropositions.add(SmallerProposition.initSmallerProposition("" + ix, "C"));
			// Each disk smaller than all larger ones.
			for (int iy = ix + 1; iy <= height; iy++) {
				smallerPropositions.add(SmallerProposition.initSmallerProposition("" + ix, "" + iy));
			}
		}
		
		return smallerPropositions;
	}
	
	private static List<Proposition> buildClearPropositions(String peg) {
		List<Proposition> clearPropositions = new ArrayList<Proposition>();
		// Top of tower is clear.
		clearPropositions.add(ClearProposition.initClearProposition("1"));
		// Clear pegs without tower on them.
		switch (peg.charAt(0)) {
		case 'A':
			clearPropositions.add(ClearProposition.initClearProposition("B"));
			clearPropositions.add(ClearProposition.initClearProposition("C"));
			break;
		case 'B':
			clearPropositions.add(ClearProposition.initClearProposition("A"));
			clearPropositions.add(ClearProposition.initClearProposition("C"));
			break;
		case 'C':
			clearPropositions.add(ClearProposition.initClearProposition("A"));
			clearPropositions.add(ClearProposition.initClearProposition("B"));
			break;
		}
		
		return clearPropositions;
	}
	
	private static Pddl buildShakeysWorldPddl() {
		// Initial static state of Shakey's World.
		List<Proposition> initialState = new ArrayList<Proposition>();
		// Goal static state of Shakey's World.
		List<Proposition> goalState = new ArrayList<Proposition>();
		
		// Set up room & location states.
		for (int roomNum = 1; roomNum <= 4; roomNum++) {
			// Rooms.
			initialState.add(RoomProposition.initRoomProposition("Room" + roomNum));
			goalState.add(RoomProposition.initRoomProposition("Room" + roomNum));
			// Locations.
			initialState.add(LocationProposition.initLocationProposition("Room" + roomNum));
			goalState.add(LocationProposition.initLocationProposition("Room" + roomNum));
			initialState.add(LocationProposition.initLocationProposition("Door" + roomNum));
			goalState.add(LocationProposition.initLocationProposition("Door" + roomNum));
			initialState.add(LocationProposition.initLocationProposition("Switch" + roomNum));
			goalState.add(LocationProposition.initLocationProposition("Switch" + roomNum));
			// Room space also in room.
			initialState.add(InProposition.initInProposition("Room" + roomNum, "Room" + roomNum));
			goalState.add(InProposition.initInProposition("Room" + roomNum, "Room" + roomNum));
			// One switch in each room.
			initialState.add(InProposition.initInProposition("Switch" + roomNum, "Room" + roomNum));
			goalState.add(InProposition.initInProposition("Switch" + roomNum, "Room" + roomNum));
			// One door in each room.
			initialState.add(InProposition.initInProposition("Door" + roomNum, "Room" + roomNum));
			goalState.add(InProposition.initInProposition("Door" + roomNum, "Room" + roomNum));
			// Same door in corridor.
			initialState.add(InProposition.initInProposition("Door" + roomNum, "Corridor"));
			goalState.add(InProposition.initInProposition("Door" + roomNum, "Corridor"));
		}
		
		// Set up box states.
		for (int boxNum = 1; boxNum <= 4; boxNum++) {
			// Locations.
			initialState.add(BoxProposition.initBoxProposition("Box" + boxNum));
			goalState.add(BoxProposition.initBoxProposition("Box" + boxNum));
			// Four boxes in room #1, initial state. Box #2 in room #2 goal state.
			initialState.add(AtProposition.initAtProposition("Box" + boxNum, "Room1"));
			// Four boxes in room #1, box #2 in room #2 goal state.
			if (boxNum != 2) {
				goalState.add(AtProposition.initAtProposition("Box" + boxNum, "Room1"));
			}
			else {
				goalState.add(AtProposition.initAtProposition("Box" + boxNum, "Room" + boxNum));
			}
		}

		// Corridor is location.
		initialState.add(RoomProposition.initRoomProposition("Corridor"));
		goalState.add(RoomProposition.initRoomProposition("Corridor"));

		// Shakey's initial location.
		initialState.add(AtProposition.initAtProposition("Shakey", "Room3"));

		// Shakey's goal location.
		goalState.add(AtProposition.initAtProposition("Shakey", "Room2"));

		// Actions.
		List<Action> actions = new ArrayList<Action>();
		actions.add(GoAction.initGoAction());
		actions.add(PushAction.initPushAction());
		
		return new ShakeysWorldPddl(initialState, goalState, actions);
	}
	
}
