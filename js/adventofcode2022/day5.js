stacks = [
    undefined,
    "tpzcslqn".split(""),
    "lptvhcg".split(""),
    "dczf".split(""),
    "gwtdlmvc".split(""),
    "pwc".split(""),
    "pfjdctsz".split(""),
    "vwgbd".split(""),
    "njsqhw".split(""),
    "rcqfslv".split("")
]

function move(times, from , to) {
    for(let i = 0; i < times; i++){
        const a = stacks[from].pop()
        stacks[to].push(a)
    }
}

function move2(size, from, to) {
    const removed = stacks[from].splice(stacks[from].length - size, size) 
    stacks[to].push(...removed)
}

function printTopStacks() {
    for(let i = 1; i < stacks.length; i++) {
        console.log(stacks[i][stacks[i].length - 1])
    }
}

function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    lines.forEach((element, index, array) => {
        const a = element.split(" ");
        move2(a[0], a[1], a[2])
    });

    printTopStacks()
}

execute('day5.input')