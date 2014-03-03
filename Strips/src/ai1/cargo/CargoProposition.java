package ai1.cargo;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class CargoProposition extends Proposition {

	public static CargoProposition initCargoProposition(String cargoValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, cargoValue));
		return new CargoProposition(variables);
	}
	
	public static CargoProposition initCargoPropositionVariable(String cargoName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(cargoName, null));
		return new CargoProposition(variables);
	}
	
	public CargoProposition(List<Variable> variables) {
		super("CARGO", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (CargoProposition) super.clone();
	}

}
