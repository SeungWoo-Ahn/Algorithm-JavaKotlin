package boj.math

class `4948` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val arr = arrayListOf<Int>()
        var max = 0
        while (true) {
            val n = readLine().toInt()
            if (n == 0) break
            if (n * 2 > max) max = 2 * n
            arr.add(n)
        }
        val table = BooleanArray(max + 1) { true }
        for (i in 2 .. max) {
            if (i * i > max) break
            if (!table[i]) continue
            for (j in i * i .. max step i) {
                table[j] = false
            }
        }
        val sb = StringBuilder()
        for (n in arr) {
            var cnt = 0
            for (i in n + 1 .. 2 * n) {
                if (table[i]) cnt++
            }
            sb.append("$cnt\n")
        }
        println(sb)
    }
}

fun main() {
    `4948`().solution()
}