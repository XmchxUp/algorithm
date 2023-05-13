package array

const (
	_defaultCapacity = 10
)

type Queue struct {
	size     int
	start    int
	end      int
	elements [_defaultCapacity]interface{}
}

func (q *Queue) Size() int {
	return q.size
}

func (q *Queue) Empty() bool {
	return q.Size() == 0
}

func (q *Queue) Enqueue(val interface{}) {
	if q.Full() {
		return
	}
	q.elements[q.end] = val
	q.end++
	q.size++
	q.end %= _defaultCapacity
}

func (q *Queue) Dequeue() interface{} {
	if q.Empty() {
		return nil
	}
	res := q.elements[q.start]
	q.start++
	q.start %= _defaultCapacity
	q.size--
	return res
}

func (q *Queue) Full() bool {
	return q.size == _defaultCapacity
}
