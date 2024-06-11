package boj.trie

import java.util.StringTokenizer

class `14725` {
    private var unused = 2
    private val nxt = Array(MX) { HashMap<String, Int>() }
    private val sb = StringBuilder()

    private fun insert(infos: Array<String>) {
        var cur = ROOT
        for (info in infos) {
            var next = nxt[cur][info]
            if (next == null) {
                nxt[cur][info] = unused
                next = unused
                unused++
            }
            cur = next
        }
    }

    private fun dfs(pair: Pair<String, Int>, depth: Int = -1) {
        if (pair.second != ROOT) {
            repeat(depth) { sb.append("--") }
            sb.append("${pair.first}\n")
        }
        val entryList = nxt[pair.second].toList().sortedBy { it.first }
        entryList.forEach { dfs(it, depth + 1) }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var st: StringTokenizer
        repeat(n) {
            st = StringTokenizer(readLine())
            val k = st.nextToken().toInt()
            val infos = Array(k) { st.nextToken() }
            insert(infos)
        }
        dfs("" to ROOT)
        print(sb)
    }

    companion object {
        private const val ROOT = 1
        private const val MX = 1_000 * 15 + 2
    }
}

fun main() {
    `14725`().solution()
}