package boj.kmp

class `16916` {
    private fun failure(s: String): IntArray {
        val f = IntArray(s.length)
        var j = 0
        for (i in 1 until s.length) {
            while (j > 0 && s[i] != s[j]) j = f[j - 1]
            if (s[i] == s[j]) f[i] = ++j
        }
        return f
    }

    private fun String.contains(s: String, f: IntArray): Int {
        var j = 0
        for (i in indices) {
            while (j > 0 && this[i] != s[j]) j = f[j - 1]
            if (this[i] == s[j]) j++
            if (j == s.length) return 1
        }
        return 0
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val p = readLine()
        val f = failure(p)
        print(s.contains(p, f))
    }
}

fun main() {
    `16916`().solution()
}