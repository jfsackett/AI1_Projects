package ai1.cargo;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class AirportProposition extends Proposition {

	public static AirportProposition initAirportProposition(String airportValue) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, airportValue));
		return new AirportProposition(variables);
	}
	
	public static AirportProposition initAirportPropositionVariable(String airportName) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(airportName, null));
		return new AirportProposition(variables);
	}
	
	public AirportProposition(List<Variable> variables) {
		super("AIRPORT", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (AirportProposition) super.clone();
	}

}
