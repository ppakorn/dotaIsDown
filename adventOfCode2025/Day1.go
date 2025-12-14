package main

import "strconv"

func day1() {
	count0 := 0
	current := 50
	readLines("Day1.txt", func(s string) {
		leftOrRight := s[0]
		amount, err := strconv.Atoi(s[1:])
		if err != nil {
			panic(err)
		}

		if amount >= 100 {
			count0 += amount / 100
			amount = amount % 100
		}

		if leftOrRight == 'L' {
			if current == 0 {
				current = 100
			}
			current -= amount
		} else {
			current += amount
		}

		if current <= 0 {
			count0 += 1
			current = (current + 100) % 100
		} else if current > 99 {
			count0 += 1
			current = current % 100
		}
	})
	print(count0)
}
