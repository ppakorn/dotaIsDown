package main

import (
	"container/heap"
	"math"
	"strconv"
	"strings"
)

func day8() {
	filename := "Day8.txt"
	maxConnection := 1000
	if strings.Contains(filename, "prac") {
		maxConnection = 10
	}

	distances := make(map[float64][][2]int, 1000)
	distanceHeap := &MaxHeapFloat{}
	heap.Init(distanceHeap)
	var boxes [][3]int
	readLines(filename, func(s string) {
		a := strings.Split(s, ",")
		a0, _ := strconv.Atoi(a[0])
		a1, _ := strconv.Atoi(a[1])
		a2, _ := strconv.Atoi(a[2])
		boxes = append(boxes, [3]int{a0, a1, a2})
	})

	for i := 0; i < len(boxes); i++ {
		for j := i + 1; j < len(boxes); j++ {
			d := distance(boxes[i], boxes[j])
			if distanceHeap.Len() < maxConnection || d < distanceHeap.FloatHeap[0] {
				if distanceHeap.Len() >= maxConnection {
					old := heap.Pop(distanceHeap).(float64)
					distances[old] = distances[old][0 : len(distances[old])-1]
				}

				heap.Push(distanceHeap, d)
				if list, ok := distances[d]; ok {
					distances[d] = append(list, [2]int{i, j})
				} else {
					distances[d] = [][2]int{{i, j}}
				}
			}
		}
	}

	println(calGroup(distances))
}

func distance(x, y [3]int) float64 {
	a := (x[0] - y[0]) * (x[0] - y[0])
	b := (x[1] - y[1]) * (x[1] - y[1])
	c := (x[2] - y[2]) * (x[2] - y[2])
	return math.Sqrt(float64(a + b + c))
}

func calGroup(m map[float64][][2]int) int {
	ngroup := 0
	groupToBoxes := make(map[int][]int)
	boxToGroup := make(map[int]int)
	for _, v := range m {
		for _, box := range v {
			b0, b1 := box[0], box[1]
			g0, ok0 := boxToGroup[b0]
			g1, ok1 := boxToGroup[b1]
			if ok0 && ok1 && g0 == g1 {
				continue
			}

			if ok0 && ok1 {
				// Merge group g1 to g0
				for _, b := range groupToBoxes[g1] {
					boxToGroup[b] = g0
				}
				groupToBoxes[g0] = append(groupToBoxes[g0], groupToBoxes[g1]...)
				delete(groupToBoxes, g1)
			} else if ok0 {
				groupToBoxes[g0] = append(groupToBoxes[g0], b1)
				boxToGroup[b1] = g0
			} else if ok1 {
				groupToBoxes[g1] = append(groupToBoxes[g1], b0)
				boxToGroup[b0] = g1
			} else {
				ngroup++
				boxToGroup[b0] = ngroup
				boxToGroup[b1] = ngroup
				groupToBoxes[ngroup] = []int{b0, b1}
			}
		}
	}

	var max1, max2, max3 int // max1 >= max2 >= max3
	for _, group := range groupToBoxes {
		l := len(group)
		if l >= max1 {
			max1, max2, max3 = l, max1, max2
		} else if l >= max2 {
			max2, max3 = l, max2
		} else if l > max3 {
			max3 = l
		}
	}

	return max1 * max2 * max3
}

func day8_2() {
	filename := "Day8.txt"
	distances := make(map[float64][][2]int, 1000)
	distanceHeap := &MinHeapFloat{}
	heap.Init(distanceHeap)
	var boxes [][3]int
	readLines(filename, func(s string) {
		a := strings.Split(s, ",")
		a0, _ := strconv.Atoi(a[0])
		a1, _ := strconv.Atoi(a[1])
		a2, _ := strconv.Atoi(a[2])
		boxes = append(boxes, [3]int{a0, a1, a2})
	})

	for i := 0; i < len(boxes); i++ {
		for j := i + 1; j < len(boxes); j++ {
			d := distance(boxes[i], boxes[j])
			heap.Push(distanceHeap, d)
			if list, ok := distances[d]; ok {
				distances[d] = append(list, [2]int{i, j})
			} else {
				distances[d] = [][2]int{{i, j}}
			}
		}
	}

	b0, b1 := calGroup2(distanceHeap, distances, len(boxes))
	println(boxes[b0][0] * boxes[b1][0])
}

func calGroup2(h *MinHeapFloat, distanceToBoxes map[float64][][2]int, target int) (int, int) {
	ngroup := 0
	groupToBoxes := make(map[int][]int)
	boxToGroup := make(map[int]int)
	maxMember := 0
	for h.Len() > 0 {
		d := heap.Pop(h).(float64)
		for _, box := range distanceToBoxes[d] {
			b0, b1 := box[0], box[1]
			g0, ok0 := boxToGroup[b0]
			g1, ok1 := boxToGroup[b1]
			if ok0 && ok1 && g0 == g1 {
				continue
			}

			if ok0 && ok1 {
				// Merge group g1 to g0
				for _, b := range groupToBoxes[g1] {
					boxToGroup[b] = g0
				}
				groupToBoxes[g0] = append(groupToBoxes[g0], groupToBoxes[g1]...)
				delete(groupToBoxes, g1)
				maxMember = max(maxMember, len(groupToBoxes[g0]))
			} else if ok0 {
				groupToBoxes[g0] = append(groupToBoxes[g0], b1)
				boxToGroup[b1] = g0
				maxMember = max(maxMember, len(groupToBoxes[g0]))
			} else if ok1 {
				groupToBoxes[g1] = append(groupToBoxes[g1], b0)
				boxToGroup[b0] = g1
				maxMember = max(maxMember, len(groupToBoxes[g1]))
			} else {
				ngroup++
				boxToGroup[b0] = ngroup
				boxToGroup[b1] = ngroup
				groupToBoxes[ngroup] = []int{b0, b1}
			}

			if maxMember == target {
				return b0, b1
			}
		}
	}

	return -1, -1
}
