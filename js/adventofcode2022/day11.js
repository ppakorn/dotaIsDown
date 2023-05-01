monkeys = []

function inspectItem(id, old) {
    switch (id) {
        case 0:
            return old * 2
        case 1:
            return old + 3
        case 2:
            return old + 6
        case 3:
            return old + 5
        case 4: 
            return old + 8
        case 5:
            return old * 5
        case 6:
            return old * old
        case 7:
            return old + 4
    }
}

function inspectItem_Test(id, old) {
    switch (id) {
        case 0:
            return old * 19
        case 1:
            return old + 6
        case 2:
            return old * old
        case 3:
            return old + 3
    }
}

function gcd(a, b) {
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}

function lcm(a, b) {
    return a / gcd(a, b) * b
}

monkeyLCM = 0
function findMonkeysLCM() {
    monkeyLCM = monkeys.reduce((acc, monkey) => {
        return lcm(acc, monkey.test)
    }, 1)
}
  
function readInput(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    let m = undefined
    lines.forEach((element, index, array) => {
        if (element.length == 0) {
            monkeys.push(m)
            m = undefined
        }

        const a = element.split(":")
        if (a[0].startsWith('Monkey')) {
            id = Number(a[0].split(" ")[1])
            m = {
                id: id, 
                inspectCount: 0
            }
        } else if (a[0].trim().startsWith('Starting')) {
            items = a[1].split(",").map(Number)
            m.items = items
        } else if (a[0].trim() == 'Test') {
            b = Number(a[1].split(" ").at(-1))
            m.test = b
        } else if (a[0].trim().startsWith('If true')) {
            b = Number(a[1].split(" ").at(-1))
            m.true = b
        } else if (a[0].trim().startsWith('If false')) {
            b = Number(a[1].split(" ").at(-1))
            m.false = b
        }
    })
}

function play() {
    const round = 10000
    for (let i=0; i<round; i++) {
        for (let j=0; j<monkeys.length; j++) {
            const monkey = monkeys[j]
            monkey.inspectCount += monkey.items.length
            const newWorry = monkey.items.map(x => inspectItem(j, x) % monkeyLCM)
            newWorry.forEach(x => {
                if (x % monkey.test == 0) {
                    monkeys[monkey.true].items.push(x)
                } else {
                    monkeys[monkey.false].items.push(x)
                }
            })
            monkey.items = []
        }
    }
}

readInput('day11.input')
findMonkeysLCM()
play()
monkeys.forEach(m => console.log(m.inspectCount))