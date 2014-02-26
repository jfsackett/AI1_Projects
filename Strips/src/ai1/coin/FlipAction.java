package ai1.coin;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Action;
import ai1.strips.Proposition;
import ai1.strips.Variable;

public class FlipAction extends Action {

	public static FlipAction initFlipAction(String fromSide, String toSide) {
		List<Variable> variables = new ArrayList<Variable>();
		Variable coin = new Variable("X", null);
		variables.add(coin);
		
		List<Proposition> preConditions = new ArrayList<Proposition>();
		preConditions.add(SideProposition.initSideProposition(fromSide));
		List<Proposition> deletePropositions = new ArrayList<Proposition>();
		deletePropositions.add(SideProposition.initSideProposition(fromSide));
		List<Proposition> addPropositions = new ArrayList<Proposition>();
		addPropositions.add(SideProposition.initSideProposition(toSide));
		
		String name = "Flip" + fromSide + toSide;
		
		return new FlipAction(name, variables, preConditions, deletePropositions, addPropositions);
	}
	
	/** Constructor. */
	public FlipAction(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> deletePropositions, List<Proposition> addPropositions) {
		super(name, variables, preConditions, deletePropositions, addPropositions);
	}

	
}
