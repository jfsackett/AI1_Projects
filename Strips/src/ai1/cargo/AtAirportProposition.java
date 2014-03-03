package ai1.cargo;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class AtAirportProposition extends Proposition {

	public static AtAirportProposition initAtAirportProposition(String itemValue, String locationValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, itemValue));
		variables.add(new Variable(null, locationValue));
		return new AtAirportProposition(variables);
	}
	
	public static AtAirportProposition initAtAirportPropositionVariable(String itemName, String locationName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(itemName, null));
		variables.add(new Variable(locationName, null));
		return new AtAirportProposition(variables);
	}
	
	public AtAirportProposition(List<Variable> variables) {
		super("AT", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (AtAirportProposition) super.clone();
	}

}
