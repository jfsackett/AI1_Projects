package ai1.squares;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SquaresPuzzle extends JFrame {
	private static final long serialVersionUID = 1L;

	public SquaresPuzzle() {
        initUI();
    }
    
    private void initUI() {
        setTitle("Squares Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        Bin bin = new Bin(500, 400);
//        bin.addItem(new Item(100, 50), 0, 0);
//        bin.addItem(new Item(80, 60), 10, 50);
//        
//        add(new BPSurface(bin));
        
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
