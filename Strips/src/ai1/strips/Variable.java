package ai1.strips;


public class Variable {

	/** Name; may be null unattached variable instance. */
	private String name;
	
	/** Value; may be null for uninstantiated variable. */
	private String value;
	
	/** Constructor. */
	public Variable(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/** Copy Constructor. */
	public Variable(Variable variable) {
		this.name = variable.name;
		this.value = variable.value;
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " Variable [name=" + name + ", value=" + value + "]";
	}
	
}
