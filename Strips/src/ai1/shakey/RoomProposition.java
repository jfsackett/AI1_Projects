package ai1.shakey;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class RoomProposition extends Proposition {

	public static RoomProposition initRoomProposition(String location) {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable(null, location));
		return new RoomProposition(variables);
	}
	
	public RoomProposition(List<Variable> variables) {
		super("ROOM", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (RoomProposition) super.clone();
	}

}
