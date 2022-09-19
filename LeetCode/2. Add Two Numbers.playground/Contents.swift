public class ListNode {
    public var val: Int
    public var next: ListNode?
    public init(_ val: Int) {
        self.val = val
        self.next = nil
    }
}

class Solution {
    var c = 0
    func addTwoNumbers(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        if l1 == nil && l2 == nil {
            return c != 0 ? ListNode(c) : nil
        }
        var val = ((l1?.val ?? 0) + (l2?.val ?? 0) + c)
        c = val / 10
        val = val % 10
        let result = ListNode(val)
        result.next = addTwoNumbers(l1?.next, l2?.next)
        return result
    }
}

let a = ListNode(2)
let b = ListNode(4)
let c = ListNode(3)
a.next = b
b.next = c
let d = ListNode(5)
let e = ListNode(6)
let f = ListNode(4)
d.next = e
e.next = f

let r = Solution().addTwoNumbers(a, d)
r?.val
r?.next?.val
r?.next?.next?.val
r?.next?.next?.next?.val