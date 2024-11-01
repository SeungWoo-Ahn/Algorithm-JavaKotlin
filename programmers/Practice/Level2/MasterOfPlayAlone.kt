package programmers.Practice.Level2

import java.util.Collections
import java.util.PriorityQueue

class MasterOfPlayAlone {
    fun solution(cards: IntArray): Int {
        val opened = BooleanArray(cards.size)
        val pq = PriorityQueue<Int>(Collections.reverseOrder())
        for (i in cards.indices) {
            var cur = cards[i]
            var groupCnt = 0
            while (opened[cur - 1].not()) {
                opened[cur - 1] = true
                cur = cards[cur - 1]
                groupCnt++
            }
            if (groupCnt > 0) {
                pq += groupCnt
            }
        }
        pq += 0 // 1번 그룹에 모두 모여 있을 경우
        return pq.poll() * pq.peek()
    }
}

fun main() {
    val cards = intArrayOf(8,6,3,7,2,5,1,4)
    val answer = MasterOfPlayAlone().solution(cards)
    print(answer)
}