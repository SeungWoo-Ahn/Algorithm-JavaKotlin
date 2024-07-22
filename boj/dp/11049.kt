package boj.dp

class `11049` {
    private var matrix: Array<Pair<Int, Int>> = arrayOf()
    private var dp: Array<IntArray> = arrayOf()

    private fun recur(s: Int, e: Int): Int {
        if (dp[s][e] != 0) return dp[s][e]
        if (s >= e) return 0
        var min = Int.MAX_VALUE
        for (mid in s until e) {
            min = minOf(
                min,
                recur(s, mid)
                + recur(mid + 1, e)
                + (matrix[s].first * matrix[mid].second * matrix[e].second)
            )
        }
        dp[s][e] = min
        return dp[s][e]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        matrix = Array(n) {
            val (r, c) = readLine().split(" ").map { it.toInt() }
            r to c
        }
        dp = Array(n) { IntArray(n) }
        print(recur(0, n - 1))
    }
}

fun main() {
    `11049`().solution()
}