package ai1.tower;

import java.util.ArrayList;
import java.util.List;

import ai1.strips.Proposition;
import ai1.strips.Variable;

public class DiskProposition extends Proposition {

	public static DiskProposition initDiskProposition(String value) {
		List<Variable> variables = new ArrayList<Variable>();
		Variable sideValue = new Variable(null, value);
		variables.add(sideValue);
		return new DiskProposition(variables);
	}
	
	public DiskProposition(List<Variable> variables) {
		super("DISK", variables);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (DiskProposition) super.clone();
	}
	
	@Override
	public String toString() {
		return "";
	}

}
