package main

import (
	"fmt"
	"strconv"
	"strings"
)

func day11() {
	var stones []int
	readLines("Day11.txt", func(s string) {
		a := strings.Split(s, " ")
		for _, b := range a {
			n, _ := strconv.Atoi(b)
			stones = append(stones, n)
		}
	})

	//for i := 0; i < 75; i++ {
	//	stones = blink(stones)
	//}
	//println(len(stones))

	sum := 0
	memo := make(map[string]int)
	for _, a := range stones {
		sum += blinkFunction(a, 75, memo)
	}
	println(sum)
}

func blink(stones []int) []int {
	var newStones []int
	for _, n := range stones {
		nStr := strconv.Itoa(n)
		if n == 0 {
			newStones = append(newStones, 1)
		} else if len(nStr)%2 == 0 {
			l := len(nStr) / 2
			a, _ := strconv.Atoi(nStr[:l])
			b, _ := strconv.Atoi(nStr[l:])
			newStones = append(newStones, a)
			newStones = append(newStones, b)
		} else {
			newStones = append(newStones, n*2024)
		}
	}
	return newStones
}

func keyxn(x, n int) string {
	return fmt.Sprintf("%d,%d", x, n)
}

func blinkFunction(x, n int, memo map[string]int) int {
	if n == 0 {
		return 1
	}

	key := keyxn(x, n)
	if value, ok := memo[key]; ok {
		return value
	}

	xStr := strconv.Itoa(x)
	result := 0
	if x == 0 {
		result = blinkFunction(1, n-1, memo)
	} else if len(xStr)%2 == 0 {
		l := len(xStr) / 2
		a, _ := strconv.Atoi(xStr[:l])
		b, _ := strconv.Atoi(xStr[l:])
		result = blinkFunction(a, n-1, memo) + blinkFunction(b, n-1, memo)
	} else {
		result = blinkFunction(2024*x, n-1, memo)
	}

	memo[key] = result
	return result
}
