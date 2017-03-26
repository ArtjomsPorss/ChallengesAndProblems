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
 *	ADDED: resetRanksOnTable()
 *	
 *	
 *	
 *	
 *	
 *
 */

package cag.card_game_fool_console;

class Table{
	static Card[] table = new Card[12];
	static Card[] discard = new Card[36];
	static int[] ranksOnTable = {0,0,0,0,0,0,0,0,0};	//create reset ranks on table method
	
	//resets rank on table to zero
	static void resetRanksOnTable(){
		for(int i = 0; i < ranksOnTable.length; ++i){
			ranksOnTable[i] = 0;
		}
	}

	
	//prints cards on table with arrows to indicate which beats which
	static void printTable(){
		System.out.print("Cards on table: ");
		String spaceOrArrow;
		for(int i = 0; i < table.length; ++i){
			if(table[i] != null){
				spaceOrArrow = i%2 == 0 ? "<-" : " ";	//after every even card on table(start from zero) put arrow, else space
				System.out.print(table[i].toString() + spaceOrArrow);
			}
		}
		System.out.println();
	}
	
	
	//prints discard pile
	static void printDiscardPile(){
		System.out.print("\nCards in discard pile: ");
		for(int i = 0; i < discard.length; ++i){
			if(discard[i] != null){
				System.out.print(discard[i].toString() + " ");
			}
		}
	}

	
	//returns last attacking card on the table
	static Card attackingCard(){
		Card card = null;
		for(int i = 0; i < table.length; ++i){
			if(table[i] == null && table[i-1] != null){
				card =  table[i-1];
			}
		}
		return card;
	}

	
	//moves cards from table to discard pile
	static void tableToDiscardPile(){
		//start moving first card from deck to discard pile
		for(int t = 0; t < table.length; ++t){
			if(table[t] == null){	//if there are no more cards, exit initial loop
				break;
			}
			for(int d = 0; d < discard.length; ++d){	//if current element is a card, move it to discard
				if(discard[d] != null){		//if element in discard has a card in it, continue to next iteration
					continue;
				}else{
					discard[d] = table[t];
					table[t] = null;
					break;
				}
			}
		}
		System.out.println("Cards from table are moved to discard pile");
	}
}// END Table class
