package ai1.squares;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ai1.squares.controller.SquaresPuzzleController;
import ai1.squares.model.SquaresPuzzleModel;
import ai1.squares.view.SquaresPuzzleView;

public class SquaresPuzzle extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private SquaresPuzzleModel model;

	private SquaresPuzzleView view;

	private SquaresPuzzleController controller;

	public SquaresPuzzle() {
		model = new SquaresPuzzleModel();
		view = new SquaresPuzzleView();
		controller = new SquaresPuzzleController(model, view);
		
        setTitle("8 Squares Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		add(view);
        
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

	public SquaresPuzzleController getController() {
		return controller;
	}
    
}
