package ai1.shakey;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class BoxProposition extends Proposition {

	public static BoxProposition initBoxProposition(String boxValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, boxValue));
		return new BoxProposition(variables);
	}
	
	public static BoxProposition initBoxPropositionVariable(String boxName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(boxName, null));
		return new BoxProposition(variables);
	}
	
	public BoxProposition(List<Variable> variables) {
		super("BOX", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (BoxProposition) super.clone();
	}

}
