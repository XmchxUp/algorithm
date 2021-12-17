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
    int temp[N];

    arrayCopy(nums2, nums, N);
    arrayCopy(nums3, nums, N);
    arrayCopy(nums4, nums, N);
    arrayCopy(nums5, nums, N);

    printf("Quick Sort：");
    quickSort(nums, 0, N);
    assert(isSorted(nums, N));
    printArray(nums, N);

    printf("Merge Sort：");
    mergeSort(nums2, 0, N, temp);
    assert(isSorted(nums2, N));
    printArray(nums2, N);


    printf("Select Sort：");
    selectSort(nums3, N);
    assert(isSorted(nums3, N));
    printArray(nums3, N);

    printf("Bubble Sort：");
    bubbleSort(nums4, N);
    assert(isSorted(nums4, N));
    printArray(nums4, N);

    printf("Insert Sort：");
    insertSort(nums5, N);
    assert(isSorted(nums5, N));
    printArray(nums5, N);

    return 0;
}