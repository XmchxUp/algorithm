package io.github.xmchxup.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class TestBitOperations {
	@Test
	void testSwapXor() {
		int a = 1;
		int b = 2;
		a ^= b;
		b ^= a;
		a ^= b;
		assertEquals(2, a);
		assertEquals(1, b);
	}

	@Test
	void testMyAbs() {
		assertEquals(123, BitOperations.myAbs(-123));
		assertEquals(123, BitOperations.myAbs(123));
	}
}
