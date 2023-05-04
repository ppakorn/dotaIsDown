import { readFileSync } from 'fs';

class MonkeyNode {
    l?: MonkeyNode;
    r?: MonkeyNode;
    op?: String;
    n?: Number;
    constructor(l?: MonkeyNode, r?: MonkeyNode, op?: string, n?: Number) {
        this.l = l
        this.r = r
        this.op = op
        this.n = n
    }
}

function execute() {
    const filename = 'day21.input'
    const data = readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    // const nodes: { [key: string]: MonkeyNode; } = {}
    // const initialCodes = lines.forEach(line => {
        // const a = line.split(': ')
        // const b = a[1].split(' ')

        // // Number monkey
        // if (b.length === 1) {
        //     nodes[a[0]] = new MonkeyNode(undefined, undefined, undefined, Number(b[0]))
        // } else {
        //     let l = nodes[a[0]]
        //     if (l === undefined) {
        //         l = new MonkeyNode()
        //     }
        //     nodes[a[0]] = new MonkeyNode()
        // }
    // })

    lines.forEach(line => {
        const a = line.split(': ')
        monkeyInputs[a[0]] = a[1]

        const b = a[1].split(' ')
        if (b.length === 1) {
            const value = Number(b[0])
            monkeyValues[a[0]] = value
            return value
        }
    })

    console.log(cal('root'))
    // console.log(monkeyValues)
}

const monkeyInputs: Record<string, string> = {}
const monkeyValues: Record<string, number> = {}

function cal(name: string): number {
    if (monkeyValues[name] !== undefined) {
        return monkeyValues[name]
    }

    const v = monkeyInputs[name].split(' ')
    const m1 = cal(v[0])
    const m2 = cal(v[2])
    let value = -20000000
    switch (v[1]) {
        case '+':
            value = m1 + m2
            break;
        case '-':
            value = m1 - m2
            break;
        case '*':
            value = m1 * m2
            break;
        case '/':
            value = m1 / m2
            break;
    }
    monkeyValues[name] = value
    return value
}

function calHuman(target: number, name: string) {
    if (name === 'humn') {
        return undefined
    }

    const v = monkeyInputs[name].split(' ')
    const m1 = 
}

execute()