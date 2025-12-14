package main

import "strconv"

func day3() {
	sum := 0
	readLines("Day3.txt", func(s string) {
		max1, max2 := -1, -1
		for i := 0; i < len(s)-1; i++ {
			c := int(s[i] - 48)

			if c > max1 {
				max1 = c
				max2 = -1
			} else if c > max2 {
				max2 = c
			}
		}

		last := int(s[len(s)-1] - 48)
		if last > max2 {
			max2 = last
		}

		sum += 10*max1 + max2
	})
	print(sum)
}

func day3_2() {
	var sum int64 = 0
	readLines("Day3.txt", func(s string) {
		maxs := make([]uint8, 12)
		init := s[len(s)-12:]
		for i := 0; i < len(init); i++ {
			maxs[i] = init[i] - 48
		}

		for i := len(s) - 13; i >= 0; i-- {
			c := s[i] - 48
			moveMaxs(maxs, c)
		}

		voltStr := ""
		for _, i := range maxs {
			voltStr += string(i + 48)
		}
		println(voltStr)
		volt, _ := strconv.ParseInt(voltStr, 10, 64)
		sum += volt
	})
	println(sum)
}

func moveMaxs(maxs []uint8, c uint8) {
	for i := 0; i < len(maxs); i++ {
		if c >= maxs[i] {
			temp := maxs[i]
			maxs[i] = c
			c = temp
		} else {
			break
		}
	}
}
