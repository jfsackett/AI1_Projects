package ai1.shakey;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class AtProposition extends Proposition {

	public static AtProposition initAtProposition(String itemValue, String locationValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, itemValue));
		variables.add(new Variable(null, locationValue));
		return new AtProposition(variables);
	}
	
	public static AtProposition initAtPropositionVariable(String itemName, String locationName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(itemName, null));
		variables.add(new Variable(locationName, null));
		return new AtProposition(variables);
	}
	
	public static AtProposition initShakeyAtPropositionVariable(String locationName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, "Shakey"));
		variables.add(new Variable(locationName, null));
		return new AtProposition(variables);
	}
	
	public AtProposition(List<Variable> variables) {
		super("AT", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (AtProposition) super.clone();
	}

}
