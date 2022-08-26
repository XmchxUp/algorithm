#include "XmVector.h"
#include <stdexcept>

namespace xm {
    template<typename T>
    XmVector<T>::XmVector(int capacity) {
        capacity_ = std::max(capacity, capacity_);
        size_ = 0;
        elements_ = std::unique_ptr<T[]>(new T[capacity_]);
    }

    template<typename T>
    int XmVector<T>::Size() const {
        return size_;
    }

    template<typename T>
    int XmVector<T>::Capacity() const {
        return capacity_;
    }

    template<typename T>
    bool XmVector<T>::IsEmpty() const {
        return Size() == 0;
    }

    template<typename T>
    T XmVector<T>::At(int index) const {
        if (index < 0 || index >= size_) {
            throw std::out_of_range("invalid index");
        }
        return elements_[index];
    }

    template<typename T>
    void XmVector<T>::Push(T elem) {
        if (size_ == capacity_) {
            resize(capacity_ << 1);
        }
        elements_[size_++] = elem;
    }

    template<typename T>
    void XmVector<T>::Insert(int index, T item) {
        if (size_ == capacity_) {
            resize(capacity_ << 1);
        }
        for (int i = size_; i > index; i--) {
            elements_[i] = elements_[i - 1];
        }
        elements_[index] = item;
        size_++;
    }

    template<typename T>
    void XmVector<T>::Prepend(T item) {
        Insert(0, item);
    }

    template<typename T>
    T XmVector<T>::Pop() {
        if (size_ == 0) {
            throw std::out_of_range("no element");
        }
        T res = elements_[--size_];
        if (capacity_ != kDefaultCapacity &&
            size_ * 4 <= capacity_) {
            resize(capacity_ >> 1);
        }
        return res;
    }

    template<typename T>
    void XmVector<T>::Delete(int index) {
        if (index < 0 || index >= size_) {
            throw std::out_of_range("invalid index");
        }
        for (int i = index; i < size_ - 1; i++) {
            elements_[i] = elements_[i + 1];
        }
        size_--;
        if (size_ * 4 == capacity_) {
            resize(capacity_ >> 1);
        }
    }

    template<typename T>
    void XmVector<T>::Remove(T item) {
        for (int i = 0; i < size_;) {
            if (elements_[i] == item) {
                Delete(i);
            } else {
                i++;
            }
        }
    }

    template<typename T>
    int XmVector<T>::Find(T item) {
        for (int i = 0; i < size_; i++) {
            if (item == elements_[i]) {
                return i;
            }
        }
        return -1;
    }

    template<typename T>
    void XmVector<T>::resize(int new_capacity) {
        std::unique_ptr<T[]> new_elements(new T[new_capacity]);
        for (int i = 0; i < size_; i++) {
            new_elements[i] = elements_[i];
        }
        elements_ = std::move(new_elements);
        capacity_ = new_capacity;
    }

}
