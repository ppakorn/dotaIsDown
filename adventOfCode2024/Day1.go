package main

import (
	"strconv"
	"strings"
)

func day1() {
	sum := 0
	var lefts []int
	//var rights []int
	rightCount := make(map[int]int)
	readLines("Day1.txt", func(s string) {
		a := strings.Split(s, "   ")
		l, _ := strconv.Atoi(a[0])
		r, _ := strconv.Atoi(a[1])
		lefts = append(lefts, l)
		//rights = append(rights, r)

		rightCount[r]++
	})

	//sort.Ints(lefts)
	//sort.Ints(rights)
	//
	//for i, l := range lefts {
	//	r := rights[i]
	//	dif := l - r
	//	if dif < 0 {
	//		dif *= -1
	//	}
	//
	//	sum += dif
	//}

	for _, l := range lefts {
		sum += rightCount[l] * l
	}

	println(sum)
}
