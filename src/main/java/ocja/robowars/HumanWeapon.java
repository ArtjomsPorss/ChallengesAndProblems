/**
 * Definition: contains templates of human weapon types and their power
 * 
 * @author Artjoms Porss, Jenn Quinn
 * Version No: 1.0
 * 
 */

package ocja.robowars;

public enum HumanWeapon {
	PITCHFORK(15F),
	MACHINEGUN(45F),
	BOMB(80F);
	
	private final float power;
	
	HumanWeapon(float power){
		this.power = power;
	}
	
	public float getPower(){
		return this.power;
	}
	
	public String getName(){
		String weaponName = "";
		switch(this){
			case PITCHFORK: weaponName =  "pitchfork"; break;
			case MACHINEGUN: weaponName =  "machinegun"; break;
			case BOMB: weaponName =  "bomb"; break;		
		}
		return weaponName;
	}
}
