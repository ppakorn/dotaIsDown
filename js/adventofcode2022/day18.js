const Queue = require("./Queue.js")

function execute() {
    const filename = 'day18.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    const cubes = new Set()
    lines.forEach((element, index) => {
        cubes.add(element)
    })

    // console.log(countSurfaces(cubes))
    // findMinMax(cubes)
    flow(cubes)
}

function findMinMax(cubes) {
    let minX = 100
    let maxX = -100
    let minY = 100
    let maxY = -100
    let minZ = 100
    let maxZ = -100
    for (const cube of cubes) {
        const elements = cube.split(",").map(Number)
        const x = elements[0]
        const y = elements[1]
        const z = elements[2]

        minX = Math.min(x, minX)
        maxX = Math.max(x, maxX)
        minY = Math.min(y, minY)
        maxY = Math.max(y, maxY)
        minZ = Math.min(z, minZ)
        maxZ = Math.max(z, maxZ)
    }

    console.log(minX, maxX)
    console.log(minY, maxY)
    console.log(minZ, maxZ)
}

// Part1
function countSurfaces(cubes) {
    let count = 0
    for (const cube of cubes) {
        const elements = cube.split(",").map(Number)
        const x = elements[0]
        const y = elements[1]
        const z = elements[2]

        if (!cubes.has([x + 1, y, z].join(","))) {
            count += 1
        }
        if (!cubes.has([x - 1, y, z].join(","))) {
            count += 1
        }
        if (!cubes.has([x, y + 1, z].join(","))) {
            count += 1
        }
        if (!cubes.has([x, y - 1, z].join(","))) {
            count += 1
        }
        if (!cubes.has([x, y, z + 1].join(","))) {
            count += 1
        }
        if (!cubes.has([x, y, z - 1].join(","))) {
            count += 1
        }
    }
    return count
}

// Part2
function flow(cubes_str) {
    // Create a 3-dimensional array with a size of 21x21x21 (for edge)
    // Because the inputs are all < 20
    
    // The grid code are
    // 0 air (default)
    // 1 lava (from input)
    // 2 water (traverse later)
    const size = 21
    const grid = Array.from({ length: size }, () =>
        Array.from({ length: size }, () => Array(size).fill(0))
    )

    // Transform cubes from string to int array
    const cubes = Array.from(cubes_str).map( x => x.split(",").map(Number) )

    // Set lava in grid
    for (const cube of cubes) {
        grid[cube[0]][cube[1]][cube[2]] = 1
    }

    traverse(grid)
    countSurfaces2(cubes, grid)
}

// Let the water fill in from 0, 0, 0 to see which area touch the surface
function traverse(grid) {
    const q = new Queue([0, 0, 0])
    while (q.size > 0) {
        const p = q.dequeue()
        const x = p[0]
        const y = p[1]
        const z = p[2]

        if (grid[x][y][z] > 0) {
            continue
        }

        grid[x][y][z] = 2

        // Go backward
        if (x - 1 > 0 && grid[x - 1][y][z] == 0) {
            q.enqueue([x - 1, y, z])
        }
        if (y - 1 > 0 && grid[x][y - 1][z] == 0) {
            q.enqueue([x, y - 1, z])
        }
        if (z - 1 > 0 && grid[x][y][z - 1] == 0) {
            q.enqueue([x, y, z - 1])
        }

        // Go forward
        if (x + 1 < grid.length && grid[x + 1][y][z] == 0) {
            q.enqueue([x + 1, y, z])
        }
        if (y + 1 < grid.length && grid[x][y + 1][z] == 0) {
            q.enqueue([x, y + 1, z])
        }
        if (z + 1 < grid.length && grid[x][y][z + 1] == 0) {
            q.enqueue([x, y, z + 1])
        }
    }

    // Check
    let count = 0
    for (const [i, v1] of grid.entries()) {
        for (const [j, v2] of v1.entries()) {
            for (const [k, v3] of v2.entries()) {
                if (v3 === 0) {
                    count += 1
                }
                // if (v3 === 1) {
                //     console.log(i, j, k)
                // }
            }
        }
    }
    console.log(count)

    console.log("Done traverse")
}

function countSurfaces2(cubes, grid) {
    let count = 0
    for (const cube of cubes) {
        const x = cube[0]
        const y = cube[1]
        const z = cube[2]

        // console.log(x, y, z)

        if (x === 0 || grid[x - 1][y][z] === 2) {
            count += 1
        }
        if (grid[x + 1][y][z] === 2) {
            count += 1
        }
        if (y === 0 || grid[x][y - 1][z] === 2) {
            count += 1
        }
        if (grid[x][y + 1][z] === 2) {
            count += 1
        }
        if (z === 0 || grid[x][y][z - 1] === 2) {
            count += 1
        }
        if (grid[x][y][z + 1] === 2) {
            count += 1
        }
    }

    console.log(count)
}

execute()