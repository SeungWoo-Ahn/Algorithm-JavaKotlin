package boj.Implement

import java.util.StringTokenizer

class `20205` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        val extendedPixel = Array(n * k) { BooleanArray(n * k) }
        for (i in 0 until n) {
            st = StringTokenizer(readLine())
            for (j in 0 until n) {
                val bit = st.nextToken().toInt() == 1
                if (!bit) continue
                for (x in i * k until (i + 1) * k) {
                    for (y in j * k until (j + 1) * k) {
                        extendedPixel[x][y] = true
                    }
                }
            }
        }
        val sb = StringBuilder()
        for (i in extendedPixel.indices) {
            for (j in extendedPixel.indices) {
                if (extendedPixel[i][j]) sb.append("1 ")
                else sb.append("0 ")
            }
            sb.append("\n")
        }
        print(sb)
    }
}

fun main() {
    `20205`().solution()
}