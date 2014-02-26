

import java.util.ArrayList;
import java.util.List;

import ai1.coin.CoinFlip;
import ai1.coin.FlipAction;
import ai1.coin.SideProposition;
import ai1.strips.Action;
import ai1.strips.Pddl;
import ai1.strips.Proposition;
import ai1.strips.Strips;

public class Controller {

	public static void main(String[] args) {
		
		List<Proposition> initialState = new ArrayList<Proposition>();
		initialState.add(SideProposition.initSideProposition("TAILS"));
		
		List<Proposition> goalState = new ArrayList<Proposition>();
		goalState.add(SideProposition.initSideProposition("HEADS"));
		
		List<Action> actions = new ArrayList<Action>();
		actions.add(FlipAction.initFlipAction("HEADS", "TAILS"));
		actions.add(FlipAction.initFlipAction("TAILS", "HEADS"));
		
		Pddl pddl = new CoinFlip(initialState, goalState, actions);
		Strips strips = new Strips(pddl);
		List<String> solutionSteps = strips.solve();
		for (String solutionStep : solutionSteps) {
			System.out.println(solutionStep);
		}
	}

}
