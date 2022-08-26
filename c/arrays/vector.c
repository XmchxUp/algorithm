XArray *xarray_new(int capacity) {
    if (capacity < kDefaultCapacity) {
        capacity = kDefaultCapacity;
    }

    XArray *arr = malloc(sizeof(XArray));
    check_address(arr);

    arr->size = 0;
    arr->capacity = capacity;
    arr->data = malloc(sizeof(int) * capacity);
    check_address(arr->data);

    return arr;
}

void xarray_destroy(XArray *arrayptr) {
    free(arrayptr->data);
    free(arrayptr);
}

void xarray_print(XArray *arrayptr) {
    printf("Size: %d\n", arrayptr->size);
    printf("Capacity: %d\n", arrayptr->capacity);

    printf("Items:\n");
    for (int i = 0; i < arrayptr->size; ++i) {
        printf("%d: %d\n", i, *(arrayptr->data + i));
    }
    printf("---------\n");
}

int xarray_size(XArray *arrayptr) {
    return arrayptr->size;
}

int xarray_capacity(XArray *arrayptr) {
    return arrayptr->capacity;
}

bool xarray_is_empty(XArray *arrayptr) {
    return xarray_size(arrayptr) == 0;
}

int xarray_at(XArray *arrayptr, int index) {
    check_index(arrayptr, index);

    return *(arrayptr->data + index);
}

void xarray_push(XArray *arrayptr, int value) {
    if (xarray_size(arrayptr) == xarray_capacity(arrayptr)) {
        resize(arrayptr, arrayptr->capacity * kGrowthFactor);
    }

    *(arrayptr->data + xarray_size(arrayptr)) = value;
    arrayptr->size++;
}

void xarray_insert(XArray *arrayptr, int index, int value) {
    check_insert_index(arrayptr, index);

    if (xarray_size(arrayptr) == xarray_capacity(arrayptr)) {
        resize(arrayptr, arrayptr->capacity * kGrowthFactor);
    }

    for (int i = xarray_size(arrayptr); i > index; --i) {
        *(arrayptr->data + i) = *(arrayptr->data + i - 1);

    }
    *(arrayptr->data + index) = value;
    arrayptr->size++;
}

void xarray_prepend(XArray *arrayptr, int value) {
    if (xarray_size(arrayptr) == xarray_capacity(arrayptr)) {
        resize(arrayptr, arrayptr->capacity * kGrowthFactor);
    }

    for (int i = xarray_size(arrayptr); i > 0; --i) {
        *(arrayptr->data + i) = *(arrayptr->data + i - 1);
    }
    *(arrayptr->data + 0) = value;
    arrayptr->size++;
}

int xarray_pop(XArray *arrayptr) {
    if (xarray_is_empty(arrayptr)) {
        exit(EXIT_FAILURE);
    }

    int value = *(arrayptr->data + arrayptr->size - 1);
    arrayptr->size--;

    if (arrayptr->capacity > kDefaultCapacity &&
        arrayptr->size * kShrinkFactor < arrayptr->capacity) {
        resize(arrayptr, arrayptr->capacity / kGrowthFactor);
    }

    return value;
}

void xarray_delete(XArray *arrayptr, int index) {
    check_index(arrayptr, index);

    for (int i = index; i < arrayptr->size; ++i) {
        *(arrayptr->data + i) = *(arrayptr->data + i + 1);
    }
    arrayptr->size--;

    if (arrayptr->capacity > kDefaultCapacity &&
        arrayptr->size * kShrinkFactor < arrayptr->capacity) {
        resize(arrayptr, arrayptr->capacity / kGrowthFactor);
    }
}

// looks for value and removes index holding it (even if in multiple places)
void xarray_remove(XArray *arrayptr, int value) {
    for (int i = 0; i < arrayptr->size; ++i) {
        if (*(arrayptr->data + i) == value) {
            xarray_delete(arrayptr, i);
            --i;
        }
    }
}

int xarray_find(XArray *arrayptr, int value) {
    int found_idx = -1;

    for (int i = 0; i < arrayptr->size; ++i) {
        if (*(arrayptr->data + i) == value) {
            found_idx = i;
            break;
        }
    }

    return found_idx;
}


void resize(XArray *arrayptr, int new_capacity) {
    arrayptr->capacity = new_capacity;
    arrayptr->data = realloc(arrayptr->data, sizeof(int) * new_capacity);
    check_address(arrayptr->data);
}

void check_address(void *p) {
    if (p == NULL) {
        printf("allocate memory error\n");
        exit(EXIT_FAILURE);
    }
}

void check_index(XArray *arrayptr, int index) {
    if (index < 0 || index >= xarray_size(arrayptr)) {
        exit(EXIT_FAILURE);
    }
}

void check_insert_index(XArray *arrayptr, int index) {
    if (index < 0 || index > xarray_size(arrayptr)) {
        exit(EXIT_FAILURE);
    }
}


