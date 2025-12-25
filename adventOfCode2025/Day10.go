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
	readLines("Day10.txt", func(s string) {
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

		stateToSetButtons := calStateToButtonPressOnce(buttons, len(targetVoltage))
		allZeroJolt := make([]int, len(targetVoltage))
		memo := map[string]int{
			getVoltageKey(allZeroJolt): 0,
		}
		result := calBackward(targetVoltage, buttons, stateToSetButtons, memo)
		if result == 100000000 {
			println(s)
		}
		sum += result
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

			// stop pushing buttons
			if isVoltageEqual(c.voltages, targetVoltages) {
				return c.count
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
	return fmt.Sprint(state) == fmt.Sprint(target)
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

func calStateToButtonPressOnce(buttons [][]int, size int) map[string][][]int {
	// return map string like "0,1,1" to [[1,2], [1,3,4]]
	// to indicate that this state of lights can be created from all off (0,0,0) from pressing set of buttons
	stateToSetButtons := make(map[string][][]int)
	initState := make([]int, size)
	calStateDFS(buttons, 0, initState, []int{}, stateToSetButtons)
	return stateToSetButtons
}

func calStateDFS(buttons [][]int, index int, state []int, pressed []int, store map[string][][]int) {
	if index == len(buttons) {
		key := getVoltageKey(state)
		if _, ok := store[key]; ok {
			store[key] = append(store[key], pressed)
		} else {
			store[key] = [][]int{pressed}
		}
		return
	}

	// not press button
	calStateDFS(buttons, index+1, slices.Clone(state), slices.Clone(pressed), store)

	// press button
	newState := slices.Clone(state)
	for _, light := range buttons[index] {
		newState[light] = (newState[light] + 1) % 2
	}
	newPressed := append(pressed, index)
	calStateDFS(buttons, index+1, newState, newPressed, store)
}

func calBackward(state []int, buttons [][]int, stateToSetButtons map[string][][]int, memo map[string]int) int {
	// end of recursion = all zero which is the initial of memo
	key := getVoltageKey(state)
	if value, ok := memo[key]; ok {
		return value
	}

	allEven := true
	for _, t := range state {
		if t%2 > 0 {
			allEven = false
			break
		}
	}

	if allEven {
		newState := cutStateHalf(state)
		result := calBackward(newState, buttons, stateToSetButtons, memo) * 2
		memo[key] = result
		return result
	}

	setButtons := stateToSetButtons[getVoltageKey2(state)]
	minPress := 100000000
	for _, bs := range setButtons {
		newState := slices.Clone(state)
		for _, b := range bs {
			effect := buttons[b]
			for _, e := range effect {
				newState[e]--
			}
		}

		if isStateExceedZero(newState) {
			continue
		}

		// correct one
		//minPress = min(minPress, 2*calBackward(cutStateHalf(newState), buttons, stateToSetButtons, memo)+len(bs))

		// wrong and slow
		minPress = min(minPress, calBackward(cutStateHalf(newState), buttons, stateToSetButtons, memo)+len(bs))
	}
	memo[key] = minPress
	return minPress
}

func isStateExceedZero(state []int) bool {
	for _, s := range state {
		if s < 0 {
			return true
		}
	}
	return false
}

// input must be all even
func cutStateHalf(state []int) []int {
	newState := slices.Clone(state)
	for i, s := range state {
		newState[i] = s / 2
	}
	return newState
}

func getVoltageKey2(state []int) string {
	// change to be light mode (0 or 1)
	lights := slices.Clone(state)
	for i, l := range lights {
		lights[i] = l % 2
	}
	return getVoltageKey(lights)
}

//190017581 too high
//1900017581
// มีอันออกสิบล้าน
