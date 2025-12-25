package main

import (
	"strconv"
	"strings"
)

func day11() {
	plan := make(map[string][]string)
	readLines("Day11.txt", func(s string) {
		a := strings.Split(s, ": ")
		target := strings.Split(a[1], " ")
		plan[a[0]] = target
	})

	ans := travel(plan, "svr", make(map[string]int), false, false)
	println(ans)
}

func travel(plan map[string][]string, current string, visited map[string]int, fft, dac bool) int {
	if current == "out" {
		if fft && dac {
			return 1
		} else {
			return 0
		}
	}

	key := visitedKey(current, fft, dac)
	if v, ok := visited[key]; ok {
		return v
	}

	if current == "dac" {
		dac = true
	} else if current == "fft" {
		fft = true
	}

	count := 0
	for _, d := range plan[current] {
		count += travel(plan, d, visited, fft, dac)
	}
	visited[key] = count
	return count
}

func visitedKey(current string, fft, dac bool) string {
	return current + strconv.FormatBool(fft) + strconv.FormatBool(dac)
}
