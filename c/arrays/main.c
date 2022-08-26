#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include "vector.h"
#include "vector.c"


int main() {
    XArray *arrayptr = xarray_new(1);

    assert(xarray_size(arrayptr) == 0);
    assert(xarray_capacity(arrayptr) == kDefaultCapacity);
    assert(xarray_is_empty(arrayptr) == true);

    xarray_push(arrayptr, 1);
    xarray_push(arrayptr, 2);
    xarray_push(arrayptr, 3);
    // 1 2 3

    assert(xarray_at(arrayptr, 0) == 1);
    assert(xarray_at(arrayptr, 2) == 3);

    xarray_insert(arrayptr, 0, 0);
    xarray_insert(arrayptr, 2, 4);
    // 0 1 4 2 3

    assert(xarray_at(arrayptr, 2) == 4);
    assert(xarray_at(arrayptr, 4) == 3);


    xarray_prepend(arrayptr, 10);
    xarray_prepend(arrayptr, 10);
    xarray_prepend(arrayptr, 10);
    xarray_prepend(arrayptr, 10);
    xarray_remove(arrayptr, 10);
    xarray_delete(arrayptr, 0);
    assert(xarray_pop(arrayptr) == 3);
    assert(xarray_find(arrayptr, 4) == 1);
    assert(xarray_find(arrayptr, 1) == 0);
    assert(xarray_find(arrayptr, 2) == 2);
    assert(xarray_find(arrayptr, 10) == -1);
    assert(xarray_find(arrayptr, 3) == -1);
    // 1 4 2

    for (int i = 10; i < 30; ++i) {
        xarray_prepend(arrayptr, i);
    }

    for (int i = 10; i < 30; ++i) {
        xarray_remove(arrayptr, i);
    }


    assert(xarray_pop(arrayptr) == 2);
    assert(xarray_pop(arrayptr) == 4);
    assert(xarray_pop(arrayptr) == 1);

    xarray_print(arrayptr);
    xarray_destroy(arrayptr);
    return 0;
}
