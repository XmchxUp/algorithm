#include "sort.h"
#include "test_sort.h"

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

void selectSort(int nums[], int N) {
    for (int i = 0; i < N; i++) {
        int min = i;
        int j;
        for (j = i + 1; j < N; j++) {
            if (nums[j] < nums[min]) {
                min = j;
            }
        }
        swap(nums, min, i);
    }
}

// [l, r)
void mergeSort(int nums[], int l, int r, int temp[]) {
    if (l + 1 >= r) {
        return;
    }

    int mid = ((r - l) >> 1) + l;
    mergeSort(nums, l, mid, temp);
    mergeSort(nums, mid, r, temp);

    int i = l, j = mid, idx = l;
    while (i < mid && j < r) {
        if (nums[i] < nums[j]) {
            temp[idx++] = nums[i++];
        } else {
            temp[idx++] = nums[j++];
        }
    }
    while (i < mid) {
        temp[idx++] = nums[i++];
    }
    while (j < r) {
        temp[idx++] = nums[j++];
    }
    for (i = l; i < r; i++) {
        nums[i] = temp[i];
    }
}

void bubbleSort(int nums[], int N) {
    for (int i = 0; i < N; i++) {
        for (int j = 1; j < N; j++) {
            if (nums[j] < nums[j - 1]) {
                swap(nums, j, j - 1);
            }
        }
    }
}

void insertSort(int nums[], int N) {
    for (int i = 0; i < N - 1; ++i) {
        for (int j = i + 1; j > 0 && nums[j] < nums[j - 1]; j--) {
            swap(nums, j, j - 1);
        }
    }
}