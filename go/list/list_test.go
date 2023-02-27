package list_test

import (
	"Algo/list"
	"testing"

	"github.com/magiconair/properties/assert"
)

func TestReverse(t *testing.T) {
	lst := list.NewLinkedList()
	lst.PushBack(3)
	lst.PushBack(2)
	lst.PushBack(1)
	lst.Reverse()
	assert.Equal(t, lst.ValueAt(0), 1)
	assert.Equal(t, lst.ValueAt(1), 2)
	assert.Equal(t, lst.ValueAt(2), 3)
}

func TestPush(t *testing.T) {
	lst := list.NewLinkedList()
	assert.Equal(t, lst.Empty(), true)
	lst.PushFront(1)
	lst.PushFront(0)
	lst.PushBack(3)

	// 0 1 3
	assert.Equal(t, lst.Front(), 0)
	assert.Equal(t, lst.Back(), 3)

	assert.Equal(t, lst.ValueAt(0), 0)
	assert.Equal(t, lst.ValueAt(2), 3)

	assert.Equal(t, lst.PopBack(), 3)
	assert.Equal(t, lst.PopFront(), 0)
	assert.Equal(t, lst.PopBack(), 1)

	assert.Equal(t, lst.Empty(), true)

	// 0 1 3 2
	lst.Insert(0, 1)
	lst.Insert(1, 2)
	lst.Insert(1, 3)
	lst.Insert(0, 0)

	assert.Equal(t, lst.Size(), 4)
	assert.Equal(t, lst.Front(), 0)
	assert.Equal(t, lst.Back(), 2)

	assert.Equal(t, lst.ValueNFromEnd(1), 2)
	assert.Equal(t, lst.ValueNFromEnd(3), 1)
	assert.Equal(t, lst.ValueNFromEnd(4), 0)

	// 1
	lst.Erase(0)
	lst.Erase(1)
	lst.Erase(1)
	assert.Equal(t, lst.Size(), 1)
	assert.Equal(t, lst.Front(), 1)

	lst.PushBack(1)
	lst.RemoveValue(1)
	assert.Equal(t, lst.Size(), 1)
	assert.Equal(t, lst.Front(), 1)

}
