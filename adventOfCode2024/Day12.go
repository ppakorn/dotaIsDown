package main

func day12() {
	var garden []string
	readLines("Day12.txt", func(s string) {
		garden = append(garden, s)
	})

	println(findPrice(garden))
}

func findPrice(garden []string) int {
	price := 0
	size0, size1 := len(garden), len(garden[0])
	visited := make([][]bool, size0)
	for i := range visited {
		visited[i] = make([]bool, size1)
	}

	for i, a := range garden {
		for j, _ := range a {
			if !visited[i][j] {
				area, perimeter := 0, 0
				findAreaPerimeter(garden, a[j], visited, i, j, &area, &perimeter)
				price += area * perimeter
			}
		}
	}

	return price
}

func findAreaPerimeter(garden []string, region uint8, visited [][]bool, pos0, pos1 int, area, perimeter *int) {
	if visited[pos0][pos1] {
		return
	}

	*area++
	visited[pos0][pos1] = true

	size0, size1 := len(garden), len(garden[0])

	for _, d := range directions {
		new0, new1 := pos0+d[0], pos1+d[1]
		if new0 < 0 || new0 >= size0 || new1 < 0 || new1 >= size1 {
			*perimeter++
			continue
		}

		newRune := garden[new0][new1]
		if newRune == region {
			findAreaPerimeter(garden, region, visited, new0, new1, area, perimeter)
		} else {
			*perimeter++
		}
	}
}
