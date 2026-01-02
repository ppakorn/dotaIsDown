package main

import (
	"strconv"
	"strings"
)

func day5() {
	before := make(map[int]map[int]bool)
	orderList := true
	sum := 0
	readLines("Day5.txt", func(s string) {
		if len(s) == 0 {
			orderList = false
		} else if orderList {
			a := strings.Split(s, "|")
			a0, _ := strconv.Atoi(a[0])
			a1, _ := strconv.Atoi(a[1])
			if before[a1] == nil {
				before[a1] = make(map[int]bool)
			}
			before[a1][a0] = true
		} else {
			a := strings.Split(s, ",")
			var inputs []int
			inputSet := make(map[int]bool)
			for _, b := range a {
				i, _ := strconv.Atoi(b)
				inputs = append(inputs, i)
				inputSet[i] = true
			}

			//if checkCorrectOrder(before, inputs, inputSet) {
			//	sum += inputs[len(inputs)/2]
			//println(s)
			//}

			if !checkCorrectOrder(before, inputs, inputSet) {
				inputs = fixOrder(before, inputs, inputSet)
				sum += inputs[len(inputs)/2]
			}
		}
	})

	println(sum)
}

func checkCorrectOrder(before map[int]map[int]bool, inputs []int, inputSet map[int]bool) bool {
	printed := make(map[int]bool)
	for _, i := range inputs {
		for b := range before[i] {
			if !inputSet[b] {
				continue
			}
			if _, ok := printed[b]; !ok {
				return false
			}
		}
		printed[i] = true
	}

	return true
}

func fixOrder(before map[int]map[int]bool, inputs []int, inputSet map[int]bool) []int {
	printed := make(map[int]bool)
	i := 0
	for i < len(inputs) {
		toBePrint := inputs[i]

		// check if all before are prints
		okToPrint := true
		for b := range before[toBePrint] {
			if !inputSet[b] {
				continue
			}
			if _, ok := printed[b]; !ok {
				okToPrint = false
				break
			}
		}
		if okToPrint {
			printed[toBePrint] = true
			i++
			continue
		}

		// not ok to print move to last
		inputs = append(inputs[:i], inputs[i+1:]...)
		inputs = append(inputs, toBePrint)
	}
	return inputs
}
