package boj.dp

class `9095` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        val d = IntArray(11).apply {
            this[1] = 1
            this[2] = 2
            this[3] = 4
        }
        var max = 1
        val input = IntArray(t) {
            val n = readLine().toInt()
            if (n > max) max = n
            n
        }
        for (i in 4 .. max) {
            d[i] = d[i - 1] + d[i - 2] + d[i -3]
        }
        input.forEach { sb.append("${d[it]}\n") }
        println(sb)
    }
}

fun main() {
    `9095`().solution()
}