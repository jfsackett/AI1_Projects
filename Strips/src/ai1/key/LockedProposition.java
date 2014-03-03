package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class LockedProposition extends Proposition {

	public static LockedProposition initLockedProposition(String doorValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, doorValue));
		return new LockedProposition(variables);
	}
	
	public LockedProposition(List<Variable> variables) {
		super("LOCKED", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (LockedProposition) super.clone();
	}

}
