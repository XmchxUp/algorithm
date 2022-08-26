#include <iostream>
#include <cassert>
#include "XmVector.h"
#include "XmVector.cc"

int main() {
    xm::XmVector<int> lst(12);
    lst.Push(1);

    assert(lst.At(0) == 1);
    assert(lst.Pop() == 1);
    assert(lst.IsEmpty());
    assert(lst.Capacity() == 16);

    for (int i = 0; i <= 16; i++) {
        lst.Push(i);
    }
    assert(lst.Capacity() == 32);
    assert(lst.Size() == 17);
    assert(lst.At(16) == 16);
    lst.Delete(15);
    assert(lst.At(15) == 16);

    lst.Push(16);
    assert(lst.Find(16) == 15);
    lst.Remove(16);
    assert(lst.Size() == 15);
    lst.Prepend(-1);
    assert(lst.At(0) == -1);
    for (int i = 0; i < 12; i++) {
        lst.Pop();
    }
    assert(lst.Capacity() == 16);
    return 0;
}
