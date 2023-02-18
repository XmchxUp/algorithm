package array

type Array struct {
	items    []interface{}
	size     int
	capacity int
}

const (
	_defaultCapacity = 1 << 4
)

// adjCapacity adjust the capacity to power of 2
func adjCapacity(capacity *int) {
	if *capacity < _defaultCapacity {
		*capacity = _defaultCapacity
		return
	}
	cp := 1
	for cp < *capacity {
		cp <<= 1
	}
	*capacity = cp
}

func NewArray(capacity int) *Array {
	adjCapacity(&capacity)
	return &Array{
		items:    make([]interface{}, capacity),
		size:     0,
		capacity: capacity,
	}
}

func (a *Array) Size() int {
	return a.size
}

func (a *Array) Capacity() int {
	return a.capacity
}

func (a *Array) IsEmpty() bool {
	return a.Size() == 0
}

func (a *Array) At(index int) interface{} {
	if !a.isValidIndex(index) {
		panic("index out of range")
	}
	return a.items[index]
}

func (a *Array) Push(item interface{}) {
	if a.size == a.capacity {
		a.resize(a.capacity << 1)
	}
	a.items[a.size] = item
	a.size++
}

func (a *Array) Insert(index int, item interface{}) {
	if !a.isValidIndex(index) {
		return
	}
	if a.size == a.capacity {
		a.resize(a.capacity << 1)
	}
	for i := a.size; i > index; i-- {
		a.items[i] = a.items[i-1]
	}
	a.items[index] = item
}

func (a *Array) Prepend(item interface{}) {
	a.Insert(0, item)
}

func (a *Array) Pop() interface{} {
	item := a.items[a.size-1]
	a.size--
	if a.capacity > _defaultCapacity && a.size*4 <= a.capacity {
		a.resize(a.capacity >> 1)
	}
	return item
}

func (a *Array) Delete(index int) {
	if !a.isValidIndex(index) {
		return
	}
	for i := index; i < a.size-1; i++ {
		a.items[i] = a.items[i+1]
	}
	a.size--
	if a.size*4 <= a.capacity {
		a.resize(a.capacity >> 1)
	}
}

func (a *Array) Remove(item interface{}) {
	for i := 0; i < a.size; {
		if a.items[i] == item {
			a.Delete(i)
		} else {
			i++
		}
	}
}

func (a *Array) Find(item interface{}) int {
	for i := 0; i < a.size; i++ {
		if a.items[i] == item {
			return i
		}
	}
	return -1
}

func (a *Array) resize(capacity int) {
	items := make([]interface{}, capacity)
	copy(items, a.items)
	a.capacity = capacity
	a.items = items
}

func (a *Array) isValidIndex(index int) bool {
	if index < 0 || index >= a.size {
		return false
	}
	return true
}
