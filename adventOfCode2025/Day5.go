package main

import (
	"sort"
	"strconv"
	"strings"
)

func day5() {
	var validRanges [][2]int64
	var toChecks []int64
	var count int64
	inputRange := true
	readLines("Day5.txt", func(s string) {
		if len(strings.TrimSpace(s)) == 0 {
			inputRange = false
		}

		if inputRange {
			a := strings.Split(s, "-")
			start, _ := strconv.ParseInt(a[0], 10, 64)
			end, _ := strconv.ParseInt(a[1], 10, 64)
			validRanges = append(validRanges, [2]int64{start, end})
		} else {
			i, _ := strconv.ParseInt(s, 10, 64)
			toChecks = append(toChecks, i)
		}
	})

	// Sort ranges
	sort.Slice(validRanges, func(i, j int) bool {
		return validRanges[i][0] < validRanges[j][0]
	})

	validRanges = mergeValidRanges(validRanges)

	// Part 1
	//for _, i := range toChecks {
	//	for _, r := range validRanges {
	//		if r[0] <= i && r[1] >= i {
	//			count += 1
	//			break
	//		}
	//	}
	//}

	for _, r := range validRanges {
		count += r[1] - r[0] + 1
		if count < 0 {
			panic("overflow")
		}
	}

	println(count)
}

func mergeValidRanges(ranges [][2]int64) [][2]int64 {
	var merged [][2]int64

	for _, r := range ranges {
		didMerge := false

		for i, m := range merged {
			if m[0] <= r[0] && r[0] <= m[1] {
				didMerge = true
				merged[i][1] = max(m[1], r[1])
			}
		}

		if !didMerge {
			merged = append(merged, r)
		}
	}

	return merged
}
