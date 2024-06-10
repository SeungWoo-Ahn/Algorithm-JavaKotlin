package boj.trie

class `7432` {
    private var unused = 2
    private val nxt = Array(MX) { HashMap<String, Int>() }
    private val depth = IntArray(MX)
    private val name = Array(MX) { "" }
    private val sb = StringBuilder()

    private fun insert(routes: List<String>) {
        var cur = ROOT
        for (route in routes) {
            var next = nxt[cur][route]
            if (next == null) {
                next = unused
                nxt[cur][route] = unused
                unused++
            }
            depth[next] = depth[cur] + 1
            name[next] = route
            cur = next
        }
    }

    private fun dfs(cur: Int) {
        if (cur != ROOT) {
            repeat(depth[cur]) { sb.append(" ") }
            sb.append("${name[cur]}\n")
        }
        val entryList = nxt[cur].toList().sortedBy { it.first }
        entryList.forEach { (_, next) -> dfs(next) }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        depth[ROOT] = -1
        repeat(n) {
            val routes = readLine().split("\\")
            insert(routes)
        }
        dfs(ROOT)
        print(sb)
    }

    companion object {
        private const val ROOT = 1
        private const val MX = 500 * 40 + 5
    }
}

fun main() {
    `7432`().solution()
}