package main

import (
	"fmt"
)

func day6() {
	var grid []string
	sum := 0
	var start [2]int

	rowToRock := make(map[int][]int)
	columnToRock := make(map[int][]int)

	i := 0
	readLines("Day6.txt", func(s string) {
		grid = append(grid, s)

		for j, a := range s {
			if a == '#' {
				rowToRock[i] = append(rowToRock[i], j)
				columnToRock[j] = append(columnToRock[j], i)
			} else if a == '^' {
				start = [2]int{i, j}
			}
		}

		i++
	})

	//gridCount := make([][]int, len(grid))
	//for j := range gridCount {
	//	gridCount[j] = make([]int, len(grid[0]))
	//}

	//travel1(grid, start, gridCount)
	//for _, a := range gridCount {
	//	for _, b := range a {
	//		sum += b
	//	}
	//}

	//println(travel2(grid, start, rowToRock, columnToRock))
	day6Max0 = len(grid)
	day6Max1 = len(grid[0])
	directions = [][2]int{
		{-1, 0},
		{0, 1},
		{1, 0},
		{0, -1},
	}
	println(travel3(grid, start[0], start[1]))

	println(sum)
}

func travel1(grid []string, pos [2]int, gridCount [][]int) {
	directions := [][2]int{
		{-1, 0},
		{0, 1},
		{1, 0},
		{0, -1},
	}
	d := 0

	max0 := len(grid)
	max1 := len(grid[0])

	for {
		direction := directions[d]
		new0, new1 := pos[0]+direction[0], pos[1]+direction[1]

		if new0 < 0 || new0 >= max0 || new1 < 0 || new1 >= max1 {
			break
		}

		if grid[new0][new1] == '#' {
			d = (d + 1) % 4
		} else {
			gridCount[new0][new1] = 1
			pos = [2]int{new0, new1}
		}
	}
}

func travel2(grid []string, pos [2]int, rowToRock, columnToRock map[int][]int) int {
	max0 := len(grid)
	max1 := len(grid[0])
	init := pos

	addRocks := make(map[string]bool)
	// up, right, down, left
	memo := []map[int][][2]int{
		make(map[int][][2]int), make(map[int][][2]int), make(map[int][][2]int), make(map[int][][2]int),
	}

	directions := [][2]int{
		{-1, 0},
		{0, 1},
		{1, 0},
		{0, -1},
	}
	d := 0

	start := pos[0]
	for {
		direction := directions[d]
		new0, new1 := pos[0]+direction[0], pos[1]+direction[1]

		if new0 < 0 || new0 >= max0 || new1 < 0 || new1 >= max1 {
			break
		}

		if grid[new0][new1] == '#' {
			var line, end int
			if d%2 == 0 {
				line, end = pos[1], pos[0]
			} else {
				line, end = pos[0], pos[1]
			}

			memo[d][line] = append(memo[d][line], [2]int{start, end})
			d = (d + 1) % 4
			start = line
		} else {
			// if in front is empty, check if should place a rock to create a loop
			nextRock := findNextRock(pos, d, rowToRock, columnToRock)
			if nextRock[0] > -1 {
				prevD := (d + 3) % 4
				opD := (d + 2) % 4
				beforeRock := [2]int{nextRock[0] + directions[prevD][0], nextRock[1] + directions[prevD][1]}
				if findPreviousPath(memo, opD, beforeRock) {
					addRocks[posToKey([2]int{new0, new1})] = true
				}
			}
			pos = [2]int{new0, new1}
		}
	}

	delete(addRocks, posToKey(init))
	return len(addRocks)
}

func posToKey(a [2]int) string {
	return fmt.Sprint(a)
}

func findNextRock(pos [2]int, currentDirection int, rowToRock, columnToRock map[int][]int) [2]int {
	if currentDirection == 0 {
		// current = up check right
		rocks := rowToRock[pos[0]]
		for _, j := range rocks {
			if j > pos[1] {
				return [2]int{pos[0], j}
			}
		}
	} else if currentDirection == 1 {
		// current = right check bottom
		rocks := columnToRock[pos[1]]
		for _, j := range rocks {
			if j > pos[0] {
				return [2]int{j, pos[1]}
			}
		}
	} else if currentDirection == 2 {
		// current = bottom check left
		rocks := rowToRock[pos[0]]
		for i := len(rocks) - 1; i >= 0; i-- {
			if rocks[i] < pos[1] {
				return [2]int{pos[0], rocks[i]}
			}
		}
	} else if currentDirection == 3 {
		// current = left check up
		rocks := columnToRock[pos[1]]
		for i := len(rocks) - 1; i >= 0; i-- {
			if rocks[i] < pos[0] {
				return [2]int{rocks[i], pos[1]}
			}
		}
	}

	return [2]int{-1, -1}
}

func findPreviousPath(memo []map[int][][2]int, direction int, pos [2]int) bool {
	m := memo[direction]

	var paths [][2]int
	var i int
	if direction%2 == 0 {
		paths = m[pos[1]]
		i = pos[0]
	} else {
		paths = m[pos[0]]
		i = pos[1]
	}

	for _, path := range paths {
		minp, maxp := min(path[0], path[1]), max(path[0], path[1])
		if minp <= i && i <= maxp {
			return true
		}
	}

	return false
}

// ลองวางหินแล้ววิ่งทุกจังหวะดูจริงๆ เลยว่า loop ไหม
// อาจจะมีทางที่ไม่เคยวิ่งผ่านมาก่อน แต่หินมันวางเป็น loop เล็กๆ แล้วส่งยามเข้าไป loop ได้
var directions [][2]int
var day6Max0, day6Max1 int

func travel3(grid []string, pos0, pos1 int) int {
	addRocks := make(map[string]bool)
	runeGrid := convertToRuneGrid(grid)

	// ต้องวางหินตั้งแต่ start
	// เพราะฉะนั้น ถ้าเคยผ่านแล้วไม่ได้วาง มาวางทีหลังไม่ได้
	// เพราะจะทำให้ paradox ยามจะไปติดหินก่อน มาไม่ถึงรอบสอง
	visited := make(map[string]bool)
	d := 0

	for {
		direction := directions[d]
		new0, new1 := pos0+direction[0], pos1+direction[1]

		if new0 < 0 || new0 >= day6Max0 || new1 < 0 || new1 >= day6Max1 {
			break
		}

		key := posToKey2(new0, new1, 0)
		if grid[new0][new1] == '#' {
			d = (d + 1) % 4
		} else {
			runeGrid[new0][new1] = '#'

			if !visited[key] && testLoop(runeGrid, pos0, pos1, d) {
				addRocks[key] = true
			}
			runeGrid[new0][new1] = '.'

			pos0, pos1 = new0, new1
		}

		visited[key] = true
	}

	return len(addRocks)
}

func convertToRuneGrid(src []string) [][]rune {
	// 1. Create the outer slice
	dst := make([][]rune, len(src))

	for i, s := range src {
		// 2. Convert each string to a []rune and assign it
		dst[i] = []rune(s)
	}
	return dst
}

func testLoop(grid [][]rune, pos0, pos1, d int) bool {
	memo := make(map[string]bool)

	for {
		key := posToKey2(pos0, pos1, d)
		if _, ok := memo[key]; ok {
			return true
		}
		memo[key] = true

		direction := directions[d]
		new0, new1 := pos0+direction[0], pos1+direction[1]

		if new0 < 0 || new0 >= day6Max0 || new1 < 0 || new1 >= day6Max1 {
			return false
		}

		if grid[new0][new1] == '#' {
			d = (d + 1) % 4
		} else {
			pos0, pos1 = new0, new1
		}
	}
}

func posToKey2(pos0, pos1, d int) string {
	return fmt.Sprint(pos0, pos1, d)
}

//588 too low
//1354 too high
