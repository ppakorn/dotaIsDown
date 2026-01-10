package main

func day10() {
	var grid []string
	readLines("Day10.txt", func(s string) {
		grid = append(grid, s)
	})

	sum := 0
	//memo := init2DArray(len(grid), len(grid[0]), -1)
	for i, a := range grid {
		for j := 0; j < len(a); j++ {
			if grid[i][j] == '0' {
				//peaks := make(map[string]bool)
				//findTrailScore(grid, i, j, peaks)
				//sum += len(peaks)
				sum += findTrailRating(grid, i, j)
			}
		}
	}
	println(sum)
}

var directions = [][2]int{
	{-1, 0},
	{0, 1},
	{1, 0},
	{0, -1},
}

func findTrailScore(grid []string, pos0, pos1 int, peaks map[string]bool) {
	if grid[pos0][pos1] == '9' {
		peaks[posToKey([2]int{pos0, pos1})] = true
		return
	}

	current := grid[pos0][pos1]
	for _, d := range directions {
		newPos0, newPos1 := pos0+d[0], pos1+d[1]
		if newPos0 < 0 || newPos0 >= len(grid) || newPos1 < 0 || newPos1 >= len(grid) {
			continue
		}
		next := grid[newPos0][newPos1]
		if next-current == 1 {
			findTrailScore(grid, newPos0, newPos1, peaks)
		}
	}
}

func findTrailRating(grid []string, pos0, pos1 int) int {
	if grid[pos0][pos1] == '9' {
		return 1
	}

	sum := 0
	current := grid[pos0][pos1]
	for _, d := range directions {
		newPos0, newPos1 := pos0+d[0], pos1+d[1]
		if newPos0 < 0 || newPos0 >= len(grid) || newPos1 < 0 || newPos1 >= len(grid) {
			continue
		}
		next := grid[newPos0][newPos1]
		if next-current == 1 {
			sum += findTrailRating(grid, newPos0, newPos1)
		}
	}
	return sum
}
