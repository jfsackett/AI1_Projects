package ai1.tower;

import java.util.List;

import ai1.strips.Pddl;
import ai1.strips.Strips;

public class Controller {

	public static void main(String[] args) {
		
		Pddl pddl = new TowerOfHanoi(null, null, null);
		Strips strips = new Strips(pddl);
		List<String> solutionSteps = strips.solve();
		for (String solutionStep : solutionSteps) {
			System.out.println(solutionStep);
		}
	}

}
