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
	String game = "123804765";

    public GamePanel() { //Bin bin) {
		super();
//		this.bin = bin;
	}

    /** Graphically render the bin and packed items. */
	private void doDrawing(Graphics graphics) {
		if (game.length() != 9) {
			return;
		}
		
        Graphics2D graphics2d = (Graphics2D) graphics;
        
//    	BPGraphicsRenderer bpGraphicsRenderer = new BPGraphicsRenderer(graphics2d, 50, 50, this.getHeight());
//
//    	bpGraphicsRenderer.visit(bin);
        
        graphics2d.setColor(Color.black);
        graphics2d.setFont(new Font("Sans Serif", Font.BOLD, 36));
        
        for (int ix = 0; ix < game.length(); ix++) {
        	int xCoord = ix % 3 * 100 + 30;
        	int yCoord = ix / 3 * 100 + 30;
            graphics2d.drawRect(xCoord, yCoord, 100, 100);
            if (game.charAt(ix) != '0') {
            	graphics2d.drawString("" + game.charAt(ix), xCoord + 40, yCoord + 62);
            }
        }

    } 

	/** Paint the component. */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }    
}
