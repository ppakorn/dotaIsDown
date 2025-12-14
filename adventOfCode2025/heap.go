package main

type IntHeap []int

func (h *IntHeap) Len() int      { return len(*h) }
func (h *IntHeap) Swap(i, j int) { (*h)[i], (*h)[j] = (*h)[j], (*h)[i] }

func (h *IntHeap) Push(x any) {
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

type MinHeap struct {
	IntHeap
}

func (h *MinHeap) Less(i, j int) bool { return h.IntHeap[i] < h.IntHeap[j] } // Min-heap logic

type MaxHeap struct {
	IntHeap
}

func (h *MaxHeap) Less(i, j int) bool {
	return h.IntHeap[i] > h.IntHeap[j]
}

type FloatHeap []float64

func (h *FloatHeap) Len() int      { return len(*h) }
func (h *FloatHeap) Swap(i, j int) { (*h)[i], (*h)[j] = (*h)[j], (*h)[i] }
func (h *FloatHeap) Push(x any) {
	*h = append(*h, x.(float64))
}
func (h *FloatHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

type MaxHeapFloat struct{ FloatHeap }

func (h *MaxHeapFloat) Less(i, j int) bool { return h.FloatHeap[i] > h.FloatHeap[j] }

type MinHeapFloat struct{ FloatHeap }

func (h *MinHeapFloat) Less(i, j int) bool { return h.FloatHeap[i] < h.FloatHeap[j] }
