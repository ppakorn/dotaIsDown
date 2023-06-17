let elves = new Set()

// count like array [0, 0] is top left
const directions = {
    N: [-1, 0],
    NE: [-1, 1],
    E: [0, 1],
    SE: [1, 1],
    S: [1, 0],
    SW: [1, -1],
    W: [0, -1],
    NW: [-1, -1]
}

let startCheckIndex = 0
const directionChecks = [
    ['N', 'NE', 'NW'],
    ['S', 'SE', 'SW'],
    ['W', 'NW', 'SW'],
    ['E', 'NE', 'SE']
]

function getFromSet(str) {
    return str.split(',').map(Number)
}

function add(a, b) {
    return [a[0] + b[0], a[1] + b[1]]
}

function execute() {
    const filename = 'day23.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    lines.forEach((line, i) => {
        [...line].forEach((c, j) => {
            if (c === '#') {
                elves.add([i, j].toString())
            }
        })
    })

    let i = 1
    while (true) {
        const mv = findMovingElves(elves)
        const moveFromTo = findDestination(mv)
        if (Object.keys(moveFromTo).length === 0) {
            console.log(i)
            break
        }
        move(moveFromTo)
        i += 1
    }
    
    // console.log(elves)
    // countEmpty()
}

function findMovingElves() {
    const movingElves = new Set()
    elves.forEach(e => {
        e = getFromSet(e)
        if (!isEmpty(e, Object.keys(directions))) {
            movingElves.add(e.toString())
        }
    })
    return movingElves
}

function findDestination(movingElves) {
    const moveFromTo = {}
    const destinationDup = {}

    for (let i = 0; i < 4; i++) {
        const directionCheck = directionChecks[(startCheckIndex + i) % 4]
        movingElves.forEach(eStr => {
            // Already has a move before 
            if (eStr in moveFromTo) {
                return
            }

            const e = getFromSet(eStr)
            if (isEmpty(e, directionCheck)) {
                const dest = add(e, directions[directionCheck[0]])
                const destStr = dest.toString()
                moveFromTo[eStr] = destStr
                if (destinationDup[destStr] === undefined) {
                    destinationDup[destStr] = [eStr]
                } else {
                    destinationDup[destStr].push(eStr)
                }
            }
        })
    }
    
    startCheckIndex = (startCheckIndex + 1) % 4

    Object.entries(destinationDup).forEach(d => {
        if (d[1].length > 1) {
            d[1].forEach(e => delete moveFromTo[e])
        }
    })

    return moveFromTo
}

function isEmpty(center, toChecks) {
    for (let i=0; i < toChecks.length; i++) {
        const d = directions[toChecks[i]]
        if(elves.has(add(center, d).toString())) {
            return false
        }
    }
    return true
}

function move(moveFromTo) {
    Object.entries(moveFromTo).forEach(e => {
        elves.delete(e[0])
        elves.add(e[1])
    })
}

function countEmpty() {
    let minRow = Number. MAX_VALUE
    let maxRow = Number. MIN_VALUE
    let minColumn = Number. MAX_VALUE
    let maxColumn = Number. MIN_VALUE

    elves.forEach(e => {
        e = getFromSet(e)
        minRow = Math.min(minRow, e[0])
        maxRow = Math.max(maxRow, e[0])
        minColumn = Math.min(minColumn, e[1])
        maxColumn = Math.max(maxColumn, e[1])
    })

    console.log((maxRow - minRow + 1) * (maxColumn - minColumn + 1) - elves.size)
}

execute()