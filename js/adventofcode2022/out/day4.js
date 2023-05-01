"use strict";
function findFullyContain(s0, s1) {
    const a = s0.split('-').map(Number);
    const b = s1.split('-').map(Number);
    return (a[0] <= b[0] && a[1] >= b[1]) || (a[0] >= b[0] && a[1] <= b[1]);
}
console.log(findFullyContain("1-7", "2-6"));
//# sourceMappingURL=day4.js.map