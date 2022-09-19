struct Queen {
    var x: Int
}

func eightQueens(current: [Int]) {
    
    if current.count == 8 {
        print(current)
    }
    
    // current[0] -> position of row 0
    let indexToFill = current.count
    (0...7).forEach {
        new in
        if checkVertically(current: current, new: new) &&
            checkDiagonallyRight(current: current, new: new, index: indexToFill) &&
            checkDiagonallyLeft(current: current, new: new, index: indexToFill) {
            eightQueens(current: current + [new])
        }
    }
}

func checkVertically(current: [Int], new: Int) -> Bool {
    return !current.contains(new)
}

func checkDiagonallyRight(current: [Int], new: Int, index: Int) -> Bool {
    for (cIndex, c) in current.enumerated() {
        if new > c && index - cIndex == new - c {
            return false
        }
    }
    
    return true
}

func checkDiagonallyLeft(current: [Int], new: Int, index: Int) -> Bool {
    for (cIndex, c) in current.enumerated() {
        if new < c && index - cIndex == c - new {
            return false
        }
    }
    
    return true
}

let t = [0, 2]
checkDiagonallyRight(current: t, new: 7, index: 7)

eightQueens(current: [])
