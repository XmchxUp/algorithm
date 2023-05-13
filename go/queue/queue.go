package queue

type Queue interface {
	Size() int
	Empty() bool
	Enqueue(val interface{})
	Dequeue() interface{}
}
