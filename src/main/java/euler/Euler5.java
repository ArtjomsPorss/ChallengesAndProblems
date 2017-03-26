package euler;

/**
 * 
 * @author artjoms.porss
 *
 * DESCRIPTION:
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 *  
 */
public class Euler5 {
	
	public static void main(String[] args) {
		new Euler5().run();
	}
	
	public void run(){
		long num = 1;
		
		do{
			if(divisibleBy1to20(num)){
				break;
			}
			++num;
		}while(true);
		
		System.out.println("Smallest number divisible by all numbers from 1 to 20 is : " + num);		
	}

	public boolean divisibleBy1to20(long num) {
		for (int i = 1; i <= 20; i++) {
			if(num % i != 0){
				return false;
			}
		}
		
		return true;
	}
	
}
