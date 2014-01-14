package ai1.squares.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ai1.squares.model.Complexity;
import ai1.squares.model.SearchMethod;
import ai1.squares.model.SquaresPuzzleModel;
import ai1.squares.model.search.SearchResult;
import ai1.squares.view.SquaresPuzzleView;

public class SquaresPuzzleController implements PropertyChangeListener {

	/** Puzzle model. */
	SquaresPuzzleModel model;
	
	/** Puzzle view. */
	SquaresPuzzleView view;

	/** Constructor. */
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
		// Disable and hide Custom puzzle field initially.
		view.getCustomTextField().setEnabled(false);
		view.getCustomTextField().setVisible(false);
		view.getCustomTextField().getDocument().addDocumentListener(buildCustomDocumentListener());
            
		// Add search method radio button action listeners.
		view.getBreadthRadioButton().addActionListener(buildSearchMethodActionListener(SearchMethod.BREADTH));
		view.getDepthRadioButton().addActionListener(buildSearchMethodActionListener(SearchMethod.DEPTH));
		view.getGreedyMinTilesWrongRadioButton().addActionListener(buildSearchMethodActionListener(SearchMethod.GREEDY_MIN_TILES_WRONG));
		view.getAstarMinTilesWrongRadioButton().addActionListener(buildSearchMethodActionListener(SearchMethod.ASTAR_MIN_TILES_WRONG));
		view.getAstarMinDistanceRadioButton().addActionListener(buildSearchMethodActionListener(SearchMethod.ASTAR_MIN_DISTANCE));
		
		// Disable Search button initially.
		view.getSearchButton().setEnabled(false);
		// Add search button listener.
		view.getSearchButton().addActionListener(buildSearchButtonActionListener());
		
		// Register this controller as model event listener.
		model.addPropertyChangeListener(this);
	}
	
	/** Build action listeners for complexity radio buttons. */
	private ActionListener buildComplexityActionListener(final Complexity complexity) {
		return new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
            	if (model.getComplexity() == null || !event.getActionCommand().equals(model.getComplexity().getName())) {            		
            		model.setComplexity(complexity);
            	}
            }
		};
	}
	
	/** Build action listeners for search method radio buttons. */
	private ActionListener buildSearchMethodActionListener(final SearchMethod searchMethod) {
		return new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
            	if (model.getSearchMethod() == null || !event.getActionCommand().equals(model.getSearchMethod().getName())) {            		
            		model.setSearchMethod(searchMethod);
            	}
            }
		};
	}
	
	/** Builds document listener for changes to custom puzzle field. */
	private DocumentListener buildCustomDocumentListener() {
		return new DocumentListener() { 
            @Override
            public void insertUpdate(DocumentEvent event) {
            	checkUpdatePuzzleState();
            }
            @Override
            public void removeUpdate(DocumentEvent event) {
            	checkUpdatePuzzleState();
            }
            @Override
            public void changedUpdate(DocumentEvent event) {
            	checkUpdatePuzzleState();
            }
            private void checkUpdatePuzzleState() {
            	if(model.isValidPuzzle(view.getCustomTextField().getText())) {
            		model.setPuzzle(view.getCustomTextField().getText());
            	}
            	else {
            		view.getSearchButton().setEnabled(false);
            	}
            }
		};
	}

	/** Builds action listener for search button. */
	private ActionListener buildSearchButtonActionListener() {
		return new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
            	view.getGamePanel().setStatus("Searching...");
            	view.getGamePanel().repaint();
            	//TODO Run as background thread.
            	SearchResult searchResult = model.search();
            	view.getGamePanel().setStatus(searchResult.toString());
            	view.getGamePanel().repaint();
            }
		};
	}
	
	/** Handles property change events from model. */
	public void propertyChange(PropertyChangeEvent event) {
        String propertyName = event.getPropertyName();

        if (propertyName.equalsIgnoreCase(SquaresPuzzleModel.COMPLEXITY)) {
    		view.getCustomTextField().setEnabled(model.getComplexity() == Complexity.CUSTOM);
    		view.getCustomTextField().setVisible(model.getComplexity() == Complexity.CUSTOM);
        	// Change to Custom complexity? 
    		if ((Complexity)event.getNewValue() == Complexity.CUSTOM) {
    			// Default puzzle.
    			model.setPuzzle(SquaresPuzzleModel.GOAL_PUZZLE);
    			view.getCustomTextField().setText(SquaresPuzzleModel.GOAL_PUZZLE);
    			view.getCustomTextField().requestFocus();
    			view.getCustomTextField().selectAll();
    		}
        }
        else if(propertyName.equalsIgnoreCase(SquaresPuzzleModel.PUZZLE)) {
        	view.getGamePanel().setPuzzle(model.getPuzzle());
        }
    	view.getSearchButton().setEnabled(model.isValidPuzzle() && model.getSearchMethod() != null);
        
    	view.getGamePanel().repaint();
        view.revalidate();
    }
}
