package javabootcamp.skippy;

public enum Direction {
	NORTH("North"), 
	SOUTH("South"), 
	EAST("East"),
	WEST("West"),
	UNKNOWN("?"); 
	
	private String dirStr = "";	

	
	Direction(String dir){
		this.dirStr = dir;
	}
	
	
	public static Direction getDirection(int directionNum){
		if(directionNum == 0){
			return Direction.NORTH;
		} else if(directionNum == 1){
			return Direction.SOUTH;
		} else if(directionNum == 2){
			return Direction.EAST;
		} else if(directionNum == 3){
			return Direction.WEST;
		} else {
			return Direction.UNKNOWN;
		}
	}
	
	
	public String toString() {
		return dirStr;
	}
}
