package ai1.strips;

import java.util.ArrayList;
import java.util.List;

public class Proposition implements Cloneable {

	/** Proposition name. */
	protected String name;

	/** Proposition variables. */
	protected List<Variable> variables;
	
	public Proposition() {
	}

	public Proposition(String name, List<Variable> variables) {
		this.name = name;
		this.variables = variables;
	}

	@Override
	public int hashCode() {
		int result = 31 + ((name == null) ? 0 : name.hashCode());
		if (variables == null) {
			return result;
		}
		for (Variable variable : variables) {
			result = 37 * result + ((variable == null) ? 0 : variable.hashCode());
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposition other = (Proposition) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (variables == null) {
			if (other.variables != null)
				return false;
		} else if (!variables.equals(other.variables))
			return false;
		for (int ix = 0; ix < variables.size(); ix++) {
			if (!variables.get(ix).equals(other.variables.get(ix))) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Proposition clone = (Proposition) super.clone();
		clone.variables = new ArrayList<Variable>();
		for (Variable variable : variables) {
			clone.variables.add(new Variable(variable));
		}
		return clone;
	}
	
	public String getName() {
		return name;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	@Override
	public String toString() {
		StringBuilder result = new  StringBuilder();
		result.append("Proposition [name=").append(name).append(", variables=");
		for (Variable variable : variables) {
			result.append(variable);
		}
		result.append("]");
		
		return result.toString();
	}

}
