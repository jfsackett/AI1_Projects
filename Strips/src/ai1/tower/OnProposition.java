package ai1.tower;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class OnProposition extends Proposition {

	/** Cost estimate of the number of moves to make this proposition true. */ 
	private int costEstimate = 0;
	
	public static OnProposition initOnProposition(String topValue, String bottomValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, topValue));
		variables.add(new Variable(null, bottomValue));
		return new OnProposition(variables);
	}
	
	public static OnProposition initOnProposition(String topValue, String bottomValue, int costEstimate) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, topValue));
		variables.add(new Variable(null, bottomValue));
		return new OnProposition(variables, costEstimate);
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

	public OnProposition(List<Variable> variables, int costEstimate) {
		super("ON", variables);
		this.costEstimate = costEstimate;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (OnProposition) super.clone();
	}

	public int getCostEstimate() {
		return costEstimate;
	}
	
}
