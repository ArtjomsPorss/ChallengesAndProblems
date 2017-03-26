package javabootcamp.skippy;

/**
 * @author artjoms.porss
 * 
 * Instance variable includes his location, initiall 0,0
 */
public class Kangaroo {
	Point currentPosition = new Point(0,0);
	
	//hop - take a random hop in one direction
	public Point hop(Direction dir){		
		return new Point(currentPosition, dir);
	}

	
	//move(direction) - move kangaroo in the given direction
	public void move(Point moveTo) {
		this.currentPosition = new Point(moveTo);
	}
	
	
	//at_home - return true if at home, means reached the target position on the grid  (gridsize-1, gridsize-1)
	public boolean at_home(Point homePoint){
		if(currentPosition.equals(homePoint)){
			return true;
		} else {
			return false;
		}
	}
	
}
