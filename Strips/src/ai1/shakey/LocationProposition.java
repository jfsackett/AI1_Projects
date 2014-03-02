package ai1.shakey;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class LocationProposition extends Proposition {

	public static LocationProposition initLocationProposition(String locationValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, locationValue));
		return new LocationProposition(variables);
	}
	
	public static LocationProposition initLocationPropositionVariable(String locationName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(locationName, null));
		return new LocationProposition(variables);
	}
	
	public LocationProposition(List<Variable> variables) {
		super("LOC", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (LocationProposition) super.clone();
	}

}
