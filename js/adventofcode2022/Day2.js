function calPoint(elf, my) {
    const e = (elf.charCodeAt(0) - 'A'.charCodeAt(0) + 1)
    const m = (my.charCodeAt(0) - 'X'.charCodeAt(0) + 1)
    let point = m 
    if (m - e == 1 || m - e == -2) {
        point += 6
    } else if (m == e) {
        point += 3
    }
    return point
}

function calPoint2(elf, result) {
    const e = (elf.charCodeAt(0) - 'A'.charCodeAt(0))
    const r = (result.charCodeAt(0) - 'X'.charCodeAt(0)) * 3
    let point = r
    if (r == 0)
        point += ((e + 2) % 3) + 1
    else if (r == 3)
        point += e + 1
    else
        point += ((e + 1) % 3) + 1
    return point
}

function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    let point = 0
    lines.forEach((element, index, array) => {
        const a = element.split(' ');
        point += calPoint2(a[0], a[1])
    });
    return point
}


console.log(execute('Day2.input'))
