/**
 *	Class that represents Card with it's rank and suit
 *	
 *	Author: Artjoms Porss
 *	
 *	dependencies: Player.java, Card.java, CardGame.java, Deck.java, CardUtil.java, Table.java		
 *	
 * 	Version : 1_3
 */

package cag.card_game_fool_console;


class Card {

	private char suit;
	private String rank;

	Card(){}

	Card(String rank, char suit){
		setSuit(suit);
		setRank(rank);
	}


	public String getRank(){
		return rank;
	}


	public void setRank(String rank){
		this.rank = rank;
	}


	public char getSuit(){
		return suit;
	}


	public void setSuit(char suit){
		this.suit = suit;
	}
	
	// prints rank+suit of a card
	public String toString(){
		return getRank() + getSuit();
	}

}
