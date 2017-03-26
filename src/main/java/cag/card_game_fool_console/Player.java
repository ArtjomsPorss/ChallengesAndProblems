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
 *	ADDED: addToTable(), 
 *	
 *	CHANGED: makeMove(), 
 *	
 *	
 *	
 *
 */

package cag.card_game_fool_console;

class Player{
	
	String playerName;

	public Player(String playerName){
		this.playerName = playerName;
	}

	Card[] hand = new Card[36];
	

	// draws cards from deck until player gets six cards
	public void drawHand(Card[] deck){
		int cardsInHand = 0;
		int cardsInDeck = 0;
	
		//counts cards in hand
		for(Card card : hand){
			if(card != null) ++cardsInHand;
		}

		//searches deck for a card and puts it in an empty
		//element in hand array
		for(int x = cardsInHand; x < 6; ++x){
			for(int i = 0; i < 36; ++i){	//Runs through deck[] searches for Card's != null.
				if(deck[i] != null){
					++cardsInDeck;
					for(int j = 0; j < 36; ++j){
						if(hand[j] == null){
							//Change hand reference to point to a card obj from deck
							hand[j] = deck[i];
							deck[i] = null;
							break;
						}
					}
					break;
				}//END if deck
			}//END for i
		}//END for x	
		
		//shows message that player draws cards 
		if(cardsInHand < 6 && cardsInDeck > 0){
			System.out.println(playerName + " draws cards from the deck..");
		}else if (cardsInHand == 6){
			System.out.println(playerName + " has full hand of cards..");			
		}else if (cardsInDeck == 0){
			System.out.println(playerName + " doesn't draw cards - deck is empty..");
		}
	}//END drawHand()


	//prints all cards in the hand
	public void printHand(){
		System.out.print("\n" + playerName + " hand: ");
		for(int i = 0; i < hand.length; ++i){
			if(hand[i] != null){
				System.out.print(i+1 + ") " + hand[i].toString() + ",  ");
			}
		}
		System.out.println();
	}//printHand()


	// returns hand[]
	public Card[] getHand(){
		return hand;
	}


	// returns amount of cards in hand
	public int cardsInHand(){
		int cards = 0;
		for(Card c : hand){
			if(c != null)	++cards;
		}
		return cards;
	}	


	// Player attacks with a card
	public void makeMove(){
		Card chosenCard = null;
		//System.out.println(playerName + " attacks");
		boolean hasMoved = false;
		int choiceInt = 0;
		do{
			
			String choiceStr = CardUtil.read("\n" + playerName + " choose card to attack: ");
			try{
				choiceInt = Integer.parseInt(choiceStr);
				if(hand[choiceInt-1] == null) throw new Exception();
				for(int i = 0; i < Table.table.length; ++i){
					if(Table.table[i] == null){
						Table.table[i] = hand[choiceInt-1];
						chosenCard = hand[choiceInt-1];
						hand[choiceInt-1] = null;
						break;
					}
				}
				hasMoved = true;
			} catch(Exception e){
				System.out.println("You can choose only from cards listed above..");
			}
		} while(!hasMoved);
		System.out.println(CardUtil.attacker.playerName + " attacks with " + chosenCard.toString());
	}


	// If defending player decides to defend with a card returns true, if inputs zero returns false
	public boolean defendMove(){
		boolean validCard = false;
		int choiceInt = 0;
		Card[] table = Table.table;
		Card[] hand = CardUtil.defender.getHand();
		Card att = Table.attackingCard();
		Card def = null;
		do{
			String choiceStr = CardUtil.read(playerName + " choose card to defend or press"
													+ " ZERO to take cards from table: ");
			try{
				choiceInt = Integer.parseInt(choiceStr);
				if(choiceInt == 0){	//if player pressed zero, draw all cards from the table
					drawCardsFromTable();
					return false;
				}
				// HERE INSERT USER CHOICE TO GRAB CARD AND PASS DEFENCE TURN if(choiceInt == 0){}
				def = hand[choiceInt-1];
				if(def == null) throw new Exception();
				if(att.getSuit() == def.getSuit() && CardUtil.rankToInt(att.getRank()) < CardUtil.rankToInt(def.getRank())){
					validCard = true;
				} else if(att.getSuit() != Deck.getTrump() && def.getSuit() == Deck.getTrump()) {
					validCard = true;
				} else {
					throw new Exception();
				}
			} catch(Exception e){
				System.out.println("\nChoose card of same suit and higher rank, or trumps..");
			}
			
		} while(!validCard);
		for(int i = 0; i < table.length; ++i){
			if(table[i] == null && table[i-1] != null){
				table[i] = hand[choiceInt-1];
				hand[choiceInt-1] = null;
			}
		}
		return true;	//player defended
	}


	// add more cards to table during your turn
	public boolean addToTable(){
		int choiceInt = 0;
		boolean validCard = false;
		do{
			String choiceStr = CardUtil.read(playerName + " choose card to add or press"
													+ " ZERO to pass turn: ");
			try{
				choiceInt = Integer.parseInt(choiceStr);
				if(choiceInt == 0){		//if player decided to pass turn
					System.out.println(playerName + " decided to pass turn");
					return false;
				}
				// initialize table rank array with card ranks from the table
				CardUtil.initTableRankArray();
				// check whether chosen card rank matches ranks on table
				if(CardUtil.ifHandCardRankMatchesTable(choiceInt-1, CardUtil.attacker.getHand())){
					// moves chosen card from hand to table
					CardUtil.moveCard(choiceInt-1, CardUtil.attacker.getHand(), Table.table);
					validCard = true;
					System.out.println(CardUtil.attacker.playerName + " adds " + 
							CardUtil.attacker.getHand()[choiceInt-1].toString() + " to table..");
				} else {
					throw new Exception();
				}
			} catch(Exception e){
				System.out.println("\nChoose card of same rank that's on the table..");
			}
		}while(!validCard);
		return true;	//player decided to add card to table
	}


	public void drawCardsFromTable(){
		Card[] table = Table.table;
		Card[] hand = CardUtil.defender.getHand();
		for(int i = 0; i < table.length; ++i){
			if(table[i] != null){
				for(int x = 0; x < hand.length; ++x){
					if(hand[x] == null){
						hand[x] = table[i];
						table[i] = null;
					}
				}
			}
		}
		System.out.println("\n" + CardUtil.defender.playerName + " draws cards from table..");
	}
}
