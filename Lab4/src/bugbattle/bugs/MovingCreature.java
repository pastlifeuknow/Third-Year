package bugbattle.bugs;

import java.awt.Color;
import java.util.Random;

import bugbattle.core.Cilia;
import bugbattle.core.Creature;
import bugbattle.core.Organ;

/**
 * A blue creature that may choose to move in the World. Always moves in the
 * same direction. Quite a stupid creature, actually.
 * 
 * @author [your names here]
 * @version [version number here]
 */
public class MovingCreature extends Creature {
	private Cilia myMover= new Cilia(this);
	/**
	 * The desired x movement. Either -1, 0 or 1.
	 */
	private int xDir;

	/**
	 * The desired y movement. Either -1, 0 or 1.
	 */
	private int yDir;

	/**
	 * A random source, used in <code>doTurn</code>
	 */
	private Random randGen = new Random();

	/**
	 * Sets the color to blue and randomly chooses desired x and y directions.
	 */
	public MovingCreature() {
		Organ.canGrow(this, myMover);
		color = Color.blue;
		xDir = randGen.nextInt(3) - 1;
		yDir = randGen.nextInt(3) - 1;
	}

	/**
	 * If it has enough strength to move, has a 50% chance of trying to move in
	 * its desired direction.
	 * 
	 * @see bugbattle.core.Creature#moveBy(int, int)
	 * @see bugbattle.core.Creature#doTurn()
	 */
	@Override
	public void doTurn() {
		myMover.moveBy(xDir, yDir);

	}

}
