XList *xlist_create() {
    XList *xlist = malloc(sizeof(XList));
    check_address(xlist);

    xlist->head = NULL;
    xlist->tail = NULL;
    xlist->size = 0;

    return xlist;
}

void xlist_destroy(XList *xlist) {
    struct Node *cur = xlist->head;
    struct Node *next;

    while (cur != NULL) {
        next = cur->next;
        free(cur);
        cur = next;
    }

    free(xlist);
}

int xlist_size(XList *xlist) {
    return xlist->size;
}

bool xlist_empty(XList *xlist) {
    return xlist_size(xlist) == 0;
}

int xlist_value_at(XList *xlist, int index) {
    struct Node *cur = xlist->head;

    // 考虑 index < 0 或者 index >= size
    int current_index = 0;
    while (cur != NULL && current_index < index) {
        cur = cur->next;
        current_index++;
    }

    if (cur == NULL || current_index > index) {
        printf("Index out of bounds\n");
        exit(EXIT_FAILURE);
    }


    return cur->data;
}

void xlist_push_front(XList *xlist, int value) {
    struct Node *head = xlist->head;

    struct Node *newNode = malloc(sizeof(struct Node));
    check_address(newNode);

    newNode->data = value;
    newNode->next = head;

    xlist->head = newNode;

    if (xlist_empty(xlist)) {
        xlist->tail = newNode;
    }

    xlist->size++;
}

void xlist_pop_front(XList *xlist) {
    if (xlist_empty(xlist)) {
        printf("Cannot pop front of empty list\n");
        exit(EXIT_FAILURE);
    }


    struct Node *temp = xlist->head;
    xlist->head = temp->next;

    if (xlist->size == 1) {
        xlist->tail = NULL;
    }

    xlist->size--;
    free(temp);
}

void xlist_push_back(XList *xlist, int value) {

    struct Node *newNode = malloc(sizeof(struct Node));
    check_address(newNode);

    newNode->data = value;
    newNode->next = NULL;

    if (xlist_empty(xlist)) {
        xlist->head = newNode;
        xlist->tail = newNode;
    } else {
        xlist->tail->next = newNode;
        xlist->tail = newNode;
    }

    xlist->size++;
}

int xlist_pop_back(XList *xlist) {
    if (xlist_empty(xlist)) {
        printf("Cannot pop back of empty list\n");
        exit(EXIT_FAILURE);
    }


    struct Node *temp = xlist->tail;

    struct Node *prev = xlist->head;
    while (prev->next != temp) {
        prev = prev->next;
    }
    prev->next = NULL;


    if (xlist->size == 1) {
        xlist->head = NULL;
    }

    xlist->size--;
    free(temp);
}

int xlist_front(XList *xlist) {
    if (xlist_empty(xlist)) {
        printf("Cannot get front of empty list\n");
        exit(EXIT_FAILURE);
    }

    return xlist->head->data;
}

int xlist_back(XList *xlist) {
    if (xlist_empty(xlist)) {
        printf("Cannot get back of empty list\n");
        exit(EXIT_FAILURE);
    }

    return xlist->tail->data;
}

void xlist_insert(XList *xlist, int index, int value) {
    if (index > xlist->size || index < 0) {
        printf("Index out of bounds\n");
        exit(EXIT_FAILURE);
    }

    struct Node *prev = xlist->head;

    int current_index = 0;
    while (current_index < index - 1) {
        prev = prev->next;
        current_index++;
    }


    struct Node *newNode = malloc(sizeof(struct Node));
    check_address(newNode);

    newNode->data = value;
    newNode->next = NULL;

    if (index == 0) {
        newNode->next = prev;
        xlist->head = newNode;
    } else if (prev == xlist->tail) {
        prev->next = newNode;
        xlist->tail = newNode;
    } else {
        struct Node *next = prev->next;
        prev->next = newNode;
        newNode->next = next;
    }
    xlist->size++;
}

void xlist_erase(XList *xlist, int index) {
    if (index >= xlist->size || index < 0) {
        printf("Index out of bounds\n");
        exit(EXIT_FAILURE);
    }

    struct Node *prev = xlist->head;
    for (int i = 0; i < index - 1; i++) {
        prev = prev->next;
    }

    if (index == 0) {
        xlist->head = xlist->head->next;
        free(prev);
    } else if (prev->next == xlist->tail) {
        struct Node *temp = prev->next;
        prev->next = NULL;
        xlist->tail = prev;
        free(temp);
    } else {
        struct Node *cur = prev->next;
        prev->next = cur->next;
        free(cur);
    }


    xlist->size--;
}

int xlist_value_n_from_end(XList *xlist, int n) {
    if (n <= 0 || n > xlist->size) {
        printf("Param n not a valid value");
        exit(EXIT_FAILURE);
    }

    struct Node *slow = xlist->head;
    struct Node *fast = xlist->head;

    for (int i = 0; i < n; ++i) {
        fast = fast->next;
    }

    while (fast) {
        slow = slow->next;
        fast = fast->next;
    }

    return slow->data;
}

void xlist_reverse(XList *xlist) {
    struct Node *prev = NULL;
    struct Node *cur = xlist->head;
    struct Node *next = NULL;

    xlist->tail = xlist->head;

    while (cur) {
        next = cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
    }

    xlist->head = prev;
}

void xlist_remove_value(XList *xlist, int value) {
    struct Node *cur = xlist->head;
    struct Node *prev = NULL;


    while (cur) {
        if (cur->data == value) {
            if (prev) {
                prev->next = cur->next;
                xlist->tail = cur == xlist->tail ? prev : xlist->tail;
            } else { // remove head
                xlist->head = cur->next;
                xlist->tail = cur == xlist->tail ? NULL : xlist->tail;
            }
            free(cur);
            break;
        }
        prev = cur;
        cur = cur->next;
    }
    xlist->size--;
}

void check_address(void *p) {
    if (p == NULL) {
        printf("allocate memory error\n");
        exit(EXIT_FAILURE);
    }
}