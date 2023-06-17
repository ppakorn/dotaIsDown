const digit = {
    '0': 0,
    '1': 1,
    '2': 2,
    '-': -1,
    '=': -2
}

function execute() {
    const filename = 'day25.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    let sum = 0
    lines.forEach(line => {
        sum += decode(line)
    })

    console.log(snafu(sum))
}

function decode(str) {
    let power = 0
    let c = 0
    str.split("").reverse().forEach(x => {
        c += digit[x] * 5 ** power
        power += 1
    })
    return c
}

function log5(n) {
    return Math.log(n) / Math.log(5)
}

function countSNAFUdigit(n) {
    const digitCountInNormalBase5 = Math.floor(log5(n)) + 1
    if (n < Math.floor(5 ** digitCountInNormalBase5 / 2) + 1) {
        return digitCountInNormalBase5
    } else {
        return digitCountInNormalBase5 + 1
    }
}

function snafu(n) {
    let ans = ""
    const digitCount = countSNAFUdigit(n)
    for (let i = digitCount - 1; i >= 0; i--) {
        const base = 5 ** i
        const halfBase = Math.floor(base / 2) + 1
        const a = Math.floor((n - halfBase) / base) + 1
        if (a === -1) {
            ans += '-'
        } else if (a === -2) {
            ans += '='
        } else {
            ans += a
        }
        n -= base * a
    }

    return ans
}

// console.log(decode("1=-0-2"))
// console.log(decode("12111"))
// console.log(decode("2=0="))
// console.log(decode("21"))
// console.log(decode("2=01"))
// console.log(decode("111"))
// console.log(decode("20012"))
// console.log(decode("112"))
// console.log(decode("1=-1="))
// console.log(decode("1-12"))
// console.log(decode("12"))
// console.log(decode("1="))
// console.log(decode("122"))


// snafu(1747)
// snafu(906)
// snafu(198)
// snafu(11)
// snafu(201)
// snafu(31)
// snafu(1257)
// snafu(32)
// snafu(353)
// snafu(107)
// snafu(7)
// snafu(3)
// snafu(37)


execute()


// for (let i = 0; i <= 10000; i++) {
//     const s = snafu(i)
//     const i2 = decode(s)
//     if (i2 != i) {
//         console.log(i, s, i2)
//         break
//     }
// }