package ai1.strips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Action {

	/** Name. */
	private String name;
	
	/** Argument list. */
	private List<Variable> variables;
	
	/** Precondition propositions. */
	private List<Proposition> preConditions;
	
	/** Remove propositions. */
	private List<Proposition> removePropositions;
	
	/** Add propositions. */
	private List<Proposition> addPropositions;
	
	/** Constructor. */
	public Action(String name, List<Variable> variables, List<Proposition> preConditions, 
					List<Proposition> removePropositions, List<Proposition> addPropositions) {
		this.name = name;
		this.variables = variables;
		this.preConditions = preConditions;
		this.removePropositions = removePropositions;
		this.addPropositions = addPropositions;
	}
	
	/** Take this action if preconditions met. */
	public List<KnowledgeBase> take(KnowledgeBase knowledgeBaseIn) {
		List<Map<String,Variable>> matchingInstanceBindings = matchPreconditions(knowledgeBaseIn);
		// Check for false match of preconditions.
		if (matchingInstanceBindings == null) {
			// None matched. Can not take action.
			return null;
		}
		
		List<KnowledgeBase> nextKnowledgeBaseStates = new ArrayList<KnowledgeBase>();
		// Check for true match with no bindings.
		if (matchingInstanceBindings.size() == 0) {
			KnowledgeBase knowledgeBase = new KnowledgeBase(knowledgeBaseIn, this);
			knowledgeBase.remove(this.removePropositions);
			knowledgeBase.add(this.addPropositions);
			nextKnowledgeBaseStates.add(knowledgeBase);
		}
		
		for (Map<String,Variable> matchingInstanceBinding : matchingInstanceBindings) {
			KnowledgeBase knowledgeBase = new KnowledgeBase(knowledgeBaseIn, this);
			List<Proposition> boundRemovePropositions = bindPropositions(this.removePropositions, matchingInstanceBinding);
			knowledgeBase.remove(boundRemovePropositions);
			List<Proposition> boundAddPropositions = bindPropositions(this.addPropositions, matchingInstanceBinding);
			knowledgeBase.add(boundAddPropositions);
			nextKnowledgeBaseStates.add(knowledgeBase);
		}
		
		return nextKnowledgeBaseStates;
	}
	
	/** Match variables that make preconditions true. */
	private List<Map<String,Variable>> matchPreconditions(KnowledgeBase knowledgeBaseIn) {
		List<List<Map<String,Variable>>> allPreconditionBindings = new ArrayList<List<Map<String,Variable>>>();
		for (Proposition preCondition : preConditions) {
			List<Map<String,Variable>> preconditionBindings = knowledgeBaseIn.bindPropositionVariables(preCondition);
			// Binding found in KB for this precondition?
			if (preconditionBindings == null) {
				// No, matches found in KB for this precondition so action may not be taken.
				return null;
			}
			if (preconditionBindings.isEmpty()) {
				// Precondition true but no bindings.
				continue;
			}
			// Add to full list of bindings.
			allPreconditionBindings.add(preconditionBindings);
		}
		
		if (allPreconditionBindings.isEmpty()) {
			// No bindings but preconditions true. Return empty list.
			return new ArrayList<Map<String,Variable>>();
		}
		
		// Find all logical variable bindings.
		List<Map<String,Variable>> matchingInstanceBindings = findMatchingInstanceBindings(new HashMap<String,Variable>(), allPreconditionBindings);
		if (matchingInstanceBindings == null || matchingInstanceBindings.isEmpty()) {
			// Variables unable to be logically bound. Action may not be taken.
			return null;
		}
		
		return matchingInstanceBindings;
	}
	
	private List<Map<String,Variable>> findMatchingInstanceBindings(Map<String,Variable> bindingInProgress, List<List<Map<String,Variable>>> remainingPreconditionBindings) {
		List<Map<String,Variable>> matchingInstanceBindings = new ArrayList<Map<String,Variable>>();
		if (remainingPreconditionBindings.size() > 0) {
			List<Map<String,Variable>> firstPreconditionBindings = remainingPreconditionBindings.get(0);
			for (Map<String,Variable> currentPreconditionBinding : firstPreconditionBindings) {
				boolean matchedBinding = true;
				for (String currentVariableBindingKey : currentPreconditionBinding.keySet()) {
					Variable currentVariableBinding = currentPreconditionBinding.get(currentVariableBindingKey);
					Variable tupleVariableBinding = bindingInProgress.get(currentVariableBindingKey);
					if (tupleVariableBinding == null) {
						// No binding yet for this variable, add it.
						bindingInProgress.put(currentVariableBindingKey, currentVariableBinding);
					}
					else {
						// Check that bound values match.
						if (!currentVariableBinding.getValue().equalsIgnoreCase(tupleVariableBinding.getValue())) {
							// No match, can't go further with this binding & previous ones.
							matchedBinding = false;
							break;
						}
					}
				}
				if (matchedBinding) {
					matchingInstanceBindings.addAll(findMatchingInstanceBindings(bindingInProgress, remainingPreconditionBindings.subList(1, remainingPreconditionBindings.size())));
				}
			}
		}
		else {
			// End state of recursion. Make sure all variables are matched for this tuple before adding it.
			for (Variable variable : variables) {
				// Is there a binding for this variable?
				if (bindingInProgress.get(variable.getName()) == null) {
					// Nope, return empty list.
					return matchingInstanceBindings;
				}
			}
			// Add completed binding.
			matchingInstanceBindings.add(bindingInProgress);
		}
		
		return matchingInstanceBindings;
	}
	
	/** Bind the add & remove propositions' variables with the instance bindings. */
	private static List<Proposition> bindPropositions(List<Proposition> propositions, Map<String,Variable> matchingInstanceBindings) {
		List<Proposition> boundPropositions = new ArrayList<Proposition>();
		for (Proposition proposition : propositions) {
			Proposition boundProposition = new Proposition(proposition);
			for (Variable variable : boundProposition.getVariables()) {
				String variableName = variable.getName();
				if ( variableName == null) {
					// Can only bind variables with name. 
					continue;
				}
				Variable matchingInstanceBinding = matchingInstanceBindings.get(variableName);
				if (matchingInstanceBinding == null) {
					System.out.println("Variable Binding Error. No matching instance binding.");
					continue;
				}
				// Bind the proposition's variable.
				variable.setValue(matchingInstanceBinding.getValue());
			}
		}
		
		return boundPropositions;
	}

	@Override
	public String toString() {
		return "" + name;
	}
	
	
}
