import { readFileSync } from 'fs';

function execute() {
    const filename = 'day21.input'
    const data = readFileSync(filename, 'utf8')
    const lines = data.split('\n')

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

    // Replace root with L - R, and will start with target=0
    const a = monkeyInputs['root'].split(' ')
    monkeyInputs['root'] = `${a[0]} - ${a[2]}`

    // console.log(monkeyInputs['root'])
    // console.log(monkeyValues)
    console.log(calHuman(0, 'root'))
}

const monkeyInputs: Record<string, string> = {}
const monkeyValues: Record<string, number> = {}

function cal(name: string) {
    if (name === 'humn') {
        return undefined
    }

    if (monkeyValues[name] !== undefined) {
        return monkeyValues[name]
    }

    const v = monkeyInputs[name].split(' ')
    const m1 = cal(v[0])
    const m2 = cal(v[2])
    if (m1 === undefined || m2 === undefined) {
        return undefined
    }

    // dummy
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
        console.log(target)
        return
    }

    const v = monkeyInputs[name].split(' ')
    const l = cal(v[0])
    const r = cal(v[2])

    const knownValue: number = (l || r)!
    let next = v[0]
    if (r === undefined) {
        next = v[2]
    }

    // dummy
    let newTarget = -20000000
    switch (v[1]) {
        case '+':
            newTarget = target - knownValue
            break;
        case '-':
            if (l === undefined) {
                newTarget = target + knownValue
            } else {
                newTarget = knownValue - target
            }
            break;
        case '*':
            newTarget = target / knownValue!
            break;
        case '/':
            if (l === undefined) {
                newTarget = target * knownValue
            } else {
                newTarget = knownValue / target
            }
            break;
    }

    calHuman(newTarget, next)
}

execute()