const fs = require('fs');

const data = fs.readFileSync('Day1.input', 'utf8');
const lines = data.split('\n');

let max = [0, 0, 0];
let current = 0;
lines.forEach((element, index, array) => {
    if (element === "") {
        calMax(current)
        current = 0
    } else {
        current += parseInt(element)
    }
});

function calMax(newVal) {
    if (newVal > max[0]) {
        max[0] = newVal
        max.sort()
    }
}

console.log(max[0] + max[1] + max[2])

