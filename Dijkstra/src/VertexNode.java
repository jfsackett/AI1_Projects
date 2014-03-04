/** Represents a node in the graph. */
public class VertexNode {

	/** Vertex. */
	String vertexId;
	
	/** Distance from start. */
	int distance;

	public VertexNode(String vertexId, int distance) {
		this.vertexId = vertexId;
		this.distance = distance;
	}

	public VertexNode(String vertexId) {
		this.vertexId = vertexId;
		this.distance = Integer.MAX_VALUE;
	}

	public String getVertexId() {
		return vertexId;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vertexId == null) ? 0 : vertexId.hashCode());
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
		VertexNode other = (VertexNode) obj;
		if (vertexId == null) {
			if (other.vertexId != null)
				return false;
		} else if (!vertexId.equals(other.vertexId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VertexNode [vertexId=" + vertexId + ", distance=" + distance
				+ "]";
	}
	
}
