
// Return 1 = wrong order, 0 = can't tell, -1 = right order
function compareInt(a, b) {
    if (a == b) {
        return 0
    } else if (a < b) {
        return -1
    } else {
        return 1
    }
}

function compare(a, b) {
    for (let i = 0; i < a.length; i++) {
        if (i >= b.length) {
            return 1
        }

        let result = undefined
        if (Array.isArray(a[i]) && Array.isArray(b[i])) {
            result = compare(a[i], b[i])
        } else if (Number.isInteger(a[i]) && Number.isInteger(b[i])) {
            result = compareInt(a[i], b[i])
        } else if (Number.isInteger(a[i]) && Array.isArray(b[i])) {
            result = compare([a[i]], b[i])
        } else if (Array.isArray(a[i]) && Number.isInteger(b[i])) {
            result = compare(a[i], [b[i]])
        }

        if (result === undefined) {
            throw "result undefined"
        }

        if (result != 0) {
            return result
        }
    }

    if (a.length < b.length) {
        return -1
    } else if (a.length == b.length) {
        return 0
    } else {
        return 1
    }
}

function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    let a = undefined
    let b = undefined
    let sum = 0
    let i = 0
    lines.forEach((element, index, array) => {
        if (index % 3 == 0) {
            a = JSON.parse(element)
        } else if (index % 3 == 1){
            b = JSON.parse(element)
        } else {
            // console.log(a)
            // console.log(b)
            // console.log(compare(a, b))
            // console.log("")
            i += 1
            if (compare(a, b) == -1) {
                sum += i
            }
        }
    });

    return sum
}

function execute2(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n').filter(l => l.length > 0);

    const a = lines.map(JSON.parse)
    const d1 = [[2]]
    const d2 = [[6]]
    a.push(d1)
    a.push(d2)
    a.sort(compare)
    // a.forEach(l => console.log(JSON.stringify(l)))

    let x = 0
    for (let i = 0; i < a.length; i++) {
        if (a[i] == d1) {
            x = i + 1
        } else if (a[i] == d2) {
            return x * (i + 1)
        }
    }
}

console.log(execute2('day13.input'))
// const a = [7,7,7,7]
// const b = [7,7,7]
// console.log(compare(a, b))