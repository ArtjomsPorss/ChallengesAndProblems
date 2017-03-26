package javabootcamp.skippy;

import java.util.HashMap;
import java.util.Random;

public class Dice {
	private Random rand = new Random();
	private HashMap<String, Integer> statistics = new HashMap<String, Integer>(4);
	private int totalThrows = 0;

	
	//throw, returns n,s,e or w with equal probability
	public Direction getDirection(){
		Direction dir = Direction.getDirection(rand.nextInt(4));
		
		++this.totalThrows;
		
		//store direction
		this.logHop(dir);
		
		return dir;
	}

	
	private void logHop(Direction dir) {
		Integer previousAttempts = statistics.get(dir.toString());
		
		statistics.put(dir.toString(), previousAttempts == null ? 0 : ++previousAttempts);
	}
	
	
	//stats, returns hashtable describing percentage of throws in each direction so far
	public String calculatePercentages(){
		Integer north = statistics.get(Direction.NORTH.toString());
		Integer south = statistics.get(Direction.SOUTH.toString());
		Integer east = statistics.get(Direction.EAST.toString());
		Integer west = statistics.get(Direction.WEST.toString());
		
		if(north != null && north > 0){
			north = 100 * north / totalThrows;
		}
		if(south != null && south > 0){
			south = 100  * south / totalThrows;
		}
		if(east != null && east > 0){
			east = 100 * east / totalThrows;
		}
		if(west != null && west > 0){
			west = 100 * west / totalThrows;
		}
		
		String message = "Die statistics:"
				+ "\n"				
				+ "Total # throws: " + totalThrows 
				+ "\n"
				+ "North: " + north + "% "
				+ "South: " + south + "% "
				+ "East: " + east + "% "
				+ "West: " + west + "% ";
		
		return message;
	}
	
	
}
