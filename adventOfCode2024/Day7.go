package main

import (
	"math"
	"strconv"
	"strings"
)

func day7() {
	sum := 0
	readLines("Day7.txt", func(s string) {
		a := strings.Split(s, ": ")
		target, _ := strconv.Atoi(a[0])
		b := strings.Split(a[1], " ")
		var numbers []int
		for _, c := range b {
			i, _ := strconv.Atoi(c)
			numbers = append(numbers, i)
		}
		if checkAddMulConcat(target, numbers, 1, numbers[0]) {
			println(target)
			sum += target
		}
	})
	println(sum)
}

func checkAddMul(target int, numbers []int, i, current int) bool {
	if i == len(numbers) {
		return target == current
	}

	return checkAddMul(target, numbers, i+1, current+numbers[i]) || checkAddMul(target, numbers, i+1, current*numbers[i])
}

func checkAddMulConcat(target int, numbers []int, i, current int) bool {
	if i == len(numbers) {
		return target == current
	}

	digit := len(strconv.Itoa(numbers[i]))
	tens := int(math.Pow10(digit))

	return checkAddMulConcat(target, numbers, i+1, current+numbers[i]) ||
		checkAddMulConcat(target, numbers, i+1, current*numbers[i]) ||
		checkAddMulConcat(target, numbers, i+1, current*tens+numbers[i])
}
