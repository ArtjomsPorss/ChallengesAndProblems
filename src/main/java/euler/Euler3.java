package euler;
import java.util.ArrayList;

/**
 * 
 * @author PorssA
 *
 * DESCRIPTION:
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
//TODO refactor to make this more beautiful
public class Euler3 {
	
	private static ArrayList<Long> primeFactors = new ArrayList<>();

	public static void main(String[] args) {
		

		long current = 600851475143L;
		
		while(isNotPrime(current)){
			for (int i = 1; i <= current; i++) {
				if(current % i == 0 && current != i  && i != 1){
					current /= i;
					primeFactors.add((long) i);
					break;
				}
			}
		}
		System.out.println(primeFactors);
		System.out.println("END");
	}

	private static boolean isNotPrime(long current) {

		for (int i = 1; i <= current; i++) {
			if(current % i == 0 && current != i  && i != 1){
				return true;
			}
		}
		
		primeFactors.add(current);
		return false;
	}

}
