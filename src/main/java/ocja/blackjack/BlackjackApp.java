/**
 * Contains main and utility methods to control game flow
 * of  blackjack game played between two AI's
 * version: 1.0
 * @author: Artjoms Porss, Colm O'Sullivan, Gregory Harman, Joe
 */
package ocja.blackjack;

import java.util.Collections;
import java.util.Scanner;

public class BlackjackApp {
	Cards deck;
	Cards playerOne;
	Cards playerTwo;
	Cards tempDeck;	//used for testing perpusos
	private static int playerOneWins= 0;
	private static int playerTwoWins= 0;
	private static int draw=0;
	private static int numGames = 1;

	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.runGame();
		bja.overAllWinner();

	}

	//sets uo the game before playing
	private void setupGame(){
		System.out.println("\n---NEW GAME---");
		System.out.println("Playing Game");
		deck = new Cards("Deck");

		createDeck(deck);
		System.out.println("Deck created");
		playerOne = new Cards("Player One");
		playerTwo = new Cards("Player Two");
	}

	//initial method which starts the game
	private void runGame(){
		boolean isPlaying = true;
		boolean isRunning = true;

		do{

			int playerOneHandValue = 0;
			int playerTwoHandValue = 0;

			setupGame();
			dealCard(deck, playerOne);
			dealCard(deck, playerOne);
			dealCard(deck, playerTwo);
			dealCard(deck, playerTwo);


			playerOneHandValue = getCardsTotalValue(playerOne);
			playerTwoHandValue = getCardsTotalValue(playerTwo);

			System.out.println("Player One starting hand contains: " + playerOne.toString() + "(Value: " + playerOneHandValue + ")");
			System.out.println("Player Two starting hand contains: " + playerTwo.toString() + "(Value: " + playerTwoHandValue + ")\n");

			playerOneHandValue = playGame(playerOneHandValue, playerOne);
			playerTwoHandValue = playGame(playerTwoHandValue, playerTwo);

			if(playerOneHandValue > playerTwoHandValue){
				System.out.println("Player One wins");
				++playerOneWins;

			}else if(playerOneHandValue<playerTwoHandValue){
				System.out.println("Player Two wins");
				++playerTwoWins;
			}else{
				System.out.println("Player have drawn");
				++draw;
			}

			System.out.println("Player one has won: "+ playerOneWins + " times in total");
			System.out.println("Player two has won: "+ playerTwoWins + " times in total");
			System.out.println("Games played so far: " + numGames);

		} while (playAgain());

	}


	//returns correct total cards value
	public static int getCardsTotalValue(Cards cards){
		int totalValue = getCardsValueNoAces(cards);	//get default value of cards
		totalValue = getCardsValueWithAces(cards, totalValue);	//aces recounted as 11 if total value <= 11

		return totalValue;	//return correct recounted value
	}

	//returns default values for all cards - aces are 1 point
	public static int getCardsValueNoAces(Cards cards){
		int value = 0;
		for(Card c : cards.getCards()){
			value += c.getRankInInt();
		}
		return value;
	}

	//counts ace as 11 points if total hand value is <= 11
	public static int getCardsValueWithAces(Cards cards, int value){
		int numAces = getNumAces(cards);
		while(numAces > 0 && value <= 11){
			numAces--;
			value+=10;
		}
		return value;

	}

	//returns number of aces in hand
	public static int getNumAces(Cards cards){
		int numAces = 0;

		for(Card c : cards.getCards()){
			if(c.getRankInInt() == 1){
				numAces++;
			}
		}	
		return numAces;
	}

	
	/**
	 * Deals one card
	 * @param deck from
	 * @param player to
	 */
	void dealCard(Cards deck, Cards player){
		player.getCards().add(deck.getCards().remove(0));
	}

	//checks players hands value and gives additional cards until point limit reached or busted 
	public int playGame(int playerHandValue, Cards player){
		boolean hold = false;

		do{
			int nextCard = 0;

			if(playerHandValue > 16){

				hold = true;

			}else if(playerHandValue < 17){
				dealCard(deck,player);
				//nextCard = player.getCards().get(player.getCards().size()-1).getRankInInt();
				//nextCard = checkForAce(player.getCards().get(player.getCards().size()-1),playerHandValue);
				//System.out.print("Next Card: " +nextCard);
				playerHandValue = getCardsTotalValue(player);
				//System.out.print("New Value : " + playerHandValue);
				System.out.println(player.getName() + " hand contains: " + player.toString() + "(Value: " + playerHandValue + ")");
			}

		}while(!hold);

		if(playerHandValue > 21){
			//player is bust return 0, is always lose)
			System.out.println(player.getName() + " has gone over 21 and busted");
			playerHandValue = 0;
			//System.out.println("New value : " + playerHandValue);
		}

		return playerHandValue;

	}

	//displays winner text at end of game
	public void overAllWinner(){
		System.out.println("Player one has won: "+ playerOneWins + " times");
		System.out.println("Player two has won: "+ playerTwoWins + " times");
		if(playerOneWins > playerTwoWins){
			System.out.println("Player one is overall winner");	

		} else if(playerTwoWins> playerOneWins){
			System.out.println("Player two is overall winner");

		}else{
			System.out.println("Game is a draw");
		}
	}

	//prompts user for 'y' or 'n' to play again
	private boolean playAgain() {
		numGames++;	//game has been played
		boolean isRunning = true;

		if(numGames == 12){
			return false;
		}

		System.out.println("Do you want to play new game?(y/n)");
		Scanner scan = new Scanner(System.in);
		do{
			String input = scan.nextLine();
			if(input.equalsIgnoreCase("n")){
				isRunning = false;


				//TODO integrate display num wins method here


				break;
			}else if(input.equalsIgnoreCase("y")){
				isRunning = true;
				break;
			}else{
				System.out.println("Invalid input, enter 'y' or 'n'");
			}
		}while(true);
		return isRunning;
	}

	
	//creates deck, shuffles deck
	static void createDeck(Cards player){
		Suits suit = Suits.DEFAULT;
		String rank = "";
		for(int s = 0; s < 4; ++s){
			//set suits
			switch(s){
			case 0 : suit = Suits.SPADES; break;
			case 1 : suit = Suits.HEARTS; break;
			case 2 : suit = Suits.CLUBS; break;
			case 3 : suit = Suits.DIAMONDS; break;
			}

			for(int r = 2; r < 15; ++r){
				//set rank
				switch(r){
				case 11 : rank = "J"; break;
				case 12 : rank = "Q"; break;
				case 13 : rank = "K"; break;
				case 14 : rank = "A"; break;
				default : rank = "" + r; break;
				}
				//create card using suit and rank set
				player.getCards().add(new Card(suit, rank));
			}
		}

		Collections.shuffle(player.getCards());
	}




}
