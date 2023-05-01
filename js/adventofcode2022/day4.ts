function findFullyContain(s0: string, s1: string): boolean {
    const a = s0.split('-').map(Number)
    const b = s1.split('-').map(Number)
    return (a[0] <= b[0] && a[1] >= b[1]) || (a[0] >= b[0] && a[1] <= b[1])
}

function findNonOverlap(s0: string, s1: string): boolean {
    const a = s0.split('-').map(Number)
    const b = s1.split('-').map(Number)
    if (a[0] < b[0]) {
        return a[1] < b[0]
    } else if (b[0] < a[0]) {
        return b[1] < a[0]
    }
    return false
}

function execute(filename: string) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    let count = 0
    lines.forEach((element: string, index: number, array: string[]) => {
        const a = element.split(",")
        if (findNonOverlap(a[0], a[1])) {
            count += 1
        }
    });
    return count
}

console.log(execute("day4.input"))