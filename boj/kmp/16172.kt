package boj.kmp

class `16172` {
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
            if (this[i] in '0' .. '9') continue
            while (j > 0 && this[i] != s[j]) j = f[j - 1]
            if (this[i] == s[j]) j++
            if (j == s.length) return 1
        }
        return 0
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val k = readLine()
        val f = failure(k)
        print(s.contains(k, f))
    }
}

fun main() {
    `16172`().solution()
}