#include "test_sort.h"
#include <stdio.h>

void arrayCopy(int dst[], int src[], int N) {
    for (int i = 0; i < N; i++) {
        dst[i] = src[i];
    }
}

bool isSorted(int nums[], int N) {
    for (int i = 1; i < N; i++) {
        if (nums[i - 1] > nums[i]) {
            return false;
        }
    }
    return true;
}

void swap(int nums[], int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}

void printArray(int nums[], int N) {
    for (int i = 0; i < N; i++) {
        printf("%d", nums[i]);
        if (i < N - 1) {
            printf(", ");
        }
    }
    printf("\n");
}