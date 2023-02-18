package array

import (
	"testing"

	"github.com/magiconair/properties/assert"
)

func TestAt(t *testing.T) {
	a := NewArray(1)
	a.Push(1)
	a.Push("2")
	assert.Equal(t, a.At(1), "2")
}

func TestInsert(t *testing.T) {
	a := NewArray(1)
	for i := 0; i < 16; i++ {
		a.Push(i)
	}
	a.Insert(2, "3")
	assert.Equal(t, a.At(2), "3")
	assert.Equal(t, a.At(3), 2)
	a.Prepend("4")
	assert.Equal(t, a.At(0), "4")
}

func TestDelete(t *testing.T) {
	a := NewArray(32)
	// 0 1 2 3 4
	for i := 0; i < 5; i++ {
		a.Push(i)
	}
	// 0 1 3 4
	a.Delete(2)
	assert.Equal(t, a.At(2), 3)
	assert.Equal(t, a.Size(), 4)
	assert.Equal(t, a.Capacity(), 16)
}

func TestRemove(t *testing.T) {
	a := NewArray(32)
	// 0 1 2 3 4
	for i := 0; i < 5; i++ {
		a.Push(i)
	}
	// 0 1 2 3 4 2
	a.Push(2)
	assert.Equal(t, a.Find(2), 2)
	assert.Equal(t, a.Find(10), -1)
	// 0 1 3 4
	a.Remove(2)
	assert.Equal(t, a.At(2), 3)
	assert.Equal(t, a.Size(), 4)
}

func TestPop(t *testing.T) {
	a := NewArray(31)
	for i := 0; i < 4; i++ {
		a.Push(i)
	}
	assert.Equal(t, a.Pop(), 3)
	assert.Equal(t, a.Capacity(), 16)
}

func TestPush(t *testing.T) {
	a := NewArray(1)
	for i := 0; i < 16; i++ {
		a.Push(i)
	}
	a.Push(1)
	assert.Equal(t, a.capacity, 32)
}

func TestAdjCapacity(t *testing.T) {
	a := NewArray(1)
	assert.Equal(t, a.capacity, 16)
	a = NewArray(31)
	assert.Equal(t, a.capacity, 32)
}
