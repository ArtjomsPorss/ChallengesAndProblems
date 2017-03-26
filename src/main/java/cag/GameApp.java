package cag;
/**
 *    @author: Artjoms Porss and Colm O'Sullivan
 *    @version: 1.0
 *    @dependencies: none
 *    Created during Java studies
 *
 *    This program shows example of Inheritance, Polymorphism, Using Static vs Instance space.
 *    
 *    Program creates array of Lifeforms and initializes it with Robot and Human objects.
 *    When each Robot or Human object created, static variable representing respective total 
 *    power is incremented by random number.
 *    Then total power of Robots and Humans are compared and who has the greatest power wins.
 *    Results are printed on screen.
 */
 
 
import java.util.Random;

class GameApp{
	private static final int MAX_NUMBER_LIFEFORMS = 1000;
   	private static final int MAX_NUMBER_ROBOTS = 500;
	private Lifeform[] lifeforms = new Lifeform[MAX_NUMBER_LIFEFORMS];
	private static int totalRobotPower = 0;
	private static int totalHumanPower = 0;

	Lifeform roboChamp = null;
	Lifeform homoChamp = null;
	int roboDeadCounter = 1;
	int homoDeadCounter = MAX_NUMBER_ROBOTS;
	int humanWin = 0;
	int roboWin = 0;
	
    
	public static void main(String[] args){
		new GameApp().runProgram();
	}

	private void runProgram(){
		intializeArray();
		//battleMethod();
		individualBattle();
	}

	public int getRobotPower(){
		return totalRobotPower;
	}
	public void setRobotPower(int newPower){
		this.totalRobotPower = newPower;
	}

	public int getHumanPower(){
		return totalHumanPower;
	}
	public void setHumanPower(int newPower){
		this.totalHumanPower = newPower;
	}
	
	private int randomGenerator(){
		Random random = new Random();
		int number = random.nextInt(((100 + 1) - 1) +1);
		return number;
	}
    
    	private void intializeArray(){
    		for (int i = 0; i < MAX_NUMBER_ROBOTS; ++i) {
        		String model = new GameApp().randomGenerator()%2 == 0 ? "Type A" : "Type B";
          		int num = new GameApp().randomGenerator() + i;
        		lifeforms[i] = new Robot(new GameApp().randomGenerator(), i + " " + num, model);
        	}
        
        	for (int i = MAX_NUMBER_ROBOTS; i < lifeforms.length; ++i) {
            		int num = new GameApp().randomGenerator() + i;
        		lifeforms[i] = new Human(new GameApp().randomGenerator(), i + " " + num, "Agent Smith number " + i);
        	}

		for(Lifeform lf : lifeforms){
			System.out.println(lf);
		}
	}

	private void battleMethod(){
		int result = 0;
		if(totalHumanPower > totalRobotPower){
			result = totalHumanPower - totalRobotPower;
			System.out.println("Human win by " + result);
		} else {
			result =  totalRobotPower - totalHumanPower;
			System.out.println("Robots win by " + result);
		}
	}

	private void individualBattle(){
		roboChamp = lifeforms[0];
		homoChamp = lifeforms[MAX_NUMBER_ROBOTS];
		for(int i = 0; i < 1000; ++i){
			try{
				if(roboChamp.getPower() > homoChamp.getPower()){
					robotWin();
				} else if(homoChamp.getPower() > roboChamp.getPower()){
					humanWin();
				} else {
					System.out.println("Draw!!");
					int num = new GameApp().randomGenerator()%2;	
					if(num == 0){
						robotWin();
					} else {
						humanWin();
					}	
				}	//lifeforms[8]instanceof Robot
			} catch (Exception e){
				
			} finally{
				System.out.println("Battle is over, one of the sides is vanquished.");
				System.out.println("Robots desintegrated during the battle: " + humanWin);
				System.out.println("Humans died during the battle: " + roboWin);
				if(roboWin < humanWin){
					System.out.println("Humans Won!");
				} else {
					System.out.println("Robots Won!");
				}
	
			}
			
			//lifeforms[i].getPower() > lifeforms[i+500].getPower()
		}
	}

	private void humanWin(){
		++roboDeadCounter;
		++humanWin;
		roboChamp = lifeforms[roboDeadCounter];
		System.out.println("Human won!");
	}

	private void robotWin(){
		++homoDeadCounter;
		++roboWin;
		homoChamp = lifeforms[homoDeadCounter];
		System.out.println("Robot won!");
	}
}

class Lifeform{
	private int power = -1;
	private String uniqueID = "Unknown";

	public Lifeform(){
	}

	public Lifeform(int power, String uniqueID){
		this.power = power;
		this.uniqueID = uniqueID;
	}
	public int getPower(){
		return this.power;
	}
  
  public String toString(){
  	return "Power: " + this.power + "\t  UniqueID: " + this.uniqueID;
  }
}

class Robot extends Lifeform{
	private String model = null;
	
	public Robot(){
	}

	public Robot(int power, String uniqueID, String model){
		super(power, uniqueID);
		this.model = model;
		GameApp ga = new GameApp();
      		ga.setRobotPower(ga.getRobotPower() + power);
	}
  
  	public String toString(){
  		return super.toString() + "\t	 Model: " + this.model;
  	}
}


class Human extends Lifeform{
	String name = "John Connor";

	public Human(){
	}

	public Human(int power, String uniqueID, String name){
		super(power, uniqueID);
		this.name = name;
		GameApp ga = new GameApp();
      		ga.setHumanPower(ga.getHumanPower() + power);
	}
  
  	public String toString(){
  		return super.toString() + "\t  Name: " + this.name;
  	}
}
