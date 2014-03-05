import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Represents a minheap data structure. */
public class MinHeap {
	
	/** List containing the elements */
	List<VertexNode> elements;
	
	/** Size of heap. */
	int size;

	/** Internal capacity of heap. */
	int capacity;
	
	/** Internal cache of element index. */
	Map<VertexNode,Integer> indexMap;

	public MinHeap() {
		elements = new ArrayList<VertexNode>();
		size = 0;
		capacity = 0;
		indexMap = new HashMap<VertexNode,Integer>();
	}
	
	/** Returns flag indicating whether or not the heap is empty. */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/** Insert node into minheap. */
	public void insert(VertexNode node) {
		if (size == capacity) {
			elements.add(node);
			capacity++;
		}
		else {
			elements.set(size, node);
		}
		indexMap.put(node, size);
		bubbleUp(size);
		size++;
	}
	
	/** Extract minimum node from minheap. */
	public VertexNode extract() {
		if (size == 0) {
			return null;
		}
		VertexNode currNode = elements.get(0);
		size--;
		if (size == 0) {
			return currNode;
		}
		swap(0, size);
		bubbleDown(0);
		
		return currNode;
	}
	
	/** Updates a node in the minheap. */
	public void update(VertexNode node) {
		Integer heapNodeIx = indexMap.get(node);
		if (heapNodeIx == null) {
			// Not found.
			return;
		}
		VertexNode heapNode = elements.get(heapNodeIx);
		heapNode.setDistance(node.getDistance());

		// Compare with parent node and determine whether to bubble up or down.
		int parentIx = (heapNodeIx - 1) / 2;
		if (heapNode.getDistance() < elements.get(parentIx).getDistance()) {
			bubbleUp(heapNodeIx);
		}
		else {
			bubbleDown(heapNodeIx);			
		}
	}
	
	/** Recursively bubble the indexed node up until less than parent. */
	private void bubbleUp(int currIx) {
		while (currIx == 0) {
			return;
		}
		int parentIx = (currIx - 1) / 2;
		checkSwap(currIx, parentIx);
		bubbleUp(parentIx);
	}
	
	/** Bubble the indexed node down until greater than parent. */
	private void bubbleDown(int currIx) {
		int minChildIx;
		int leftChildIx = currIx * 2 + 1;
		if (leftChildIx >= size) {
			// No children.
			return;
		}
		int rightChildIx = currIx * 2 + 2;
		if (rightChildIx >= size) {
			// Only left child.
			minChildIx = leftChildIx;
		}
		else {
			// Two children, find smaller child.
			if (elements.get(leftChildIx).getDistance() < elements.get(rightChildIx).getDistance()) {
				minChildIx = leftChildIx;
			}
			else {
				minChildIx = rightChildIx;
			}
		}
		// Swap & recurse if min child distance < current distance.
		if (elements.get(currIx).getDistance() > elements.get(minChildIx).getDistance()) {
			swap(currIx, minChildIx);
			bubbleDown(minChildIx);
		}
	}
	
	/** Check values at indexes and swap if element at child > element at parent. */
	private void checkSwap(int childIx, int parentIx) {
		VertexNode childNode = elements.get(childIx);
		VertexNode parentNode = elements.get(parentIx);
		if (childNode.getDistance() < parentNode.getDistance()) {
			elements.set(childIx, parentNode);
			indexMap.put(parentNode, childIx);
			elements.set(parentIx, childNode);
			indexMap.put(childNode, parentIx);
		}
	}
	
	/** Swap two elements. */
	private void swap(int ix1, int ix2) {
		VertexNode currNode = elements.get(ix1);
		elements.set(ix1, elements.get(ix2));
		indexMap.put(elements.get(ix2), ix1);
		elements.set(ix2, currNode);
		indexMap.put(currNode, ix2);
	}
	
	/** Validates state of heap - testing only. */
	@SuppressWarnings("unused")
	private boolean validate() {
		for (int ix = size - 1; ix > 0; ix--) {
			if (elements.get(ix).getDistance() < elements.get((ix-1)/2).getDistance()) {
				System.out.println("Node: " + elements.get(ix) + "  out of place with: " + elements.get((ix-1)/2));
				return false;
			}
		}
		
		return true;
	}
	
}
