const Queue = require("./Queue.js")

function readInput(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');
    return lines
}

function findS() {
    for (let i = 0; i < grid.length ; i++) {
        const j = grid[i].indexOf("S")
        if (j >= 0) {
            return [i, j]
        }
    }
}

function isValidPoint(p) {
    return p[0] >= 0 && p[0] < rowSize && p[1] >= 0 && p[1] < columnSize
}

function canGo(source, destination) {
    let a = grid[source[0]][source[1]]
    let b = grid[destination[0]][destination[1]]
    if (a == "S") {
        a = "a"
    }
    if (b == "E") {
        b = "z"
    }
    return a.charCodeAt(0) + 1 >= b.charCodeAt(0)
}

function hasVisited(p) {
    return visited.has(`${p[0]},${p[1]}`)
}

function visit(p) {
    visited.add(`${p[0]},${p[1]}`)
}

function findPath(start) {
    start.push(0)
    visited = new Set()
    // Objects in queue are in format [row, column, no. of step]
    const q = new Queue(start)

    while (q.size > 0) {
        const p = q.dequeue()
        if (grid[p[0]][p[1]] == "E") {
            return p[2]
        }

        const current = [p[0], p[1]]
        if (hasVisited(current)) {
            continue
        }
        visit(current)

        const left = [p[0], p[1] - 1]
        const right = [p[0], p[1] + 1]
        const up = [p[0] - 1, p[1]]
        const down = [p[0] + 1, p[1]]

        if (isValidPoint(left) && !hasVisited(left) && canGo(current, left)) {
            q.enqueue([...left, p[2] + 1])
        }
        if (isValidPoint(right) && !hasVisited(right) && canGo(current, right)) {
            q.enqueue([...right, p[2] + 1])
        }
        if (isValidPoint(up) && !hasVisited(up) && canGo(current, up)) {
            q.enqueue([...up, p[2] + 1])
        }
        if (isValidPoint(down) && !hasVisited(down) && canGo(current, down)) {
            q.enqueue([...down, p[2] + 1])
        }
    }

}

function findShortesta() {
    let min = 10000000
    for (let i = 0; i < rowSize; i++) {
        for (let j = 0; j < columnSize ; j++) {
            if (grid[i][j] == 'a' || grid[i][j] == 'S') {
                const path = findPath([i, j])
                if (path < min) {
                    min = path
                }
            }
        }
    }
    return min
}

const grid = readInput('day12.input')
const sPosition = findS(grid)
const rowSize = grid.length
const columnSize = grid[0].length
let visited = new Set()
console.log(findShortesta())