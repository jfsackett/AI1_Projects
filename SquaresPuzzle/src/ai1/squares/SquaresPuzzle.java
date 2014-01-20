package ai1.squares;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ai1.squares.controller.SquaresPuzzleController;
import ai1.squares.model.SquaresPuzzleModel;
import ai1.squares.view.SquaresPuzzleView;

public class SquaresPuzzle extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// GUI display constants.
	private static String TITLE = "8 Squares Puzzle";
	private static int WIDTH = 650;
	private static int HEIGHT = 500;
	
	/** MVC model. */
	private SquaresPuzzleModel model;

	/** MVC view. */
	private SquaresPuzzleView view;

	/** MVC controller. */
	private SquaresPuzzleController controller;

	/** Constructor. */
	public SquaresPuzzle() {
		model = new SquaresPuzzleModel();
		view = new SquaresPuzzleView();
		controller = new SquaresPuzzleController(model, view);
		
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		add(view);
        
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }
	
	/** Main program. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	SquaresPuzzle squaresPuzzle = new SquaresPuzzle();
            	squaresPuzzle.setVisible(true);
            }
        });
    }

    /** Accessor. */
	public SquaresPuzzleController getController() {
		return controller;
	}
    
}
