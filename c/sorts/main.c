#include <assert.h>
#include "sort.c"
#include "test_sort.c"


int main(int argc, char *argv[]) {
    int N = 20;
    int nums[] = {1, 3, 5, 7, 2, 6, 4, 8, 9, 2, 8, 7, 6, 0, 3, 5, 9, 4, 1, 0};

    quickSort(nums, 0, N);
    assert(isSorted(nums, N));
    printArray(nums, N);


    return 0;
}