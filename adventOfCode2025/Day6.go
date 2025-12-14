package main

import (
	"strconv"
	"strings"
)

func day6() {
	var numbers [][]string
	var operators []string
	readLines("Day6-prac.txt", func(s string) {
		a := strings.Fields(s)
		if a[0][0] >= '9' || a[0][0] <= '0' {
			operators = a
		} else {
			numbers = append(numbers, a)
		}
	})

	var sum int64
	for i := 0; i < len(numbers[0]); i++ {
		var n int64
		if operators[i] == "*" {
			n = 1
		} else if operators[i] == "+" {
			n = 0
		} else {
			panic("unsupported operator")
		}

		for j := 0; j < len(numbers); j++ {
			k, _ := strconv.ParseInt(numbers[j][i], 10, 64)
			if operators[i] == "*" {
				n *= k
			} else if operators[i] == "+" {
				n += k
			}
			if n < 0 {
				println(j)
				panic("overflow")
			}
		}

		sum += n
		if sum < 0 {
			panic("overflow")
		}
	}

	println(sum)
}

func day6_2() {
	var numbers []string
	var operators string
	readLines("Day6.txt", func(s string) {
		if s[0] == '*' || s[0] == '+' {
			operators = s
		} else {
			numbers = append(numbers, s)
		}
	})

	maxLen := 0
	for i := 0; i < len(numbers); i++ {
		maxLen = max(maxLen, len(numbers[i]))
	}

	var result int64
	var op byte
	var sum int64
	for i := 0; i < maxLen; i++ {
		if i < len(operators) && operators[i] != ' ' {
			result, op = restart(operators[i])
		}

		s := ""
		for j := 0; j < len(numbers); j++ {
			if i < len(numbers[j]) && numbers[j][i] != ' ' {
				s += string(numbers[j][i])
			}
		}

		// End of problem
		if s == "" {
			sum += result
			if sum < 0 {
				panic("sum overflow")
			}
			continue
		}

		n, _ := strconv.ParseInt(s, 10, 64)
		if op == '+' {
			result += n
		} else if op == '*' {
			result *= n
		}
		if result < 0 {
			panic("overflow")
		}
	}
	sum += result

	println(sum)
}

func restart(op byte) (int64, byte) {
	if op == '+' {
		return int64(0), op
	} else if op == '*' {
		return int64(1), op
	} else {
		panic("unsupported op")
	}
}
