package ai1.tower;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class OnProposition extends Proposition {

	/** Weight of the disk in this proposition for calculating heuristic value. */ 
	private int weight = 0;
	
	public static OnProposition initOnProposition(String topValue, String bottomValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, topValue));
		variables.add(new Variable(null, bottomValue));
		return new OnProposition(variables);
	}
	
	public static OnProposition initOnProposition(String topValue, String bottomValue, int weight) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, topValue));
		variables.add(new Variable(null, bottomValue));
		return new OnProposition(variables, weight);
	}
	
	public static OnProposition initOnPropositionVariable(String topName, String bottomName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(topName, null));
		variables.add(new Variable(bottomName, null));
		return new OnProposition(variables);
	}
	
	public OnProposition(List<Variable> variables) {
		super("ON", variables);
	}

	public OnProposition(List<Variable> variables, int weight) {
		super("ON", variables);
		this.weight = weight;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (OnProposition) super.clone();
	}

	public int getWeight() {
		return weight;
	}
	
}
