package boj.math

import java.util.StringTokenizer

class `10830` {
    private var origin: Array<IntArray> = arrayOf()

    private fun multiply(
        n: Int,
        o1: Array<IntArray>,
        o2: Array<IntArray>
    ): Array<IntArray> {
        val ret = Array(n) { IntArray(n) }
        for (i in o1.indices) {
            for (j in o1[i].indices) {
                var sum = 0
                for (k in o1.indices) {
                    sum += o1[i][k] * o2[k][j]
                }
                ret[i][j] = sum % MOD
            }
        }
        return ret
    }

    private fun pow(n: Int, a: Array<IntArray>, exp: Long): Array<IntArray> {
        if (exp <= 1) return a
        var ret = pow(n, a, exp / 2)
        ret = multiply(n, ret, ret)
        if (exp % 2 == 1L) {
            ret = multiply(n, ret, origin)
        }
        return ret
    }

    private fun printResult(result: Array<IntArray>) {
        val sb = StringBuilder()
        for (i in result.indices) {
            for (j in result.indices) {
                sb.append("${result[i][j]} ")
            }
            sb.appendLine()
        }
        print(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val b = st.nextToken().toLong()
        origin = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() % MOD }
        }
        val result = pow(n, origin, b)
        printResult(result)
    }

    companion object {
        private const val MOD = 1_000
    }
}

fun main() {
    `10830`().solution()
}