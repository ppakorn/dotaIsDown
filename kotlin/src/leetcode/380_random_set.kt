package leetcode

import kotlin.random.Random

class RandomizedSet() {
    private val mem = mutableSetOf<Int>()
    private var head: Node? = null

    // ใช้ linked list มาเก็บว่าตอนนี้มีค่าอะไรบ้าง ไว้ random
    // เพราะ set access แบบ set[i] ไม่ได้
    // ตอน delete เป็น soft delete ถ้าตอน random แล้วเจอว่าโดน delete แล้วค่อยลบ node

    fun insert(`val`: Int): Boolean {
        if (isExist(`val`)) {
            return false
        }
        mem.add(`val`)

        val n = Node(`val`)
        if (head == null) {
            head = n
            n.previous = n
            n.next = n
        } else {
            head!!.next!!.previous = n
            n.next = head!!.next
            n.previous = head
            head!!.next = n
        }

        return true
    }

    fun remove(`val`: Int): Boolean {
        if (isExist(`val`)) {
            mem.remove(`val`)
            return true
        }
        return false
    }

    private fun isExist(v: Int): Boolean {
        return mem.contains(v)
    }

    fun getRandom(): Int {
        val x = Random.nextInt(0, 100)
        (0 until x).forEach {
            if (!isExist(head!!.`val`)) {
                removeNode(head!!)
            }
            moveHeadNext()
        }
        while (true) {
            moveHeadNext()

            if (isExist(head!!.`val`)) {
                return head!!.`val`
            }

            removeNode(head!!)
        }
    }

    private fun removeNode(node: Node) {
        node.previous?.next = node.next
        node.next?.previous = node.previous
    }

    private fun moveHeadNext() {
        head = head!!.next
    }
}

fun main() {
    val s = RandomizedSet()
    println(s.insert(1))
    println(s.remove(2))
    println(s.insert(2))
    println(s.getRandom())
    println(s.getRandom())
    println(s.getRandom())
    println(s.remove(1))
    println(s.insert(2))
    println(s.getRandom())
}