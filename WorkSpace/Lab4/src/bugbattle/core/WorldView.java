package bugbattle.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * Produces a view of the World. Locations are rendered as circles, with colours
 * based on the reported colour of the location in the World.
 * <strong>Important:</strong> The <code>paintComponent</code> method assumes
 * that the WorldView is square. Non-square WorldViews will either have runtime
 * errors or will result in some locations not being shown.
 * 
 * @author [your names here]
 * @version [version number here]
 */
public class WorldView extends JPanel {

	/**
	 * Support for object serialization, which we aren't doing (yet).
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Uses Java2D to render a view of the World. See class comment.
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		double x, y;
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));
		double locSize = (getSize().width / (double) World.WORLD_WIDTH);
		double creatureSize = locSize - 1;
		for (int loc = 0; loc < World.WORLD_SIZE; loc++) {
			Color color = World.getWorld().colorAt(loc);
			if (color != null) {
				x = World.loc2x(loc) * locSize + 0.5;
				y = World.loc2y(loc) * locSize + 0.5;
				g2.setPaint(color);
				g2.fill(new Ellipse2D.Double(x, y, creatureSize, creatureSize));

			}
		}
	}
}
