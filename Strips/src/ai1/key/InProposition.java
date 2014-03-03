package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class InProposition extends Proposition {

	public static InProposition initInProposition(String itemValue, String locationValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, itemValue));
		variables.add(new Variable(null, locationValue));
		return new InProposition(variables);
	}
	
	public static InProposition initInPropositionVariable(String itemName, String locationName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(itemName, null));
		variables.add(new Variable(locationName, null));
		return new InProposition(variables);
	}
	
	public InProposition(List<Variable> variables) {
		super("IN", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (InProposition) super.clone();
	}

}
