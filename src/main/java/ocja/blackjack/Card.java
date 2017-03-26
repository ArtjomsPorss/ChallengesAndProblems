/**
 * Holds state, constructors and behaviour of card in the game
 * version: 1.0
 * @author: Artjoms Porss, Colm O'Sullivan, Gregory Harman, Joe
 */
package ocja.blackjack;

public class Card {

	//state
	private Suits suit;
	private String rank;
	
	//constructor
	public Card(Suits suit, String rank){
		this.suit = suit;
		this.rank = rank;
	}
	
	//behaviour
	public Suits getSuit() {
		return suit;
	}
	public void setSuit(Suits suit) {
		this.suit = suit;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public char getSuitInChar(){
		char suitChar = ' ';
		switch(this.suit){
		case SPADES : suitChar = 'S'; break;
		case HEARTS : suitChar = 'H'; break;
		case CLUBS : suitChar = 'C'; break;
		case DIAMONDS : suitChar = 'D'; break;
		}
		return suitChar;
	}
	
	public String toString(){
		String cardName = "";
		cardName = this.rank + getSuitInChar();
		
		return cardName;
	}
	
	
	//returns cards value in blackjack game, aces return 1
	public int getRankInInt(){
		int rank = 0;
		switch(this.rank){
		case "J" : rank = 10; break;
		case "Q" : rank = 10; break;
		case "K" : rank = 10; break;
		case "A" : rank = 1; break;
		default : rank = Integer.parseInt(getRank()); break;
		}
		
		return rank;
	}
}
