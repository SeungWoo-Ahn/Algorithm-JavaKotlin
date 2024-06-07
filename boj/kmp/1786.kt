package boj.kmp

class `1786` {
    private fun failure(s: String): IntArray {
        val f = IntArray(s.length)
        var j = 0
        for (i in 1 until s.length) {
            while (j > 0 && s[i] != s[j]) j = f[j - 1]
            if (s[i] == s[j]) f[i] = ++j
        }
        return f
    }

    private fun String.search(pattern: String, failure: IntArray): MutableList<Int> {
        val searched = mutableListOf<Int>()
        var j = 0
        for (i in indices) {
            while (j > 0 && this[i] != pattern[j]) j = failure[j - 1]
            if (this[i] == pattern[j]) j++
            if (j == pattern.length) {
                searched += (i + 1 - j) + 1
                j = failure[j - 1]
            }
        }
        return searched
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val text = readLine()
        val pattern = readLine()
        val failure = failure(pattern)
        val searched = text.search(pattern, failure)
        println("${searched.size}\n${searched.joinToString(" ")}")
    }
}

fun main() {
    `1786`().solution()
}