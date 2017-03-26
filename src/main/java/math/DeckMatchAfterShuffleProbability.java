package math;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author artjoms.porss
 * 
 * IDEA:
 * Based on Austrian scientists Ludwig Botzmann's explanation of second law of thermodynamics - the law of entropy.
 * Which in short states that state of all atoms will sometime return from disordered to their initial ordered state.
 * Here is it's representation using probability.
 * 
 * SHORT DESCRIPTION: 
 * A deck of cards is created and is shuffled until it returns to it's initial state. 
 * Short statistics such as min, max and average shuffle count (for the deck to reach it's initial state) are then displayed.
 * 
 * LONG DESCRIPTION:
 * A deck of cards created (Currently only 8 cards are used - otherwise runs too long.)
 * A copy of initial deck is created.
 * A copy is compared to initial deck after every shuffle.
 * If it is a match, an amount of shuffles is saved and a next shuffle is performed until total reshuffle count set by user is reached.
 * At the end short statistics are shown: 
 * - what was the least amount of shuffles taken for the deck to return to initial state
 * - what was the max amount of shuffles taken for the deck to return to initial state
 * - what was the average shuffle count
 * 
 *
 */
public class DeckMatchAfterShuffleProbability {

	private static final int TOTAL_RESHUFFLES = 20000;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		long currentShuffle = 0;
		LinkedList<Long> matchList = new LinkedList<>();
		
		/**
		 * PSEUDOCODE
		 * create a deck
		 * create a copy of deck
		 * shuffle a copy, 
		 * increment shuffle counter, 
		 * if original ad copy are in same order , add current shuffle counter to matchList of matches
		 *  else go back to shuffling
		 * 
		 * 
		 * if currentShuffle is less than TOTAL_RESHUFFLES go back to shuffling a copy
		 *  else move to printing statistics
		 * 
		 * print statistics - average shuffle count, min and max counts needed for the deck to reach initial state
		 */
		LinkedList<String> deck = createDeck();
		LinkedList<String> deckCopy = (LinkedList<String>)deck.clone();
		
		/*
		 * for statistics, make a stated amount of re-shuffles to find average amount for this amount of cards in the deck
		 */
		do{
			do{
				Collections.shuffle(deckCopy);
				++currentShuffle;
				System.out.println(currentShuffle + " : current shuffle | decks matched : "+matchList.size()+" time(s)");

			} while (! same(deck,deckCopy));
			
			matchList.add(currentShuffle);
			currentShuffle = 0;
			
		}while(matchList.size() < TOTAL_RESHUFFLES);

		System.out.println(getAverageShuffleCount(matchList) + " shuffles on average was needed for the cards to get back into initial order");
		System.out.println("minimum shuffles : " + min(matchList) + " | max shuffles : " + max(matchList));
		System.out.println(deck);
	}
	
	//get highest number stored in a list
	private static long max(LinkedList<Long> matchList) {
		long max = 0;
		
		for (Long long1 : matchList) {
			if(max < long1){
				max = long1;
			}
		}
		return max;
	}

	//get lowest number stored in a list
	private static long min(LinkedList<Long> matchList) {
		long min = 0;
		
		for (Long long1 : matchList) {
			if(min > long1 || min == 0){
				min = long1;
			}
		}
		return min;
	}

	//get average of all numbers in a list
	private static long getAverageShuffleCount(LinkedList<Long> matchList) {
		long shuffles = 0;
		
		for (Long long1 : matchList) {
			shuffles+=long1;
		}
		return shuffles / matchList.size();
	}

	// check whether both lists have same strings in them
	private static boolean same(LinkedList<String> deck, LinkedList<String> deckCopy) {
		
		for (int i = 0; i < deck.size(); i++) {
			if(!deck.get(i).equals(deckCopy.get(i))){
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates a deck of cards.
	 * Currently only 8 cards are in the deck, otherwise it takes too much time for me to wait 
	 *  until copy of the deck is shuffled to match with original.
	 * If you want to add more cards, decrement initial number "r" in the second for loop
	 *  from 13 to like 12 or 11 if you have extremely fast PC
	 * @return a list of cards
	 */
	public static LinkedList<String> createDeck(){
		LinkedList<String> deck = new LinkedList<>();
		//creates arrayList of cards


		String suit = " ";
		String rank = " ";
		for(int s = 0; s < 4; ++s){			//sets suits
			switch(s){
				case 0 : suit = "H"; break;
				case 1 : suit = "D"; break;
				case 2 : suit = "C"; break;
				case 3 : suit = "S"; break;
			}
			
			for(int r = 13; r < 15; ++r){	//sets ranks
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
				}

				//adds new card with suit and rank for current card
				deck.add(suit+rank);	
			}
		}
		return deck;
	}

}
