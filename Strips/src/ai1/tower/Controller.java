package ai1.tower;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Pddl;
import ai1.strips.Proposition;
import ai1.strips.Strips;

public class Controller {

	public static void main(String[] args) {
		
		List<Proposition> initialState = new ArrayList<Proposition>();
		initialState.add(new SideProposition("Heads"));
		
		List<Proposition> goalState = new ArrayList<Proposition>();
		goalState.add(new SideProposition("Tails"));
		
		List<Action> actions = new ArrayList<Action>();
		actions.add(new FlipAction(initialState, initialState, goalState));
		
		Pddl pddl = new TowerOfHanoi(initialState, goalState, actions);
		Strips strips = new Strips(pddl);
		List<String> solutionSteps = strips.solve();
		for (String solutionStep : solutionSteps) {
			System.out.println(solutionStep);
		}
	}

}
