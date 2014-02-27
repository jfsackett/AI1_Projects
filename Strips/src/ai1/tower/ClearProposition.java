package ai1.tower;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class ClearProposition extends Proposition {

	public static ClearProposition initClearProposition(String item) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, item));
		return new ClearProposition(variables);
	}
	
	public static ClearProposition initClearPropositionVariable(String name) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(name, null));
		return new ClearProposition(variables);
	}
	
	public ClearProposition(List<Variable> variables) {
		super("CLEAR", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (ClearProposition) super.clone();
	}
	
	@Override
	public String toString() {
		return "";
	}

}
