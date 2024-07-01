package boj.kmp

class `5525` {
    private fun makeP(n: Int): String {
        val sb = StringBuilder()
        sb.append("IOI")
        repeat(n - 1) { sb.append("OI") }
        return sb.toString()
    }

    private fun failure(p: String): IntArray {
        val f = IntArray(p.length)
        var j = 0
        for (i in 1 until p.length) {
            while (j > 0 && p[i] != p[j]) j = f[j - 1]
            if (p[i] == p[j]) f[i] = ++j
        }
        return f
    }

    private fun String.search(p: String, failure: IntArray): Int {
        var cnt = 0
        var j = 0
        for (i in indices) {
            while (j > 0 && this[i] != p[j]) j = failure[j - 1]
            if (this[i] == p[j]) j++
            if (j == p.length) {
                cnt++
                j = failure[j - 1]
            }
        }
        return cnt
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()
        val s = readLine()
        val p = makeP(n)
        val failure = failure(p)
        val cnt = s.search(p, failure)
        print(cnt)
    }
}

fun main() {
    `5525`().solution()
}