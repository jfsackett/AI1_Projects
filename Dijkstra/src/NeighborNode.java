
/** Represents a neighbor node in the adjacency list. */
public class NeighborNode {

	/** Vertex Id. */
	String vertexId;
	
	/** Distance from neighbor. */
	int distance;
	
	public NeighborNode(String vertexId, int distance) {
		this.vertexId = vertexId;
		this.distance = distance;
	}

	public String getVertexId() {
		return vertexId;
	}

	public int getDistance() {
		return distance;
	}
	
}
