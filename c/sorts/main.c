#include <assert.h>
#include "sort.c"
#include "test_sort.c"


int main(int argc, char *argv[]) {
    int N = 20;
    int nums[] = {1, 3, 5, 7, 2, 6, 4, 8, 9, 2, 8, 7, 6, 0, 3, 5, 9, 4, 1, 0};
    int nums2[N];
    int nums3[N];
    int nums4[N];
    int nums5[N];

    arrayCopy(nums2, nums, N);
    arrayCopy(nums3, nums, N);
    arrayCopy(nums4, nums, N);
    arrayCopy(nums5, nums, N);

    printf("Quick Sort");
    quickSort(nums, 0, N);
    assert(isSorted(nums, N));
    printArray(nums, N);


    printf("Select Sort");
    selectSort(nums2, N);
    assert(isSorted(nums, N));
    printArray(nums, N);

    return 0;
}