"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var fs_1 = require("fs");
var MonkeyNode = /** @class */ (function () {
    function MonkeyNode(l, r, op, n) {
        this.l = l;
        this.r = r;
        this.op = op;
        this.n = n;
    }
    return MonkeyNode;
}());
function execute() {
    var filename = 'day21-test.input';
    var data = (0, fs_1.readFileSync)(filename, 'utf8');
    var lines = data.split('\n');
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
    lines.forEach(function (line) {
        var a = line.split(': ');
        monkeyInputs[a[0]] = a[1];
    });
    console.log(cal('root'));
}
var monkeyInputs = {};
var monkeyValues = {};
function cal(name) {
    if (monkeyValues[name] !== undefined) {
        return monkeyValues[name];
    }
    var v = monkeyInputs[name].split(' ');
    if (v.length === 1) {
        var value_1 = Number(v[0]);
        monkeyValues[name] = value_1;
        return value_1;
    }
    var m1 = cal(v[0]);
    var m2 = cal(v[2]);
    var value = -20000000;
    switch (v[1]) {
        case '+':
            value = m1 + m2;
            break;
        case '-':
            value = m1 - m2;
            break;
        case '*':
            value = m1 * m2;
            break;
        case '/':
            value = m1 / m2;
            break;
    }
    monkeyValues[name] = value;
    return value;
}
execute();
