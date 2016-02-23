package bugbattle.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bugbattle.bugs.MovingCreature;

/**
 * The World consists of a square grid whose edges wrap, so the bottom row is
 * considered adjacent to the top row and the left column adjacent to the right.
 * Cells on the grid may be empty ( Java null values) or occupied by subclasses
 * of Creature. The World is a Singleton, lazily instantiated in the
 * <code>getWorld</code> method.
 * 
 * @author [your names here]
 * @version [version number here]
 */
public class World {

	/**
	 * The width of the World, which is square.
	 */
	public static final int WORLD_WIDTH = 40;

	/**
	 * Total number of locations in the World.
	 */
	public static final int WORLD_SIZE = WORLD_WIDTH * WORLD_WIDTH;

	/**
	 * Number of creatures to be created on initialisation.
	 */
	public static final int N_CREATURES = 200;

	/**
	 * The maximum energy that a Creature will be fed in a turn.
	 * 
	 * @see #doTurn()
	 */
	public static final int MAX_FOOD_ENERGY = 200;

	/**
	 * The energy required to move a creature to an adjacent location.
	 */
	public static final int MOVE_ENERGY = 20;

	/**
	 * All the locations in the World.
	 */
	private List<Creature> locations;

	/**
	 * Stores the single instance of the World.
	 */
	private static World theWorld;

	/**
	 * Random generator.
	 */
	private static Random randGen = new Random();

	/**
	 * Initializes the World. Private as World is a Singleton.
	 * 
	 * @see #initialize()
	 */
	private World() {
		initialize();
	}

	/**
	 * First fills the World's locations with empty spaces, then inserts
	 * N_CREATURES worth of MovingCreatures into randomly-chosen locations in
	 * the World.
	 */
	private void initialize() {
		locations = new ArrayList<Creature>(WORLD_SIZE);
		for (int ix = 0; ix < WORLD_SIZE; ix++) {
			locations.add(new NullCreature());
		}
		for (int ix = 0; ix < N_CREATURES; ix++) {
			Creature c = new MovingCreature();
			int loc = randGen.nextInt(WORLD_SIZE);
			locations.set(loc, c);
		}
	}

	/**
	 * Lazily instantiates the World as necessary.
	 * 
	 * @return the single instance of the World
	 */
	static World getWorld() {
		if (theWorld == null) {
			theWorld = new World();
		}
		return theWorld;
	}

	/**
	 * Finds all the creatures in the World and feeds them each a random amount
	 * less than or equal to MAX_FOOD_ENERGY. After all have been fed, calls
	 * doTurn() exactly once on each live creature.
	 * 
	 */
	 void doTurn() {
		// Collect creatures into a separate list for calling doTurn.
		// Necessary to avoid calling doTurn() twice in a single turn
		// for creatures that move to a location not yet dealt with.
		List<Creature> creatures = new ArrayList<Creature>();
		for (Creature c : locations) {
			//TODO: REMOVE if (c != null) {
				c.feed(randGen.nextInt(MAX_FOOD_ENERGY + 1));
				creatures.add(c);
			//}
		}
		for (Creature c : creatures) {
			c.doTurn();
		}
	}

	/**
	 * @param c
	 *            the Creature to be found
	 * @return the index of the Creature's location in the locations list, -1 if
	 *         not found
	 */
	 int locationOf(Creature c) {
		return locations.indexOf(c);
	}

	/**
	 * Attempts to move the Creature to a new location with its x position
	 * changed by dx and y position by dy. Creature will only be moved if it is
	 * alive, currently has a valid location, and if dx and dy are in the range
	 * -1...1. If the creature has less than MOVE_ENERGY its strength is reduced
	 * to zero and it is not moved. If it has enough energy, its current
	 * location is set to null and it attacks the requested location.
	 * 
	 * @see #attack(Creature, int)
	 * 
	 * @param c
	 *            Creature to be moved.
	 * @param dx
	 *            requested change in x position
	 * @param dy
	 *            requested change in y position
	 */
	 void move(Creature c, int dx, int dy) {
		int newX, newY, loc, newLoc;
		loc = locationOf(c);
		if (c.isAlive() && loc != -1 && dx >= -1 && dx <= 1 && dy >= -1
				&& dy <= 1) {
/*			TODO :REMOVE 
 * 			if (c.getStrength() < MOVE_ENERGY) {
				// not enough energy to move, but I tried; damn
				c.expend(c.getStrength());
			} else {
				c.expend(MOVE_ENERGY);*/
				newX = wrapEdge(loc2x(loc) + dx);
				newY = wrapEdge(loc2y(loc) + dy);
				newLoc = xy2loc(newX, newY);
				//TODO: REMOVE locations.set(loc, null);
				locations.set(loc,new NullCreature());
				attack(c, newLoc);				
		//	}
		}
	}

	/**
	 * If there is no creature at the specified location, the attacker is placed
	 * there. If there is a creature, the strengths of the two are compared. The
	 * stronger of the two is fed with the strength of the weaker (i.e., the
	 * stronger eats the weaker). If the two have exactly the same strength, the
	 * defender is deemed to be stronger. The weaker creature dies. If the
	 * attacker won, it is moved to the attacked location.
	 * 
	 * @param attacker
	 *            the attacking creature
	 * @param loc
	 *            the location to be attacked
	 */
	private void attack(Creature attacker, int loc) {
		Creature defender = locations.get(loc);
		/*TODO: REMOVE if (defender == null) {
			locations.set(loc, attacker);
		} else {*/
		if (defender.getStrength() >= attacker.getStrength()) {
			defender.feed(attacker.getStrength());
			attacker.die();
		} else {
			attacker.feed(defender.getStrength());
			defender.die();
			locations.set(loc, attacker);
		}
		//}
	}

	/**
	 * @param loc
	 *            the location whose color is desired
	 * @return the color of the Creature at that location, null if no creature
	 *         is found
	 */
	 Color colorAt(int loc) {
		Creature c = locations.get(loc);
		if (c == null) {
			return null;
		} else {
			return c.getColor();
		}
	}

	/**
	 * @param loc
	 *            an index in the locations list
	 * @return the corresponding x (horizontal) coordinate
	 */
	 static int loc2x(int loc) {
		return loc % WORLD_WIDTH;
	}

	/**
	 * @param loc
	 *            an index in the locations list
	 * @return the corresponding y (vertical) coordinate
	 */
	 static int loc2y(int loc) {
		return loc / WORLD_WIDTH;
	}

	/**
	 * @param x
	 *            an x coordinate in the World
	 * @param y
	 *            a y coordinate in the World
	 * @return the index of the location corresponding to the x & y specified
	 */
	 static int xy2loc(int x, int y) {
		return y * WORLD_WIDTH + x;
	}

	/**
	 * Re-maps coordinates that are "off the edge of the World" to the other
	 * side. Coordinates that are "too high" map to zero; coordinates that are
	 * "too low" map to WORLD_WIDTH-1 (the maximum coordinate)
	 * 
	 * @param coord
	 *            an x or y coordinate in the World, which may be "off the edge"
	 * @return the re-mapped coordinate value
	 */
	 static int wrapEdge(int coord) {
		if (coord >= WORLD_WIDTH) {
			return 0;
		}
		if (coord < 0) {
			return WORLD_WIDTH - 1;
		}
		return coord;
	}

}
