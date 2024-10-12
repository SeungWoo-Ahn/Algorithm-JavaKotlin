package programmers.Practice.Level2

import java.util.PriorityQueue

class DefenseGame {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        val pq = PriorityQueue<Int>()
        var restN = n
        for (i in enemy.indices) {
            if (pq.size < k) {
                pq += enemy[i]
            } else {
                var num = enemy[i]
                if (pq.peek() < enemy[i]) {
                    num = pq.poll()
                    pq += enemy[i]
                }
                if (restN >= num) {
                    restN -= num
                } else {
                    return i
                }
            }
        }
        return enemy.size
    }
}

fun main() {
    val n = 7
    val k = 3
    val enemy = intArrayOf(4, 2, 4, 5, 3, 3, 1)
    val answer = DefenseGame().solution(n, k, enemy)
    println(answer)
}