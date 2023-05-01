const { max } = require('lodash')
const { exit } = require('process')

let stones = {}
let deepest = 0
function collectStones(filename) {
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    lines.forEach((element, index) => {
        const a = element.split(" -> ")
        for (let i = 1; i < a.length; i++) {
            const s = a[i - 1].split(",").map(Number)
            const d = a[i].split(",").map(Number)
            if (s[0] == d[0]) {
                if (stones[s[0]] === undefined) {
                    stones[s[0]] = new Set()
                }
                const min = Math.min(s[1], d[1])
                const max = Math.max(s[1], d[1])
                for (let j = min; j <= max ; j++) {
                    stones[s[0]].add(j)
                }
                deepest = Math.max(deepest, max)
            } else if (s[1] == d[1]) {
                const min = Math.min(s[0], d[0])
                const max = Math.max(s[0], d[0])
                for (let j = min; j <= max ; j++) {
                    if (stones[j] === undefined) {
                        stones[j] = new Set([s[1]])
                    } else {
                        stones[j].add(s[1])
                    }
                }
                deepest = Math.max(deepest, s[1])
            } else {
                throw "Input not right"
            }
        }
    })
}

const sands = {}
let sandsCount = 0
function dropSands() {
    while (true) {
        dropOneSand()
    }
}

function dropOneSand() {
    let previousPos = undefined
    let newPos = [500, 0]
    if (!isSpotEmpty(newPos)) {
        console.log(sandsCount)
        throw "start point block"
    }

    do {
        previousPos = newPos
        newPos = dropSandOnce(previousPos)

        // if (newPos !== null && newPos[1] >= deepest) {
        //     console.log(sandsCount)
        //     exit(0)
        // }
    } while (newPos !== null && newPos[1] < deepest)

    sandsCount += 1
    if (sands[previousPos[0]] === undefined) {
        sands[previousPos[0]] = new Set([previousPos[1]])
    } else {
        sands[previousPos[0]].add(previousPos[1])
    }
    // console.log(sandsCount)
}

function isSpotEmpty(spot) {
    const x = spot[0]
    const y = spot[1]
    const isStone = stones[x] !== undefined && stones[x].has(y)
    const isSand = sands[x] !== undefined && sands[x].has(y)
    return !isStone && !isSand
}

function dropSandOnce(pos) {
    // return new position of sand
    const x = pos[0]
    const y = pos[1]

    if (isSpotEmpty([x, y + 1])) {
        return [x, y + 1]
    }
    if (isSpotEmpty([x - 1, y + 1])) {
        return [x - 1, y + 1]
    }
    if (isSpotEmpty([x + 1, y + 1])) {
        return [x + 1, y + 1]
    }
    return null
}

collectStones('day14.input')
deepest += 2
dropSands()