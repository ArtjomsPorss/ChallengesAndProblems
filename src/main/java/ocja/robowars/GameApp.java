/**
 * Definition: Class that loads menu and plays different robot battling games
 * 
 * @author Artjoms Porss, Jenn Quinn
 * Version No: 1.0
 * 
 */

package ocja.robowars;

import java.util.Random;
import java.util.Scanner;

public class GameApp {

	private int humanWins = 0;
	private int robotWins = 0;
	private int draws = 0;

	private Human[] humans = new Human[500];
	private Robot[] robots = new Robot[500];

	private Scanner scan = new Scanner(System.in);
	private Random rand = new Random();

	public static void main(String[] args) {
//		Robot r = new Robot("Model", "ID", 100);
//		System.out.println(r.getWeaponPower());
		//ENUM TEST
//		RobotWeapon bmb = RobotWeapon.BOMB;
//		System.out.println("Bomb's power " + bmb.getPower());
//		bmb = RobotWeapon.CLAWS;
//		System.out.println("claws power " + bmb.getPower());
		
		GameApp ga = new GameApp();
		ga.runProgram();
	}


	private void runProgram(){


		boolean fight = true;
		do{
			createLifeforms();
			//remove TODO
			
			System.out.println();
			System.out.println("Creating new life for your sadistic pleasure..");
			System.out.println("How do you want the lifeforms to do battle?");
			System.out.println("1) Last Man Standing");
			System.out.println("2) Through life");
			System.out.println("3) One on one battles");
			System.out.println("4) Weapon battle");
			System.out.println("5) Quit");
			System.out.print("Choose an option: ");
			while(!scan.hasNextInt()){
				scan.next();
				System.out.print("Not a number, please enter a number between 1 and 4:");
			}

			int userChoice = scan.nextInt();

			switch(userChoice){
			case 1: 
				lastManStanding();
				break;
			case 2: 
				lifeBattle();
				break;
			case 3: 
				oneOnOneBattle();
				break;
			case 5:
				fight = false;
				System.out.println("Thanks for playing the game, bye!");
				break;
			case 4: 
				weaponBattle();
				break;
			default: 
				System.out.println("Invalid choice, please pick a number between 1 and 4");
				break;
			}	
		}while(fight);

	}

	private void createLifeforms() {
		//creating robots
				for(int i = 0; i < robots.length; ++i){
					if(i % 2 == 0){
						robots[i] = new Robot("Type A", "R" + (i + 1), rand.nextInt(101), rand.nextInt(3));
					}else{
						robots[i] = new Robot("Type B", "R" + (i + 1), rand.nextInt(101), rand.nextInt(3));
					}
				}


				//creating humans
				for(int i = 0; i < humans.length; ++i){
					humans[i] = new Human(createName(), rand.nextInt(101), rand.nextInt(3));
				}		
	}

	private void weaponBattle(){
		boolean inBattle = true;
		int r = 0;
		int h = 0;
		while(inBattle){
			System.out.println();
//			System.out.println(humans[h].toString() + " weapon: " + humans[h].getWeaponPower()
//					+ " defense " + humans[h].getDefense() + " life: " + humans[h].getLife());
//			System.out.println(robots[r].toString() + " weapon: " + robots[r].getWeaponPower()
//					+ " defense " + robots[r].getDefense() + " life: " + robots[r].getLife());
			if(humans[h].getWeaponPower() > robots[r].getDefense()){
				robots[r].setLife(robots[r].getLife() - (humans[h].getWeaponPower() - robots[r].getDefense()));
				System.out.println(humans[h].toWeaponString() + " hits " + robots[r].toWeaponString());
			}
			else {
				humans[h].setLife(humans[h].getLife() -(robots[r].getDefense() - humans[h].getWeaponPower()));
				System.out.println(humans[h].toWeaponString() + " is hit by recoil from " + robots[r].toWeaponString());
			}
			
			if(robots[r].getWeaponPower() > humans[h].getDefense()){
				humans[h].setLife(humans[h].getLife() - (robots[r].getWeaponPower() - humans[h].getDefense()));
				System.out.println(robots[r].toWeaponString() + " hits " + humans[h].toWeaponString());
			}
			else {
				robots[r].setLife(robots[r].getLife() - (humans[h].getDefense() - robots[r].getWeaponPower()));
				System.out.println(robots[r].toWeaponString() + " is hit by recoil from " + humans[h].toWeaponString());
			}
			System.out.println(robots[r].getID() + "(" + (int)robots[r].getLife() + " life) VS "
						+ humans[h].getID() + "(" + (int)humans[h].getLife() + " life)");
			/*
			 //testing printf
			System.out.printf("%s + (%d life) + VS %s + ( %dlife)", robots[r].getID(), robots[r].getLife()
					, humans[h].getID(), humans[h].getLife());
			 */
			if(robots[r].getLife() <= 0){
				++r;
			}
			if(humans[h].getLife() <= 0){
				++h;
			}

			if(r >= robots.length || h >= humans.length){
				inBattle = false;
			}
		}

		int humansLeft = 500 - (h + 1);
		int robotsLeft = 500 - (r + 1);
		//print who won
		if(humansLeft > robotsLeft){
			System.out.println("Humans win the battle. " + humansLeft + " survivors.");
		}else{
			System.out.println("Robots win the battle. " + robotsLeft + " survivors");
		}
	}

	private void lifeBattle(){
		//LIFE BATTLE
		boolean inBattle = true;
		int r = 0;
		int h = 0;
		while(inBattle){
			robots[r].setLife(robots[r].getLife() - humans[h].getPower());
			humans[h].setLife(humans[h].getLife() - robots[r].getPower());

			System.out.println(getFightResult(robots[r], humans[h]));

			if(robots[r].getLife() <= 0){
				++r;
			}
			if(humans[h].getLife() <= 0){
				++h;
			}

			if(r >= robots.length || h >= humans.length){
				inBattle = false;
			}
		}

		int humansLeft = 500 - (h + 1);
		int robotsLeft = 500 - (r + 1);
		//print who won
		if(humansLeft > robotsLeft){
			System.out.println("Humans win the battle. " + humansLeft + " survivors.");
		}else{
			System.out.println("Robots win the battle. " + robotsLeft + " survivors");
		}
	}

	private void oneOnOneBattle(){
		//ONE ON ONE POWER BATTLE
		for(int b = 0; b < humans.length; ++b){
			char sign = ' ';
			if(humans[b].getPower() > robots[b].getPower()){
				sign = '>';
				++humanWins;
			}else if(humans[b].getPower() < robots[b].getPower()){
				++robotWins;
				sign= '<';
			}else{
				++draws;
				sign = '=';
			}
			System.out.println(humans[b].toString() + " " + sign + " " + robots[b].toString());
		}

		//print results

		System.out.println("Humans won " + humanWins + " times");
		System.out.println("Robots won " + robotWins + " times");
		System.out.println("Humans and Robots drew " + draws + " times");

		System.out.println("Total human power was " + Human.totalPower);
		System.out.println("Total robot power was " + Robot.totalPower);

		System.out.println("The winners by total power are " + (Human.totalPower > Robot.totalPower ? "humans" : "robots")  );
	}

	private void lastManStanding(){
		boolean inBattle = true;
		int r = 0;
		int h = 0;
		while(inBattle){
			char sign = ' ';
			if(humans[h].getPower() > robots[r].getPower()){
				sign = '>';
				System.out.println(humans[h].toString() + " " + sign + " " + robots[r].toString());
				humans[h].setPower(humans[h].getPower() - 0.5F);
				++r;
			}
			else if (robots[r].getPower() > humans[h].getPower()){
				sign= '<';
				System.out.println(humans[h].toString() + " " + sign + " " + robots[r].toString());
				robots[r].setPower(robots[r].getPower() - 0.5F);
				++h;
			}
			else{
				sign = '=';
				System.out.println(humans[h].toString() + " " + sign + " " + robots[r].toString());
				++r;
				++h;

			}

			if(r >= robots.length || h >= humans.length){
				inBattle = false;
			}
		}

		int robotSurvivors = 500 - r;
		int humanSurvivors = 500 - h;

		if(r == 500){
			System.out.println("Humans win. " + humanSurvivors + " survivors.");
		}
		else{
			System.out.println("Robots win. "+ robotSurvivors + " survivors.");
		}

	}

	private String getFightResult(Robot robot, Human human) {
		//TODO
		if(robot.getLife() <= 0 && human.getLife() <= 0){
			return robot.toString() + " and " + human.toString() + ", both died";
		} else if (robot.getLife() <= 0 && human.getLife() > 0){
			return robot.toString() + " - died and " + human.toString() + " - lives";
		} else if (robot.getLife() > 0 && human.getLife() <= 0){
			return robot.toString() + " - lives and " + human.toString() + " - died";
		} else {
			//fight again
			return robot.toString() + " and " + human.toString() + ", round 2!!";
		}

	}


	//returns a random name of 2 - 7 letters
	private String createName(){
		Random r = new Random();
		char[] name = new char[r.nextInt(6) + 2];
		char[] vowels = new char[]{'a','e','i','o','u'};
		char[] consonant = new char[]{'b','c','d','f','g','h','j'
				,'k','l','m','n','p','q','r','s','t','v','w','x','y','z'};

		for(int l = 0; l < name.length; ++l){
			if(l % 2 == 0){
				name[l] = consonant[r.nextInt(21)];
			}else{
				name[l] = vowels[r.nextInt(5)];
			}
		}

		return new String(name);
	}

}
