package main

func day4() {
	var grid []string
	readLines("Day4.txt", func(s string) {
		grid = append(grid, s)
	})

	count := 0
	for i, m := range grid {
		for j, n := range m {
			//if n == 'X' {
			//	count += findXMAS1FromX(grid, i, j)
			//}

			if n == 'A' && findXMAS2FromA(grid, i, j) {
				count++
			}
		}
	}

	println(count)
}

func findXMAS1FromX(grid []string, i, j int) int {
	c := 0

	maxI := len(grid) - 4
	maxJ := len(grid[0]) - 4

	// horizontal
	if j <= maxJ && grid[i][j:j+4] == "XMAS" {
		c++
	}

	// horizontal reverse
	if j >= 3 && grid[i][j-3:j+1] == "SAMX" {
		c++
	}

	// vertical
	if i <= maxI && grid[i+1][j] == 'M' && grid[i+2][j] == 'A' && grid[i+3][j] == 'S' {
		c++
	}

	// vertical reverse
	if i >= 3 && grid[i-1][j] == 'M' && grid[i-2][j] == 'A' && grid[i-3][j] == 'S' {
		c++
	}

	// north-west
	if i >= 3 && j >= 3 && grid[i-1][j-1] == 'M' && grid[i-2][j-2] == 'A' && grid[i-3][j-3] == 'S' {
		c++
	}

	// north-east
	if i >= 3 && j <= maxJ && grid[i-1][j+1] == 'M' && grid[i-2][j+2] == 'A' && grid[i-3][j+3] == 'S' {
		c++
	}

	// south-east
	if i <= maxI && j <= maxJ && grid[i+1][j+1] == 'M' && grid[i+2][j+2] == 'A' && grid[i+3][j+3] == 'S' {
		c++
	}

	// south-west
	if i <= maxI && j >= 3 && grid[i+1][j-1] == 'M' && grid[i+2][j-2] == 'A' && grid[i+3][j-3] == 'S' {
		c++
	}

	return c
}

func findXMAS2FromA(grid []string, i, j int) bool {
	if i < 1 || i > len(grid)-2 ||
		j < 1 || j > len(grid[0])-2 {
		return false
	}

	nwTose := false
	neTosw := false

	if (grid[i-1][j-1] == 'M' && grid[i+1][j+1] == 'S') || (grid[i-1][j-1] == 'S' && grid[i+1][j+1] == 'M') {
		nwTose = true
	}

	if (grid[i-1][j+1] == 'M' && grid[i+1][j-1] == 'S') || (grid[i-1][j+1] == 'S' && grid[i+1][j-1] == 'M') {
		neTosw = true
	}

	return nwTose && neTosw
}
