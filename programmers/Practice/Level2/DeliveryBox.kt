package programmers.Practice.Level2

import java.util.Stack

class DeliveryBox {
    fun solution(order: IntArray): Int {
        val subBelt = Stack<Int>()
        var idx = 0
        var num = 1
        var result = 0
        while (idx < order.size) {
            if (order[idx] == num) {
                idx++
                num++
                result++
            } else if (order[idx] > num) {
                subBelt.push(num)
                num++
            } else {
                if (subBelt.isNotEmpty() && subBelt.peek() == order[idx]) {
                    subBelt.pop()
                    idx++
                    result++
                } else break
            }
        }
        return result
    }
}

fun main() {
    val order = intArrayOf(4, 3, 1, 2, 5)
    val answer = DeliveryBox().solution(order)
    print(answer)
}