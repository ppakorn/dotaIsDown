function runHorizontal(grid, seenable) {
    // Left to right
    grid.forEach((row, i) => {
        let max = -1
        row.forEach((tree, j) => {
            if (tree > max) {
                seenable[i][j] = true
                max = tree
            }
        })
    })

    // Right to left
    grid.forEach((row, i) => {
        let max = -1
        for (let j = row.length - 1; j>=0; j--) {
            const tree = row[j]
            if (tree > max) {
                seenable[i][j] = true
                max = tree
            }
        }
    })
}

function runVertical(grid, seenable) {
    // Top to bottom
    const row = grid.length
    const column = grid[0].length
    for (let i = 0; i < column; i++) {
        let max = -1
        for (let j = 0; j < row; j++) {
            const tree = grid[j][i]
            if (tree > max) {
                seenable[j][i] = true
                max = tree
            }
        }
    }

    // Bottom to top
    for (let i = column - 1; i >= 0; i--) {
        let max = -1
        for (let j = row - 1; j >= 0; j--) {
            const tree = grid[j][i]
            if (tree > max) {
                seenable[j][i] = true
                max = tree
            }
        }
    }
}

function countSeenable(seenable) {
    return seenable.reduce((acc, row) => {
        return acc + row.filter(Boolean).length
    }, 0)
}

function findMaxPosition(grid) {
    const row = grid.length
    const column = grid[0].length
    let max = 0
    for (let i = 1; i < row; i++) {
        for (let j = 1; j < column; j++) {
            const tree = grid[i][j]

            // Look up
            let up = 0
            for (let k = i - 1; k >= 0; k--) {
                up += 1
                if (grid[k][j] >= tree) {
                    break
                }
            }

            // Look down
            let down = 0
            for (let k = i + 1; k < row; k++) {
                down += 1
                if (grid[k][j] >= tree) {
                    break
                }
            }

            // Look left
            let left = 0
            for (let k = j - 1; k >= 0; k--) {
                left += 1
                if (grid[i][k] >= tree) {
                    break
                }
            }

            // Look right
            let right = 0
            for (let k = j + 1; k < column; k++) {
                right += 1
                if (grid[i][k] >= tree) {
                    break
                }
            }

            const score = up * down * left * right
            if (score > max) {
                max = score
            }
        }
    }
    return max
}


function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    const grid = lines.map(l => {
        const a = l.split("")
        return a.map(i => Number(i))
    })
    
    const row = grid.length
    const column = grid[0].length
    const seenable = Array.from({ length: row }, () => 
        Array.from({ length: column }, () => false )
    )

    runHorizontal(grid, seenable)
    runVertical(grid, seenable)
    // console.table(seenable)
    // console.log(countSeenable(seenable))

    console.log(findMaxPosition(grid))
}

execute('day8.input')