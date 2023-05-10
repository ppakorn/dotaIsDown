function execute() {
    const filename = 'day22.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    const grid = Array.from({length: 7}, () => []);
    let i = 1
    let codes = ""
    lines.forEach(line => {
        if (!line) {
            i += 1
            return
        }
        if (i <= 6) {
            grid[i].push(line)
        } else {
            codes = line
        }
    })
    l = grid[1].length

    run(grid, codes)
}
let l = -1

function extractCode(code) {
    const regex = /(\d+|[A-Z])/g;
    return code.match(regex);
}

function run(grid, code) {
    let point = [1, 0, 0, 0]               // board, facing, row, column
    const steps = extractCode(code)

    steps.forEach(step => {
        console.log(`step=${step} point=${point}`)
        const n = Number(step)
        if (n) {
            walk(grid, point, n)
        } else {
            point[1] = turn(point[1], step)
        }
    })

    // console.log(point)
    // console.log(facing)
    console.log(point)
}

function walk(grid, point, n) {
    while (n > 0) {
        console.log(point, n)
        // mutate point which is board, facing, row, column
        switch (point[1]) {
            case 0:
                n = walkRight(grid, point, n)
                break
            case 1:
                n = walkDown(grid, point, n)
                break
            case 2:
                n = walkLeft(grid, point, n)
                break
            case 3:
                n = walkUp(grid, point, n)
                break
        }
    }
}

function walkRight(grid, point, n) {
    const board = point[0]
    const facing = point[1]
    const row = point[2]
    let column = point[3]
    for (let i = 0; i < n; i++) {
        if (column === l - 1) {
            // newBoard = board, facing, row, column
            const newBoard = changeBoard(board, facing, row, column)
            if (grid[newBoard[0]][newBoard[2]][newBoard[3]] === '#') {
                break
            } else {
                point.splice(0, point.length, ...newBoard)
                return n - 1 - i
            }
        } else if (grid[board][row][column + 1] === '#') {
            break
        } else if (grid[board][row][column + 1] === '.') {
            column += 1
        } else {
            throw `walkRight else ${point}`
        }
    }
    point[3] = column
    return 0
}

function walkLeft(grid, point, n) {
    const board = point[0]
    const facing = point[1]
    const row = point[2]
    let column = point[3]
    for (let i = 0; i < n; i++) {
        if (column === 0) {
            // newBoard = board, facing, row, column
            const newBoard = changeBoard(board, facing, row, column)
            if (grid[newBoard[0]][newBoard[2]][newBoard[3]] === '#') {
                break
            } else {
                point.splice(0, point.length, ...newBoard).slice()
                return n - 1 - i
            }
        } else if (grid[board][row][column - 1] === '#') {
            break
        } else if (grid[board][row][column - 1] === '.') {
            column -= 1
        } else {
            throw `walkLeft else ${point}`
        }
    }
    point[3] = column
    return 0
}

function walkDown(grid, point, n) {
    const board = point[0]
    const facing = point[1]
    let row = point[2]
    const column = point[3]
    for (let i = 0; i < n; i++) {
        if (row === l - 1) {
            // newBoard = board, facing, row, column
            const newBoard = changeBoard(board, facing, row, column)
            if (grid[newBoard[0]][newBoard[2]][newBoard[3]] === '#') {
                break
            } else {
                point.splice(0, point.length, ...newBoard)
                return n - 1 - i
            }
        } else if (grid[board][row + 1][column] === '#') {
            break
        } else if (grid[board][row + 1][column] === '.') {
            row += 1
        } else {
            throw `walkDown else ${point}`
        }
    }
    point[2] = row
    return 0
}

function walkUp(grid, point, n) {
    const board = point[0]
    const facing = point[1]
    let row = point[2]
    const column = point[3]
    for (let i = 0; i < n; i++) {
        if (row === 0) {
            // newBoard = board, facing, row, column
            const newBoard = changeBoard(board, facing, row, column)
            if (grid[newBoard[0]][newBoard[2]][newBoard[3]] === '#') {
                break
            } else {
                point.splice(0, point.length, ...newBoard)
                return n - 1 - i
            }
        } else if (grid[board][row - 1][column] === '#') {
            break
        } else if (grid[board][row - 1][column] === '.') {
            row -= 1
        } else {
            throw `walkUp else ${point}`
        }
    }
    point[2] = row
    return 0
}

function turn(currentFacing, direction) {
    // return new facing
    // 0 = right
    // 1 = down
    // 2 = left
    // 3 = up

    if (direction == 'R') {
        return (currentFacing + 1) % 4
    } else {
        return (currentFacing + 3) % 4
    }
}

function changeBoard(board, facing, row, column) {
    // return new board, facing, row, column
    switch (board) {
        case 1: return changeBoard1(facing, row, column)
        case 2: return changeBoard2(facing, row, column)
        case 3: return changeBoard3(facing, row, column)
        case 4: return changeBoard4(facing, row, column)
        case 5: return changeBoard5(facing, row, column)
        case 6: return changeBoard6(facing, row, column)
    }
}

function changeBoard1(facing, row, column) {
    switch (facing) {
        case 0:
            return [2, 0, row, 0]
        case 1:
            return [4, 1, 0, column]
        case 2:
            return [5, 2, row, l - 1]	
        case 3:
            return [3, 1, 0, l - 1 - column]
    }
}

function changeBoard2(facing, row, column) {
    switch (facing) {
        case 0:
            return [6, 0, row, 0]
        case 1:
            return [4, 2, column, l - 1]
        case 2:
            return [1, 2, row, l - 1]
        case 3:
            return [3, 0, column, 0]
    }
}

function changeBoard3(facing, row, column) {
    switch (facing) {
        case 0:
            return [5, 1, 0, l - 1 - row]
        case 1:
            return [6, 1, 0, column]
        case 2:
            return [2, 1, 0, row]
        case 3:
            return [1, 1, 0, l - 1 - column]
    }
}

function changeBoard4(facing, row, column) {
    switch (facing) {
        case 0:
            return [2, 3, l - 1, row]
        case 1:
            return [6, 3, l - 1, l - 1 - column]
        case 2:
            return [5, 3, l - 1, l - 1 - row]
        case 3:
            return [1, 3, l - 1, column]
    }
}

function changeBoard5(facing, row, column) {
    switch (facing) {
        case 0:
            return [1, 0, row, 0]
        case 1:
            return [4, 0, l - 1 - column, 0]
        case 2:
            return [6, 2, row, l - 1]
        case 3:
            return [3, 2, l - 1 - column, l - 1]
    }
}

function changeBoard6(facing, row, column) {
    switch (facing) {
        case 0:
            return [5, 0, row, 0]
        case 1:
            return [4, 3, l - 1, l - 1 - column]
        case 2:
            return [2, 2, row, l - 1]
        case 3:
            return [3, 3, l - 1, column]
    }
}

execute()