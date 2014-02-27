package ai1.tower;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class SmallerProposition extends Proposition {

	public static SmallerProposition initSmallerProposition(String smallerValue, String largerValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, smallerValue));
		variables.add(new Variable(null, largerValue));
		return new SmallerProposition(variables);
	}
	
	public static SmallerProposition initSmallerPropositionVariable(String smallerName, String largerName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(smallerName, null));
		variables.add(new Variable(largerName, null));
		return new SmallerProposition(variables);
	}
	
	public SmallerProposition(List<Variable> variables) {
		super("SMALLER", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (SmallerProposition) super.clone();
	}
	
	@Override
	public String toString() {
		return "";
	}

}
