package javabootcamp.skippy;

/**
 * @author artjoms.porss
 *
 * Reperesents a point in the grid.
 */
public class Point {
	private int x;
	private int y;	
	
	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	
	//creates a copy of a point passed in parameter
	public Point(Point other) {
		this.setX(other.getX());
		this.setY(other.getY());
	}
	
	
	public Point(Point other, Direction dir){
		this(other);
		
		if(dir == Direction.NORTH){
			++this.x;
		} else if(dir == Direction.SOUTH){
			--this.x;
		} else if(dir == Direction.EAST){
			--this.y;
		} else if(dir == Direction.WEST){
			++this.y;
		}
	}


	//equals - compares two points for equality
	public boolean equals(Point other){
		if(this.getX() == other.getX() && this.getY() == other.getY()){
			return true;
		}
		return false;
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
