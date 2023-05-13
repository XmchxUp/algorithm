package list

type node struct {
	val  interface{}
	next *node
}
type Queue struct {
	size int
	head *node
	tail *node
}

func (q *Queue) Enqueue(val interface{}) {
	newNode := &node{
		val: val,
	}
	if q.Empty() {
		q.head = newNode
		q.tail = newNode
	} else {
		q.tail.next = newNode
		q.tail = newNode
	}
	q.size++
}

func (q *Queue) Dequeue() interface{} {
	if q.Empty() {
		return nil
	}
	res := q.head.val
	if q.size == 1 {
		q.tail = nil
		q.head = nil
	} else {
		q.head = q.head.next
	}
	q.size--
	return res
}

func (q *Queue) Empty() bool {
	return q.size == 0
}

func (q *Queue) Size() int {
	return q.size
}
