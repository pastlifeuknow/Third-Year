/**
 * 
 */
package bugbattle.core;

import java.util.Random;


/**
 * @author s27106
 *
 */
public class Cilia extends Organ {	
	
	private Random randGen = new Random();

	public Cilia(Creature h) {
		host=h;
		CREATION_COST=100;
		MAINTENANCE_COST=10;
		USE_COST=20;
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
	public void moveBy(int dx, int dy) {
		if((randGen.nextInt(2) == 1) && canUse()){
			World.getWorld().move(host,dx, dy);
		}
	}

}
