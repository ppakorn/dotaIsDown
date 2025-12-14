package main

import (
	"strconv"
	"strings"
)

var sum_day2 int64

func main_day2() {
	readLines("Day2.txt", day2_2)
	print(sum_day2)
}

func day2_2(s string) {
	c := strings.Split(s, "-")
	start, _ := strconv.ParseInt(c[0], 10, 64)
	end, _ := strconv.ParseInt(c[1], 10, 64)

	for i := start; i <= end; i++ {
		if isSilly2(i) {
			sum_day2 += i
		}
	}
}

func day2_1(s string) {
	c := strings.Split(s, "-")
	if isSilly(c[0]) {
		a, _ := strconv.ParseInt(c[0], 10, 64)
		sum_day2 += a
	}

	i := findNextSilly(c[0])
	for isLowerOrEqual(i, c[1]) {
		a, _ := strconv.ParseInt(i, 10, 64)
		sum_day2 += a
		i = findNextSilly(i)
	}
}

func isLowerOrEqual(s, limit string) bool {
	if len(s) > len(limit) {
		return false
	}

	if len(s) < len(limit) {
		return true
	}

	for i := 0; i < len(s); i++ {
		if s[i] < limit[i] {
			return true
		} else if s[i] > limit[i] {
			return false
		}
	}

	return true
}

func findNextSilly(s string) string {
	if len(s)%2 == 0 {
		return findNextSillyEven(s)
	} else {
		return findNextSillyOdd(s)
	}
}

func findNextSillyEven(s string) string {
	firstHalf := s[:len(s)/2]
	secondHalf := s[len(s)/2:]

	if isLowerOrEqual(firstHalf, secondHalf) {
		firstHalfInt, _ := strconv.ParseInt(firstHalf, 10, 64)
		firstHalfInt++
		newFirstHalf := strconv.FormatInt(firstHalfInt, 10)
		return newFirstHalf + newFirstHalf
	} else {
		return firstHalf + firstHalf
	}
}

func findNextSillyOdd(s string) string {
	zero := len(s) / 2
	newHalf := "1" + strings.Repeat("0", zero)
	return newHalf + newHalf
}

func isSilly(s string) bool {
	if len(s)%2 != 0 {
		return false
	}

	firstHalf := s[:len(s)/2]
	secondHalf := s[len(s)/2:]

	return firstHalf == secondHalf
}

func isSilly2(i int64) bool {
	s := strconv.FormatInt(int64(i), 10)
	s0 := s[0]
	for j := 1; j < len(s); j++ {
		if s[j] != s0 {
			continue
		}

		// check length
		if len(s)%j != 0 {
			continue
		}

		// check repeat
		repeat := s[:j]
		k := j
		for k = j; k < len(s); k += j {
			if s[k:k+j] != repeat {
				break
			}
		}
		if k == len(s) {
			return true
		}
	}
	return false
}
