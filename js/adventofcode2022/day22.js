function execute() {
    const filename = 'day22.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    let grid = []
    let code = ""
    let isGrid = true
    
    lines.forEach(line => {
        if (!line) {
            isGrid = false
        } else if (isGrid) {
            grid.push(line)
        } else {
            code = line
        }
    })

    run(grid, code)
}

function printGrid(grid) {
    for (const i of grid) {
        console.log(i)
    }
}

function extractCode(code) {
    const regex = /(\d+|[A-Z])/g;
    return code.match(regex);
}

function run(grid, code) {
    let point = [0, grid[0].indexOf('.')]
    let facing = 0
    const steps = extractCode(code)

    steps.forEach(step => {
        console.log(point, facing, step)
        const n = Number(step)
        if (n) {
            walk(grid, point, facing, n)
        } else {
            facing = turn(facing, step)
        }
    })

    // console.log(point)
    // console.log(facing)
    console.log((point[0] + 1) * 1000 + (point[1] + 1) * 4 + facing)
}

function walk(grid, point, facing, n) {
    switch (facing) {
        case 0:
            walkRight(grid, point, n)
            break
        case 1:
            walkDown(grid, point, n)
            break
        case 2:
            walkLeft(grid, point, n)
            break
        case 3:
            walkUp(grid, point, n)
            break
    }
}

function walkRight(grid, point, n) {
    const row = point[0]
    let column = point[1]
    for (let i = 0; i < n; i++) {
        if (!isInGrid(grid, row, column + 1)) {
            const leftmost = grid[row].length - grid[row].trimStart().length
            if (grid[row][leftmost] === '#') {
                break
            } else {
                column = leftmost
            }
        } else if (grid[row][column + 1] === '#') {
            break
        } else if (grid[row][column + 1] === '.') {
            column += 1
        } else {
            throw `walkRight else ${point}`
        }
    }
    point[1] = column
}

function walkLeft(grid, point, n) {
    const row = point[0]
    let column = point[1]
    for (let i = 0; i < n; i++) {
        if (!isInGrid(grid, row, column - 1)) {
            const rightmost = grid[row].trimEnd().length - 1
            if (grid[row][rightmost] === '#') {
                break
            } else {
                column = rightmost
            }
        } else if (grid[row][column - 1] === '#') {
            break
        } else if (grid[row][column - 1] === '.') {
            column -= 1
        } else {
            throw `walkLeft else ${point}`
        }
    }
    point[1] = column
}

function walkDown(grid, point, n) {
    let row = point[0]
    const column = point[1]
    for (let i = 0; i < n; i++) {
        if (!isInGrid(grid, row + 1, column)) {
            let upmost = 0
            for (upmost = 0; upmost < grid.length; upmost++) {
                if (grid[upmost][column] === '.' || grid[upmost][column] === '#') {
                    break
                }
            }
            if (grid[upmost][column] === '#') {
                break
            } else {
                row = upmost
            }
        } else if (grid[row + 1][column] === '#') {
            break
        } else if (grid[row + 1][column] === '.') {
            row += 1
        } else {
            throw `walkDown else ${point}`
        }
    }
    point[0] = row
}

function walkUp(grid, point, n) {
    let row = point[0]
    const column = point[1]
    for (let i = 0; i < n; i++) {
        if (!isInGrid(grid, row - 1, column)) {
            let downmost = grid.length - 1
            for (downmost = grid.length - 1; downmost >= 0; downmost--) {
                if (grid[downmost][column] === '.' || grid[downmost][column] === '#') {
                    break
                }
            }
            if (grid[downmost][column] === '#') {
                break
            } else {
                row = downmost
            }
        } else if (grid[row - 1][column] === '#') {
            break
        } else if (grid[row - 1][column] === '.') {
            row -= 1
        } else {
            throw `walkUp else ${point}`
        }
    }
    point[0] = row
}

function isInGrid(grid, row, column) {
    return row >= 0 && row < grid.length
        && column >= 0 && column < grid[row].length
        && grid[row][column]
        && (grid[row][column] === '.' || grid[row][column] === '#')
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


execute()