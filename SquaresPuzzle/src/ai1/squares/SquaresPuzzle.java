package ai1.squares;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class SquaresPuzzle extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static String COMPLEXITY = "Complexity";
	private static String EASY = "Easy";
	private static String MEDIUM = "Medium";
	private static String HARD = "Hard";
	private static String NEW = "New";
	private static String SEARCH_METHOD = "Search Method";
	private static String BREADTH = "Breadth First";
	private static String DEPTH = "Depth First";

	public SquaresPuzzle() {
        initUI();
    }
    
    private void initUI() {
        setTitle("Squares Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel gamePanel = new GamePanel();
        
        JRadioButton easyRadioButton = new JRadioButton(EASY);
//      easyButton.setActionCommand(pigString);
        JRadioButton mediumRadioButton = new JRadioButton(MEDIUM);
        JRadioButton hardRadioButton = new JRadioButton(HARD);
        JRadioButton newRadioButton = new JRadioButton(NEW);

        JPanel complexityPanel = new JPanel();
        complexityPanel.setLayout(new BoxLayout(complexityPanel, BoxLayout.Y_AXIS));
        complexityPanel.setBorder(BorderFactory.createTitledBorder(COMPLEXITY));
        complexityPanel.add(easyRadioButton);
        complexityPanel.add(mediumRadioButton);
        complexityPanel.add(hardRadioButton);
        complexityPanel.add(newRadioButton);
        ButtonGroup complexityButtonGroup = new ButtonGroup();
        complexityButtonGroup.add(easyRadioButton);
        complexityButtonGroup.add(mediumRadioButton);
        complexityButtonGroup.add(hardRadioButton);
        complexityButtonGroup.add(newRadioButton);

        JRadioButton breadthRadioButton = new JRadioButton(BREADTH);
        JRadioButton depthRadioButton = new JRadioButton(DEPTH);

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
        
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 30));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        buttonPanel.add(startButton);
        
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        getContentPane().add(optionPanel, BorderLayout.EAST);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        
        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	SquaresPuzzle squaresPuzzle = new SquaresPuzzle();
            	squaresPuzzle.setVisible(true);
            }
        });
    }
}
