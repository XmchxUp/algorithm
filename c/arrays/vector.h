#ifndef VECTOR_H
#define VECTOR_H


#include <stdbool.h>

typedef struct _xm_array {
    int size;
    int capacity;
    int *data;
} XArray;

const int kDefaultCapacity = 16;
const int kGrowthFactor = 2;
const int kShrinkFactor = 4;

// array function
XArray *xarray_new(int capacity);

void xarray_destroy(XArray *arrayptr);

void xarray_print(XArray *arrayptr);

int xarray_size(XArray *arrayptr);

int xarray_capacity(XArray *arrayptr);

bool xarray_is_empty(XArray *arrayptr);

int xarray_at(XArray *arrayptr, int index);

void xarray_push(XArray *arrayptr, int value);

void xarray_insert(XArray *arrayptr, int index, int value);

void xarray_prepend(XArray *arrayptr, int value);

int xarray_pop(XArray *arrayptr);

void xarray_delete(XArray *arrayptr, int index);

// looks for value and removes index holding it (even if in multiple places)
void xarray_remove(XArray *arrayptr, int value);

int xarray_find(XArray *arrayptr, int value);


// tool function
void check_address(void *p);

void check_index(XArray *arrayptr, int index);

void check_insert_index(XArray *arrayptr, int index);

void resize(XArray *arrayptr, int new_capacity);

#endif