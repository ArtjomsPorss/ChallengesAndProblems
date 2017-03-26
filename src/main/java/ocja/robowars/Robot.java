/**
 * Definition: Template for robot objects
 * 
 * @author Artjoms Porss, Jenn Quinn
 * Version No: 1.0
 * 
 */

package ocja.robowars;

public class Robot extends Lifeform{
	private String modelName;
	private RobotWeapon rw = RobotWeapon.CLAWS;
	
	public static float totalPower = 0;
	/** 
	 * @param modelName String
	 * @param ID String
	 * @param power	int
	 */
	public Robot(String modelName, String ID, int power){
		super(ID, power);
		this.modelName = modelName;
		totalPower += power;
	}
	
	
	public Robot(String modelName, String ID, int power, int weapon){
		super(ID, power);
		this.modelName = modelName;
		this.setWeapon(weapon);
		totalPower += power;
	}
	
	
	public String getModelName(){
		return this.modelName;
	}
	
	
	public String toString(){
		return this.getModelName() + " " + super.toString();
	}
	
	//gets weapon power * (robots power / 100)
	public float getWeaponPower(){
		return this.rw.getPower() * (this.getPower() / 100f);
	}
	
	public void setWeapon(int num){
		switch(num){
		case 0 : this.rw = RobotWeapon.CLAWS; break;
		case 1 : this.rw = RobotWeapon.LASER; break;
		case 2 : this.rw = RobotWeapon.ROCKETLAUCHER; break;
		}
	}
	
	public String toWeaponString(){
		return this.getID() + "(" + (int)this.getLife() + " life) with " + this.rw.getName(); 
	}
}
