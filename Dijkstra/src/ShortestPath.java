import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Main computation of Dijkstra's shortest path algorithm. */
public class ShortestPath {

	/** Map of vertex nodes. */
	Map<String,VertexNode> verticesMap;
	
	/** Constructor. */
	public ShortestPath(String inputFileName) {
		verticesMap = readVertices(inputFileName);
	}
 
	/** Read input file of vertices. */
	private static Map<String,VertexNode> readVertices(String inputFileName) {
		Map<String,VertexNode> vertices = new HashMap<String,VertexNode>();
		BufferedReader reader = null;
		try {
			// Open file into buffered reader.
			reader = new BufferedReader(new FileReader(inputFileName));
			// Read length line, not needed.
			reader.readLine();
			
			// Loop through & read the vertices, store in map. 
			String row;
			while ((row = reader.readLine()) != null && row.trim().length() != 0) {
				// Split line by any whitespace.
				String[] split = row.trim().split("\\s+");
				String vertexId = split[0];
				String neighborId = split[1];
				// Check whether parent vertex has already been added to map.
				VertexNode vertex = vertices.get(vertexId);
				if (vertex == null) {
					// Nope, create & add it.
					vertex = new VertexNode(vertexId);
					vertices.put(vertexId, vertex);
				}
				// Add neighbor node.
				vertex.getNeighborNodes().add(new NeighborNode(neighborId, Integer.parseInt(split[2])));
				
				// Check whether neighbor vertex has already been added to map.
				VertexNode neighborVertex = vertices.get(neighborId);
				if (neighborVertex == null) {
					// Nope, create & add it.
					neighborVertex = new VertexNode(neighborId);
					vertices.put(neighborId, neighborVertex);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(inputFileName);
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println(inputFileName);
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (IOException e) {}
			}
		}
		
		return vertices;
	}
	
	/** Compute the shortest path. */
	public ShortestPathResult compute() {
		// Retrieve start vertex, initialize distance to 0.
		VertexNode startVertex = verticesMap.get("A");
		startVertex.setDistance(0);
		
		// Loop through all vertices & add to the minheap.
		MinHeap minHeap = new MinHeap();
		for (Map.Entry<String,VertexNode> entry : verticesMap.entrySet()) {
			minHeap.insert(entry.getValue());
		}
		
		// Loop until no more node to evaluate in heap.
		while (!minHeap.isEmpty()) {
			VertexNode currentVertex = minHeap.extract();
			for (NeighborNode neighbor : currentVertex.getNeighborNodes()) {
				// Get neighbor vertex from map.
				VertexNode neighborVertex = verticesMap.get(neighbor.getVertexId());
				// Check whether distance through current node is shorter than previous distance.
				if (neighborVertex.getDistance() > currentVertex.getDistance() + neighbor.getDistance()) {
					// Yes, update distance & minheap.
					neighborVertex.setParentNode(currentVertex);
					neighborVertex.setDistance(currentVertex.getDistance() + neighbor.getDistance());
					// Refresh heap since distance could have chaanged.
					minHeap.update(neighborVertex);
				}
			}
		}
		
		// Retrieve end vertex.
		VertexNode endVertex = verticesMap.get("B");
		
		// Return shortest path info from B.
		return new ShortestPathResult(endVertex);
	}
	
	/** Main Program. Process command line parms & creates ShortestPath with input filename. */
	public static void main(String[] args) {
		String inputFileName = null;
		
		// Process command line.
		switch(args.length) {
		case 1:
			inputFileName = args[0];
			break;
		default:
			System.out.println("Command line error. Usage:\njava ShortestPath <input filename>");
			System.exit(1);
		}
		
		// Create shortest path determiner.
		ShortestPath shortestPath = new ShortestPath(inputFileName);
		// Compute shortest path between start (A) and end (B).
		ShortestPathResult result = shortestPath.compute();
		
		System.out.println(result);
	}
	
	/** Holds the result of the shortest path computation. */
	private static class ShortestPathResult {
		/** Total shortest distance. */
		int totalDistance;
		
		/** List of vertex ids in shortest path. */
		List<String> vertexIds = new ArrayList<String>();

		/** Constructor. */
		public ShortestPathResult(VertexNode endVertex) {
			this.totalDistance = endVertex.getDistance();
			this.vertexIds = reversePath(endVertex);
		}
		
		/** Nodes link to parents so recursively reverse path for display. */
		private static List<String> reversePath(VertexNode vertex) {
			if (vertex == null) {
				return new ArrayList<String>();
			}
			List<String> result = reversePath(vertex.getParentNode());
			result.add(vertex.getVertexId());
			return result;
		}

		/** To string. */
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append(totalDistance).append("\n");
			for (String vertexId : vertexIds) {
				result.append(vertexId).append(" ");
			}
			
			return result.toString();
		}
	}
	
}

