/**
 * 
 */
package bugbattle.bugs;

import java.awt.Color;

//import bugbattle.core.Cilia;
import bugbattle.core.Creature;
import bugbattle.core.EnergySensor;
import bugbattle.core.Organ;

/**
 * @author s27106
 *
 */
public class ObservantCreature extends Creature {
	private Organ mySensor=new EnergySensor(this);
	private int dx,dy;
	public ObservantCreature() {
		color=Color.RED;
		Organ.canGrow(this,mySensor);
		
	}

	/* (non-Javadoc)
	 * @see bugbattle.core.Creature#doTurn()
	 */
	@Override
	public void doTurn() {
		((EnergySensor)mySensor).sense(dx,dy);
	}

}
