package ai1.coin;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class SideProposition extends Proposition {

	public static SideProposition initSideProposition(String value) {
		List<Variable> variables = new ArrayList<Variable>();
		Variable coin = new Variable(null, "COIN");
		variables.add(coin);
		Variable sideValue = new Variable(null, value);
		variables.add(sideValue);
		return new SideProposition(variables);
	}
	
	public SideProposition(List<Variable> variables) {
		super("SIDE", variables);
	}

}
