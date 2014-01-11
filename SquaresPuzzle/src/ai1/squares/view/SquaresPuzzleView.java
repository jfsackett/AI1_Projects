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

import ai1.squares.model.Complexity;
import ai1.squares.model.SearchMethod;

public class SquaresPuzzleView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static String COMPLEXITY = "Complexity";
//	private static String EASY = "Easy";
//	private static String MEDIUM = "Medium";
//	private static String HARD = "Hard";
//	private static String CUSTOM = "Custom";
	private static String SEARCH_METHOD = "Search Method";
//	private static String BREADTH = "Breadth First";
//	private static String DEPTH = "Depth First";

	/** Radio button selects easy puzzle. */
	private JRadioButton easyRadioButton = new JRadioButton(Complexity.EASY.getName());
	/** Radio button selects medium puzzle. */
	private JRadioButton mediumRadioButton = new JRadioButton(Complexity.MEDIUM.getName());
	/** Radio button selects hard puzzle. */
	private JRadioButton hardRadioButton = new JRadioButton(Complexity.HARD.getName());
	/** Radio button selects custom puzzle. */
	private JRadioButton customRadioButton = new JRadioButton(Complexity.CUSTOM.getName());

	/** Radio button selects breadth-first search. */
	private JRadioButton breadthRadioButton = new JRadioButton(SearchMethod.BREADTH.getName());
	/** Radio button selects depth-first search. */
	private JRadioButton depthRadioButton = new JRadioButton(SearchMethod.DEPTH.getName());
	
	/** Start search button. */
	private JButton searchButton = new JButton("Search");
	
	public SquaresPuzzleView() {
		setLayout(new BorderLayout());
		buildUI();
    }
    
	/** Build the UI layout. */
    private void buildUI() {        
        JPanel gamePanel = new GamePanel();
        
        JPanel complexityPanel = new JPanel();
        complexityPanel.setLayout(new BoxLayout(complexityPanel, BoxLayout.Y_AXIS));
        complexityPanel.setBorder(BorderFactory.createTitledBorder(COMPLEXITY));
        complexityPanel.add(easyRadioButton);
        complexityPanel.add(mediumRadioButton);
        complexityPanel.add(hardRadioButton);
        complexityPanel.add(customRadioButton);
        ButtonGroup complexityButtonGroup = new ButtonGroup();
        complexityButtonGroup.add(easyRadioButton);
        complexityButtonGroup.add(mediumRadioButton);
        complexityButtonGroup.add(hardRadioButton);
        complexityButtonGroup.add(customRadioButton);

        JPanel searchMethodPanel = new JPanel();
        searchMethodPanel.setLayout(new BoxLayout(searchMethodPanel, BoxLayout.Y_AXIS));
        searchMethodPanel.setBorder(BorderFactory.createTitledBorder(SEARCH_METHOD));
        searchMethodPanel.add(breadthRadioButton);
        searchMethodPanel.add(depthRadioButton);
        ButtonGroup searchMethodButtonGroup = new ButtonGroup();
        searchMethodButtonGroup.add(breadthRadioButton);
        searchMethodButtonGroup.add(depthRadioButton);

        JPanel optionPanel = new JPanel(new GridLayout(0, 1));
//        optionPane.setLayout(new BoxLayout(optionPane, BoxLayout.Y_AXIS));
        optionPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 20, 20));
//        optionPane.add(Box.createHorizontalGlue());
        optionPanel.add(complexityPanel);
//        optionPane.add(Box.createRigidArea(new Dimension(10, 0)));
        optionPanel.add(searchMethodPanel);
        
        searchButton.setPreferredSize(new Dimension(100, 30));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        buttonPanel.add(searchButton);
        
        add(gamePanel, BorderLayout.CENTER);
        add(optionPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

	public JRadioButton getEasyRadioButton() {
		return easyRadioButton;
	}

	public JRadioButton getMediumRadioButton() {
		return mediumRadioButton;
	}

	public JRadioButton getHardRadioButton() {
		return hardRadioButton;
	}

	public JRadioButton getCustomRadioButton() {
		return customRadioButton;
	}

	public JRadioButton getBreadthRadioButton() {
		return breadthRadioButton;
	}

	public JRadioButton getDepthRadioButton() {
		return depthRadioButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

}
