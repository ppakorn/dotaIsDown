const Queue = require("./Queue.js")
const {
    MaxPriorityQueue
} = require('@datastructures-js/priority-queue');

const input = {}
const path = {
    AA: {}
}

function readInput(filename) {
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    lines.forEach((element, index) => {
        element = element
            .replace("Valve ", "")
            .replace("has flow rate=", "")
            .replace(" tunnels lead to valves ", "")
            .replace(" tunnel leads to valve ", "")
        const a = element.split(";")
        const b = a[0].split(" ")
        const c = a[1].split(", ")

        const flow = Number(b[1])
        input[b[0]] = {
            valve: flow,
            nexts: c
        }

        if (flow > 0) {
            path[b[0]] = {}
        }
    })
}

function calShortestPath() {
    for (a of Object.keys(path)) {
        calShortestPathFromOnePoint(a)
    }

    Object.keys(path).forEach(start => {
        Object.keys(path[start]).forEach(end => {
            if (start == end || !Object.keys(path).includes(end)) {
                delete path[start][end]
            }
        })
    })
}

function calShortestPathFromOnePoint(start) {
    const s = [start, 0]
    const q = new Queue(s)
    const visited = new Set()

    while(q.size > 0) {
        const i = q.dequeue()
        
        if (visited.has(i[0])) {
            continue
        }

        visited.add(i[0])
        path[start][i[0]] = i[1]

        for (j of input[i[0]].nexts) {
            q.enqueue([j, i[1] + 1])
        }
    }
}

best = {}
// function isBetter(i) {
//     return best[i.node] === undefined || best[i.node] < i.released
// }

function isBetter2(key, released) {
    return best[key] === undefined || best[key] < released
}

function isBetter3(i, turn) {
    const key = [i.nodeHuman, i.nodeElephant].toString()
    const b = best[key]
    if (b === undefined || b.released < i.released) {
        best[key] = {
            released: i.released,
            minuteHuman: i.minuteHuman,
            minuteElephant: i.minuteElephant
        }
        return true
    }
    // if (b.released == i.released && (i.minuteHuman > b.minuteHuman || i.minuteElephant > b.minuteElephant)) {
    //     return true
    // }
    if (b.minuteHuman > i.minuteHuman && b.minuteElephant > i.minuteElephant) {
        return false
    }

    if (turn == "human") {
        if (input[i.nodeHuman].valve * i.minuteHuman + i.released > b.released) {
            return true
        }
    } else {
        if (input[i.nodeElephant].valve * i.minuteElephant + i.released > b.released) {
            return true
        }
    }
    return false
}

// function openValve(i) {
//     return {
//         node: i.node,
//         released: i.released + input[i.node].valve * (i.minute - 1),
//         minute: i.minute - 1,
//         opened: new Set([...i.opened, i.node])
//     }
// }

function openValve2(i) {
    const result = []
    const human = input[i.nodeHuman].valve > 0 && !i.opened.has(i.nodeHuman)
    const elephant = input[i.nodeElephant].valve > 0 && !i.opened.has(i.nodeElephant)

    if (i.minuteHuman == i.minuteElephant && i.nodeHuman != i.nodeElephant) {
        if (human && elephant) {
            result.push({
                nodeHuman: i.nodeHuman,
                nodeElephant: i.nodeElephant,
                released: i.released + (input[i.nodeHuman].valve + input[i.nodeElephant].valve) * (i.minuteHuman - 1),
                minuteHuman: i.minuteHuman - 1,
                minuteElephant: i.minuteElephant - 1,
                opened: new Set([...i.opened, i.nodeHuman, i.nodeElephant])
            })
        }
        if (human) {
            result.push({
                nodeHuman: i.nodeHuman,
                nodeElephant: i.nodeElephant,
                released: i.released + input[i.nodeHuman].valve * (i.minuteHuman - 1),
                minuteHuman: i.minuteHuman - 1,
                minuteElephant: i.minuteElephant,
                opened: new Set([...i.opened, i.nodeHuman])
            })
        }
        if (elephant) {
            result.push({
                nodeHuman: i.nodeHuman,
                nodeElephant: i.nodeElephant,
                released: i.released + input[i.nodeElephant].valve * (i.minuteElephant - 1),
                minuteHuman: i.minuteHuman,
                minuteElephant: i.minuteElephant - 1,
                opened: new Set([...i.opened, i.nodeElephant])
            })
        }
    } else if ((i.minuteHuman == i.minuteElephant && i.nodeHuman == i.nodeElephant) || (i.minuteHuman > i.minuteElephant)) {
        if (human) {
            result.push({
                nodeHuman: i.nodeHuman,
                nodeElephant: i.nodeElephant,
                released: i.released + input[i.nodeHuman].valve * (i.minuteHuman - 1),
                minuteHuman: i.minuteHuman - 1,
                minuteElephant: i.minuteElephant,
                opened: new Set([...i.opened, i.nodeHuman])
            })
        }
    } else if (elephant) {
        result.push({
            nodeHuman: i.nodeHuman,
            nodeElephant: i.nodeElephant,
            released: i.released + input[i.nodeElephant].valve * (i.minuteElephant - 1),
            minuteHuman: i.minuteHuman,
            minuteElephant: i.minuteElephant - 1,
            opened: new Set([...i.opened, i.nodeElephant])
        })
    }
    
    // // open only human
    // if (human) {
    //     result.push({
    //         nodeHuman: i.nodeHuman,
    //         nodeElephant: i.nodeElephant,
    //         released: i.released + input[i.nodeHuman].valve * (i.minuteHuman - 1),
    //         minuteHuman: i.minuteHuman - 1,
    //         minuteElephant: i.minuteElephant,
    //         opened: new Set([...i.opened, i.nodeHuman])
    //     })
    // }

    // // open only elephant
    // if (elephant) {
    //     result.push({
    //         nodeHuman: i.nodeHuman,
    //         nodeElephant: i.nodeElephant,
    //         released: i.released + input[i.nodeElephant].valve * (i.minuteElephant - 1),
    //         minuteHuman: i.minuteHuman,
    //         minuteElephant: i.minuteElephant - 1,
    //         opened: new Set([...i.opened, i.nodeElephant])
    //     })
    // }

    return result
}

function openValve3(i, turn) {
    if (turn == "human") {
        return {
            nodeHuman: i.nodeHuman,
            nodeElephant: i.nodeElephant,
            released: i.released + input[i.nodeHuman].valve * (i.minuteHuman - 1),
            minuteHuman: i.minuteHuman - 1,
            minuteElephant: i.minuteElephant,
            opened: new Set([...i.opened, i.nodeHuman])
        }
    } else {
        return {
            nodeHuman: i.nodeHuman,
            nodeElephant: i.nodeElephant,
            released: i.released + input[i.nodeElephant].valve * (i.minuteElephant - 1),
            minuteHuman: i.minuteHuman,
            minuteElephant: i.minuteElephant - 1,
            opened: new Set([...i.opened, i.nodeElephant])
        }
    }
}

// function execute() {
//     const q = new MaxPriorityQueue((o) => o.minute)
//     const s = {
//         node: 'AA',
//         released: 0,
//         minute: 30,
//         opened: new Set()
//     }
//     q.enqueue(s)
    
//     while (q.size() > 0) {
//         const i = q.dequeue()
//         if (i.minute <= 0) {
//             continue
//         }

//         if (!isBetter(i)) {
//             continue
//         }
//         best[i.node] = i.released

//         // open valve
//         if (input[i.node].valve > 0 && !i.opened.has(i.node)) {
//             q.enqueue(openValve(i))
//         }
        
//         // go nexts
//         for ([j, c] of Object.entries(path[i.node])) {
//             const next = {
//                 node: j,
//                 released: i.released,
//                 minute: i.minute - c,
//                 opened: new Set(i.opened)
//             }
//             q.enqueue(next)
//         }
//     }
// }

function findMax() {
    let max = 0
    Object.values(best).forEach(x => {
        if (x.released > max) {
            max = x
        }
    })
    console.log(max)
}

function execute2() {
    // const q = new MaxPriorityQueue((o) => Math.max(o.minuteHuman, o.minuteElephant))
    const q = new MaxPriorityQueue((o) => 10000 * Math.max(o.minuteHuman, o.minuteElephant) + Math.min(o.minuteHuman, o.minuteElephant))
    const s = {
        nodeHuman: 'AA',
        nodeElephant: 'AA',
        released: 0,
        minuteHuman: 26,
        minuteElephant: 26,
        opened: new Set()
    }
    q.enqueue(s)
    
    while (q.size() > 0) {
        const i = q.dequeue()
        if (i.minuteHuman <= 0 && i.minuteElephant <= 0) {
            continue
        }

        if ((i.nodeHuman == 'DD' && i.nodeElephant == 'AA' && i.released == 0) 
        || (i.nodeHuman == 'HH' && i.nodeElephant == 'JJ' && i.released == 480) 
        || (i.nodeHuman == 'HH' && i.nodeElephant == 'BB' && i.released >= 963) 
        // || (i.nodeHuman == 'EE' && i.nodeElephant == 'BB')
        // || (i.nodeHuman == 'EE' && i.nodeElephant == 'CC')
        ){
            console.log()
        } 

        // const key = [i.nodeHuman, i.nodeElephant].toString()
        // if (!isBetter2(key, i.released)) {
        //     continue
        // }
        // best[key] = i.released

        let turn = "human"
        if (i.minuteElephant > i.minuteHuman) {
            turn = "elephant"
        }

        if (!isBetter3(i, turn)) {
            continue
        }

        // open valve
        // const resultOpenValve = openValve2(i)
        // resultOpenValve.forEach(x => q.enqueue(x))

        if ((turn == "human" && input[i.nodeHuman].valve > 0 && !i.opened.has(i.nodeHuman))
            || (turn == "elephant" && input[i.nodeElephant].valve > 0 && !i.opened.has(i.nodeElephant))) {
                const next = openValve3(i, turn)
                q.enqueue(next)
        }
        
        // go nexts
        if (turn == "human") {
            for ([j, c] of Object.entries(path[i.nodeHuman])) {
                const next = {
                    nodeHuman: j,
                    nodeElephant: i.nodeElephant,
                    released: i.released,
                    minuteHuman: i.minuteHuman - c,
                    minuteElephant: i.minuteElephant,
                    opened: new Set(i.opened)
                }
                q.enqueue(next)
            }
        } else if (turn == "elephant") {
            for ([j, c] of Object.entries(path[i.nodeElephant])) {
                const next = {
                    nodeHuman: i.nodeHuman,
                    nodeElephant: j,
                    released: i.released,
                    minuteHuman: i.minuteHuman,
                    minuteElephant: i.minuteElephant - c,
                    opened: new Set(i.opened)
                }
                q.enqueue(next)
            }
        }
        //  else {
        //     for ([j, c] of Object.entries(path[i.nodeHuman])) {
        //         for ([j2, c2] of Object.entries(path[i.nodeElephant])) {
        //             const next = {
        //                 nodeHuman: j,
        //                 nodeElephant: j2,
        //                 released: i.released,
        //                 minuteHuman: i.minuteHuman - c,
        //                 minuteElephant: i.minuteElephant - c2,
        //                 opened: new Set(i.opened)
        //             }
        //             q.enqueue(next)
        //         }
        //     }
        // }
    }
}

readInput('day16.input')
calShortestPath()
execute2()
findMax()


// 2358 too low