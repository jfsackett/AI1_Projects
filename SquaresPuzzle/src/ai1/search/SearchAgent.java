package ai1.search;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** Provides all manners of search capabilities using pluggable search strategies. */
public class SearchAgent {
	
	/** Search strategy in use. */
	SearchStrategy searchStrategy;
	
	/** Constructor. */
	public SearchAgent(SearchStrategy searchStrategy) {
		this.searchStrategy = searchStrategy;
	}

	/** Perform search from start node to goal node. */
	public SearchResult search(Node startNode, Node goalNode) {
		// Check for invalid input.
		if (startNode == null || goalNode == null) {
			return new SearchResult();
		}
		
		// Add start node to frontier.
		searchStrategy.add(startNode);
		
		// Bookkeeping.
		long numNodesVisited = 0;
		Date startTime = new Date();
		
		// Loop until frontier nodes exhausted, per search strategy.
		while (searchStrategy.hasMoreNodes())  {
			// Get the next node, according to search strategy.
			Node currNode = searchStrategy.getNext();
			numNodesVisited++;
			// Goal node?
			if (currNode.equals(goalNode)) {
				// Return search result.
				long msecElapsed = new Date().getTime() - startTime.getTime();
				return new SearchResult(true, msecElapsed, numNodesVisited, reverseNodeLinks(currNode));
			}
			
			// Add successor nodes to frontier.
			searchStrategy.add(currNode.getSuccessorNodes());			
		}

		// Unsuccessful search. "Can't get there from here."
		long msecElapsed = new Date().getTime() - startTime.getTime();
		return new SearchResult(false, msecElapsed, numNodesVisited, new LinkedList<Node>());
	}
	
	/** Return list of moves reversed from goal so in order. */
	private static List<Node> reverseNodeLinks(Node node) {
		List<Node> resultList = new LinkedList<Node>();
		if (node == null) {
			return resultList;
		}
		// Use stack to reverse puzzle moves.
		Deque<Node> Nodes = new ArrayDeque<Node>();
		while (node != null) {
			Nodes.push(node);
			node = node.getParentNode();
		}
		// Iterate through stack, adding to list.
		Iterator<Node> iterNodes = Nodes.iterator();
		while(iterNodes.hasNext()) {
			resultList.add(iterNodes.next());
		}
		
		return resultList;
	}

	/** Print puzzle moves in list. */
	public void printNodes(List<Node> Nodes) {
		for (Node node : Nodes) {
			System.out.println(node);
		}
	}
}
