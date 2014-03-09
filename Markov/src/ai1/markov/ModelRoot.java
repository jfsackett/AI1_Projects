package ai1.markov;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModelRoot {

	/** Root / substring of name. */
	private String nameRoot;
	
	/** Count table for next letter. */
	private Map<Character, Double> nextLetterCountMap;
	
	/** Total count. */
	private Double totalCount = 0.0d;
	
	/** Probability table for next letter. */
	private LinkedHashMap<Double, Character> nextLetterProbMap;

	public ModelRoot(String nameRoot) {
		this.nameRoot = nameRoot;
		this.nextLetterCountMap = new HashMap<Character, Double>();
		this.nextLetterProbMap = new LinkedHashMap<Double, Character>();
	}
	
	public void incrementCount(char nextLetter) {
		Double count = nextLetterCountMap.get(nextLetter);
		if (count == null) {
			// Initialize count.
			count = 0.0d;
		}
		count++;
		nextLetterCountMap.put(nextLetter, count);
		totalCount++;
	}
	
	public void calculateProbabilties() {
		double runningPropability = 0.0d;
		for (Character nextLetter : nextLetterCountMap.keySet()) {
			runningPropability += nextLetterCountMap.get(nextLetter) / totalCount;
			nextLetterProbMap.put(runningPropability, nextLetter);
		}
	}
	
	public Character generateCharacter() {
		double random = Math.random();
		for (Double probability : nextLetterProbMap.keySet()) {
			if (random < probability) {
				return nextLetterProbMap.get(probability);
			}
		}
		return null;
	}

	public String getNameRoot() {
		return nameRoot;
	}
	
}
