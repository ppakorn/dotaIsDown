package main

func calGCD(a, b int) int {
	if a < 0 || b < 0 {
		panic("a and b must be > 0")
	}
	q := a / b
	r := a - b*q

	if r == 0 {
		return b
	}
	return calGCD(b, r)
}
