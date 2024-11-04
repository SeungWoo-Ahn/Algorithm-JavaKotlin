package programmers.Practice.Level2

import java.util.PriorityQueue

class ArcheryCompetition {
    private data class Case(val scoreGap: Int, val info: List<Int>) : Comparable<Case> {
        override fun compareTo(other: Case): Int {
            if (scoreGap != other.scoreGap) {
                return other.scoreGap - scoreGap
            }
            for (i in 10 downTo 0) {
                if (info[i] != other.info[i]) {
                    return other.info[i] - info[i]
                }
            }
            return 0
        }
    }

    private val pq = PriorityQueue<Case>()

    private fun dfs(
        depth: Int = 0,
        restArrowCnt: Int,
        peachScore: Int = 0,
        ryanScore: Int = 0,
        peachInfo: IntArray,
        ryanInfo: IntArray
    ) {
        if (depth == peachInfo.size) {
            if (ryanScore > peachScore) {
                pq += Case(
                    scoreGap = ryanScore - peachScore,
                    info = ryanInfo.toList()
                )
            }
            return
        }
        val k = 10 - depth
        for (arrowCnt in 0..restArrowCnt) {
            ryanInfo[depth] = arrowCnt
            if (arrowCnt == 0 && peachInfo[depth] == 0) { // 아무도 k점 못 가져감
                dfs(
                    depth = depth + 1,
                    restArrowCnt = restArrowCnt,
                    peachScore = peachScore,
                    ryanScore = ryanScore,
                    peachInfo = peachInfo,
                    ryanInfo = ryanInfo
                )
            } else if (arrowCnt > peachInfo[depth]) { // 라이언이 이김
                dfs(
                    depth = depth + 1,
                    restArrowCnt = restArrowCnt - arrowCnt,
                    peachScore = peachScore,
                    ryanScore = ryanScore + k,
                    peachInfo = peachInfo,
                    ryanInfo = ryanInfo
                )
            } else { // 어피치가 이김
                dfs(
                    depth = depth + 1,
                    restArrowCnt = restArrowCnt - arrowCnt,
                    peachScore = peachScore + k,
                    ryanScore = ryanScore,
                    peachInfo = peachInfo,
                    ryanInfo = ryanInfo
                )
            }
            ryanInfo[depth] = 0
        }
    }

    fun solution(n: Int, info: IntArray): IntArray {
        val ryanInfo = IntArray(info.size)
        dfs(restArrowCnt = n, peachInfo = info, ryanInfo = ryanInfo)
        return if (pq.isEmpty()) intArrayOf(-1)
        else pq.peek().info.toIntArray()
    }
}

fun main() {
    val n = 5
    val info = intArrayOf(2,1,1,1,0,0,0,0,0,0,0)
    val answer = ArcheryCompetition().solution(n, info)
    print(answer.joinToString())
}