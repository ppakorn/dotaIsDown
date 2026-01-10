package main

func day9() {
	//var disk []int
	sum := 0
	i := 0
	//id := 0
	//size := 0

	// start and size
	var ids [][2]int // id = index
	var spaces [][2]int
	readLines("Day9.txt", func(s string) {
		//for a, r := range s {
		//	n := int(r - '0')
		//	size += n
		//	if a%2 == 0 {
		//		for j := 0; j < n; i, j = i+1, j+1 {
		//			disk[i] = id
		//		}
		//		id++
		//	} else {
		//		for j := 0; j < n; i, j = i+1, j+1 {
		//			disk[i] = -1
		//		}
		//	}
		//}

		for a, r := range s {
			n := int(r - '0')
			if a%2 == 0 {
				ids = append(ids, [2]int{i, n})
			} else {
				spaces = append(spaces, [2]int{i, n})
			}
			i += n
		}
	})

	//disk = make([]int, size)
	//
	//// find first space
	//j := findSpace(disk, 0)
	//
	//// last number
	//i--
	//
	//for i > j {
	//	disk[j], disk[i] = disk[i], 0
	//	j = findSpace(disk, j+1)
	//	i = findNumberReverse(disk, i-1)
	//}
	//
	////fmt.Printf("%d", disk)
	//
	//for k := 0; k < size; k++ {
	//	sum += k * disk[k]
	//}

	defrag(ids, spaces)
	sum = checksum2(ids)
	println(sum)
}

func findSpace(disk []int, from int) int {
	j := from
	for ; j < len(disk); j++ {
		if disk[j] == -1 {
			return j
		}
	}
	return len(disk)
}

func findNumberReverse(disk []int, from int) int {
	j := from
	for ; j >= 0; j-- {
		if disk[j] > -1 {
			return j
		}
	}
	return -1
}

func findLeftMostEnoughSpace(spaces [][2]int, need int) int {
	for i, s := range spaces {
		if s[1] >= need {
			return i
		}
	}
	return -1
}

func defrag(ids [][2]int, spaces [][2]int) {
	for i := len(ids) - 1; i >= 0; i-- {
		file := ids[i]
		size := file[1]

		spaceIndex := findLeftMostEnoughSpace(spaces, size)
		if spaceIndex == -1 || spaces[spaceIndex][0] >= file[0] {
			continue
		}

		ids[i][0] = spaces[spaceIndex][0]
		spaces[spaceIndex][0] += size
		spaces[spaceIndex][1] -= size
	}
}

func checksum2(ids [][2]int) int {
	sum := 0
	for id, v := range ids {
		for i := v[0]; i < v[0]+v[1]; i++ {
			sum += id * i
		}
	}
	return sum
}
