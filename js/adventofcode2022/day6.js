function checkUniqueness(arr) {
    const set = new Set(arr)
    return set.size == arr.length
}

function findStartSignal(s) {
    let packetSize = 14
    let mem = s.slice(0, packetSize).split("")
    let i = packetSize
    if (checkUniqueness(mem)) {
        return i+1
    }
    
    for (i; i < s.length; i++) {
        const c = s[i]
        mem[i % packetSize] = c
        if (checkUniqueness(mem)) {
            return i+1
        }
    }

    throw "No Signal"
}

function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    lines.forEach((element, index, array) => {
        console.log(findStartSignal(element))
    });
}

execute('day6.input')