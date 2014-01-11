package ai1.squares.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ai1.squares.model.Complexity;
import ai1.squares.model.SearchMethod;
import ai1.squares.model.SquaresPuzzleModel;
import ai1.squares.view.SquaresPuzzleView;

public class SquaresPuzzleController {

	/** Puzzle model. */
	SquaresPuzzleModel model;
	
	/** Puzzle view. */
	SquaresPuzzleView view;

	public SquaresPuzzleController(SquaresPuzzleModel model, SquaresPuzzleView view) {
		this.model = model;
		this.view = view;
		
		// Tie view events to callbacks.
		initialize();
	}
	
	/** Initialize callbacks from view events. */
	private void initialize() {
		// Add complexity radio button action listeners.
		view.getEasyRadioButton().addActionListener(buildComplexityActionListener(Complexity.EASY));
		view.getMediumRadioButton().addActionListener(buildComplexityActionListener(Complexity.MEDIUM));
		view.getHardRadioButton().addActionListener(buildComplexityActionListener(Complexity.HARD));
		view.getCustomRadioButton().addActionListener(buildComplexityActionListener(Complexity.CUSTOM));
            
		// Add search method radio button action listeners.
		view.getBreadthRadioButton().addActionListener(buildSearchMethodActionListener(SearchMethod.BREADTH));
		view.getDepthRadioButton().addActionListener(buildSearchMethodActionListener(SearchMethod.DEPTH));
		
		// Disable Search button initially.
		view.getSearchButton().setEnabled(false);
	}
	
	/** Build action listeners for complexity radio buttons. */
	private ActionListener buildComplexityActionListener(final Complexity complexity) {
		return new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
            	model.setComplexity(complexity);
            }
		};
	}
	
	/** Build action listeners for search method radio buttons. */
	private ActionListener buildSearchMethodActionListener(final SearchMethod searchMethod) {
		return new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
            	model.setSearchMethod(searchMethod);
            }
		};
	}
	
}
