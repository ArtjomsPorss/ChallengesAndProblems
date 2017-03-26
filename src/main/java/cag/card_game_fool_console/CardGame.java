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
 *		CHARS FOR SUITS:
 *		char hearts = 03;	setSuit((char)03)
 *		char clubs = 05;	setSuit((char)05)
 *		char spades = 06;	setSuit((char)06)
 *
 *		RANKS:
 *		6,7,8,9,10,J,Q,K,A
 *
 *		CARD CLASS CONSTRUCTOR:
 *		Card(char suit, String rank)
 *
 *		setRank("A")	example of setting rank Ace to a card
 *
 *		First goes rank, then suit, like here Queen of Hearts
 *		Card QH = new Card("Q", (char)03);   example of creating a single card using constructor
 *
 *
 *	ADDED:
 *	
 *	
 *	
 *	
 *	
 *
 */

package cag.card_game_fool_console;

class CardGame {

	public static void main(String[] args){
		//create players
		Player p1 = new Player("Me");
		Player p2 = new Player("Pc");

		boolean endDefend = false;	//becomes true when turn ends
		boolean endGame = false;	//becomes true when game ends

		Deck deck = new Deck();
		deck.createDeck();

		do{
			deck.shuffleDeck();
		} while(!deck.setTrump());


		//players draw cards
		p1.drawHand(deck.getDeck());
		p2.drawHand(deck.getDeck());

		//deck.printDeck();

		CardUtil.checkGoesFirst(p1, p2);

		//start turn
		do{
			endDefend = false;		//reset end turn value
			p1.drawHand(deck.getDeck());
			p2.drawHand(deck.getDeck());
			// check if players have more than zero cards in hand
			if(CardUtil.attacker.cardsInHand() != 0 && CardUtil.defender.cardsInHand() != 0){
				Deck.showTrumpCard();
				CardUtil.defender.printHand();
				CardUtil.attacker.printHand();
				CardUtil.attacker.makeMove();	//attacker makes move
			} else {	//one player has zero cards after drawing from deck => no cards in deck, no cards in 1 of players hands
				break;		//exit game loop, control moves to winner check phase, bec. one of players won
			}

			//Table.printTable();


			do{
				if(CardUtil.checkIfAbleToDefend()){	// if defender can defend
					CardUtil.announceDefender();
					Deck.showTrumpCard();
					Table.printTable();
					CardUtil.attacker.printHand();
					CardUtil.defender.printHand();
					if(!CardUtil.defender.defendMove()){	//defender defends with a card or takes cards
						break;	//if defender decided to take cards instead of defending, break current turn
					} else {
						Table.printTable();	//if defender defended with a card, print table
					}
					if(CardUtil.canAddToTable()){	// check if attacker can add more cards to table
						Deck.showTrumpCard();
						Table.printTable();
						CardUtil.defender.printHand();
						CardUtil.attacker.printHand();
						if(CardUtil.attacker.addToTable()){	//attacker adds more cards to table
							continue;
						}
					}
						//if attacker cannot add cards to table or decides not to, defender defended successfully
						Table.tableToDiscardPile();	//move cards from table to discard pile
						Table.printDiscardPile();	//displays discard pile
						CardUtil.switchAttackerDefender();	//switch attacker and defender for next turn
						endDefend = true;			//end turn

					

				} else {	//defender can't defend
					Table.printTable();
					CardUtil.defender.drawCardsFromTable();		//defender takes cards from table
					CardUtil.attacker.printHand();
					CardUtil.defender.printHand();
					endDefend = true;							//end turn - attacker will attack again
				}
				//if attacker can add more to table, attacker goes again
			} while(!endDefend);
		} while(!endGame);

		//phase where winner is determined
		if(p1.cardsInHand() == 0 && p2.cardsInHand() != 0){
			System.out.println(p1.playerName + " win the game!");
		} else if (p2.cardsInHand() == 0 && p1.cardsInHand() != 0){
			System.out.println(p2.playerName + " win the game!");
		} else {
			System.out.println("It's a draw!");
		}
		System.out.println("<=========== Game ends here ==========>");
	}
}
