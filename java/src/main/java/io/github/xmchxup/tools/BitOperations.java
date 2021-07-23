package io.github.xmchxup.tools;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BitOperations {
	public static int myAbs(int a) {
		// logical >>>
		// arithmetic >>
		// x is postive: bit31=0x0
		// x is negative: bit31=0xffffffff=-1
		final int bit31 = a >> 31;
		/// two's complement
		return (a ^ bit31) - bit31;
	}
}
