package programmers.Practice.Level2

class CantorBitString {
    private lateinit var size: LongArray

    private fun makeSize(n: Int) {
        size = LongArray(n + 1)
        size[0] = 1L
        for (i in 1..n) {
            size[i] = size[i - 1] * 5
        }
    }

    private fun dfs(n: Int, depth: Int, l: Long, r: Long, k: Long): Int {
        val diff = n - depth
        val en = size[diff] * k
        val st = en - size[diff] + 1
        if (st > r || en < l) {
            return 0
        }
        if (l <= st && en <= r) {
            var cnt = 1
            repeat(diff) {
                cnt = cnt shl 2
            }
            return cnt
        }
        var sum = 0
        val nxtK = k * 5
        sum += dfs(n, depth + 1, l, r, nxtK - 4)
        sum += dfs(n, depth + 1, l, r, nxtK - 3)
        sum += dfs(n, depth + 1, l, r, nxtK - 1)
        sum += dfs(n, depth + 1, l, r, nxtK)
        return sum
    }

    fun solution(n: Int, l: Long, r: Long): Int {
        makeSize(n)
        return dfs(n, 0, l, r, 1L)
    }
}

fun main() {
    val n = 2
    val l = 4L
    val r = 17L
    val answer = CantorBitString().solution(n, l, r)
    print(answer)
}