package ai1.strips;

import java.util.ArrayList;
import java.util.List;

public class Proposition {

	/** Proposition name. */
	protected String name;

	/** Proposition variables. */
	protected List<Variable> variables;
	
	public Proposition(String name, List<Variable> variables) {
		this.name = name;
		this.variables = variables;
	}

	/** Copy Constructor. */
	public Proposition(Proposition proposition) {
		this.name = proposition.name;
		this.variables = new ArrayList<Variable>();
		for (Variable variable : proposition.variables) {
			this.variables.add(new Variable(variable));
		}
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

	public String getName() {
		return name;
	}

	public List<Variable> getVariables() {
		return variables;
	}

}
