/**
 * 
 *	DESCRIPTION
 *	
 *	Author: Artjoms Porss
 *	
 *	dependencies: Player.java, Card.java, CardGame.java, Deck.java, CardUtil.java, Table.java	
 *	
 * 	Version : 1_3
 * 	
 *	
 *	
 *	ADDED: initTableRankArray() moveCard() ifHandCardRankMatchesTable()
 *	
 *	
 *	
 *	  	nextInt is normally exclusive of the top value,
 *   		so add 1 to make it inclusive
 * 		int randomNum = rand.nextInt((max - min) + 1) + min;
 *	
 *
 */
//testy
package cag.card_game_fool_console;

import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class CardUtil{
	static Random random = new Random();
	public static boolean player1Turn;
	public static Player attacker;
	public static Player defender;
	public static Player tempPlayer;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	
	//switch attacker and defender
	public static void switchAttackerDefender(){
		//System.out.print("\nAttacker " + attacker.playerName + ", defender " + defender.playerName);
		tempPlayer = attacker;
		attacker = defender;
		defender = tempPlayer;
		//System.out.print(" are switched to:\nAtacker: " + attacker.playerName + " defender: " + defender.playerName);
		System.out.println("\n\n======  " + attacker.playerName + "'s turn  ======\n");
	}


	// the player who has lowest trump in hand goes first
	public static void checkGoesFirst(Player p1, Player p2){
		System.out.println("\nPlayer with lowest trump card in hand goes first..");
		int rank1 = 15;		// 15 is above ace
		int rank2 = 15;
		// find lowest trump
		for(int i = 0; i < 35; ++i){
			if(p1.hand[i] != null){
				if(p1.hand[i].getSuit() == Deck.getTrump()){
					if(rank1 > rankToInt(p1.hand[i].getRank())){
						rank1 = rankToInt(p1.hand[i].getRank());
						//System.out.println("Player 1 lowest rank: " + rank1);
					}
				}
			}
		}
		for(int i = 0; i < 35; ++i){
			if(p2.hand[i] != null){
				if(p2.hand[i].getSuit() == Deck.getTrump()){
					if(rank2 > rankToInt(p2.hand[i].getRank())){
						rank2 = rankToInt(p2.hand[i].getRank());
						//System.out.println("Player 2 lowest rank: " + rank2);
					}
				}
			}
		}
		boolean attackerChosen = false;
		do{
			if(rank1 < rank2 || rank1 > rank2){
				if(rank1 < rank2){
					player1Turn = true;
					attacker = p1;
					defender = p2;
					System.out.println("\nAttacker: " + attacker.playerName + 
						"\nDefender: " + defender.playerName);
					attackerChosen = true;
				} else {
					player1Turn = false;
					attacker = p2;
					defender = p1;
					System.out.println("\nAttacker: " + attacker.playerName + 
						"\nDefender: " + defender.playerName);
					attackerChosen = true;
				}
				//System.out.println(attacker.playerName + " goes first..");
			} else {
				int randomNum = random.nextInt((2 + 1) - 1) + 1;
				rank1 += randomNum;
				randomNum = random.nextInt((2 + 1) - 1) + 1;
				rank2 += randomNum;
			}
		}while(!attackerChosen);
	}//firstPlayerGoesFirst


	//prints defender name
	public static void announceDefender(){
		System.out.println(defender.playerName + " defends...");
	}


	// checks if defending player has cards of same suit higher rank or trumps be able to defend
	public static boolean checkIfAbleToDefend(){
		boolean defends = false;
		Card[] table = Table.table;
		Card[] hand = CardUtil.defender.getHand();
		search:
		for(int i = 0; i < table.length; ++i){
			if(Table.table[i] == null && Table.table[i-1] != null){
				for(int x = 0; x < hand.length; ++x){
					if(hand[x] != null){
						if(table[i-1].getSuit() == hand[x].getSuit()){ 	//if cards are of same suit 
							if(rankToInt(table[i-1].getRank()) < rankToInt(hand[x].getRank())){
								defends = true;		//if rank of card on table is less than rank in the hand => can defend
								//System.out.println(table[i-1].toString() + "<-" + hand[x].toString());
								break search;
							} else {				//rank of card on table is greater than rank in hand => can't defend
								defends = false;
								//System.out.println(table[i-1].toString() + "<-" + hand[x].toString());
							}
						} else if (table[i-1].getSuit() != Deck.getTrump() && hand[x].getSuit() == Deck.getTrump()){
							defends = true;		//if card on table is not trump and card in hand is trump => can defend
							//System.out.print(table[i-1].toString() + "<-" + hand[x].toString());
							break search;
						}
					}
				}
			} else {
				defends = false;
			}
		}
		if(defends){
			System.out.println(CardUtil.defender.playerName + " can defend");
		} else{
			System.out.println(CardUtil.defender.playerName + " can't defend");
		}
		return defends;
	}


	//checks whether attacker can add more cards to the table
	static boolean canAddToTable(){
		Card[] hand = CardUtil.attacker.getHand();
		Card[] table = Table.table;
		boolean canAdd = false;
		int cardsOnTable = 0;

		// initialize table rank array
		initTableRankArray();

		// compare hand ranks with rank array values
		for(int f = 0; f < hand.length; ++f){
			if(hand[f] != null){
				for(int y = 0; y < Table.ranksOnTable.length; ++y){
					if(CardUtil.rankToInt(hand[f].getRank()) == Table.ranksOnTable[y]){
						canAdd = true;
					}
				}
			}
		}

		// counts amount of cards on the table
		for(int i = 0; i < Table.ranksOnTable.length; ++i){
			if(table[i] != null){
				++cardsOnTable;
			}
		}
		// check if there are cards in defending hand and there is space on the table and ranks in hand and table match
		if(cardsOnTable < 11 && CardUtil.defender.cardsInHand() > 0 && canAdd){
			canAdd = true;
		} else {
			canAdd = false;
		}

		System.out.println("Attacker can add to table: " + canAdd);
		return canAdd;
	}


	// initialize table rank array with ranks from cards on the table
	public static void initTableRankArray(){
		//reset all ranks to zero, then initialize
		Table.resetRanksOnTable();
		Card[] table = Table.table;
		int[] ranks = Table.ranksOnTable;
		for(int i = 0; i < table.length; ++i){
			if(table[i] != null){
				for(int x = 0; x < ranks.length; ++x){
					if(ranks[x] == CardUtil.rankToInt(table[i].getRank())){
						break;
					}else if(ranks[x] == 0){
						ranks[x] = CardUtil.rankToInt(table[i].getRank());
						//System.out.println("Rank " + x + " on table = " + ranks[x]);
						break;
					}
				}
			}
		}
	}


	//checks if chosen card rank in hand matches ranks on table
	public static boolean ifHandCardRankMatchesTable(int choice, Card[] hand){
		boolean matches = false;
		for(int i = 0; i < Table.ranksOnTable.length; ++i){
			if(Table.ranksOnTable[i] == CardUtil.rankToInt(hand[choice].getRank())){
				matches = true;
				break;
			} else { 
				matches = false;
			}
		}
		return matches;
	}


	//moves chosen card from array1 to array2
	public static void moveCard(int choice, Card[] array1, Card[] array2){
		for(int i = 0; i < array2.length; ++i){
			if(array2[i] == null){
				array2[i] = array1[choice];
				array1[choice] = null;
				//System.out.println("CardUtil.moveCard() - card moved..");
				break;
			}
		}
	}
	
	
	public static String read(String text){
		String choice = null;
		System.out.print(text);
		try{
			choice = new CardUtil().reader.readLine();
		}catch(IOException e){
			
		}
		return choice;
	}

	
	//takes rank of card as int and returns string
	public static String rankToStr(int rankInt){
		String rankStr;
		switch(rankInt){
			case 11 :	{
						rankStr = "J";
						return rankStr;
					}
			case 12 :	{
						rankStr = "Q";
						return rankStr;
					}
			case 13 :	{
						rankStr = "K";
						return rankStr;
					}
			case 14 :	{
						rankStr = "A";
						return rankStr;
					}
			default :	{
						rankStr = Integer.toString(rankInt);
						return rankStr;
					}
		}//switch
	}


	//takes rank string and returns respective int
	public static int rankToInt(String rankStr){
		int rankInt;
		switch(rankStr){
			case "J" :	{
						rankInt = 11;
						return rankInt;
					}
			case "Q" :	{
						rankInt = 12;
						return rankInt;
					}
			case "K" :	{
						rankInt = 13;
						return rankInt;
					}
			case "A" :	{
						rankInt = 14;
						return rankInt;
					}
			default :	{
						rankInt = Integer.parseInt(rankStr);
						return rankInt;
					}
		}
	}

}
