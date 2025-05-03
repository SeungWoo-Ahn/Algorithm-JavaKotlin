package programmers.Practice.Level3

class MoveBallSimulation {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var sx = x
        var ex = x
        var sy = y
        var ey = y
        for (i in queries.lastIndex downTo 0) {
            val (command, d) = queries[i]
            when (command) {
                0 -> {
                    ey = minOf(ey + d, m - 1)
                    if (sy > 0) {
                        if (sy + d >= m) return 0
                        sy += d
                    }
                }
                1 -> {
                    sy = maxOf(sy - d, 0)
                    if (ey < m - 1) {
                        if (ey - d < 0) return 0
                        ey -= d
                    }
                }
                2 -> {
                    ex = minOf(ex + d, n - 1)
                    if (sx > 0) {
                        if (sx + d >= n) return 0
                        sx += d
                    }
                }
                3 -> {
                    sx = maxOf(sx - d, 0)
                    if (ex < n - 1) {
                        if (ex - d < 0) return 0
                        ex -= d
                    }
                }
            }
        }
        return (ex - sx + 1).toLong() * (ey - sy + 1).toLong()
    }
}

fun main() {
    val n = 2
    val m = 5
    val x = 0
    val y = 1
    val queries = arrayOf(
        intArrayOf(3, 1),
        intArrayOf(2, 2),
        intArrayOf(1, 1),
        intArrayOf(2, 3),
        intArrayOf(0, 1),
        intArrayOf(2, 1)
    )
    val answer = MoveBallSimulation().solution(n, m, x, y, queries)
    print(answer)
}