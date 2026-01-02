package main

import (
	"regexp"
	"strconv"
	"strings"
)

func day3() {
	sum := 0
	enabled := true
	readLines("Day3.txt", func(s string) {
		re := regexp.MustCompile("mul\\(\\d{1,3},\\d{1,3}\\)|don't\\(\\)|do\\(\\)")
		matches := re.FindAllString(s, -1)
		for _, m := range matches {
			if m == "don't()" {
				enabled = false
			} else if m == "do()" {
				enabled = true
			} else if enabled {
				a := strings.Split(m, ",")
				a0, a1 := a[0][4:], a[1][:len(a[1])-1]
				b0, _ := strconv.Atoi(a0)
				b1, _ := strconv.Atoi(a1)
				sum += b0 * b1
			}
		}
	})

	println(sum)
}
