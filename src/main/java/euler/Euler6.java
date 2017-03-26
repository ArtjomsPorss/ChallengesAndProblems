package euler;

/**
 * 
 * @author artjoms.porss
 *
 * DESCRIPTION:
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum 	of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers
 *  and the square of the sum is 3025 − 385 = 2640.
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 *  
 */
public class Euler6 {

	public static void main(String[] args) {
		
		new Euler6().run();

	}
	
	
	private void run() {
		
		int sumOfSquares = sumOfSquaresOfFirstHundredInts();
		int squareOfSum = squareOfSumOfFirstHundredInts();
		
		System.out.println("Sum of squares: " + sumOfSquares);
		System.out.println("Square of the sum: " + squareOfSum);
		System.out.println("Difference between te sum of squares and square of the sum is: " + (squareOfSum - sumOfSquares));
		
	}	
	
	
	public int sumOfSquaresOfFirstHundredInts(){
		int sum = 0;
		
		for (int i = 1; i <= 100; i++) {
			sum += i * i;
		}
		
		return sum;
	}
	
	
	public int squareOfSumOfFirstHundredInts(){
		int sum = 0;
		
		for (int i = 1; i <= 100; i++) {
			sum += i;
		}
		
		return sum * sum;
	}

}
