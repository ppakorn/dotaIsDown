class Node {
    constructor(name, parent) {
        this.name = name
        this.parent = parent
        this.subdirs = {}
        this.files = {}
        this.size = 0
    }

    addSubDir(name) {
        const subDir = new Node(name, this)
        this.subdirs[name] = subDir
    }

    addFile(size, name) {
        this.files[name] = size
    }

    cd(subdir) {
        if (!this.subdirs[subdir]) {
            throw `${subdir} not found`
        }
        return this.subdirs[subdir]
    }
}

function calSize(node) {
    const sizeFiles = Object.values(node.files).reduce((previous, size) => {
        return previous + size
    }, 0)
    const sizeSubdirs = Object.values(node.subdirs).reduce((previous, subnode) => {
        return previous + calSize(subnode)
    }, 0)
    node.size = sizeFiles + sizeSubdirs
    return node.size
}

function print(node) {
    console.log(node.name, node.size, node.subdirs, node.files)
    Object.values(node.subdirs).forEach( subnode => {
        print(subnode)
    })
}

let sum = 0
function findAtMost100k(node) {
    if (node.size <= 100_000) {
        sum += node.size
    }
    Object.values(node.subdirs).forEach( subnode => {
        findAtMost100k(subnode)
    })
    return sum
}

let min = 70_000_000
let spaceNeeded = 2_805_968 // calculated from 70M - size of "/"
function findSmallestToDelete(node) {
    if (node.size > spaceNeeded && node.size < min) {
        min = node.size
    }
    Object.values(node.subdirs).forEach( subnode => {
        findSmallestToDelete(subnode)
    })
}

function execute(filename) {
    const fs = require('fs');
    const data = fs.readFileSync(filename, 'utf8');
    const lines = data.split('\n');

    const root = new Node("/")
    let pointer = root
    lines.forEach((element, index, array) => {
        const a = element.split(" ") 
        // Ignore $ ls command
        if (a[0] == '$' && a[1] == 'ls') {
            return   
        }

        if (a[0] == '$' && a[1] == 'cd') {
            if (a[2] == '/') {
                pointer = root
            } else if (a[2] == '..') {
                pointer = pointer.parent
            } else {
                pointer = pointer.cd(a[2])
            }
        } else if (a[0] == 'dir') {
            pointer.addSubDir(a[1])
        } else {
            pointer.addFile(Number(a[0]), a[1])
        }
    });

    calSize(root)
    print(root)

    // part 1
    // sum = 0
    // console.log(findAtMost100k(root))

    findSmallestToDelete(root)
    console.log(min)
}

execute('day7.input')