/**
 * Definition: Is a parent class for Human and Robot class
 * 
 * @author Artjoms Porss, Jenn Quinn
 * Version No: 1.0
 * 
 */

package ocja.robowars;

public class Lifeform {
	
	protected String ID;
	protected float power;
	protected float life = 100;
	protected float armour = 40;
	
	
	public float getDefense() {
		return armour * (power / 100);
	}


//	public void setArmour(float armour) {
//		this.armour = armour;
//	}


	public void setPower(float power) {
		this.power = power;
	}


	public Lifeform(String ID, float power) {
		this.ID = ID;
		this.power = power;
	}

	public String getID() {
		return this.ID;
	}

	public float getPower() {
		return this.power;
	}
	
	public String toString(){
		return this.getID() + "'s power is " + this.getPower();
	}
	
	public float getLife(){
		return this.life;
	}
	
	public void setLife(float life){
		this.life = life;
	}
}
