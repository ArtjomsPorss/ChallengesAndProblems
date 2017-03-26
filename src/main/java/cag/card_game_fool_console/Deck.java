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
 *	ADDED: NO CHANGES
 *	
 *	
 *	
 *	
 *	
 *
 */

package cag.card_game_fool_console;

import java.util.Random;

class Deck{
	//VARIABLES==========================
	private static char trump;	
	// holds Array of cards
	private static Card[] deck = new Card[36];


	//METHODS============================	
	// trump getter
	public static char getTrump(){
		return trump;
	}


	// sets trump from last card in the deck - if its not ace, else - re-shuffle deck(ace cannot be trump card)
	public boolean setTrump(){
		if(!deck[35].getRank().equals("A")){
			trump = deck[35].getSuit();
			//showTrumpCard();
			return true;
		} else {
			System.out.println("\n!!!Aces can't be bottom trump card. Reshuffle the deck!!!");
			return false;
		}
	}


	// print trump
	public static void printTrump(){
		System.out.println("Trumps are: " + trump);
	}


	// shows bottom card from the deck, trumps are assigned according to suit of this card using setTrump()
	public static void showTrumpCard(){
		if(deck[35] != null){				//if there is last card in the deck, show it
			System.out.println("\nBottom trump card in the deck: " + deck[35].toString());
		}else{								//if there is no last card in the deck, show trumps instead of card
			System.out.println("\nNo more cards in deck.");
			printTrump();
		}
	}


	// Create deck of 36 cards with appropriate suits and ranks
	public void createDeck(){
		int forLoopCardDeckCounter = 0;		//used to iterate through deck array
		System.out.println("\nDeck created.. ");
		char suit = ' ';
		String rank = " ";
		for(int s = 0; s < 4; ++s){			//sets card suits
			switch(s){
				case 0 : suit = 'H'; break;
				case 1 : suit = 'D'; break;
				case 2 : suit = 'C'; break;
				case 3 : suit = 'S'; break;
			}//END switch
			for(int r = 6; r < 15; ++r){	//sets card ranks
				if(r > 10) {
					switch(r){
						// if rank is greater than 10, change it to J, Q, K or A respectively
						case 11 : rank = "J"; break;
						case 12 : rank = "Q"; break;
						case 13 : rank = "K"; break;
						case 14 : rank = "A"; break;
					}
				} else {
					rank = Integer.toString(r);		// if rank is below 11, set it
				}//END if else

				//sets suits and rank for current card
				deck[forLoopCardDeckCounter] = new Card(rank, suit);
				//System.out.print(deck[forLoopCardDeckCounter].toString());

				++forLoopCardDeckCounter;				
			}//END for r
		}//END for s
	}//END createDeck


	// Shuffle deck algorithm
	public void shuffleDeck(){
			Random random = new Random();
			for(int i = deck.length - 1; i > 0; --i){
				int index = random.nextInt(i + 1);
				Card tempCard = deck[index];
				deck[index] = deck[i];
				deck[i] = tempCard;
			}
			System.out.println("\nDeck shuffled..");
	}//shuffleDeck()

	
	// Returns deck
	public Card[] getDeck(){
		return deck;
	}


	// Print deck
	public void printDeck(){
		int cardsInDeck = 0;

		//counts amount of cards in deck
		for(Card card : deck){
			if(card != null){
				++cardsInDeck;
			}
		}
		System.out.print("\n" + cardsInDeck + " card(s) are in deck: " );
		//prints them on screen
		for(Card card : deck){
			if(card != null) System.out.print(card.toString() + " ");
		}
		System.out.println();
	}

}
