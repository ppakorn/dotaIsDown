"use strict";
var _ = require('lodash');
function execute(s) {
    let half = s.length / 2;
    let firstHalf = s.substring(0, half).split("");
    let secondHalf = s.substring(half).split("");
    let intercept = _.interception(firstHalf, secondHalf);
    if (intercept.length > 1) {
        throw "expect only 1 intercept";
    }
    return calPoint(intercept);
}
function calPoint(intercept) {
    const c = intercept[0].charCodeAt(0);
    if (c >= 'a'.charCodeAt(0)) {
        return c - 'a'.charCodeAt(0) + 1;
    }
    else {
        return c - 'A'.charCodeAt(0) + 27;
    }
}
console.log(execute("vJrwpWtwJgWrhcsFMMfFFhFp"));
console.log(execute("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"));
console.log(execute("PmmdzqPrVvPwwTWBwg"));
console.log(execute("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"));
console.log(execute("ttgJtRGJQctTZtZT"));
console.log(execute("CrZsJsPPZsGzwwsLwLmpwMDw"));
//# sourceMappingURL=Day3.js.map