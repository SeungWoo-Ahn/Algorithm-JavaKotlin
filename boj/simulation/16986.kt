package boj.simulation

import java.util.StringTokenizer

class `16986` {
    private var n = 0
    private var k = 0
    private lateinit var a: Array<IntArray>
    private lateinit var bigData: Array<IntArray>
    private var found = false

    private fun permutation(depth: Int, used: BooleanArray) {
        if (found) return
        if (depth == n) {
            val jiWooWin = simulation()
            if (jiWooWin) {
                found = true
            }
            return
        }
        for (i in used.indices) {
            if (used[i]) continue
            used[i] = true
            bigData[0][depth] = i
            permutation(depth + 1, used)
            used[i] = false
        }
    }

    private fun simulation(): Boolean {
        val winCnt = IntArray(3)
        val idx = IntArray(3)
        var player1 = 0
        var player2 = 1
        while (idx[0] < n) {
            val winner = play(player1, player2, idx)
            val restPlayer = getRestPlayer(player1, player2)
            if (++winCnt[winner] == k) {
                return winner == 0
            }
            idx[player1]++
            idx[player2]++
            player1 = winner
            player2 = restPlayer
        }
        return false
    }

    private fun play(player1: Int, player2: Int, idx: IntArray): Int {
        val result1 = bigData[player1][idx[player1]]
        val result2 = bigData[player2][idx[player2]]
        return when (a[result1][result2]) {
            0 -> player2
            1 -> maxOf(player1, player2)
            2 -> player1
            else -> throw IllegalArgumentException()
        }
    }

    private fun getRestPlayer(player1: Int, player2: Int): Int {
        return 3 - (player1 + player2)
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        k = st.nextToken().toInt()
        a = Array(n) {
            st = StringTokenizer(readLine(), " ")
            IntArray(n) { st.nextToken().toInt() }
        }
        bigData = Array(3) { IntArray(20) }
        for (player in 1..2) {
            st = StringTokenizer(readLine(), " ")
            for (i in bigData[player].indices) {
                bigData[player][i] = st.nextToken().toInt() - 1
            }
        }
    }

    fun solution() {
        input()
        val used = BooleanArray(n)
        permutation(0, used)
        if (found) {
            print(1)
        } else {
            print(0)
        }
    }
}

fun main() {
    `16986`().solution()
}