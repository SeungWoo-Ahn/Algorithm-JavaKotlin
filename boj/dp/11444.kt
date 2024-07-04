package boj.dp

class `11444` {
    private val origin = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))

    private fun multiply(o1: Array<LongArray>, o2: Array<LongArray>): Array<LongArray> {
        val ret = Array(2) { LongArray(2) }
        ret[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % MOD
        ret[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % MOD
        ret[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % MOD
        ret[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % MOD
        return ret
    }

    private fun pow(a: Array<LongArray>, exp: Long): Array<LongArray> {
        if (exp <= 1) return a
        var ret = pow(a, exp / 2)
        ret = multiply(ret, ret)
        if (exp % 2 == 1L) {
            ret = multiply(ret, origin)
        }
        return ret
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toLong()
        val a = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))
        print(pow(a, n - 1)[0][0])
    }

    companion object {
        private const val MOD = 1_000_000_007
    }
}

fun main() {
    `11444`().solution()
}