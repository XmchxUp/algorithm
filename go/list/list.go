package list

type node struct {
	val  interface{}
	next *node
}

type LinkedList struct {
	size int
	head *node
}

func NewLinkedList() *LinkedList {
	return &LinkedList{
		size: 0,
		head: nil,
	}
}

func (ll *LinkedList) Size() int {
	return ll.size
}

func (ll *LinkedList) Empty() bool {
	return ll.Size() == 0
}

func (ll *LinkedList) ValueAt(idx int) interface{} {
	checkIndex(ll, idx)
	return find(ll, idx).val
}

func (ll *LinkedList) PushFront(val interface{}) {
	newNode := &node{
		val:  val,
		next: ll.head,
	}
	ll.head = newNode
	ll.size++
}

func (ll *LinkedList) PopFront() interface{} {
	checkNotEmpty(ll)
	res := ll.head.val
	ll.head = ll.head.next
	ll.size--
	return res
}

func (ll *LinkedList) PushBack(val interface{}) {
	newNode := &node{
		val:  val,
		next: nil,
	}
	if ll.Empty() {
		ll.head = newNode
	} else {
		last := find(ll, ll.Size()-1)
		last.next = newNode
	}
	ll.size++
}

func (ll *LinkedList) PopBack() interface{} {
	checkNotEmpty(ll)
	if ll.Size() == 1 {
		res := ll.head.val
		ll.head = ll.head.next
		ll.size--
		return res
	}
	prev := find(ll, ll.Size()-2)
	res := prev.next.val
	prev.next = nil
	ll.size--
	return res
}

func (ll *LinkedList) Front() interface{} {
	checkIndex(ll, 0)
	return ll.head.val
}

func (ll *LinkedList) Back() interface{} {
	checkIndex(ll, 0)
	return find(ll, ll.Size()-1).val
}

func (ll *LinkedList) Insert(idx int, val interface{}) {
	if idx > ll.Size() {
		panic("out of range")
	} else if idx == ll.Size() {
		ll.PushBack(val)
	} else if idx == 0 {
		ll.PushFront(val)
	} else {
		prev := find(ll, idx-1)
		newNode := &node{
			val:  val,
			next: prev.next,
		}
		prev.next = newNode
		ll.size++
	}
}

func (ll *LinkedList) Erase(idx int) {
	checkIndex(ll, idx)
	if idx == 0 {
		ll.PopFront()
	} else if idx == ll.Size()-1 {
		ll.PopBack()
	} else {
		prev := find(ll, idx-1)
		prev.next = prev.next.next
		ll.size--
	}
}

func (ll *LinkedList) ValueNFromEnd(n int) interface{} {
	idx := ll.Size() - n
	checkIndex(ll, idx)
	return find(ll, idx).val
}

func (ll *LinkedList) Reverse() {
	p := ll.head
	st := make([]*node, ll.Size())
	for i := 0; i < ll.Size(); i++ {
		st[i] = p
		p = p.next
	}
	dummy := &node{
		next: ll.head,
	}
	prev := dummy
	for len(st) > 0 {
		curr := st[len(st)-1]
		prev.next = curr
		curr.next = nil
		prev = curr
		st = st[:len(st)-1]
	}
	ll.head = dummy.next
}

func (ll *LinkedList) RemoveValue(val interface{}) {
	p := ll.head
	for i := 0; i < ll.Size(); i++ {
		if p.val == val {
			ll.Erase(i)
			return
		}
	}

}

func find(ll *LinkedList, idx int) *node {
	res := ll.head
	for idx > 0 {
		idx--
		res = res.next
	}
	return res
}

func checkIndex(ll *LinkedList, idx int) {
	if idx >= ll.Size() {
		panic("out of range")
	}
}

func checkNotEmpty(ll *LinkedList) {
	if ll.Empty() {
		panic("list is empty")
	}
}
