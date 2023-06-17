function execute() {
    const filename = 'day19.input'
    const fs = require('fs')
    const data = fs.readFileSync(filename, 'utf8')
    const lines = data.split('\n')

    const blueprints = []
    lines.forEach((element, index) => {
        const a = element.split(':')
        const b = {
            id: Number(a[0]),
            oreRobotReq: Number(a[1]),                         // ore
            clayRobotReq: Number(a[2]),                        // ore
            obsidianRobotReq: a[3].split(',').map(Number),     // ore and clay
            geodeRobotReq: a[4].split(',').map(Number)         // ore and obsidian
        }

        b.maxOreConsume = Math.max(b.oreRobotReq, b.clayRobotReq, b.obsidianRobotReq[0], b.geodeRobotReq[0])
        blueprints.push(b)
    })

    let c = 1
    blueprints.forEach(bp => {
        max = -1
        findMax(bp)
        c *= max
    })
    console.log(c)

    // findMax(blueprints[0])
    // console.log(max)
}

function findMax(blueprint) {
    const initialState = {
        oreRobot: 1,
        clayRobot: 0,
        obsidianRobot: 0,
        geodeRobot: 0,
        ore: 0,
        clay: 0,
        obsidian: 0,
        geode: 0
    }
    
    return run(blueprint, initialState, 1, 'initial', [])
}

let max = 0
let lastMinute = 32
function run(blueprint, state, minute, lastAction, siblings) {

    // Minute start at 1 - lastMinute
    if (minute > lastMinute) {
        if (state.geode > max) {
            max = state.geode
        }
        return
    }

    // If best possible way still cannot beat max, ignore
    if (possibleMax(state.geode, state.currentGeodeRobot, minute) <= max) {
        return
    }

    const newStates = buildRobot(blueprint, state, minute, lastAction, siblings)
    collect(newStates, state)

    newStates.forEach(newState => {
        run(blueprint, newState[1], minute + 1, newState[0], newStates)
    })
}

function buildRobot(blueprint, currentState, minute, lastAction, siblings) {

    // Return pair of an action (robot built) and the new state
    // Such as [['clay', {...}], ['obsidian', {...}]

    const siblingActions = siblings.map(x => x[0])
    const lastTurnCanBuildObsidianButDidNot = lastAction == 'nothing' && siblingActions.includes('obsidian')
    const lastTurnCanBuildClayButDidNot = lastAction == 'nothing' && siblingActions.includes('clay')
    const lastTurnCanBuildOreButDidNot = lastAction == 'nothing' && siblingActions.includes('ore')

    const canBuildClayRobot = minute <= lastMinute - 5              // This robot won't produce any geode
        && currentState.clayRobot < blueprint.obsidianRobotReq[1]   // If we have enough robot for max consumption per turn, no need more robots
        && !lastTurnCanBuildClayButDidNot
        && currentState.ore >= blueprint.clayRobotReq
    const canBuildObsidianRobot = minute <= lastMinute - 3                                        
        && currentState.obsidianRobot < blueprint.geodeRobotReq[1]         
        && !lastTurnCanBuildObsidianButDidNot
        && currentState.ore >= blueprint.obsidianRobotReq[0]
        && currentState.clay >= blueprint.obsidianRobotReq[1]
    const canBuildGeodeRobot = minute <= lastMinute - 1
        && currentState.ore >= blueprint.geodeRobotReq[0]
        && currentState.obsidian >= blueprint.geodeRobotReq[1]
    const canBuildOreRobot = minute <= lastMinute - 3
        && currentState.oreRobot < blueprint.maxOreConsume
        && !lastTurnCanBuildOreButDidNot
        && currentState.ore >= blueprint.oreRobotReq

    // If can build geode robot, build it no matter what
    if (canBuildGeodeRobot) {
        return [buildGeodeRobot(blueprint, currentState)]
    }

    // After this is DFS
    const newStates = []

    // Build clay robot
    if (canBuildObsidianRobot) {
        newStates.push(buildObsidianRobot(blueprint, currentState))
    }

    // Build obsidian robot
    if (canBuildClayRobot) {
        newStates.push(buildClayRobot(blueprint, currentState))
    }

    // Build ore robot
    if (canBuildOreRobot) {
        newStates.push(buildOreRobot(blueprint, currentState))
    }

    // For do nothing
    newStates.push(['nothing', currentState])
    return newStates
}

function buildClayRobot(blueprint, currentState) {
    const newState = Object.assign({}, currentState)
    newState.clayRobot += 1
    newState.ore -= blueprint.clayRobotReq
    return ['clay', newState]
}

function buildObsidianRobot(blueprint, currentState) {
    const newState = Object.assign({}, currentState)
    newState.obsidianRobot += 1
    newState.ore -= blueprint.obsidianRobotReq[0]
    newState.clay -= blueprint.obsidianRobotReq[1]
    return ['obsidian', newState]
}

function buildGeodeRobot(blueprint, currentState) {
    const newState = Object.assign({}, currentState)
    newState.geodeRobot += 1
    newState.ore -= blueprint.geodeRobotReq[0]
    newState.obsidian -= blueprint.geodeRobotReq[1]
    return ['geode', newState]
}

function buildOreRobot(blueprint, currentState) {
    const newState = Object.assign({}, currentState)
    newState.oreRobot += 1
    newState.ore -= blueprint.oreRobotReq
    return ['ore', newState]
}

// Mutating
function collect(newStates, currentState) {
    newStates.forEach(x => {
        const newState = x[1]
        newState.ore += currentState.oreRobot
        newState.clay += currentState.clayRobot
        newState.obsidian += currentState.obsidianRobot
        newState.geode += currentState.geodeRobot
    })
}

function possibleMax(currentGeode, currentGeodeRobot, minute) {
    const remainingMinute = lastMinute - minute
    const buildFromCurrentRobot = remainingMinute * currentGeodeRobot
    const possibleFromFutureRobot = remainingMinute * (remainingMinute - 1) / 2
    return currentGeode + buildFromCurrentRobot + possibleFromFutureRobot
}

execute()