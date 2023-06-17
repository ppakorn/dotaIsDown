const fs = require('fs')
let jets = fs.readFileSync('day17.input', 'utf8')
const chamber = Array.from({ length: 500_000 }, () => [undefined, undefined, undefined, undefined, undefined, undefined, undefined])
const rocks = ['-', '+', 'L', 'i', 'o']     // L is mirror

// position [vertical, horizontal], vertical 0 is bottom
// - left most
// + center
// L bottom right
// i bottom
// o bottom left

function isDownable(rock, pos) {
    const pos0 = pos[0]
    const pos1 = pos[1]
    switch (rock) {
        case '-':
            return pos0 > 0 
            && chamber[pos0 - 1][pos1] === undefined
            && chamber[pos0 - 1][pos1 + 1] === undefined
            && chamber[pos0 - 1][pos1 + 2] === undefined
            && chamber[pos0 - 1][pos1 + 3] === undefined
        case '+':
            return pos0 > 1
            && chamber[pos0 - 1][pos1 - 1] === undefined
            && chamber[pos0 - 2][pos1] === undefined
            && chamber[pos0 - 1][pos1 + 1] === undefined
        case 'L':
            return pos0 > 0
            && chamber[pos0 - 1][pos1 - 2] === undefined
            && chamber[pos0 - 1][pos1 - 1] === undefined
            && chamber[pos0 - 1][pos1] === undefined
        case 'i':
            return pos0 > 0
            && chamber[pos0 - 1][pos1] === undefined
        case 'o':
            return pos0 > 0
            && chamber[pos0 - 1][pos1] === undefined
            && chamber[pos0 - 1][pos1 + 1] === undefined
    }
}

function isLeftable(rock, pos) {
    const pos0 = pos[0]
    const pos1 = pos[1]
    switch (rock) {
        case '-':
            return pos1 > 0 
            && chamber[pos0][pos1 - 1] === undefined
        case '+':
            return pos1 > 1
            && chamber[pos0 - 1][pos1 - 1] === undefined
            && chamber[pos0][pos1 - 2] === undefined
            && chamber[pos0 + 1][pos1 - 1] === undefined
        case 'L':
            return pos1 > 2
            && chamber[pos0 + 2][pos1 - 1] === undefined
            && chamber[pos0 + 1][pos1 - 1] === undefined
            && chamber[pos0][pos1 - 3] === undefined
        case 'i':
            return pos1 > 0
            && chamber[pos0 + 3][pos1 - 1] === undefined
            && chamber[pos0 + 2][pos1 - 1] === undefined
            && chamber[pos0 + 1][pos1 - 1] === undefined
            && chamber[pos0][pos1 - 1] === undefined
        case 'o':
            return pos1 > 0
            && chamber[pos0 + 1][pos1 - 1] === undefined
            && chamber[pos0][pos1 - 1] === undefined
    }
}

function isRightable(rock, pos) {
    const pos0 = pos[0]
    const pos1 = pos[1]
    switch (rock) {
        case '-':
            return pos1 < 3
            && chamber[pos0][pos1 + 4] === undefined
        case '+':
            return pos1 < 5
            && chamber[pos0 - 1][pos1 + 1] === undefined
            && chamber[pos0][pos1 + 2] === undefined
            && chamber[pos0 + 1][pos1 + 1] === undefined
        case 'L':
            return pos1 < 6
            && chamber[pos0 + 2][pos1 + 1] === undefined
            && chamber[pos0 + 1][pos1 + 1] === undefined
            && chamber[pos0][pos1 + 1] === undefined
        case 'i':
            return pos1 < 6
            && chamber[pos0 + 3][pos1 + 1] === undefined
            && chamber[pos0 + 2][pos1 + 1] === undefined
            && chamber[pos0 + 1][pos1 + 1] === undefined
            && chamber[pos0][pos1 + 1] === undefined
        case 'o':
            return pos1 < 5
            && chamber[pos0 + 1][pos1 + 2] === undefined
            && chamber[pos0][pos[1] + 2] === undefined
    }
}

function initialPos(rock, highest) {
    switch (rock) {
        case '-':
            return [highest + 4, 2]
        case '+':
            return [highest + 5, 3]
        case 'L':
            return [highest + 4, 4]
        case 'i':
            return [highest + 4, 2]
        case 'o':
            return [highest + 4, 2]
    }
}

function checkStop(rock, pos) {
    const pos0 = pos[0]
    const pos1 = pos[1]
    switch (rock) {
        case '-':
            return chamber[pos0][pos1] === undefined
            && chamber[pos0][pos1 + 1] === undefined
            && chamber[pos0][pos1 + 2] === undefined
            && chamber[pos0][pos1 + 3] === undefined
        case '+':
            return chamber[pos0][pos1] === undefined
            && chamber[pos0][pos1 - 1] === undefined
            && chamber[pos0][pos1 + 1] === undefined
            && chamber[pos0 - 1][pos1] === undefined
            && chamber[pos0 + 1][pos1] === undefined
        case 'L':
            return chamber[pos0][pos1] === undefined
            && chamber[pos0][pos1 - 1] === undefined
            && chamber[pos0][pos1 - 2] === undefined
            && chamber[pos0 + 1][pos1] === undefined
            && chamber[pos0 + 2][pos1] === undefined
        case 'i':
            return chamber[pos0][pos1] === undefined
            && chamber[pos0 + 1][pos1] === undefined
            && chamber[pos0 + 2][pos1] === undefined
            && chamber[pos0 + 3][pos1] === undefined
        case 'o':
            return chamber[pos0][pos1] === undefined
            && chamber[pos0][pos1 + 1] === undefined
            && chamber[pos0 + 1][pos1] === undefined
            && chamber[pos0 + 1][pos1 + 1] === undefined
    }
}

let maxDeep = 0
function stop(rock, pos) {
    if (!checkStop(rock, pos)) {
        throw `wrong when stop ${rock} ${pos}`
    }

    // Return new highest
    const pos0 = pos[0]
    const pos1 = pos[1]
    let highestOfThisPiece = -1
    switch (rock) {
        case '-':
            chamber[pos0][pos1] = 0
            chamber[pos0][pos1 + 1] = 0
            chamber[pos0][pos1 + 2] = 0
            chamber[pos0][pos1 + 3] = 0
            highestOfThisPiece = pos0
            break
        case '+':
            chamber[pos0][pos1] = 0
            chamber[pos0][pos1 - 1] = 0
            chamber[pos0][pos1 + 1] = 0
            chamber[pos0 - 1][pos1] = 0
            chamber[pos0 + 1][pos1] = 0
            highestOfThisPiece = pos0 + 1
            break
        case 'L':
            chamber[pos0][pos1] = 0
            chamber[pos0][pos1 - 1] = 0
            chamber[pos0][pos1 - 2] = 0
            chamber[pos0 + 1][pos1] = 0
            chamber[pos0 + 2][pos1] = 0
            highestOfThisPiece = pos0 + 2
            break
        case 'i':
            chamber[pos0][pos1] = 0
            chamber[pos0 + 1][pos1] = 0
            chamber[pos0 + 2][pos1] = 0
            chamber[pos0 + 3][pos1] = 0
            highestOfThisPiece = pos0 + 3
            break
        case 'o':
            chamber[pos0][pos1] = 0
            chamber[pos0][pos1 + 1] = 0
            chamber[pos0 + 1][pos1] = 0
            chamber[pos0 + 1][pos1 + 1] = 0
            highestOfThisPiece = pos0 + 1
            break
    }

    // Check how deep the piece went
    // if (highestOfThisPiece - highest == -35) {
    //     maxDeep = highestOfThisPiece - highest
    //     console.log(`${rock} max deep ${maxDeep}`)
    // }

    return Math.max(highestOfThisPiece, highest)
}

let lr = 0
function getJet() {
    const c = jets[lr]
    lr = (lr + 1) % jets.length
    return c
}

function moveFromJet(rock, pos, jet) {
    // Return new pos
    switch (jet) {
        case '<':
            if (isLeftable(rock, pos)) {
                return [pos[0], pos[1] - 1]
            }
            return pos
        case '>':
            if (isRightable(rock, pos)) {
                return [pos[0], pos[1] + 1]
            }
            return pos
    }
}

let highest = -1
let mem = {}
function execute() {
    let i = 0
    while (i < 2755) {
        const rock = rocks[i % rocks.length]
        let pos = initialPos(rock, highest)

        // Super hacky
        let firstJet = lr

        while (true) {
            let jet = getJet()
            pos = moveFromJet(rock, pos, jet)
            if (isDownable(rock, pos)) {
                pos = [pos[0] - 1, pos[1]]
            } else {
                highest = stop(rock, pos)
                saveMem(rock, firstJet, i, highest)
                break
            }
        }

        i += 1
    }

    checkMem()
}

function saveMem(rock, jet, i, highest) {
    if (mem[rock] === undefined) {
        mem[rock] = {}
    }

    if (mem[rock][jet] === undefined) {
        mem[rock][jet] = []
    }

    mem[rock][jet].push([i, highest])
}

function checkMem() {
    let maxLen = 0
    for (const [rock, value1] of Object.entries(mem)) {
        for (const [firstJet, highests] of Object.entries(value1)) {
            if (highests.length > maxLen) {
                maxLen = highests.length
                console.log(`rock = ${rock}, firstJet = ${firstJet}`)
                for (const a of highests) {
                    console.log(`${a[0]}, ${a[1]}`)
                }
            }
        }
    }
}

function print() {
    for (let i = highest; i >= 0; i--) {
        let l = ""
        for (let j = 0; j < 7; j++) {
            if (chamber[i][j] === undefined) {
                l += '.'
            } else {
                l += '#'
            }
        }
        console.log(l)
    }
}


execute()
// print()
console.log(highest)
