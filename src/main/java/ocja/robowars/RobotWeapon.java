/**
 * Definition: contains templates of human weapon types and their power
 * 
 * @author Artjoms Porss, Jenn Quinn
 * Version No: 1.0
 * 
 */

package ocja.robowars;

public enum RobotWeapon {
	CLAWS(15F),
	LASER(45F),
	ROCKETLAUCHER(80F);
	
	private final float power;
	
	RobotWeapon(float power){
		this.power = power;
	}
	
	public float getPower(){
		return this.power;
	}
	
	public String getName(){
		String weaponName = "";
		switch(this){
			case CLAWS: weaponName =  "claws"; break;
			case LASER: weaponName =  "laser"; break;
			case ROCKETLAUCHER: weaponName =  "rocket launcher"; break;		
		}
		return weaponName;
	}
}
