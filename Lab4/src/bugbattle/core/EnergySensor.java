/**
 * 
 */
package bugbattle.core;


/**
 * @author s27106
 *
 */
public class EnergySensor extends Organ {
	/**
	 * 
	 */
	public EnergySensor(Creature h) {
		host=h;
		CREATION_COST = 100;
		MAINTENANCE_COST = 10;
		USE_COST = 2;
	}
	public int sense(int dx, int dy){
		if(canUse()){
			//int currentLoc=((World) World.getWorld()).locationOf(host);
			
			return 1;
			
		}else{
			return -1;
		}
	}
}
