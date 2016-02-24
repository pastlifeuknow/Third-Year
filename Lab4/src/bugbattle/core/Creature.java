package bugbattle.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The abstract superclass of all creatures. Subclasses <strong>must</strong>
 * call super() in their constructor to receive their initial allocation of
 * energy.
 * 
 * @author [your names here]
 * @version [version number here]
 */
public abstract class Creature {

	public List<Organ> organs = new ArrayList<Organ>();
	/**
	 * The creature's initial strength on creation.
	 */
	public static final int START_STRENGTH = 1500;

	/**
	 * The strength required to keep a creature alive for one turn.
	 */
	public static final int MAINTENANCE_COST = 100;

	/**
	 * The creature's color. Should be set in the subclass constructor.
	 */
	protected Color color = Color.black;

	/**
	 * The creature's current strength.
	 */
	private int strength = START_STRENGTH;

	/**
	 * Records whether or not the creature is alive.
	 */
	private boolean alive = true;

	/**
	 * If the creature is alive, adds foodEnergy to the creature's strength
	 * 
	 * @param foodEnergy
	 *            the amount of food given to the creature
	 */
	void feed(int foodEnergy) {
		if (isAlive()) {
			strength = strength + foodEnergy;
		}
	}

	/**
	 * Subtracts energy from the creature's strength; if strength goes below
	 * zero, kills the creature by calling die()
	 * 
	 * @param energy
	 *            the energy expended
	 */
	void expend(int energy) {
		strength = strength - energy;
		if (strength < 0) {
			die();
		}
	}

	/**
	 * Sets alive to false and strength to zero.
	 */
	void die() {
		alive = false;
		strength = 0;
	}

	/**
	 * @return if the creature is alive, its defined colour, otherwise, black
	 */
	final Color getColor() {
		if (isAlive()) {
			return color;
		} else {
			return Color.black;
		}
	}

	/**
	 * Asks the World to move the creature in the desired direction.
	 * 
	 * @see World#move(Creature, int, int)
	 * @param dx
	 *            the requested change in x coordinates
	 * @param dy
	 *            the requested change in y coordinates
	 */
	protected final void moveBy(int dx, int dy) {
		World.getWorld().move(this, dx, dy);
	}

	/**
	 * @return the creature's current strength
	 */
	protected final int getStrength() {
		return strength;
	}

	/**
	 * @return is the creature alive?
	 */
	protected final boolean isAlive() {
		return alive;
	}

	/**
	 * Subclasses must override to define desired behaviour on each simulation
	 * turn.
	 */
	public abstract void doTurn();

}
