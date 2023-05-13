package array

import "testing"

func TestQueue_Dequeue(t *testing.T) {
	q := Queue{}
	q.Dequeue()
	q.Enqueue("aa")
	q.Enqueue(2)
	q.Enqueue(3)

	val := q.Dequeue()
	if val != "aa" {
		t.Errorf("want: %v, got: %v", "aaa", val)
	}
	val = q.Dequeue()
	if val != 2 {
		t.Errorf("want: %v, got: %v", 2, val)
	}
	q.Enqueue(333)
	val = q.Dequeue()
	if val != 3 {
		t.Errorf("want: %v, got: %v", 3, val)
	}
	q.Dequeue()
	if !q.Empty() {
		t.Errorf("should be empty")
	}
}

func TestQueue_Empty(t *testing.T) {
	q := Queue{}
	if !q.Empty() {
		t.Errorf("should be empty")
	}
	q.Enqueue(1)
	if q.Empty() {
		t.Errorf("should not be empty")
	}
	q.Dequeue()
	if !q.Empty() {
		t.Errorf("should be empty")
	}
	for i := 0; i < 10; i++ {
		q.Enqueue(i)
	}
	if !q.Full() {
		t.Errorf("should be full")
	}
}

func TestQueue_Enqueue(t *testing.T) {
	q := Queue{}
	q.Enqueue(1)
	q.Enqueue(2)
	q.Enqueue(3)
	if q.Empty() {
		t.Errorf("should not be empty")
	}
	if q.Size() != 3 {
		t.Errorf("size want: %d, got: %d", 3, q.Size())
	}
}
