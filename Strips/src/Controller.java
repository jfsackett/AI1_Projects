

import java.util.ArrayList;
import java.util.List;

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
		List<Proposition> initialState = new ArrayList<Proposition>();
		// Initial state.
		initialState.add(OnProposition.initOnProposition("ONE", "TWO"));
		initialState.add(OnProposition.initOnProposition("TWO", "THREE"));
		initialState.add(OnProposition.initOnProposition("THREE", "A"));
		// Constant initial state.
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "A"));
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "B"));
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "C"));
		initialState.add(SmallerProposition.initSmallerProposition("TWO", "A"));
		initialState.add(SmallerProposition.initSmallerProposition("TWO", "B"));
		initialState.add(SmallerProposition.initSmallerProposition("TWO", "C"));
		initialState.add(SmallerProposition.initSmallerProposition("THREE", "A"));
		initialState.add(SmallerProposition.initSmallerProposition("THREE", "B"));
		initialState.add(SmallerProposition.initSmallerProposition("THREE", "C"));
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "TWO"));
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "THREE"));
		initialState.add(SmallerProposition.initSmallerProposition("TWO", "THREE"));
		// Variable initial state.
		initialState.add(ClearProposition.initClearProposition("ONE"));
		initialState.add(ClearProposition.initClearProposition("B"));
		initialState.add(ClearProposition.initClearProposition("C"));

		// Goal propositions.
		OnProposition diskOneOnProposition = OnProposition.initOnProposition("ONE", "TWO", 1);
		OnProposition diskTwoOnProposition = OnProposition.initOnProposition("TWO", "THREE", 2);
		OnProposition diskThreeOnProposition = OnProposition.initOnProposition("THREE", "B", 3);
		// Goal propositions for heuristic.
		List<Proposition> goalStateHeuristic = new ArrayList<Proposition>();
		goalStateHeuristic.add(diskOneOnProposition);
		goalStateHeuristic.add(diskTwoOnProposition);
		goalStateHeuristic.add(diskThreeOnProposition);
		// Goal state.
		List<Proposition> goalState = new ArrayList<Proposition>();
		goalState.add(diskOneOnProposition);
		goalState.add(diskTwoOnProposition);
		goalState.add(diskThreeOnProposition);
		// Constant initial state.
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "A"));
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "B"));
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "C"));
		goalState.add(SmallerProposition.initSmallerProposition("TWO", "A"));
		goalState.add(SmallerProposition.initSmallerProposition("TWO", "B"));
		goalState.add(SmallerProposition.initSmallerProposition("TWO", "C"));
		goalState.add(SmallerProposition.initSmallerProposition("THREE", "A"));
		goalState.add(SmallerProposition.initSmallerProposition("THREE", "B"));
		goalState.add(SmallerProposition.initSmallerProposition("THREE", "C"));
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "TWO"));
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "THREE"));
		goalState.add(SmallerProposition.initSmallerProposition("TWO", "THREE"));
		// Variable initial state.
		goalState.add(ClearProposition.initClearProposition("ONE"));
		goalState.add(ClearProposition.initClearProposition("A"));
		goalState.add(ClearProposition.initClearProposition("C"));
		
		// Actions.
		List<Action> actions = new ArrayList<Action>();
		actions.add(MoveAction.initMoveAction());
		
		Pddl pddl = new TowerOfHanoiPddl(initialState, goalState, actions, goalStateHeuristic);
		Strips strips = new Strips(pddl);
		List<String> solutionSteps = strips.solve();
		for (String solutionStep : solutionSteps) {
			System.out.println(solutionStep);
		}
	}

/*
		// Initial state.
		initialState.add(OnProposition.initOnProposition("ONE", "TWO"));
		initialState.add(OnProposition.initOnProposition("TWO", "A"));
		// Constant initial state.
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "A"));
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "B"));
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "C"));
		initialState.add(SmallerProposition.initSmallerProposition("TWO", "A"));
		initialState.add(SmallerProposition.initSmallerProposition("TWO", "B"));
		initialState.add(SmallerProposition.initSmallerProposition("TWO", "C"));
		initialState.add(SmallerProposition.initSmallerProposition("ONE", "TWO"));
		// Variable initial state.
		initialState.add(ClearProposition.initClearProposition("ONE"));
		initialState.add(ClearProposition.initClearProposition("B"));
		initialState.add(ClearProposition.initClearProposition("C"));

		// Goal state.
		List<Proposition> goalState = new ArrayList<Proposition>();
		goalState.add(OnProposition.initOnProposition("ONE", "TWO"));
		goalState.add(OnProposition.initOnProposition("TWO", "B"));
		// Constant initial state.
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "A"));
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "B"));
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "C"));
		goalState.add(SmallerProposition.initSmallerProposition("TWO", "A"));
		goalState.add(SmallerProposition.initSmallerProposition("TWO", "B"));
		goalState.add(SmallerProposition.initSmallerProposition("TWO", "C"));
		goalState.add(SmallerProposition.initSmallerProposition("ONE", "TWO"));
		// Variable initial state.
		goalState.add(ClearProposition.initClearProposition("ONE"));
		goalState.add(ClearProposition.initClearProposition("A"));
		goalState.add(ClearProposition.initClearProposition("C"));
	
 */
}
