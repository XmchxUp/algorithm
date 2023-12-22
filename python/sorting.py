from random import randint
from timeit import repeat


def run_sorting_algorithm(algorithm, array):
    setup_code = f"from __main__ import {algorithm}" if algorithm != "sorted" else ""

    stmt = f"{algorithm}({array})"

    times = repeat(setup=setup_code, stmt=stmt, repeat=3, number=10)

    print(f"Algorithm: {algorithm}. Minimum execution time: {min(times)}")


def bubble_sort(array):
    n = len(array)
    for i in range(n):
        already_sorted = True
        for j in range(i + 1, n):
            if array[i] > array[j]:
                array[j], array[i] = array[i], array[j]
                already_sorted = False
        if already_sorted:
            break
    return array


def insertion_sort(array, left=0, right=None):
    if right is None:
        right = len(array) - 1
    for i in range(left + 1, right + 1):
        j = i - 1
        val = array[i]
        while j >= left and array[j] > val:
            array[j + 1] = array[j]
            j -= 1
        array[j + 1] = val
    return array


def merge(left, right):
    if len(left) == 0:
        return right

    if len(right) == 0:
        return left

    result = []
    i = 0
    j = 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    if i <= len(left):
        result += left[i:]
    if j <= len(right):
        result += right[j:]
    return result


def merge_sortV2(array):
    if len(array) < 2:
        return array

    mid = len(array) // 2
    return merge(merge_sortV2(array[:mid]), merge_sortV2(array[mid:]))


def merge_sort(array):
    def helper(lo, hi):
        if lo >= hi:
            return

        mid = ((hi - lo) >> 1) + lo

        helper(lo, mid)
        helper(mid + 1, hi)

        i = lo
        j = mid + 1
        idx = 0
        tmp_array = []
        while i <= mid and j <= hi:
            if array[i] < array[j]:
                tmp_array.append(array[i])
                i += 1
            else:
                tmp_array.append(array[j])
                j += 1
            idx += 1

        while i <= mid:
            tmp_array.append(array[i])
            i += 1
            idx += 1
        while j <= hi:
            tmp_array.append(array[j])
            j += 1
            idx += 1
        for k in range(lo, hi + 1):
            array[k] = tmp_array[k - lo]

    helper(0, len(array) - 1)
    return array


def quick_sort(array):
    if len(array) < 2:
        return array
    low, same, high = [], [], []

    pviot = array[0]
    for i in range(len(array)):
        if array[i] < pviot:
            low.append(array[i])
        elif array[i] > pviot:
            high.append(array[i])
        else:
            same.append(array[i])
    return quick_sort(low) + same + quick_sort(high)


def tim_sort(array):
    min_run = 32
    n = len(array)

    for i in range(0, n, min_run):
        insertion_sort(array, i, min(i + min_run - 1, n - 1))

    size = min_run
    while size < n:
        for start in range(0, n, size * 2):
            midpoint = start + size - 1
            end = min((start + size * 2 - 1, n - 1))
            merged_array = merge(
                array[start : midpoint + 1], array[midpoint + 1 : end + 1]
            )
            array[start : start + len(merged_array)] = merged_array
        size *= 2
    return array


ARRAY_LENGTH = 10

if __name__ == "__main__":
    array = [randint(0, 1000) for i in range(ARRAY_LENGTH)]
    print(insertion_sort(array))
    print(tim_sort(array))

    # run_sorting_algorithm(algorithm="sorted", array=array)
    # run_sorting_algorithm(algorithm="bubble_sort", array=array)
    # run_sorting_algorithm(algorithm="insertion_sort", array=array)
    run_sorting_algorithm(algorithm="merge_sort", array=array)
    run_sorting_algorithm(algorithm="merge_sortV2", array=array)
    run_sorting_algorithm(algorithm="quick_sort", array=array)
