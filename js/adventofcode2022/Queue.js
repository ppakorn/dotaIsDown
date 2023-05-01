class QueueNode {
    constructor(obj) {
        this.obj = obj
        this.next = undefined
    }
}

class Queue {
    constructor(headObj) {
        this.head = new QueueNode(headObj)
        this.size = 1
    }

    enqueue(obj) {
        let i = this.head
        if (i === undefined) {
            this.head = new QueueNode(obj)
            this.size = 1
            return
        }

        while (i.next) {
            i = i.next
        }
        i.next = new QueueNode(obj)
        this.size += 1
    }

    dequeue() {
        const r = this.head
        this.head = this.head.next
        this.size -= 1
        return r.obj
    }

    print() {
        let i = this.head
        console.log(i.obj)
        while (i.next) {
            i = i.next
            console.log(i.obj)
        }
    }
}

module.exports = Queue