package euler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Euler4Test {
	
	private Euler4 euler4;

	@Before
	public void setUp() throws Exception {
		euler4 = new Euler4();
	}

	@Test
	public void testIsPalindromeForLoop() {
		assertTrue(euler4.isPalindromeForLoop(909));
		assertTrue(euler4.isPalindromeForLoop(9009));
		assertTrue(euler4.isPalindromeForLoop(11811));
		assertFalse(euler4.isPalindromeForLoop(10811));
		assertFalse(euler4.isPalindromeForLoop(10811));
	}

	@Test
	public void testIsPalindromeDoWhileLoop() {
		fail("Not yet implemented");
	}

}
