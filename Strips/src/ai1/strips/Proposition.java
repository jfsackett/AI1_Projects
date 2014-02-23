package ai1.strips;

public abstract class Proposition {

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);

	public enum Type {
		CONCRETE,
		VARIABLE;
	}
}
