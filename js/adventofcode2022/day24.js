// count like array [0, 0] is top left
const directions = {
    '^': [-1, 0],
    '>': [0, 1],
    'v': [1, 0],
    '<': [0, -1],
    '-': [0, 0]         // for stay still
}

const winds = [new Set()]
let rowSize = -1
let columnSize = -1
let qSize = 2000
let firstStepSize = 100

function execute() {
    const filename = 'day24.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    rowSize = lines.length
    columnSize = lines[0].length

    lines.forEach((line, row) => {
        [...line].forEach((c, column) => {
            if (c !== '.') {
                winds[0].add([row, column, c].toString())
            }
        })
    })

    const a = goForward(1)
    const b = goBack(a + 2)
    goForward(b + 2)
    // for (let i = 19; i < 50; i++){
    //     console.log(i)
    //     goBack(i)
    // }
}

function goForward(startMinute) {
    let q = []

    // Have to wait until can start the first step
    // Can have multiple first step
    while (q.length < firstStepSize) {
        while (winds.length - 1 < startMinute) {
            calNextWind()
        }
        if (isPositionClear(0, 0, startMinute)) {
            q.push([0, 0, startMinute])
        }
        startMinute += 1
    }

    // To make the least start minute gets pop first
    q.reverse()

    // [row, column, minute]
    const visited = new Set()
    
    // First time
    while (q.length > 0) {
        const state = q.pop()
        if (visited.has(state.toString())) {
            continue
        }

        if (state[0] === rowSize - 1 && state[1] === columnSize - 1) {
            console.log("Finish Go Forward")
            console.log(state)
            return state[2]
        }

        visited.add(state.toString())

        // Calculate wind of the minute if not yet
        const nextMinute = state[2] + 1
        while (winds.length - 1 < nextMinute) {
            calNextWind()
        }

        // Push all possible moves in to queue
        const wind = winds[nextMinute]
        Object.values(directions).forEach(d => {
            const nextRow = state[0] + d[0]
            if (nextRow < 0 || nextRow >= rowSize) {
                return
            }
            const nextColumn = state[1] + d[1]
            if (nextColumn < 0 || nextColumn >= columnSize) {
                return
            }
            const newState = [nextRow, nextColumn, nextMinute]
            if (!visited.has(newState.toString()) && isPositionClear(nextRow, nextColumn, nextMinute)) {
                q.push(newState)
            }
        })

        // Sort queue to make higher distance and lower minute get pop first
        q.sort((a, b) => {
            const sumA = a[0] + a[1] - 10 * a[2];
            const sumB = b[0] + b[1] - 10 * b[2];
            return sumA - sumB;
        })

        // prune state to only some best
        q = q.slice(-qSize)
    }
}

function goBack(startMinute) {
    let q = []

    // Have to wait until can start the first step
    // can have multiple first step
    while (q.length < firstStepSize) {
        while (winds.length - 1 < startMinute) {
            calNextWind()
        }
        if (isPositionClear(rowSize - 1, columnSize - 1, startMinute)) {
            q.push([rowSize - 1, columnSize - 1, startMinute])
        }
        startMinute += 1
    }

    // To make the least start minute gets pop first
    q.reverse()

    // [row, column, minute]
    const visited = new Set()

    // First time
    while (q.length > 0) {
        const state = q.pop()
        if (visited.has(state.toString())) {
            continue
        }

        if (state[0] === 0 && state[1] === 0) {
            console.log("Finish Go Back")
            console.log(state)
            return state[2]
        }

        visited.add(state.toString())

        // Calculate wind of the minute if not yet
        const nextMinute = state[2] + 1
        while (winds.length - 1 < nextMinute) {
            calNextWind()
        }

        // Push all possible moves in to queue
        const wind = winds[nextMinute]
        Object.values(directions).forEach(d => {
            const nextRow = state[0] + d[0]
            if (nextRow < 0 || nextRow >= rowSize) {
                return
            }
            const nextColumn = state[1] + d[1]
            if (nextColumn < 0 || nextColumn >= columnSize) {
                return
            }
            const newState = [nextRow, nextColumn, nextMinute]
            if (!visited.has(newState.toString()) && isPositionClear(nextRow, nextColumn, nextMinute)) {
                q.push(newState)
            }
        })

        // Sort queue to make near 0, 0 and lower minute get pop first
        q.sort((a, b) => {
            const sumA = - a[0] - a[1] - 10 * a[2];
            const sumB = - b[0] - b[1] - 10 * b[2];
            return sumA - sumB;
        })

        // prune state to only some best
        q = q.slice(-qSize)
    }
}

function calNextWind() {
    const current = winds[winds.length - 1]
    const next = new Set()
    current.forEach(w => {
        w = w.split(',')
        const newWind = addWind(Number(w[0]), Number(w[1]), w[2])
        next.add(newWind.toString())
    })
    winds.push(next)
}

function addWind(row, column, direction) {
    const d = directions[direction]
    
    let newRow = row + d[0]
    if (newRow < 0) {
        newRow = rowSize - 1
    } else if (newRow === rowSize) {
        newRow = 0
    }

    let newColumn = column + d[1]
    if (newColumn < 0) {
        newColumn = columnSize - 1
    } else if (newColumn === columnSize){
        newColumn = 0
    }

    return [newRow, newColumn, direction]
}

function isPositionClear(row, column, minute) {
    const hasUpWind = winds[minute].has([row, column, '^'].toString())
    const hasDownWind = winds[minute].has([row, column, 'v'].toString())
    const hasLeftWind = winds[minute].has([row, column, '<'].toString())
    const hasRightWind = winds[minute].has([row, column, '>'].toString())
    return !hasUpWind && !hasDownWind && !hasLeftWind && !hasRightWind
}

execute()