function check() {
    console.log(i, X)
    if (i == checkCycle) {
        sum += checkCycle * X
        checkCycle += 40
    }
}

function checkPixel() {
    const position = (i % 40) - 1
    if (Math.abs(position - X) <= 1) {
        crt += "#"
    } else {
        crt += "."
    }

    if (i % 40 == 0) {
        crt += "\n"
    }
}

let sum = 0
let checkCycle = 20
let i = 1
let X = 1
let crt = ""
function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    lines.forEach((element, index, array) => {
        const a = element.split(" ")

        if (a[0] == 'noop') {
            checkPixel()
            i += 1
        } else {
            const offset = Number(a[1])
            checkPixel()
            i += 1
            checkPixel()
            i += 1
            X += offset
        }
    })

    console.log(crt)
}

execute('day10.input')
