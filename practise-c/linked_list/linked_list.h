#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#ifndef PRACTICE_C_LINKED_LIST_H
#define PRACTICE_C_LINKED_LIST_H

struct Node {
    int data;
    struct Node *next;
};

typedef struct XLinkedList {
    int size;
    struct Node *head;
    struct Node *tail;
} XList;

XList *xlist_create();

void xlist_destroy(XList *xlist);

int xlist_size(XList *xlist);

bool xlist_empty(XList *xlist);

int xlist_value_at(XList *xlist, int index);

void xlist_push_front(XList *xlist, int value);

void xlist_pop_front(XList *xlist);

void xlist_push_back(XList *xlist, int value);

int xlist_pop_back(XList *xlist);

int xlist_front(XList *xlist);

int xlist_back(XList *xlist);

void xlist_insert(XList *xlist, int index, int value);

void xlist_erase(XList *xlist, int index);

int xlist_value_n_from_end(XList *xlist, int n);

void xlist_reverse(XList *xlist);

void xlist_remove_value(XList *xlist, int value);

void check_address(void *p);

#endif //PRACTICE_C_LINKED_LIST_H
