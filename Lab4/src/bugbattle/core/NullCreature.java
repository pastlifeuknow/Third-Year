/**
 * 
 */
package bugbattle.core;

import java.awt.Color;

/**
 * @author s27106
 *
 */
/**
 * @author s27106
 *
 */
public class NullCreature extends Creature {

	public NullCreature() {
		color=new Color(10,10,10,0);
		super.expend(START_STRENGTH);
	}
	/* (non-Javadoc)
	 * @see bugbattle.core.Creature#die()
	 */
	void die(){}
	/* (non-Javadoc)
	 * @see bugbattle.core.Creature#feed(int)
	 */
	void feed(int foodEnergy){}
	/* (non-Javadoc)
	 * @see bugbattle.core.Creature#expend(int)
	 */
	void expend(int energy){}
	
	/* (non-Javadoc)
	 * @see bugbattle.core.Creature#doTurn()
	 */
	@Override
	public void doTurn() {

	}

}
