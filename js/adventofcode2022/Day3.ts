const _ = require('lodash');

function find(s: string): number {
    let half = s.length / 2;
    let firstHalf = s.substring(0, half).split("");
    let secondHalf = s.substring(half).split("");
    let intersect = _.intersection(firstHalf, secondHalf);
    if (intersect.length > 1) {
        throw "expect only 1 intersection"
    }

    return calPoint(intersect)
}

function find2(s0: string, s1: string, s2: string): number {
    let intersect1 = _.intersection(s0.split(""), s1.split(""));
    let intersect2 = _.intersection(intersect1, s2.split(""));
    if (intersect2.length > 1) {
        throw "expect only 1 intersection"
    }
    return calPoint(intersect2)
}

function calPoint(intersection: string[]): number {
    const c = intersection[0].charCodeAt(0)
    if (c >= 'a'.charCodeAt(0)) {
        return c - 'a'.charCodeAt(0) + 1
    } else {
        return c - 'A'.charCodeAt(0) + 27
    }
}

function execute(filename: string) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    let point = 0
    let s0: string = ""
    let s1: string = ""
    lines.forEach((element: string, index: number, array: string[]) => {
        if (index % 3 == 0) {
            s0 = element
        } else if (index % 3 == 1) {
            s1 = element
        } else {
            point += find2(s0, s1, element)
        }
    });
    return point
}

console.log(execute("day3.input"))