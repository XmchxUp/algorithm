#ifndef PRACTICE_CPP_XMVECTOR_H
#define PRACTICE_CPP_XMVECTOR_H


#include <memory>

namespace xm {
    template<typename T>
    class XmVector {
    private:
        const static int kDefaultCapacity = 16;

        int size_{0};
        int capacity_{kDefaultCapacity};
        std::unique_ptr<T[]> elements_;

    public:
        XmVector(int capacity);
        ~XmVector() = default;
        int Size() const;
        int Capacity() const;
        bool IsEmpty() const;
        T At(int index) const;
        void Push(T elem);
        void Insert(int index, T item);
        void Prepend(T item);
        T Pop();
        void Delete(int index);
        void Remove(T item);
        int Find(T item);
    private:
        void resize(int new_capacity);
    };
}
#endif //PRACTICE_CPP_XMVECTOR_H
