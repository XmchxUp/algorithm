
void run_all_tests() {
    test_size();
    test_empty();
    test_push_front();
    test_pop_front();
    test_value_at();
    test_push_back();
    test_pop_back();
    test_front();
    test_back();
    test_insert();
    test_erase();
    test_value_n_from_end();
    test_reverse();
    test_reverse_single();
    test_reverse_empty();
    test_remove_value();
}


void test_size() {
    XList *list = xlist_create();

    assert(xlist_size(list) == 0);
    xlist_push_front(list, 1);
    xlist_push_front(list, 2);
    assert(xlist_size(list) == 2);

    xlist_destroy(list);
}

void test_empty() {
    XList *list = xlist_create();

    xlist_push_front(list, 4);
    xlist_push_front(list, 3);

    assert(xlist_empty(list) == false);

    xlist_pop_front(list);
    xlist_pop_front(list);

    assert(xlist_empty(list) == true);

    xlist_destroy(list);
}

void test_push_front() {
    XList *list = xlist_create();

    xlist_push_front(list, 4);
    xlist_push_front(list, 3);
    xlist_push_front(list, 2);
    assert(xlist_size(list) == 3);
    xlist_push_front(list, 1);
    assert(xlist_size(list) == 4);

    xlist_destroy(list);
}

void test_pop_front() {
    XList *list = xlist_create();

    xlist_push_front(list, 4);
    xlist_push_front(list, 3);
    xlist_push_front(list, 2);

    xlist_pop_front(list);
    xlist_pop_front(list);
    assert(xlist_size(list) == 1);

    xlist_destroy(list);
}

void test_value_at() {
    XList *list = xlist_create();

    xlist_push_front(list, 4);
    xlist_push_front(list, 3);
    xlist_push_front(list, 2);

//    xlist_value_at(list, -1);
    assert(xlist_value_at(list, 2) == 4);
    assert(xlist_value_at(list, 1) == 3);
    assert(xlist_value_at(list, 0) == 2);
//    xlist_value_at(list, 4);

    xlist_destroy(list);
}

void test_push_back() {
    XList *list = xlist_create();

    xlist_push_back(list, 4);
    assert(xlist_size(list) == 1);

    xlist_push_back(list, 3);
    xlist_push_back(list, 2);

    assert(xlist_size(list) == 3);

    assert(xlist_value_at(list, 0) == 4);
    assert(xlist_value_at(list, 1) == 3);
    assert(xlist_value_at(list, 2) == 2);

    xlist_destroy(list);
}

void test_pop_back() {
    XList *list = xlist_create();

    xlist_push_back(list, 4);
    xlist_push_back(list, 3);
    xlist_push_back(list, 2);

    xlist_pop_back(list);
    assert(xlist_size(list) == 2);

    assert(xlist_value_at(list, 0) == 4);
    assert(xlist_value_at(list, 1) == 3);

    xlist_destroy(list);
}

void test_front() {
    XList *list = xlist_create();

    xlist_push_back(list, 4);
    xlist_push_back(list, 3);
    xlist_push_back(list, 2);

    assert(xlist_front(list) == 4);

    xlist_destroy(list);
}

void test_back() {
    XList *list = xlist_create();

    xlist_push_back(list, 4);
    xlist_push_back(list, 3);
    xlist_push_back(list, 2);

    assert(xlist_back(list) == 2);

    xlist_destroy(list);
}

void test_insert() {
    XList *list = xlist_create();

//    xlist_insert(list, -1, 10);
    xlist_insert(list, 0, 10);
    xlist_insert(list, 0, 11);
    xlist_insert(list, 1, 12);
    xlist_insert(list, 2, 13); // 11 12 13 10
    xlist_insert(list, 4, 14); // 11 12 13 10 14
//    xlist_insert(list, 6, 15);

    assert(xlist_size(list) == 5);

    assert(xlist_value_at(list, 0) == 11);
    assert(xlist_value_at(list, 1) == 12);
    assert(xlist_value_at(list, 2) == 13);
    assert(xlist_value_at(list, 3) == 10);
    assert(xlist_value_at(list, 4) == 14);

    xlist_destroy(list);
}

void test_erase() {
    XList *list = xlist_create();


    xlist_push_front(list, 1);
    xlist_erase(list, 0);

    assert(xlist_size(list) == 0);

    xlist_push_back(list, 0);
    xlist_push_back(list, 1);

    xlist_erase(list, 0);
    assert(xlist_front(list) == 1);
    assert(xlist_back(list) == 1);

    xlist_push_back(list, 2);
    xlist_erase(list, 1);

    assert(xlist_front(list) == 1);
    assert(xlist_back(list) == 1);

    xlist_push_front(list, 0); // 0 1
    xlist_push_front(list, -1); // -1 0 1
    xlist_erase(list, 1);

    assert(xlist_value_at(list, 0) == -1);
    assert(xlist_value_at(list, 1) == 1);

    xlist_destroy(list);
}

void test_value_n_from_end() {
    XList *list = xlist_create();

    xlist_push_front(list, 1);
    xlist_push_front(list, 10);
    xlist_push_front(list, 100);
    xlist_push_front(list, 1000);
    xlist_push_front(list, 10000);
    xlist_push_front(list, 100000);

    assert(xlist_value_n_from_end(list, 1) == 1);
    assert(xlist_value_n_from_end(list, 2) == 10);
    assert(xlist_value_n_from_end(list, 3) == 100);
    assert(xlist_value_n_from_end(list, 5) == 10000);

    xlist_destroy(list);
}

void test_reverse() {
    XList *list = xlist_create();

    xlist_push_back(list, 1);
    xlist_push_back(list, 10);
    xlist_push_back(list, 100);
    xlist_push_back(list, 1000);

    xlist_reverse(list);

    assert(xlist_value_at(list, 0) == 1000);
    assert(xlist_value_at(list, 1) == 100);
    assert(xlist_value_at(list, 2) == 10);
    assert(xlist_value_at(list, 3) == 1);

    xlist_destroy(list);
}

void test_reverse_single() {
    XList *list = xlist_create();

    xlist_push_front(list, 1);

    xlist_reverse(list);

    assert(xlist_value_at(list, 0) == 1);

    xlist_destroy(list);
}

void test_reverse_empty() {
    XList *list = xlist_create();

    xlist_reverse(list);

    xlist_destroy(list);
}

void test_remove_value() {
    XList *list = xlist_create();

    xlist_push_front(list, 1);
    xlist_push_front(list, 2);
    xlist_push_front(list, 3); // 3 2 1

    xlist_remove_value(list, 2);
    assert(xlist_front(list) == 3);
    assert(xlist_back(list) == 1);
    assert(xlist_size(list) == 2);

    xlist_remove_value(list, 3); // 1
    assert(xlist_front(list) == 1);
    assert(xlist_back(list) == 1);
    assert(xlist_size(list) == 1);

    xlist_push_front(list, 2); // 2 1
    xlist_remove_value(list, 1);
    assert(xlist_front(list) == 2);
    assert(xlist_back(list) == 2);
    assert(xlist_size(list) == 1);

    xlist_remove_value(list, 1);
    assert(xlist_empty(list) == true);

    xlist_destroy(list);
}