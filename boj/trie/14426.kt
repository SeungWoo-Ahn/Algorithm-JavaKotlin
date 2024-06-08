package boj.trie

class `14426` {
    private var unused = 2
    private val nxt = Array(MX) { IntArray(26) { -1 } }

    private fun Char.toIdx() = this - 'a'

    private fun insert(s: String) {
        var cur = ROOT
        for (c in s) {
            if (nxt[cur][c.toIdx()] == - 1)
                nxt[cur][c.toIdx()] = unused++
            cur = nxt[cur][c.toIdx()]
        }
    }

    private fun isPrefix(s: String): Boolean {
        var cur = ROOT
        for (c in s) {
            if (nxt[cur][c.toIdx()] == -1)
                return false
            cur = nxt[cur][c.toIdx()]
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        repeat(n) {
            val s = readLine()
            insert(s)
        }
        var answer = 0
        repeat(m) {
            val s = readLine()
            if (isPrefix(s)) answer++
        }
        println(answer)
    }

    companion object {
        private const val ROOT = 1
        private const val MX = 10_000 * 500 + 5
    }
}

fun main() {
    `14426`().solution()
}