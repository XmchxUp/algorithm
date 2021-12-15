#include "sort.h"

// [l, r)
void quickSort(int nums[], int l, int r) {
    if (l + 1 >= r) {
        return;
    }

    int lo = l, hi = r - 1;
    int key = nums[lo];

    while (lo < hi) {
        while (lo < hi && nums[hi] >= key) {
            hi--;
        }
        nums[lo] = nums[hi];
        while (lo < hi && nums[lo] <= key) {
            lo++;
        }
        nums[hi] = nums[lo];
    }
    nums[lo] = key;
    quickSort(nums, l, lo);
    quickSort(nums, lo + 1, r);
}