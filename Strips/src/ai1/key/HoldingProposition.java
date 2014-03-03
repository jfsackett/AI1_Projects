package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class HoldingProposition extends Proposition {

	public static HoldingProposition initHoldingProposition(String keyValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, keyValue));
		return new HoldingProposition(variables);
	}
	
	public HoldingProposition(List<Variable> variables) {
		super("HOLDING", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (HoldingProposition) super.clone();
	}

}
