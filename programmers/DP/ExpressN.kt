package programmers.DP

class ExpressN {
    fun solution(N: Int, number: Int): Int {
        val dp = Array(9) { mutableListOf<Int>() }
        val history = HashSet<Int>()
        var v = N
        for (i in 1..8) {
            if (v == number) {
                return i
            }
            dp[i] += v
            history += v
            v = v * 10 + N
        }
        for (i in 2..8) {
            for (j in 1 until i) {
                for (left in dp[j]) {
                    for (right in dp[i - j]) {
                        for (o in 0 until 4) {
                            var nxt = 0
                            when (o) {
                                0 -> nxt = left + right
                                1 -> nxt = left - right
                                2 -> nxt = left * right
                                3 -> nxt = left / right
                            }
                            if (nxt == number) return i
                            if (nxt == 0) continue
                            if (history.add(nxt)) {
                                dp[i] += nxt
                            }
                        }
                    }
                }
            }
        }
        return -1
    }
}

fun main() {
    val answer = ExpressN().solution(1, 121)
    print(answer)
}