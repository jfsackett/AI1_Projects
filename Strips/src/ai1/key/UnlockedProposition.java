package ai1.key;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class UnlockedProposition extends Proposition {

	public static UnlockedProposition initUnlockedProposition(String doorValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, doorValue));
		return new UnlockedProposition(variables);
	}
	
	public UnlockedProposition(List<Variable> variables) {
		super("UNLOCKED", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (UnlockedProposition) super.clone();
	}

}
