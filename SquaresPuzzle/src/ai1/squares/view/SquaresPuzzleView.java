package ai1.squares.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ai1.squares.model.Complexity;
import ai1.squares.model.SearchMethod;

public class SquaresPuzzleView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static String COMPLEXITY = "Complexity";
	private static String SEARCH_METHOD = "Search Method";

	/** Game display panel. */
	GamePanel gamePanel;
	
	/** Radio button selects easy puzzle. */
	private JRadioButton easyRadioButton = new JRadioButton(Complexity.EASY.getName());
	/** Radio button selects medium puzzle. */
	private JRadioButton mediumRadioButton = new JRadioButton(Complexity.MEDIUM.getName());
	/** Radio button selects hard puzzle. */
	private JRadioButton hardRadioButton = new JRadioButton(Complexity.HARD.getName());
	/** Radio button selects custom puzzle. */
	private JRadioButton customRadioButton = new JRadioButton(Complexity.CUSTOM.getName());
	/** Entry field for custom game layout. */
	private JTextField customTextField = new JTextField(9);

	/** Radio button selects breadth-first search. */
	private JRadioButton breadthRadioButton = new JRadioButton(SearchMethod.BREADTH.getName());
	/** Radio button selects depth-first search. */
	private JRadioButton depthRadioButton = new JRadioButton(SearchMethod.DEPTH.getName());
	/** Radio button selects Greedy minimize incorrect tiles search. */
	private JRadioButton greedyMinTilesWrongRadioButton = new JRadioButton(SearchMethod.GREEDY_MIN_TILES_WRONG.getName());
	/** Radio button selects A* minimize incorrect tiles search. */
	private JRadioButton astarMinTilesWrongRadioButton = new JRadioButton(SearchMethod.ASTAR_MIN_TILES_WRONG.getName());
	/** Radio button selects A* minimize distance search. */
	private JRadioButton astarMinDistanceRadioButton = new JRadioButton(SearchMethod.ASTAR_MIN_DISTANCE.getName());
	
	/** Start search button. */
	private JButton searchButton = new JButton("Search");
	
	public SquaresPuzzleView() {
		setLayout(new BorderLayout());
		buildUI();
    }
    
	/** Build the UI layout. */
    private void buildUI() {
        gamePanel = new GamePanel();
        
        JPanel complexityPanel = new JPanel();
        complexityPanel.setLayout(new BoxLayout(complexityPanel, BoxLayout.PAGE_AXIS));
        complexityPanel.setBorder(BorderFactory.createTitledBorder(COMPLEXITY));
        complexityPanel.add(easyRadioButton);
        complexityPanel.add(mediumRadioButton);
        complexityPanel.add(hardRadioButton);
        complexityPanel.add(customRadioButton);
        complexityPanel.add(customTextField);
        customTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, customTextField.getPreferredSize().height));
        ButtonGroup complexityButtonGroup = new ButtonGroup();
        complexityButtonGroup.add(easyRadioButton);
        complexityButtonGroup.add(mediumRadioButton);
        complexityButtonGroup.add(hardRadioButton);
        complexityButtonGroup.add(customRadioButton);

        JPanel searchMethodPanel = new JPanel();
        searchMethodPanel.setLayout(new BoxLayout(searchMethodPanel, BoxLayout.PAGE_AXIS));
        searchMethodPanel.setBorder(BorderFactory.createTitledBorder(SEARCH_METHOD));
        searchMethodPanel.add(breadthRadioButton);
        searchMethodPanel.add(depthRadioButton);
        searchMethodPanel.add(greedyMinTilesWrongRadioButton);
        searchMethodPanel.add(astarMinTilesWrongRadioButton);
        searchMethodPanel.add(astarMinDistanceRadioButton);
        ButtonGroup searchMethodButtonGroup = new ButtonGroup();
        searchMethodButtonGroup.add(breadthRadioButton);
        searchMethodButtonGroup.add(depthRadioButton);
        searchMethodButtonGroup.add(greedyMinTilesWrongRadioButton);
        searchMethodButtonGroup.add(astarMinTilesWrongRadioButton);
        searchMethodButtonGroup.add(astarMinDistanceRadioButton);

        JPanel optionPanel = new JPanel(new GridLayout(0, 1));
        optionPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 20, 20));
        optionPanel.add(complexityPanel);
        optionPanel.add(searchMethodPanel);
        
        searchButton.setPreferredSize(new Dimension(100, 30));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        buttonPanel.add(searchButton);
        
        add(gamePanel, BorderLayout.CENTER);
        add(optionPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    /** Accessor. */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

    /** Accessor. */
	public JRadioButton getEasyRadioButton() {
		return easyRadioButton;
	}

    /** Accessor. */
	public JRadioButton getMediumRadioButton() {
		return mediumRadioButton;
	}

    /** Accessor. */
	public JRadioButton getHardRadioButton() {
		return hardRadioButton;
	}

    /** Accessor. */
	public JRadioButton getCustomRadioButton() {
		return customRadioButton;
	}

    /** Accessor. */
	public JTextField getCustomTextField() {
		return customTextField;
	}

    /** Accessor. */
	public JRadioButton getBreadthRadioButton() {
		return breadthRadioButton;
	}

    /** Accessor. */
	public JRadioButton getDepthRadioButton() {
		return depthRadioButton;
	}

    /** Accessor. */
	public JButton getSearchButton() {
		return searchButton;
	}

    /** Accessor. */
	public JRadioButton getGreedyMinTilesWrongRadioButton() {
		return greedyMinTilesWrongRadioButton;
	}

    /** Accessor. */
	public JRadioButton getAstarMinTilesWrongRadioButton() {
		return astarMinTilesWrongRadioButton;
	}

    /** Accessor. */
	public JRadioButton getAstarMinDistanceRadioButton() {
		return astarMinDistanceRadioButton;
	}

}
