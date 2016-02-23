package bugbattle.core;

public class Organ {
	protected int CREATION_COST;
	protected int MAINTENANCE_COST;
	protected int USE_COST;
	protected Creature host;
	protected static boolean grown=false;
	
	public Organ() {
		
	}
	/**
	 * Checks if the host has enough energy to complete action
	 * and to reduce cheating reduces hosts energy.
	 * @return boolean value if it can be used
	 */
	public final boolean canUse(){
		maintain();
		//check if grown if not try to grow
		if(grown){	
			if(host.getStrength()>USE_COST){
				host.expend(USE_COST);
				return true;
			}
			else{
				host.expend(host.getStrength());
			}
		}
		else{			
			canGrow(host, this);
		}	
		return false;
	}
	
	/**
	 * canGrow 
	 * Grows an organ if the host creature has enough energy to grow it
	 * @param host - owner of organ
	 * @param g - current organ
	 * @return - true if organ can be grown
	 * 			 false if organ cannot be grown
	 */
	public static final void canGrow(Creature host, Organ g){
		if(host.getStrength()>g.CREATION_COST){
			host.expend(g.CREATION_COST);
			grown = true;
		}
	}
	

	/**
	 * maintain
	 * charges energy cost of maintaining the organ to host
	 * or kills the organ
	 */
	private final void maintain(){
		if(host.getStrength()>MAINTENANCE_COST){
			host.expend(MAINTENANCE_COST);
		}
		else{
			kill();
		}
	}
	/**
	 * Kill
	 * kills the organ.
	 */
	private final void kill() {
		grown=false;		
	}

}
