package programmers.Practice.Level2

class PerfectCrime {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val cache = hashSetOf<String>()
        var result = MAX

        fun dfs(depth: Int, a: Int, b: Int) {
            if (depth == info.size) {
                if (a < result) {
                    result = a
                }
                return
            }

            val key = "${depth}-${a}-${b}"
            if (cache.contains(key)) {
                return
            }
            cache += key

            val na = a + info[depth][0]
            val nb = b + info[depth][1]
            if (na < n && na < result) {
                dfs(depth + 1, na, b)
            }
            if (nb < m) {
                dfs(depth + 1, a, nb)
            }
        }
        dfs(0, 0, 0)
        return if (result == MAX) -1
        else result
    }

    companion object {
        private const val MAX = 121
    }
}

fun main() {
    val info = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(2, 1),
    )
    val n = 4
    val m = 4
    val answer = PerfectCrime().solution(info, n, m)
    print(answer)
}