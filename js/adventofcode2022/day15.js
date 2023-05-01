let targetRow = 2000000
let beaconsOnTargetRow = new Set()

function extractInput(str) {
    let firstCoordinates = str.match(/x=(-?\d+), y=(-?\d+)/);
    let secondCoordinates = str.match(/x=(-?\d+), y=(-?\d+)$/);

    let s = [parseInt(firstCoordinates[1]), parseInt(firstCoordinates[2])];
    let b = [parseInt(secondCoordinates[1]), parseInt(secondCoordinates[2])];

    if (b[1] == targetRow) {
        beaconsOnTargetRow.add(b[0])
    }

    return [s, b]
}

let inputs = []
function readInput(filename) {
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    inputs = lines.map(extractInput)
    return inputs
}

let fourPoints = {}
function find4PointsAllInputs() {
    fourPoints = inputs.reduce((a, c) => {
        const s = c[0]
        const b = c[1]
        return Object.assign(a, {[s]: find4Points(s, b)})
    }, {})
}

function find4Points(s, b) {
    const d = manhattan(s, b)
    return {
        b: b,
        top: [s[0], s[1] - d],
        bottom: [s[0], s[1] + d],
        left: [s[0] - d, s[1]],
        right: [s[0] + d, s[1]]
    }
}

function manhattan(s, b) {
    const hDiff = Math.abs(s[0] - b[0])
    const vDiff = Math.abs(s[1] - b[1])
    return hDiff + vDiff
}

function countUnavailableInRow(rowNumber) {
    const onTheRow = new Set()
    for (const [key, points] of Object.entries(fourPoints)) {
        const s = key.split(",").map(Number)
        if (rowNumber >= points.top[1] && rowNumber <= points.bottom[1]) {
            const d = rowNumber > s[1] ? points.bottom[1] - rowNumber : rowNumber - points.top[1]
            for (let i = s[0] - d; i <= s[0] + d; i++) {
                onTheRow.add(i)
            }
        }
    }
    return onTheRow
}

readInput('day15.input')
find4PointsAllInputs()
// console.log(fourPoints[[8, 7]].top)

// const onTheRow = countUnavailableInRow(targetRow)
// beaconsOnTargetRow.forEach(x => {onTheRow.delete(x)})
// console.log(onTheRow.size)

const area = 4000000
// const availableGrid = Array.from({ length: area + 1 }, () => Array.from({length: area + 1 }, () => true))
// function mark() {
//     for (const [key, points] of Object.entries(fourPoints)) {
//         const s = key.split(",").map(Number)
//         markUnavailableOnce(s, points)
//         conlose.log("DONE", key)
//     }
// }

// function markUnavailableOnce(s, points) {
//     let d = 0
//     const startTop = Math.max(points.top[1], 0)
//     d = startTop - points.top[1]
//     for (let y = startTop; y <= s[1]; y++) {
//         const startHon = Math.max(s[0] - d, 0)
//         const stopHon = Math.min(s[0] + d, area)
//         // console.log(y, startHon, stopHon)
//         for (let x = startHon; x <= stopHon; x++) {
//             availableGrid[x][y] = false
//         }
//         d += 1
//     }

//     d -= 1
//     const stopBottom = Math.min(points.bottom[1], area)
//     for (let y = s[1] + 1; y <= stopBottom; y++) {
//         d -= 1
//         const startHon = Math.max(s[0] - d, 0)
//         const stopHon = Math.min(s[0] + d, area)
//         // console.log(y, startHon, stopHon)
//         for (let x = startHon; x <= stopHon; x++) {
//             availableGrid[x][y] = false
//         }
//     }
// }

// function findDistress() {
//     for (let x = 0; x <= area; x++) {
//         for (let y = 0; y <= area; y++) {
//             if (availableGrid[x][y]) {
//                 console.log("x=", x, "y=", y)
//                 return
//             }
//         }
//     }
// }

// const a = [8, 7]
// const b = fourPoints[a]
// markUnavailableOnce(a, b)
// mark()
// findDistress()

function findDistress2() {
    for (let y = 0; y <= area; y++) {
        const c = Object.values(fourPoints).filter(i => overTheRow(y, i)).map(i => mapToLeftRight(y, i)).sort(function(a, b) {
            return a[0] - b[0]
        })
        mergeLeftRight(c, y)

        if (y % 100000 == 0) {
            console.log(y)
        }
    }
}

function overTheRow(row, points) {
    return row >= points.top[1] && row <= points.bottom[1]
}

function mapToLeftRight(row, points) {
    const center = (points.top[1] + points.bottom[1]) / 2
    const d = row > center ? points.bottom[1] - row : row - points.top[1]
    return [points.top[0] - d, points.top[0] + d]
}

function mergeLeftRight(c, y) {
    let right = 0
    c.forEach(i => {
        if (i[0] > right + 1) {
            console.log("y=", y, "right=", right, "i[0]=", i[0])
        }
        right = Math.max(right, i[1])
        if (right > area) {
            return area
        }
    })

    if (right < area) {
        console.log("y=", y, "right=", right)
    }
}

findDistress2()