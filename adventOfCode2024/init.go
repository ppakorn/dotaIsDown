package main

import "slices"

func init2DArray(row, column, initValue int) [][]int {
	/*a := make([][]int, row)
	for i := range a {
		a[i] = slices.Repeat(a[i], -1)
	}
	return a*/
	line := slices.Repeat([]int{initValue}, column)
	return slices.Repeat([][]int{line}, row)
}
