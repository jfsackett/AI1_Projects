package ai1.cargo;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class InPlaneProposition extends Proposition {

	public static InPlaneProposition initInPlaneProposition(String itemValue, String locationValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, itemValue));
		variables.add(new Variable(null, locationValue));
		return new InPlaneProposition(variables);
	}
	
	public static InPlaneProposition initInPlanePropositionVariable(String itemName, String locationName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(itemName, null));
		variables.add(new Variable(locationName, null));
		return new InPlaneProposition(variables);
	}
	
	public InPlaneProposition(List<Variable> variables) {
		super("IN", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (InPlaneProposition) super.clone();
	}

}
