import java.util.Random;


public class ShortestPath {

	
	/** Main Program. Process command line parms & create ClosestPair with input file. */
	public static void main(String[] args) {
		String inputFileName = null;
		
		// Process command line.
		switch(args.length) {
		case 1:
			inputFileName = args[0];
			break;
		default:
			System.out.println("Command line error. Usage:\njava ClosestPair <input filename>");
			System.exit(1);
		}
		
		Random random = new Random();
		MinHeap minHeap = new MinHeap();
		minHeap.insert(new VertexNode("A", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("B", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("C", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("D", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("E", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("F", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("G", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("H", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("I", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("J", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("K", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("L", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("M", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("N", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("O", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("P", random.nextInt(100)+1));
		minHeap.insert(new VertexNode("Q", random.nextInt(100)+1));
		
		VertexNode node = minHeap.extract();
		System.out.println(node);
		node = minHeap.extract();
		System.out.println(node);
		node = minHeap.extract();
		System.out.println(node);
		node = minHeap.extract();
		System.out.println(node);
		node = minHeap.extract();
		System.out.println(node);

		node = new VertexNode("R", random.nextInt(100)+1);
		minHeap.insert(node);
		node.setDistance(random.nextInt(100)+1);
		minHeap.update(node);
		minHeap.update(node);
		node.setDistance(random.nextInt(100)+1);
		minHeap.update(node);

		System.out.println(minHeap);
		
		// Create closest pair determiner.
//		ClosestPair closestPair = new ClosestPair(inputFileName);
//		// Compute closest pair of points.
//		Point[] closestPoints = closestPair.compute();
		
//		System.out.println("The minimum distance is:");
//		System.out.println(String.format("%.9f", closestPoints[0].distanceTo(closestPoints[1])) + ": " + closestPoints[0] + "<--->" + closestPoints[1]);
	}
 
}

