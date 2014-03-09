package ai1.markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Variable order Markov model for generating names based on input set. */
public class NamesMarkovModel {
	/** Order of the Markov model. */
	private int modelOrder; 
	
	/** Input names. */
	private List<String> inputNames;
	
	/** Map of name roots & probability tables. */
	private Map<String, ModelRoot> modelRoots;

	/** Constructor. */
	public NamesMarkovModel(int modelOrder, List<String> inputNames) {
		this.modelOrder = modelOrder;
		this.inputNames = inputNames;
		buildModel(modelOrder, inputNames);
	}
	
	/** Build the model by parsing the input names into roots and counting the characters following each. */
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
		for (ModelRoot modelRoot : modelRoots.values()) {
			modelRoot.calculateProbabilties();
		}
	}
	
	/** Make the name lower case & bound with dashes _ */ 
	private static String normalizeName(String inputName, int modelOrder) {
		StringBuilder name = new StringBuilder(inputName.toLowerCase());
		for (int ix = 0; ix < modelOrder - 1; ix++) {
			name.insert(0, '_');
			name.append('_');
		}
		
		return name.toString();
	}
	
	/** Main method to generate the names from this model. */
	public List<String> generateNames(int numNames, int minNameLength, int maxNameLength) {
		List<String> names = new ArrayList<String>();
		while (names.size() < numNames) {
			String name = generateName();
			if (name.length() < minNameLength || name.length() > maxNameLength) {
				// Name too short or long.
				continue;
			}
			if (inputNames.contains(name)) {
				// Name in input set, not unique.
				continue;
			}
			names.add(name);
		}
		
		return names;
	}
	
	/** Generate a single name from the model. */
	private String generateName() {
		String name = new String();
		String suffix = new String();
		for (int ix = 0; ix < modelOrder - 1; ix++) {
			name += "_";
			suffix += "_";
		}
		
		do {
			int nameLength = name.length();
			ModelRoot modelRoot = modelRoots.get(name.substring(nameLength - modelOrder + 1));
			if (modelRoot == null) {
				System.out.println("Error, should have found: '" + name.substring(nameLength - modelOrder + 1, nameLength + 1) + '\'');
			}
			name += modelRoot.generateCharacter();
		} while (!suffix.equals(name.substring(name.length() - modelOrder + 1)));
		
		return name.substring(modelOrder - 1, modelOrder).toUpperCase() + name.substring(modelOrder, name.length() - modelOrder + 1);
	}
	
}
