package euler;

/**
 * 
 * @author artjoms.porss
 *
 * DESCRIPTION:
 * A palindromic number reads the same both ways. 
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *  
 */
public class Euler4 {

	public static void main(String[] args) {
		new Euler4().findLargestPalindrome();
		
//		boolean isPal = isPalindrome(90109);
//		System.out.println(isPal);
	}

	private void findLargestPalindrome() {
		int palindrome = 0;
		
		for (int i = 999; i >= 100; i--) {
			for (int j = 999; j >= 100; j--) {
				int num = i*j;
				if(isPalindromeForLoop(num)){
					if(palindrome < num){
						palindrome = num;
					}
				}
				num = i - j;
				if(isPalindromeForLoop(num)){
					if(palindrome < num){
						palindrome = num;
					}
				}
				num = i + j;
				if(isPalindromeForLoop(num)){
					if(palindrome < num){
						palindrome = num;
					}
				}
				num = i / j;
				if(isPalindromeForLoop(num)){
					if(palindrome < num){
						palindrome = num;
					}
				}
			}			
		}
		
		System.out.println("Largest palindrome: " + palindrome);
	}

	
	public boolean isPalindromeForLoop(int num) {
		String numStr = num + "";
		
		//a for-loop with two variables
		for (int j2 = numStr.length() -1, i2 = 0; j2 >= 0 && i2 <= numStr.length() -1; --j2, ++i2) {
			if(numStr.charAt(i2) != numStr.charAt(j2)){
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean isPalindromeDoWhileLoop(int num){
		String numStr = num + "";
		
//		a do-while way to do this
		int i = 0;
		int j = numStr.length() -1;
		
		do{
			if(numStr.charAt(i) != numStr.charAt(j)){
				return false;
			}
			++i;
			--j;
		}while(i <= numStr.length() -1 && j >= 0);
		
		return true;
	}
}
