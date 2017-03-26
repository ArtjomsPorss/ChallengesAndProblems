/**
 * Holds state and behaviour of collection of cards, used for players, deck
 * version: 1.0
 * @author: Artjoms Porss, Colm O'Sullivan, Gregory Harman, Joe
 */
package ocja.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Cards {
	ArrayList<Card> cards = new ArrayList<Card>();

	private String name;	//holds player name
	
	public Cards(String name){
		this.name = name;
	}

	//adds ace to this cards collection - used to test 'all aces' condition
	public void addAce(){
		cards.add(new Card(Suits.SPADES, "A"));
	}
	
	
	public String getName() {
		return name;
	}


	public ArrayList<Card> getCards(){
		return this.cards;
	}
	
	
	//returns string representation of all cards in this object
	public String toString(){
		String cards = " ";
		for(Card c: this.cards){
			cards+= c.toString() + " ";
		}
		
		return cards;
	}
	
}
