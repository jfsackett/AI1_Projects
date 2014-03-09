import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ai1.markov.NamesMarkovModel;

public class Controller {

	public static void main(String[] args) {
		BufferedReader reader = null;
		boolean maleName = false;
		int minNameLength = 5;
		int maxNameLength = 12;
		int modelOrder = 3;
		int numNames = 50;
		List<String> inputNames = new ArrayList<String>();
		try {
//			reader = new BufferedReader(new InputStreamReader(System.in));
//			
//			// Male or female name.
//			System.out.println("Generate (M)ale or (F)emale name?");
//			String input = reader.readLine();
//			if (input != null && input.length() > 0) {
//				switch (input.charAt(0)) {
//				case 'm':
//				case 'M':
//					System.out.println("Male name.");
//					maleName = true;
//					break;
//				case 'f':
//				case 'F':
//					System.out.println("Female name.");
//					maleName = false;
//					break;
//				default:
//					System.out.println("Defaulting to Female name.");
//					maleName = false;
//					break;
//				}
//			}
//			else {
//				// No input, exit.
//				System.exit(1);
//			}
//			
//			// Minimum name length.
//			System.out.println("Minimum name length?");
//			input = reader.readLine();
//			if (input != null && input.length() > 0) {
//				try  {
//					minNameLength = Integer.parseInt(input);
//				} catch (NumberFormatException ex) {
//					System.out.println("Minimum name length defaulting to 4.");
//					minNameLength = 4;
//				}
//			}
//			else {
//				// No input, exit.
//				System.exit(1);
//			}
//			
//			// Maximum name length.
//			System.out.println("Maximum name length?");
//			input = reader.readLine();
//			if (input != null && input.length() > 0) {
//				try  {
//					maxNameLength = Integer.parseInt(input);
//				} catch (NumberFormatException ex) {
//					System.out.println("Maximum name length defaulting to 8.");
//					maxNameLength = 8;
//				}
//			}
//			else {
//				// No input, exit.
//				System.exit(1);
//			}
//			
//			// Order of model.
//			System.out.println("Order of model?");
//			input = reader.readLine();
//			if (input != null && input.length() > 0) {
//				try  {
//					modelOrder = Integer.parseInt(input);
//				} catch (NumberFormatException ex) {
//					System.out.println("Model order defaulting to 3.");
//					modelOrder = 3;
//				}
//			}
//			else {
//				// No input, exit.
//				System.exit(1);
//			}
//			
//			// Number of names.
//			System.out.println("Number of names to generate?");
//			input = reader.readLine();
//			if (input != null && input.length() > 0) {
//				try  {
//					numNames = Integer.parseInt(input);
//				} catch (NumberFormatException ex) {
//					System.out.println("Number of names defaulting to 50.");
//					numNames = 50;
//				}
//			}
//			else {
//				// No input, exit.
//				System.exit(1);
//			}
//			
			String input;
			// Open names' file into buffered reader.
			reader = new BufferedReader(new FileReader((maleName) ? "namesBoys.txt" : "namesGirls.txt"));
			
			// Loop through & read the names, store in list.
			inputNames = new ArrayList<String>();
			while ((input = reader.readLine()) != null && input.trim().length() != 0) {
				inputNames.add(input.trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (IOException e) {}
			}
		}

		NamesMarkovModel namesMarkovModel = new NamesMarkovModel(modelOrder, inputNames);
		List<String> generatedNames = namesMarkovModel.generateNames(numNames, minNameLength, maxNameLength);
		for (String name : generatedNames) {
			System.out.println(name);
		}
	}

}
