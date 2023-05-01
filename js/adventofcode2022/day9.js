function isAdjacent(point1, point2) {
    return Math.abs(point1[0] - point2[0]) <= 1 && Math.abs(point1[1] - point2[1]) <= 1
}

// mutate function
function moveHead(h, direction) {
    // return previous position
    const previous = h.slice()

    switch (direction) {
        case "R":
            h[0] += 1
            break
        case "L":
            h[0] -= 1
            break
        case "U":
            h[1] += 1
            break
        case "D":
            h[1] -= 1
            break
    }

    return previous
}

function moveTail(h, t) {
    if (h[0] == t[0] && h[1] == t[1]) return
    if (h[1] == t[1]) {
        // move horizontal
        if (h[0] - t[0] == 2) {
            // right
            t[0] += 1
        } else if (t[0] - h[0] == 2) {
            // left
            t[0] -= 1
        }
    } else if (h[0] == t[0]) {
        // move vertical
        if (h[1] - t[1] == 2) {
            // up
            t[1] += 1
        } else if (t[1] - h[1] == 2) {
            // down
            t[1] -= 1
        }
    } else {
        // move diagonal
        // if (h[0] > t[0] && h[1] > t[1]) {
        //     t[0] += 1
        //     t[1] += 1
        // } else if (h[0] < t[0] && h[1] > t[1]) {
        //     t[0] -= 1
        //     t[1] += 1
        // }

        if (h[0] > t[0]) {
            t[0] += 1
        } else {
            t[0] -= 1
        }

        if (h[1] > t[1]) {
            t[1] += 1
        } else {
            t[1] -= 1
        }
    }
}

function move(direction, times) {
    for (let i = 0; i < times; i++) {
        let previous = moveHead(knots[0], direction)
        for (let k = 1; k < 10; k++) {
            if (!isAdjacent(knots[k - 1], knots[k])) {
                moveTail(knots[k - 1], knots[k])
                if (k == 9) {
                    visited.add(knots[k].join())
                }
            }            
        }
    }
}

let knots = Array.from({ length: 10 }, () => [0, 0])
let visited = new Set(["0,0"])
function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    lines.forEach((element, index, array) => {
        const a = element.split(" ")
        move(a[0], Number(a[1]))
    })

    // visited.forEach(console.log)
    console.log(visited.size)
}

execute("day9.input")