package javabootcamp.skippy;

/**
 * @author artjoms.porss
 *
 * A square 2D grid that is used for kangaroo to jump around
 * Starting point will always be: 0,0
 * End point will always be: gridsize-1, gridsize-1
 */
public class Grid {
	private int size;
	private Point home;

	public Grid(int size){
		this.size = size;
		this.home = new Point(size-1, size-1);
	}
	
	
	//lies_outside(point) returns true if given point is outside grid
	public boolean lies_outside(Point potential){		
		
		if(potential.getX() > size-1 
				|| potential.getX() < 0
				|| potential.getY() > size-1 
				|| potential.getY() < 0){
			
			return true;
		}
		
		return false;
	}
	
	public int getSize() {
		return size;
	}

	public Point getHome() {
		return home;
	}
}
