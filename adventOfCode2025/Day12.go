package main

import (
	"strconv"
	"strings"
)

func day12() {
	minAreaNeeded := map[int]int{
		0: 5,
		1: 7,
		2: 7,
		3: 7,
		4: 6,
		5: 7,
	}
	answer := 0
	readLines("Day12.txt", func(s string) {
		a := strings.Split(s, ": ")
		b := strings.Split(a[0], "x")
		c := strings.Split(a[1], " ")

		width, _ := strconv.Atoi(b[0])
		height, _ := strconv.Atoi(b[1])
		totalArea := width * height

		area := 0
		for i, m := range c {
			n, _ := strconv.Atoi(m)
			area += n * minAreaNeeded[i]
		}

		if area < totalArea {
			answer++
			println(s)
			println(totalArea - area)
			println()
		}
	})
	println(answer)
}
