package boj.greedy

class `20365` {
    private fun Char.toIdx(): Int {
        return when (this) {
            'R' -> 0
            'B' -> 1
            else -> throw IllegalArgumentException()
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val s = readLine()
        val cntByGroup = IntArray(2)
        var cur = s.first()
        cntByGroup[cur.toIdx()]++
        for (i in 1 until n) {
            if (s[i] != cur) {
                cur = s[i]
                cntByGroup[cur.toIdx()]++
            }
        }
        print(cntByGroup.min() + 1)
    }
}

fun main() {
    `20365`().solution()
}