package io.github.xmchxup.others;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BinarySearch {
	public static int binarySearch(int[] array, int target) {
		int low = 0;
		int high = array.length - 1;
		int mid;

		// [low,high]
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (target == array[mid]) {
				return mid;
			} else if (target < array[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return -1;
	}

	public static int binarySearchRecursion(int[] array, int target) {
		return helper(array, target, 0, array.length - 1);
	}

	private static int helper(int[] array, int target, int low, int high) {
		if (low > high) return -1;

		int mid = ((high - low) >> 1) + low;
		if (target == array[mid]) {
			return mid;
		} else if (target < array[mid]) {
			return helper(array, target, 0, mid - 1);
		} else {
			return helper(array, target, low + 1, high);
		}
	}
}
