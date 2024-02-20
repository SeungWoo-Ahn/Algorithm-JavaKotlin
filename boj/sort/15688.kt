package boj.sort

class `15688` {

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val range = 1_000_000
        val countArr = IntArray(range * 2 + 1)
        var max = 0
        var min = range * 2
        repeat(n) {
            (readLine().toInt() + range).let {
                if (it > max) max = it
                if (it < min) min = it
                countArr[it]++
            }
        }
        val sb = StringBuilder()
        for (i in min .. max) {
            repeat(countArr[i]) { sb.append("${i - range}\n") }
        }
        println(sb)
    }
}

fun main() {
    `15688`().solution()
}