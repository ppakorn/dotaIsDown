package main

import (
	"sort"
	"strconv"
	"strings"
)

func day9() {
	var reds [][2]int
	readLines("Day9.txt", func(s string) {
		a := strings.Split(s, ",")
		a0, _ := strconv.Atoi(a[0])
		a1, _ := strconv.Atoi(a[1])
		reds = append(reds, [2]int{a0, a1})
	})

	maxArea := 0
	for i := 0; i < len(reds); i++ {
		for j := i + 1; j < len(reds); j++ {
			height := reds[i][0] - reds[j][0] + 1
			width := reds[i][1] - reds[j][1] + 1
			area := height * width
			if area < 0 {
				area = -area
			}

			maxArea = max(maxArea, area)
		}
	}
	println(maxArea)
}

func day9_2() {
	var points [][2]int
	var hLines [][2][2]int // left -> right
	var vLines [][2][2]int // top -> bottom

	readLines("Day9.txt", func(s string) {
		a := strings.Split(s, ",")
		a0, _ := strconv.Atoi(a[0])
		a1, _ := strconv.Atoi(a[1])

		current := [2]int{a0, a1}
		if len(points) == 0 {
			points = append(points, current)
			return
		}
		previous := points[len(points)-1]

		if current[0] == previous[0] {
			line := [2][2]int{previous, current}
			if current[1] < previous[1] {
				line = [2][2]int{current, previous}
			}
			vLines = append(vLines, line)
		} else if current[1] == previous[1] {
			line := [2][2]int{previous, current}
			if current[0] < previous[0] {
				line = [2][2]int{current, previous}
			}
			hLines = append(hLines, line)
		} else {
			panic("new shape")
		}

		points = append(points, current)
	})

	// connect first and last points
	first := points[0]
	last := points[len(points)-1]
	if first[0] == last[0] {
		line := [2][2]int{first, last}
		if last[1] < first[1] {
			line = [2][2]int{last, first}
		}
		vLines = append(vLines, line)
	} else {
		line := [2][2]int{first, last}
		if last[0] < first[0] {
			line = [2][2]int{last, first}
		}
		hLines = append(hLines, line)
	}

	// sort
	sort.Slice(hLines, func(i, j int) bool {
		return hLines[i][0][1] < hLines[j][0][1]
	})
	sort.Slice(vLines, func(i, j int) bool {
		return vLines[i][0][0] < vLines[j][0][0]
	})

	//println(checkLineIntersect(hLines, vLines))
	//println(checkDuplicatePoints(points))

	maxArea := 0
	for i := 0; i < len(points); i++ {
		for j := i + 1; j < len(points); j++ {
			x, y := points[i], points[j]
			width := max(x[0], y[0]) - min(x[0], y[0]) + 1
			height := max(x[1], y[1]) - min(x[1], y[1]) + 1
			area := height * width

			if area < maxArea {
				continue
			}

			if !isAreaRed(hLines, vLines, x, y) {
				continue
			}

			maxArea = area
		}
	}
	println(maxArea)
}

// ไม่มีทางเป็นสีขาวทั้งผืนเพราะไม่มีทางที่จะมีรูโหว่ตรงกลาง
// เพราะฉะนั้นเช็คว่าเป็นผืนเดียวกันหมด (ไม่มีการ interrupt) พอ
func isAreaRed(hLines, vLines [][2][2]int, x, y [2]int) bool {
	top, bottom, left, right := findCorners(x, y)
	return noHLinesIntersect(hLines, top, bottom, left, right) && noVLineIntersect(vLines, top, bottom, left, right)
}

func findCorners(x, y [2]int) (top, bottom, left, right int) {
	if x[0] < y[0] {
		left, right = x[0], y[0]
	} else {
		left, right = y[0], x[0]
	}

	if x[1] < y[1] {
		top, bottom = x[1], y[1]
	} else {
		top, bottom = y[1], x[1]
	}

	return
}

func noVLineIntersect(vLines [][2][2]int, top, bottom, left, right int) bool {
	if top == bottom {
		return true
	}
	for _, v := range vLines {
		inHRange := left < v[0][0] && v[0][0] < right
		vIntersectTop := v[0][1] <= top && v[1][1] > top
		vIntersectBottom := v[0][1] < bottom && v[1][1] >= bottom
		if inHRange && (vIntersectTop || vIntersectBottom) {
			return false
		}
	}
	return true
}

func noHLinesIntersect(hLines [][2][2]int, top, bottom, left, right int) bool {
	if left == right {
		return true
	}
	for _, h := range hLines {
		inVRange := top < h[0][1] && h[0][1] < bottom
		hIntersectLeft := h[0][0] <= left && h[1][0] > left
		hIntersectRight := h[0][0] < right && h[1][0] >= right
		if inVRange && (hIntersectLeft || hIntersectRight) {
			return false
		}
	}
	return true
}

func checkLineIntersect(hLine, vLine [][2][2]int) bool {
	for _, h := range hLine {
		for _, v := range vLine {
			if v[0][0] > h[0][0] && v[0][0] < h[1][0] &&
				h[0][1] > v[0][1] && h[0][1] < v[1][1] {
				println("intersect")
				println("h", h[0][0], h[0][1], h[1][0], h[1][1])
				println("v", v[0][0], v[0][1], v[1][0], v[1][1])
				return true
			}
		}
	}
	return false
}

func checkDuplicatePoints(points [][2]int) bool {
	pointSet := make(map[[2]int]bool)
	for _, p := range points {
		if pointSet[p] {
			println("duplicate point", p[0], p[1])
			return true
		}
		pointSet[p] = true
	}
	return false
}

// day9_2 ยังไม่เวิค เปลี่ยนวิธีคิดใหม่ เป็นย่อย map ให้เล็กลง เอาไว้เช็คว่าแดงหมดมั้ย
// เวิคแล้ว ผิดตอนคิด width height เสือกเอา x[0] - y[0] + 1
// ถ้า x[0] มันน้อยกว่า ค่ามันจะติดลบ แล้ว +1 ขึ้นมา ex (20 - 10 + 1) != (10 - 20 + 1)

// ห่างเท่าไรไม่สนใจลดเหลือห่าง 2 หมด
// ได้จุดที่แดงทั้งหมดใหญ่สุดแล้วค่อยไปหาพื้นที่จริง

func day9_3() {
	var points [][2]int
	var point0, point1 []int
	filename := "Day9.txt"
	readLines(filename, func(s string) {
		a := strings.Split(s, ",")
		a0, _ := strconv.Atoi(a[0])
		a1, _ := strconv.Atoi(a[1])
		points = append(points, [2]int{a0, a1})
		point0 = append(point0, a0)
		point1 = append(point1, a1)
	})

	map0, map1 := make(map[int]int), make(map[int]int)
	sort.Ints(point0)
	sort.Ints(point1)

	for i, v := range point0 {
		if _, ok := map0[v]; ok {
			// ถ้าค่าซ้ำ ให้ข้ามไป ห่างไปหน่อยไม่เป็นไร
			continue
		}
		map0[v] = (i + 1) * 2
	}

	for i, v := range point1 {
		if _, ok := map1[v]; ok {
			continue
		}
		map1[v] = (i + 1) * 2
	}

	//max0, max1 := 0, 0
	//m0, m1 := 0, 0
	//for k, v := range map0 {
	//	if v > max0 {
	//		max0 = v
	//		m0 = k
	//	}
	//}
	//for k, v := range map1 {
	//	if v > max1 {
	//		max1 = v
	//		m1 = k
	//	}
	//}
	//println("0:", m0, max0)
	//println("1:", m1, max1)
	//println(point0[len(point0)-1], point1[len(point1)-1])

	size := 1000
	if strings.Contains(filename, "prac") {
		size = 20
	}
	grid := drawGreenLines(points, map0, map1, size)
	floodFill(grid)

	// check grid
	for _, i := range grid {
		for _, j := range i {
			print(j)
		}
		println()
	}

	maxArea := findMaxArea(points, grid, map0, map1)
	println(maxArea)
}

// [x, y] first value = x-axis = column, not row
func drawGreenLines(points [][2]int, map0, map1 map[int]int, size int) [][]int {
	grid := make([][]int, size)
	for i := range grid {
		grid[i] = make([]int, size)
	}
	for i := 0; i < len(points); i++ {
		p := [2]int{map0[points[i][0]], map1[points[i][1]]}
		var q [2]int
		if i == len(points)-1 {
			q = [2]int{map0[points[0][0]], map1[points[0][1]]}
		} else {
			q = [2]int{map0[points[i+1][0]], map1[points[i+1][1]]}
		}

		if p[0] == q[0] {
			// vertical line
			for j := min(p[1], q[1]); j <= max(p[1], q[1]); j++ {
				grid[j][p[0]] = 1
			}
		} else if p[1] == q[1] {
			// horizontal line
			for j := min(p[0], q[0]); j <= max(p[0], q[0]); j++ {
				grid[p[1]][j] = 1
			}
		} else {
			panic("new shape")
		}
	}
	return grid
}

func floodFill(grid [][]int) {
	// start from (0,0)
	queues := [][2]int{{0, 0}}
	directions := [][2]int{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}
	i := 0
	for i < len(queues) {
		current := queues[i]
		for _, d := range directions {
			neighbor := [2]int{current[0] + d[0], current[1] + d[1]}
			if neighbor[0] < 0 || neighbor[0] >= len(grid) ||
				neighbor[1] < 0 || neighbor[1] >= len(grid[0]) {
				continue
			}

			if grid[neighbor[0]][neighbor[1]] == 0 {
				grid[neighbor[0]][neighbor[1]] = 2
				queues = append(queues, neighbor)
			}
		}
		i++
	}
}

func findMaxArea(points [][2]int, grid [][]int, map0, map1 map[int]int) int64 {
	maxArea := int64(0)
	for i := 0; i < len(points)-1; i++ {
		for j := i + 1; j < len(points); j++ {
			x, y := points[i], points[j]
			width := int64(max(x[0], y[0]) - min(x[0], y[0]) + 1)
			height := int64(max(x[1], y[1]) - min(x[1], y[1]) + 1)
			area := height * width

			if area <= maxArea {
				continue
			}

			mappedX0 := map0[x[0]]
			mappedX1 := map1[x[1]]
			mappedY0 := map0[y[0]]
			mappedY1 := map1[y[1]]
			if !isAllRed(grid, mappedX0, mappedX1, mappedY0, mappedY1) {
				continue
			}

			maxArea = area
			println("new max area:", maxArea, "from points", x[0], x[1], "and", y[0], y[1])
		}
	}
	return maxArea
}

func isAllRed(grid [][]int, x0, x1, y0, y1 int) bool {
	for i := min(x0, y0); i <= max(x0, y0); i++ {
		for j := min(x1, y1); j <= max(x1, y1); j++ {
			if grid[j][i] == 2 {
				return false
			}
		}
	}
	return true
}

//1351587876 too low
//1351617690
