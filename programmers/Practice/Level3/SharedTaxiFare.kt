package programmers.Practice.Level3

class SharedTaxiFare {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val map = Array(n + 1) { x ->
            IntArray(n + 1) { y ->
                if (x == y) 0
                else INF
            }
        }
        for ((c, d, f) in fares) {
            map[c][d] = f
            map[d][c] = f
        }
        for (k in 1..n) {
            for (x in 1..n) {
                if (map[x][k] == INF) continue
                for (y in x + 1..n) {
                    if (map[y][k] == INF) continue
                    val viaK = map[x][k] + map[k][y]
                    if (viaK < map[x][y]) {
                        map[x][y] = viaK
                        map[y][x] = viaK
                    }
                }
            }
        }
        var answer = INF
        for (i in 1..n) {
            answer = minOf(answer, map[s][i] + map[i][a] + map[i][b])
        }
        return answer
    }

    companion object {
        private const val INF = 20_000_001
    }
}

fun main() {
    val n = 6
    val s = 4
    val a = 6
    val b = 2
    val fares = arrayOf(
        intArrayOf(4, 1, 10),
        intArrayOf(3, 5, 24),
        intArrayOf(5, 6, 2),
        intArrayOf(3, 1, 41),
        intArrayOf(5, 1, 24),
        intArrayOf(4, 6, 50),
        intArrayOf(2, 4, 66),
        intArrayOf(2, 3, 22),
        intArrayOf(1, 6, 25)
    )
    val answer = SharedTaxiFare().solution(n, s, a, b, fares)
    print(answer)
}