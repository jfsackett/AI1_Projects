package ai1.markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamesMarkovModel {
	/** Order of the Markov model. */
	private int modelOrder; 
	
	/** Map of name roots & probability tables. */
	private Map<String, ModelRoot> modelRoots;

	public NamesMarkovModel(int modelOrder, List<String> inputNames) {
		this.modelOrder = modelOrder;
		buildModel(modelOrder, inputNames);
	}
	
	private void buildModel(int modelOrder, List<String> inputNames) {
		modelRoots = new HashMap<String, ModelRoot>();
		// Count roots of size modelOrder - 1.
		for (String inputName : inputNames) {
			String name = normalizeName(inputName, modelOrder);
			for (int ix = 0; ix <= name.length() - modelOrder; ix++) {
				String nameRoot = name.substring(ix, ix + modelOrder - 1);
				ModelRoot modelRoot = modelRoots.get(nameRoot);
				if (modelRoot == null) {
					// First encounter, create & put in map;
					modelRoot = new ModelRoot(nameRoot);
					modelRoots.put(modelRoot.getNameRoot(), modelRoot);
				}
				modelRoot.incrementCount(name.charAt(ix + modelOrder - 1));
			}
		}
		
		// Calculate probabilities for each modelRoot character.
		double runningProbability = 0.0d;
		for (ModelRoot modelRoot : modelRoots.values()) {
			modelRoot.calculateProbabilties();
		}
		System.out.println(runningProbability);
	}
	
	private static String normalizeName(String inputName, int modelOrder) {
		StringBuilder name = new StringBuilder(inputName.toLowerCase());
		for (int ix = 0; ix < modelOrder - 1; ix++) {
			name.insert(0, '_');
			name.append('_');
		}
		
		return name.toString();
	}
	
	public List<String> generateNames(int numNames, int minNameLength, int maxNameLength) {
		List<String> names = new ArrayList<String>();
		while (names.size() < numNames) {
			String name = generateName();
			if (name.length() < minNameLength || name.length() > maxNameLength) {
				// Name too short or long.
				continue;
			}
			names.add(name);
		}
		
		return names;
	}
	
	private String generateName() {
		String name = new String();
		for (int ix = 0; ix < modelOrder - 1; ix++) {
			name += "_";
		}
		
		do {
			int nameLength = name.length();
			ModelRoot modelRoot = modelRoots.get(name.substring(nameLength - modelOrder + 1));
			if (modelRoot == null) {
				System.out.println("Error, should have found: '" + name.substring(nameLength - modelOrder + 1, nameLength + 1) + '\'');
			}
			name += modelRoot.generateCharacter();
		} while (!"__".equals(name.substring(name.length() - modelOrder + 1)));
		
		return name.substring(2, name.length() - 2);
	}
	
}
