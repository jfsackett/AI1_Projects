package ai1.squares.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * This class is responsible for rendering the game state to the graphics context.
 * @author Joseph Sackett
 */
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/** The game to be rendered. */
	String puzzle = "123804765";
	
	/** The Status text to display. */
	String status = "";

    public GamePanel() {
		super();
	}

    /** Graphically render the bin and packed items. */
	private void doDrawing(Graphics graphics) {
		if (puzzle.length() != 9) {
			return;
		}
		
        Graphics2D graphics2d = (Graphics2D) graphics;
        
        graphics2d.setColor(Color.black);
        graphics2d.setFont(new Font("Sans Serif", Font.BOLD, 36));
        
        for (int ix = 0; ix < puzzle.length(); ix++) {
        	int xCoord = ix % 3 * 100 + 30;
        	int yCoord = ix / 3 * 100 + 30;
            graphics2d.drawRect(xCoord, yCoord, 100, 100);
            if (puzzle.charAt(ix) != '0') {
            	graphics2d.drawString("" + puzzle.charAt(ix), xCoord + 40, yCoord + 62);
            }
        }

        graphics2d.setFont(new Font("Sans Serif", Font.PLAIN, 18));
    	graphics2d.drawString(status, 30, 370);
    } 

	/** Paint the component. */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

	public String getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(String puzzle) {
		this.puzzle = puzzle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}    
}
