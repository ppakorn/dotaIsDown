package main

import (
	"fmt"
	"slices"
	"strconv"
	"strings"
)

func day10() {
	sum := 0
	readLines("Day10-prac.txt", func(s string) {
		a := strings.Split(s, " ")

		target := a[0][1 : len(a[0])-1]

		var buttons [][]int
		for i := 1; i < len(a)-1; i++ {
			b := a[i][1 : len(a[i])-1]
			c := strings.Split(b, ",")
			var button []int
			for j := 0; j < len(c); j++ {
				asInt, _ := strconv.Atoi(c[j])
				button = append(button, asInt)
			}
			buttons = append(buttons, button)
		}

		current := strings.Repeat(".", len(target))
		count := pressDFS(target, current, buttons, 0, 0)
		sum += count
	})
	println(sum)
}

func pressDFS(target, current string, buttons [][]int, index, pressCount int) int {
	if target == current {
		return pressCount
	}

	if index == len(buttons) {
		return 10000
	}

	// press the index button
	lights := buttons[index]
	newState := []byte(current)
	for _, l := range lights {
		if current[l] == '#' {
			newState[l] = '.'
		} else {
			newState[l] = '#'
		}
	}

	// not press button
	count1 := pressDFS(target, current, buttons, index+1, pressCount)

	// press button
	count2 := pressDFS(target, string(newState), buttons, index+1, pressCount+1)

	return min(count1, count2)
}

func day10_2() {
	sum := 0
	readLines("Day10-prac.txt", func(s string) {
		a := strings.Split(s, " ")

		targetVoltageStr := strings.Split(a[len(a)-1][1:len(a[len(a)-1])-1], ",")
		var targetVoltage []int
		for _, v := range targetVoltageStr {
			vi, _ := strconv.Atoi(v)
			targetVoltage = append(targetVoltage, vi)
		}

		var buttons [][]int
		for i := 1; i < len(a)-1; i++ {
			b := a[i][1 : len(a[i])-1]
			c := strings.Split(b, ",")
			var button []int
			for j := 0; j < len(c); j++ {
				asInt, _ := strconv.Atoi(c[j])
				button = append(button, asInt)
			}
			buttons = append(buttons, button)
		}

		count := pressVoltageBFS(targetVoltage, buttons)
		sum += count
	})
	println(sum)
}

type voltageState struct {
	voltages []int
	count    int
}

func pressVoltageBFS(targetVoltages []int, buttons [][]int) int {
	l := len(targetVoltages)
	init := voltageState{
		voltages: make([]int, l),
		count:    0,
	}
	queue := []voltageState{init}
	visited := make(map[string]bool)
	for len(queue) > 0 {
		c := queue[0]
		queue = queue[1:]

		// stop pushing buttons
		if isVoltageEqual(c.voltages, targetVoltages) {
			return c.count
		}

		// push all buttons to queue
		for _, button := range buttons {
			newVoltage := slices.Clone(c.voltages)
			for _, b := range button {
				newVoltage[b]++
			}

			key := getVoltageKey(newVoltage)
			if _, ok := visited[key]; ok {
				continue
			}

			// exceed not continue from this state
			if isVoltageExceed(newVoltage, targetVoltages) {
				continue
			}

			newState := voltageState{
				voltages: newVoltage,
				count:    c.count + 1,
			}
			queue = append(queue, newState)
			visited[key] = true
		}
	}
	return -100000000
}

func isVoltageEqual(state, target []int) bool {
	for i := 0; i < len(state); i++ {
		if state[i] != target[i] {
			return false
		}
	}
	return true
}

func isVoltageExceed(state, target []int) bool {
	for i := 0; i < len(state); i++ {
		if state[i] > target[i] {
			return true
		}
	}
	return false
}

func getVoltageKey(state []int) string {
	return fmt.Sprint(state)
}
