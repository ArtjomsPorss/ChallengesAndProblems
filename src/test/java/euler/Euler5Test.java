package euler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Euler5Test {

	private Euler5 euler5;

	@Before
	public void setUp() throws Exception {
		euler5 = new Euler5();
	}

	@Test
	public void test() {
		assertFalse(euler5.divisibleBy1to20(2));
		assertTrue(euler5.divisibleBy1to20(232792560));
		assertEquals(0, 232792560 % 1);
		assertEquals(0, 232792560 % 2);
		assertEquals(0, 232792560 % 3);
		assertEquals(0, 232792560 % 4);
		assertEquals(0, 232792560 % 5);
		assertEquals(0, 232792560 % 6);
		assertEquals(0, 232792560 % 7);
		assertEquals(0, 232792560 % 8);
		assertEquals(0, 232792560 % 9);
		assertEquals(0, 232792560 % 10);
		assertEquals(0, 232792560 % 11);
		assertEquals(0, 232792560 % 12);
		assertEquals(0, 232792560 % 13);
		assertEquals(0, 232792560 % 14);
		assertEquals(0, 232792560 % 15);
		assertEquals(0, 232792560 % 16);
		assertEquals(0, 232792560 % 17);
		assertEquals(0, 232792560 % 18);
		assertEquals(0, 232792560 % 19);
		assertEquals(0, 232792560 % 20);
	}

}
