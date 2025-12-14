package main

func day4() {
	var m [][]byte
	readLines("Day4.txt", func(s string) {
		m = append(m, []byte(s))
	})

	count := 0
	moved := true
	for moved {
		moved = false
		for y := 0; y < len(m); y++ {
			for x := 0; x < len(m[0]); x++ {
				if m[y][x] == '@' {
					around := countAround(m, x, y)
					if around < 4 {
						count++
						m[y][x] = '.'
						moved = true
					}
				}
			}
		}
	}
	println(count)
}

func countAround(m [][]byte, x, y int) int {
	count := 0
	for dy := -1; dy <= 1; dy++ {
		for dx := -1; dx <= 1; dx++ {
			if dx == 0 && dy == 0 {
				continue
			}

			targetX := x + dx
			targetY := y + dy
			if targetX < 0 || targetY < 0 || targetY >= len(m) || targetX >= len(m[0]) {
				continue
			}

			if m[targetY][targetX] == '@' {
				count++
			}
		}
	}
	return count
}
