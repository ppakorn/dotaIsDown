function execute() {
    const filename = 'day20.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    const initialCodes = lines.map(Number)
    const initialCodesWithOrder = []
    const l = initialCodes.length
    const count = {}
    let element0 = undefined
    const key = 811589153

    for (const c of initialCodes) {
        if (count[c] === undefined) {
            count[c] = 1
        } else {
            count[c] += 1
        }

        const element = [count[c], c]
        initialCodesWithOrder.push(element)
        if (c === 0) {
            element0 = element
        }
    }

    const wip = [...initialCodesWithOrder]
    for (let k = 0; k < 10; k++) {
        for (const i of initialCodesWithOrder) {
            const startPos = wip.indexOf(i)
            let newPos = (startPos + i[1] * key) % (l -1)
            arraymove(wip, startPos, newPos)
        }
    }

    // for (const [key, value] of Object.entries(count)) {
    //     if (value > 1) {
    //         console.log(key)
    //     }
    // }
      

    const indexOf0 = wip.indexOf(element0)
    const a = findAfter(wip, indexOf0, 1000)
    const b = findAfter(wip, indexOf0, 2000)
    const c = findAfter(wip, indexOf0, 3000)

    // console.log(wip.map(x => x[1] * key))
    console.log((a + b + c) * key)
}

function arraymove(arr, fromIndex, toIndex) {
    // if (toIndex === 0) {
    //     toIndex = arr.length
    // }
    var element = arr[fromIndex]
    arr.splice(fromIndex, 1)
    arr.splice(toIndex, 0, element)
}

function findAfter(arr, startPos, nAfter) {
    // if (nAfter >= arr.length) {
    //     nAfter = nAfter % arr.length
    // }
    let newPos = (startPos + nAfter) % arr.length
    return arr[newPos][1]
}

execute()
