package javabootcamp.skippy;

import java.util.Scanner;

/**
 * @author artjoms.porss
 * 
 * 
 */
public class SkippyApplication {
	
	public static void main(String[] args) throws InterruptedException {

		//start and run the simulation
		new SkippyApplication().startSimulation();
	}
	
	
	public void startSimulation() throws InterruptedException {

		//input - dimension of the grid, int greater or equal to 1, check this
		int gridSize = getGridSize();
		
		//instantiate kangarooo and grid
		Grid grid = new Grid(gridSize);
		Kangaroo kangaroo = new Kangaroo();	//by default starts at the top left point of the grid
		Dice dice = new Dice();
		
		//output - simple program execution for a grid dimension
		do{
			Point hop = kangaroo.hop(dice.getDirection());
			
			if(grid.lies_outside(hop)){			
				System.out.println("Oops, hit the boundary: (" + hop.getX() + ", " + hop.getY() + ")!" );
			} else {
				kangaroo.move(hop);
				System.out.println("Hopped to: (" + hop.getX() + ", " + hop.getY() + ")!" );
			}
			
		} while (!kangaroo.at_home(grid.getHome())); //exit when kangaroo is at the top-right point in the grid
		
		//print the final report, including dice statistics
		System.out.println(dice.calculatePercentages());		
	}

	
	//get grid size from the user
	private int getGridSize() {
		Scanner scan = new Scanner(System.in);
		int num = 0;
		
		System.out.print("Specify grid size with a number above zero and press ENTER: ");
		String numStr = scan.nextLine().trim();
		try{
			num = Integer.parseInt(numStr);	//will throw exception if user entered letters
			if(num < 1){	//will throw exception if user entered number below 1
				throw new NumberFormatException();
			}
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid number");
			System.exit(1); //FAILURE CODE
		} finally {
			scan.close();
		}
		
		return num;
	}
}
