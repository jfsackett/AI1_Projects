package ai1.squares.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ai1.squares.model.Complexity;
import ai1.squares.model.SearchMethod;
import ai1.squares.model.SquaresPuzzleModel;
import ai1.squares.view.SquaresPuzzleView;

public class SquaresPuzzleController implements PropertyChangeListener {

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
		// Add search button listener.
		view.getSearchButton().addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
            	model.search();
            }
		});
		
		// Register this controller as model event listener.
		model.addPropertyChangeListener(this);
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
	
	/** Handles property change events from model. */
	public void propertyChange(PropertyChangeEvent event) {
        String propertyName = event.getPropertyName();
//        Object newValue = getComplexity.getNewValue();

        if (propertyName.equalsIgnoreCase(SquaresPuzzleModel.COMPLEXITY) || propertyName.equalsIgnoreCase(SquaresPuzzleModel.SEARCH_METHOD)) {
        	view.getSearchButton().setEnabled(model.getComplexity() != null && model.getSearchMethod() != null);
        }
    }
}
