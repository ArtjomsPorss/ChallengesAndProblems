package cag;
/** 
 * Simple game written during first week of City&Guilds Java shortcourse.
 * You have a character with attributes. You are given several commands,
 * which program accepts as an input(only). Depending on which command you
 * choose, character attributes change. Your goal is to keep character alive,
 * which is - to keep attributes above or not less than zero, depending on attribute.
 *
 * 
 *	 
 * @author Artjoms Porss 
 * @version 1.0  
 * @dependencies none
 *  
 *  
 */

import java.io.Console;

class CharacterGame{

	public static int actionCounter = 0;

	public static void main(String[] args){
		Console con = System.console();
		System.out.println("\nYou can study, work, feast,"+ 				
		" eat.\n" +
		"Every action you do, changes your state.\n"+
		"Depending on your action you whether live "+
		"long and prosper, or die in misery..\n" +
		"(Tip: Don't let any of your indices below 1 ;)");
	
		//	player parameters are 4:
		// education, satiety, power and enthusiasm and money
		int educ = 7;
		int sati = 7;
		int powe = 7;
		int enth = 7;
		int money = 7;
		//	activities
		// work, study, rest, buy food, eat, feast
		boolean alive = true;
			
		do{		
			String action = con.readLine("\nType \"work\", \"study\", \"rest\""+
			", \"feast\", \"eat\" as actions.\n"+
			"\"state\" to see your state." + 
			"\nOr \"quit\" to exit game:\t");
			action = action.toLowerCase();
			
			//	making work .. work
			if(action.equals("work")){
				educ += 0;
				sati -= 2;
				powe -= 2;
				money +=2;
				enth -=2;
				System.out.println("\n\n\tYou have worked hard.");
				alive = AliveCheck(educ, sati, powe, enth, money);
			// use PrintState to print stats on the screen
				PrintState(educ,sati,powe,enth,money);
			}
			// making study ..
			else if(action.equals("study")){
				educ += 2;
				sati -= 1;
				powe -= 2;
				money -=2;
				enth -=2;
				System.out.println("\n\n\tYou have diligently studied.");
				alive = AliveCheck(educ, sati, powe, enth, money);
			// use PrintState to print stats on the screen
				PrintState(educ,sati,powe,enth,money);
			}
			// making feast ..
			else if(action.equals("feast")){
				educ -= 1;
				sati += 2;
				powe -= 1;
				money -=1;
				enth +=2;
				System.out.println("\n\n\tYou have feasted madly.");
				alive = AliveCheck(educ, sati, powe, enth, money);
			// use PrintState to print stats on the screen
				PrintState(educ,sati,powe,enth,money);
			}
			// making eat ..
			else if(action.equals("eat")){
				sati += 2;
				powe += 1;
				money -=1;
				enth +=1;
				System.out.println("\n\n\tYou have eaten your fill.");
				alive = AliveCheck(educ, sati, powe, enth, money);
			// use PrintState to print stats on the screen
				PrintState(educ,sati,powe,enth,money);
			}
			// making rest ..
			else if(action.equals("rest")){
				sati -= 1;
				powe += 2;
				money -=1;
				enth +=1;
				System.out.println("\n\n\tYou have rested well.");
				alive = AliveCheck(educ, sati, powe, enth, money);
			// use PrintState to print stats on the screen
				PrintState(educ,sati,powe,enth,money);
			}
			// PrintState command for player to be called
			else if(action.equals("state")){
				PrintState(educ,sati,powe,enth,money);
			}
			else if(action.equals("quit")){
				System.out.println("\n\tActions performed during this session : " +
							 actionCounter + "\n\n--- GAME ENDS ---");
				alive = false;
			}
			// If player types everything other than commands..
			else{
			System.out.println("This is not an activity available " + 
			"to you..");
			}		
		} while(alive == true);	
	}

	// creating PrintState method to print user's stats on screen
	public static void PrintState(int educ, int sati, int powe, int enth, int money){
	System.out.println("\nYour state now is:"+
			"\nEducation : \t" + educ +
			"\nSatiety : \t" + sati +
			"\nPower : \t" + powe +
			"\nEnthusiasm : \t" + enth +
			"\nMoney : \t" + money);
	}

	// Creating AliveCheck method to check if the player 
	// stays alive after each activity performed.
	// Action counter shows how many actions user pefrormed
	// at the end of the game.
	public static boolean AliveCheck(int educ, int sati, int powe, int enth, int money){
		boolean isAlive = true;
		++actionCounter;
		if(educ < 0){
			isAlive = false;
		}
		else if(sati <= 0){
			isAlive = false;
		}
		else if(powe <= 0){
			isAlive = false;
		}
		else if(money < 0){
			isAlive = false;
		}
		else if(enth <= 0){
			isAlive = false;
		} else{
			isAlive = true;
		}
		if(isAlive == false){
			System.out.println("\tAnd died during the process" + 
					"\n\tActions performed during this session : " + actionCounter +
					"\n\n--- GAME OVER ---");
			return false;
		} else {
			return true;
		}
	}
}
