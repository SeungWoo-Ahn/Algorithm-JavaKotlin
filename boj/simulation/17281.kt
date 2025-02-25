package boj.simulation

import java.util.StringTokenizer

class `17281` {
    private var n = 0
    private lateinit var playerInfo: Array<IntArray>
    private var maxScore = 0

    private fun setOrder(depth: Int, order: IntArray, used: BooleanArray) {
        if (depth == order.size) {
            val score = simulation(order)
            if (score > maxScore) {
                maxScore = score
            }
            return
        }
        if (depth == 3) {
            setOrder(depth + 1, order, used)
        } else {
            for (playerNum in used.indices) {
                if (used[playerNum]) continue
                used[playerNum] = true
                order[depth] = playerNum
                setOrder(depth + 1, order, used)
                used[playerNum] = false
            }
        }
    }

    private fun simulation(order: IntArray): Int {
        var inning = 0
        var idx = 0
        var score = 0
        while (inning < n) {
            var outCnt = 0
            val ground = BooleanArray(4)
            while (outCnt < 3) {
                val result = playerInfo[inning][order[idx]]
                if (result == 0) {
                    outCnt++
                } else {
                    ground[0] = true
                    for (base in ground.lastIndex downTo 0) {
                        if (ground[base]) {
                            val nextBase = base + result
                            if (nextBase > ground.lastIndex) {
                                score++
                            } else {
                                ground[nextBase] = true
                            }
                            ground[base] = false
                        }
                    }
                }
                idx = idx.nextIdx()
            }
            inning++
        }
        return score
    }

    private fun Int.nextIdx(): Int {
        return (this + 1) % PLAYER_NUM
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()
        var st: StringTokenizer
        playerInfo = Array(n) {
            st = StringTokenizer(readLine(), " ")
            IntArray(PLAYER_NUM) { st.nextToken().toInt() }
        }
    }

    fun solution() {
        input()
        val order = IntArray(PLAYER_NUM)
        val used = BooleanArray(PLAYER_NUM)
        order[3] = 0
        used[0] = true
        setOrder(0, order, used)
        print(maxScore)
    }

    companion object {
        private const val PLAYER_NUM = 9
    }
}

fun main() {
    `17281`().solution()
}