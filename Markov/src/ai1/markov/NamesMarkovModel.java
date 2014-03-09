package ai1.markov;

import java.util.ArrayList;
import java.util.List;

public class NamesMarkovModel {
	private int modelOrder;
	
	private List<String> inputNames;

	public NamesMarkovModel(int modelOrder, List<String> inputNames) {
		this.modelOrder = modelOrder;
		this.inputNames = inputNames;
	}
	
	public List<String> generateNames(int numNames, int minNameLength, int maxNameLength) {
		List<String> names = new ArrayList<String>();
		names.add("Joesph");
		
		return names;
	}
}
