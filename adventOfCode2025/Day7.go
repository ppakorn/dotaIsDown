package main

func day7() {
	var m []string
	readLines("Day7.txt", func(s string) {
		m = append(m, s)
	})

	count := 0
	beams := make(map[int]bool, 1)
	for i := 0; i < len(m[0]); i++ {
		if m[0][i] == 'S' {
			beams[i] = true
			break
		}
	}

	for i := 2; i < len(m); i++ {
		newBeams := make(map[int]bool, 10)
		for k := range beams {
			if m[i][k] == '^' {
				count += 1
				if k == 0 {
					newBeams[1] = true
				} else if k == len(m[0])-1 {
					newBeams[len(m[0])-2] = true
				} else {
					newBeams[k+1] = true
					newBeams[k-1] = true
				}
			} else {
				newBeams[k] = true
			}
		}
		beams = newBeams
	}

	println(count)
}

func day7_2() {
	var m []string
	readLines("Day7.txt", func(s string) {
		m = append(m, s)
	})

	timelines := make([]int, len(m[0]))
	for i := 0; i < len(m[0]); i++ {
		if m[0][i] == 'S' {
			timelines[i] = 1
			break
		}
	}

	for i := 2; i < len(m); i++ {
		newTimelines := make([]int, len(m[0]))
		for j, count := range timelines {
			if count == 0 {
				continue
			}

			if m[i][j] == '^' {
				if j == 0 {
					newTimelines[1] += count
				} else if count == len(m[0])-1 {
					newTimelines[len(m[0])-2] += count
				} else {
					newTimelines[j-1] += count
					newTimelines[j+1] += count
				}
			} else {
				newTimelines[j] += count
			}
		}
		timelines = newTimelines
	}

	sum := 0
	for _, c := range timelines {
		sum += c
	}
	println(sum)
}
