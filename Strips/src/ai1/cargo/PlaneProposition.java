package ai1.cargo;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class PlaneProposition extends Proposition {

	public static PlaneProposition initPlaneProposition(String planeValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, planeValue));
		return new PlaneProposition(variables);
	}
	
	public static PlaneProposition initPlanePropositionVariable(String planeName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(planeName, null));
		return new PlaneProposition(variables);
	}
	
	public PlaneProposition(List<Variable> variables) {
		super("PLANE", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (PlaneProposition) super.clone();
	}

}
