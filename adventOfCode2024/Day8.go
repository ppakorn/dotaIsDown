package main

func day8() {
	ant := make(map[rune][][2]int)
	i := 0
	var size0, size1 int
	readLines("Day8.txt", func(s string) {
		size1 = len(s)
		for j, a := range s {
			if a != '.' {
				ant[a] = append(ant[a], [2]int{i, j})
			}
		}
		i++
	})
	size0 = i

	antinodes := make(map[string]bool)
	for _, nodes := range ant {
		findAntinodes2(nodes, antinodes, size0, size1)
	}

	println(len(antinodes))
}

func findAntinodes(nodes [][2]int, antinodes map[string]bool, size0, size1 int) {
	for m := 0; m < len(nodes)-1; m++ {
		n0 := nodes[m]
		for n := m + 1; n < len(nodes); n++ {
			n1 := nodes[n]

			dif0, dif1 := n0[0]-n1[0], n0[1]-n1[1]
			antinode0 := [2]int{n0[0] + dif0, n0[1] + dif1}
			antinode1 := [2]int{n1[0] - dif0, n1[1] - dif1}

			if 0 <= antinode0[0] && antinode0[0] < size0 && 0 <= antinode0[1] && antinode0[1] < size1 {
				antinodes[posToKey(antinode0)] = true
			}
			if 0 <= antinode1[0] && antinode1[0] < size0 && 0 <= antinode1[1] && antinode1[1] < size1 {
				antinodes[posToKey(antinode1)] = true
			}
		}
	}
}

func findAntinodes2(nodes [][2]int, antinodes map[string]bool, size0, size1 int) {
	if len(nodes) > 1 {
		for _, n := range nodes {
			antinodes[posToKey(n)] = true
		}
	}

	for m := 0; m < len(nodes)-1; m++ {
		n0 := nodes[m]
		for n := m + 1; n < len(nodes); n++ {
			n1 := nodes[n]

			// ทำเศษส่วนอย่างต่ำให้ dif ก่อนเอาไปใช้
			dif0, dif1 := n0[0]-n1[0], n0[1]-n1[1]
			d0, d1 := dif0, dif1
			if d0 < 0 {
				d0 *= -1
			}
			if d1 < 0 {
				d1 *= -1
			}
			gcd := calGCD(d0, d1)
			dif0, dif1 = dif0/gcd, dif1/gcd

			d := 1
			for {
				a0, a1 := n0[0]+d*dif0, n0[1]+d*dif1
				if 0 > a0 || a0 >= size0 || 0 > a1 || a1 >= size1 {
					break
				}

				antinodes[posToKey([2]int{a0, a1})] = true
				d++
			}

			d = 1
			for {
				a0, a1 := n1[0]-d*dif0, n1[1]-d*dif1
				if 0 > a0 || a0 >= size0 || 0 > a1 || a1 >= size1 {
					break
				}

				antinodes[posToKey([2]int{a0, a1})] = true
				d++
			}
		}
	}
}
