package ai1.strips;

import java.util.ArrayList;
import java.util.List;

public class Strips {

	/** Problem definition to solve. */
	Pddl pddl;

	/** Constructor. */
	public Strips(Pddl pddl) {
		this.pddl = pddl;
	}
	
	/** Solve the problem. */
	public List<String> solve() {
		return new ArrayList<String>();
	}
}
