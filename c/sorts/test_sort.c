#include "test_sort.h"
#include <stdio.h>

bool isSorted(int nums[], int N) {
    for (int i = 1; i < N; i++) {
        if (nums[i - 1] > nums[i]) {
            return false;
        }
    }
    return true;
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