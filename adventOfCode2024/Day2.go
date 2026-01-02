package main

import (
	"slices"
	"strconv"
	"strings"
)

func day2() {
	count := 0
	readLines("Day2.txt", func(s string) {
		a := strings.Split(s, " ")
		var inputs []int
		for _, b := range a {
			c, _ := strconv.Atoi(b)
			inputs = append(inputs, c)
		}

		safe, unsafeIndex := checkSafety(inputs)
		if safe {
			count++
			return
		}

		// Try removing unsafe or its previous and try again
		inputs2 := slices.Clone(inputs)
		inputs2 = append(inputs2[:unsafeIndex], inputs2[unsafeIndex+1:]...)
		safe, _ = checkSafety(inputs2)
		if safe {
			count++
			return
		}

		if unsafeIndex == 0 {
			return
		}
		inputs3 := slices.Clone(inputs)
		inputs3 = append(inputs3[:unsafeIndex-1], inputs3[unsafeIndex:]...)
		safe, _ = checkSafety(inputs3)
		if safe {
			count++
			return
		}
	})

	println(count)
}

func checkSafety(inputs []int) (safe bool, unsafeIndex int) {
	upOrDown := findUpOrDownSafetyOnce(inputs)
	if upOrDown == 0 {
		return
	}

	safe = true
	for i := 1; i < len(inputs); i++ {
		dif := max(inputs[i], inputs[i-1]) - min(inputs[i], inputs[i-1])
		if dif > 3 || dif == 0 ||
			(upOrDown == 1 && inputs[i] < inputs[i-1]) ||
			(upOrDown == -1 && inputs[i] > inputs[i-1]) {
			unsafeIndex = i
			safe = false
			break
		}
	}

	return
}

// 1 = up, -1 = down, 0 = unsafe
func findUpOrDownSafetyOnce(a []int) int {
	// check which get to 2 first, because can be remove once
	countUp := 0
	countDown := 0
	countEqual := 0
	for i := 1; i < len(a); i++ {
		if a[i] == a[i-1] {
			countEqual++
		} else if a[i] > a[i-1] {
			countUp++
		} else {
			countDown++
		}

		if countUp > 1 {
			return 1
		} else if countDown > 1 {
			return -1
		} else if countEqual > 1 {
			return 0
		}
	}

	return 0
}

// 382 too low
