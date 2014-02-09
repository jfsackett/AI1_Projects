import java.util.Comparator;

/** Represents a point in the X/Y plane. */
public class Point {

	/** X coordinate. */
	private int xCoord;
	
	/** Y coordinate. */
	private int yCoord;

	public Point(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}
	
	/** Compute distance to another point. */
	public double distanceTo(Point point) {
		return Math.abs(Math.sqrt(Math.pow(this.getxCoord() - point.getxCoord(), 2) + Math.pow(this.getyCoord() - point.getyCoord(), 2)));
	}

	/** Generate hash code. */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}

	/** Check whether this point equals to another. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (xCoord != other.xCoord)
			return false;
		if (yCoord != other.yCoord)
			return false;
		return true;
	}

	/** Convert to string for display. */
	@Override
	public String toString() {
		return "(" + xCoord + ", " + yCoord + ")";
	}

	/** Comparator for sorting by X coordinate. */
	public static final Comparator<Point> xCoordComparator = new Comparator<Point>() {
		public int compare(Point point1, Point point2) {
			return point1.getxCoord() - point2.getxCoord();
		}
	};	

	/** Comparator for sorting by Y coordinate. */
	public static final Comparator<Point> yCoordComparator = new Comparator<Point>() {
		public int compare(Point point1, Point point2) {
			return point1.getyCoord() - point2.getyCoord();
		}
	};	
}
