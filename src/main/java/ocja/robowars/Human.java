/**
 * Definition: Template for human objects
 * 
 * @author Artjoms Porss, Jenn Quinn
 * Version No: 1.0
 * 
 */

package ocja.robowars;

public class Human extends Lifeform {
	
	private HumanWeapon hw = HumanWeapon.PITCHFORK;
	public static float totalPower = 0f;

	public Human(){
		super(("Agent Smith" + (-1)), -1);
	}
	
	public Human(int name, int power){
		super(("Agent Smith" + name), power);
		totalPower += power;
	}
	
	public Human(String name, int power){
		super(name, power);
		totalPower += power;
	}
	
	public Human(String name, int power, int weapon){
		super(name, power);
		this.setWeapon(weapon);
		totalPower += power;
	}
	
	public float getPower(){
		return super.getPower();
	}
	
	//gets weapon power * (robots power / 100)
	public float getWeaponPower(){
		return this.hw.getPower() * (this.getPower() / 100f);
	}
	
	public void setWeapon(int num){
		switch(num){
		case 0 : this.hw = HumanWeapon.PITCHFORK; break;
		case 1 : this.hw = HumanWeapon.MACHINEGUN; break;
		case 2 : this.hw = HumanWeapon.BOMB; break;
		}
	}
	
	public String toWeaponString(){
		return this.getID() + "(" + (int)this.getLife()+ " life) with " + this.hw.getName(); 
	}
}
