package boj.trie

class `14425` {
    private var unused = 2
    private var chk = BooleanArray(MX)
    private var nxt = Array(MX) { IntArray(26) { -1 } }

    private fun charToInt(c: Char): Int {
        return c - 'a'
    }

    private fun insert(s: String) {
        var cur = ROOT
        for (c in s) {
            if (nxt[cur][charToInt(c)] == -1)
                nxt[cur][charToInt(c)] = unused++
            cur = nxt[cur][charToInt(c)]
        }
        chk[cur] = true
    }

    private fun find(s: String): Boolean {
        var cur = ROOT
        for (c in s) {
            if (nxt[cur][charToInt(c)] == -1)
                return false
            cur = nxt[cur][charToInt(c)]
        }
        return chk[cur]
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
            if (find(s)) answer++
        }
        println(answer)
    }

    companion object {
        private const val ROOT = 1
        private const val MX = 10_000 * 500 + 5
    }
}

fun main() {
    `14425`().solution()
}