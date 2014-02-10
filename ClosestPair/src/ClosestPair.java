import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/** Main application class to determine the closest two points from a set of input points. */
public class ClosestPair {

	/** List of points sorted by x coordinate. */
	private List<Point> pointsSortedX = new ArrayList<Point>();
	
	/** List of points sorted by y coordinate. */
	private List<Point> pointsSortedY = new ArrayList<Point>();
	
	/** Constructor. */
	public ClosestPair(String inputFileName) {
		List<Point> pointsList = readPoints(inputFileName);
		Point[] points = pointsList.toArray(new Point[0]);
		
		// Fill & sort collection of points by X coordinate. */
		mergeSort(points, 0, points.length, Point.xCoordComparator);
		for (int ix = 0; ix < points.length; ix++) {
			pointsSortedX.add(points[ix]);
		}
		
		// Fill & sort collection of points by Y coordinate. */
		mergeSort(points, 0, points.length, Point.yCoordComparator);
		for (int ix = 0; ix < points.length; ix++) {
			pointsSortedY.add(points[ix]);
		}
	}

	/** Read input file of points. */
	private static List<Point> readPoints(String inputFileName) {
		List<Point> points = new LinkedList<Point>();
		BufferedReader reader = null;
		try {
			// Open file into buffered reader.
			reader = new BufferedReader(new FileReader(inputFileName));
			
			String row;
			// Loop through & read the points, store in list. 
			while ((row = reader.readLine()) != null && row.trim().length() != 0) {
				// Split line by any whitespace.
				String[] split = row.split("\\s+");
				points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
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
		
		return points;
	}
	
	/** Paramaterizes left or right separation. */
	private enum Side {LEFT, RIGHT};
		
	/**
	 * Recursive function to employ divide & conquer to find closest pair of points.
	 * @param points Subset of points from which this function will determine closest pair of points, sorted by X coordinate.
	 * @param pointsSortedY Global sorted (by Y coord) list of points.
	 * @return 2 element array containing the two closest points.
	 */
	private static Point[] findClosestPair(List<Point> subsetPointsSortedX, List<Point> allPointsSortedY) {		// Check for recursion termination condition.
		if (subsetPointsSortedX.size() <= 3) {
			return findClosestPairBruteForce(subsetPointsSortedX);
		}
		
		// Separate points into left & right (of median) collections. 
		List<Point> leftPointsSortedX = separatePoints(Side.LEFT, subsetPointsSortedX);
		List<Point> rightPointsSortedX = separatePoints(Side.RIGHT, subsetPointsSortedX);
		
		// Determine closest two points on each separate side.
		Point[] closestPairLeft = findClosestPair(leftPointsSortedX, allPointsSortedY);
		Point[] closestPairRight = findClosestPair(rightPointsSortedX, allPointsSortedY);
		
		// Find X coordinate separating left & right sides.
		double middleXCoordinate = (rightPointsSortedX.get(0).getxCoord() + leftPointsSortedX.get(leftPointsSortedX.size()-1).getxCoord()) / 2;
		
		// Check which side had closer points and check the middle stripe for closer points than the one from the side.
		if (closestPairLeft[0].distanceTo(closestPairLeft[1]) < closestPairRight[0].distanceTo(closestPairRight[1])) {
			// Left points closer, check the middle stripe for points closer than they.
			return checkMiddleStripe(closestPairLeft, middleXCoordinate, subsetPointsSortedX, allPointsSortedY);
		}
		
		// Right points closer, check the middle stripe for points closer than they.
		return checkMiddleStripe(closestPairRight, middleXCoordinate, subsetPointsSortedX, allPointsSortedY);
	}
	
	/** Checks middle stripe between left and right side for points closer than the closest on a separate side. */
	private static Point[] checkMiddleStripe(Point[] closestPairSide, double middleXCoordinate, List<Point> subsetPointsSortedX, List<Point> allPointsSortedY) {
		// Initialize minimum distance and point with that distance to that found on the side.
		double closestDistance = closestPairSide[0].distanceTo(closestPairSide[1]);
		Point[] closestPair = closestPairSide;
		
		// Determine left coordinate of stripe.
		double startXRange = middleXCoordinate - closestDistance;
		// Determine right coordinate of stripe.
		double endXRange = middleXCoordinate + closestDistance;
		List<Point> middlePointsSortedX = new ArrayList<Point>();
		for (Point point : subsetPointsSortedX) {
			// If within stripe, add to the list.
			if (point.getxCoord() >= startXRange && point.getxCoord() <= endXRange) {
				middlePointsSortedX.add(point);
			}
		}
		
		// Use all points sorted by Y to build list of middle points sorted by Y.
		List<Point> middlePointsSortedY = new ArrayList<Point>();
		for (Point point : allPointsSortedY) {
			// If in the stripe.
			if (binarySearch(middlePointsSortedX, point)) {
				// Add to list of middle points sorted by Y coord.
				middlePointsSortedY.add(point);
			}
		}
		
		// Loop through middle stripe and compare each point to the next 7 points, looking for closer ones.
		for (int ix = 0; ix < middlePointsSortedY.size(); ix++) {
			for (int iy = ix + 1; iy <= ix + 7 && iy < middlePointsSortedY.size(); iy++) {
				double distance = middlePointsSortedY.get(ix).distanceTo(middlePointsSortedY.get(iy));
				// If closer than points before, keep this set & distance.
				if (distance < closestDistance) {
					closestPair[0] = middlePointsSortedY.get(ix);
					closestPair[1] = middlePointsSortedY.get(iy);
					closestDistance = distance;
				}
			}
		}		
		
		return closestPair;
	}
	
	/** Perform binary search for a point in a collection sorted by X coordinate. */ 
	private static boolean binarySearch(List<Point> middlePointsSortedX, Point point) {
		int middleSize = middlePointsSortedX.size();
		// Check recursion end conditions.
		if (middleSize == 0) {
			return false;
		}
		if (middleSize == 1) {
			return point.equals(middlePointsSortedX.get(0));
		}
		
		// Check middle point.
		int middleIndex = middleSize / 2;
		Point middlePoint = middlePointsSortedX.get(middleIndex);
		if (point.equals(middlePoint)) {
			return true;
		}
		
		// Recurse left side.
		if (point.getxCoord() < middlePoint.getxCoord()) {
			return binarySearch(middlePointsSortedX.subList(0, middleIndex), point);
		}
		
		// Recurse right side.
		return binarySearch(middlePointsSortedX.subList(middleIndex+1, middlePointsSortedX.size()), point);
	}
	
	
	/** Finds closest pair of points by brute force. Use for degenerate case of divide & conquer. */
	private static Point[] findClosestPairBruteForce(List<Point> points) {
		// Holds the closest pair so far.
		Point[] closestPair = new Point[2];
		// Holds the minimum distance so far.
		double closestDistance = Double.MAX_VALUE;
		// Iterate nested through the points, and compare the distance to each other.
		for (Point point1 : points) {
			for (Point point2 : points) {
				// Ignore same point.
				if (point1.equals(point2)) {
					continue;
				}
				double distance = point1.distanceTo(point2);
				// If closer than points before, keep this set & distance.
				if (distance < closestDistance) {
					closestPair[0] = point1;
					closestPair[1] = point2;
					closestDistance = distance;
				}
			}
		}
		
		return closestPair;
	}
	
	/** Separates a list of points by whether it is on the left or right of median point. */
	private static List<Point> separatePoints(Side side, List<Point> pointsSortedX) {
		List<Point> result = new ArrayList<Point>();
		// Calculate range of indexes with which to separate.
		int middle = pointsSortedX.size() / 2;
		int start, end;
		if (side == Side.LEFT) {
			start = 0;
			end = middle;
		}
		else {
			start = middle;
			end = pointsSortedX.size();
		}
		
		// Loop through indexes, adding to result.
		for (int ix = start; ix < end; ix++) {
			result.add(pointsSortedX.get(ix));
		}
		
		return result;
	}
	
	/** Mergesort for points. Paramaterizized by comparator (for x & y coords). */
	private static void mergeSort(Point[] points, int start, int end, Comparator<Point> comparator) {
		if (start + 1 < end) {
			// Determine middle index.
			int middle = (start + end) / 2;
			// Recurse left.
			mergeSort(points, start, middle, comparator);
			// Recurse right.
			mergeSort(points, middle, end, comparator);
			// Merge & sort the two sorted sides.
			merge(points, start, middle, end, comparator);
		}
	}

	/** Merge and sort left range with right range. */
	private static void merge(Point[] pointsIn, int low, int middle, int high, Comparator<Point> comparator) {
		// Allocate buffer to hold sorted points. 
		int bufferLen = high - low;
		Point[] points = new Point[bufferLen];
		
		// Move through both ranges & move the point with lower coordinate to buffer.
		int ixOut = 0;
		int ixLeft = low;
		int ixRight = middle;
		while (ixLeft < middle && ixRight < high) {
			if (comparator.compare(pointsIn[ixLeft], pointsIn[ixRight]) <= 0) {
				points[ixOut++] = pointsIn[ixLeft++];
			}
			else {
				points[ixOut++] = pointsIn[ixRight++];
			}
		}
		
		// Copy remaining left side, if any.
		while (ixLeft < middle) {
			points[ixOut++] = pointsIn[ixLeft++];
		}
		
		// Copy remaining right side, if any.
		while (ixRight < high) {
			points[ixOut++] = pointsIn[ixRight++];
		}
		
		// Copy sorted buffer back to input array.
		for (int ix = 0; ix < bufferLen; ix++) {
			pointsIn[low + ix] = points[ix];
		}
	}
	
	/** Computes the closest pair of points. */
	public Point[] compute() {
		// Find the closest points in global list. Both collections have same point, just sorted by different coordinate.
		return findClosestPair(pointsSortedX, pointsSortedY);
	}
	
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
		
		// Create closest pair determiner.
		ClosestPair closestPair = new ClosestPair(inputFileName);
		// Compute closest pair of points.
		Point[] closestPoints = closestPair.compute();
		
		System.out.println("The minimum distance is:");
		System.out.println(String.format("%.9f", closestPoints[0].distanceTo(closestPoints[1])) + ": " + closestPoints[0] + "<--->" + closestPoints[1]);
	}
 
}
