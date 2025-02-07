package boj.union_find

class `17619` {
    data class Log(
        val num: Int,
        val x1: Int,
        val x2: Int,
    ) : Comparable<Log> {
        private val length = x2 - x1 + 1

        override fun compareTo(other: Log): Int {
            return if (x1 != other.x1) x1 - other.x1
            else if (length != other.length) other.length - length
            else num - other.num
        }
    }

    private lateinit var parent: IntArray

    private fun find(x: Int): Int {
        if (parent[x] == x) {
            return x
        }
        return find(parent[x]).also { parent[x] = it }
    }

    private fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY) return
        if (rootX < rootY) parent[rootY] = rootX
        else parent[rootX] = rootY
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, q) = readLine().splitToInt()
        val logList = mutableListOf<Log>()
        repeat(n) {
            val (x1, x2, _) = readLine().splitToInt()
            logList += Log(it + 1, x1, x2)
        }
        parent = IntArray(n + 1) { it }
        logList.sort()
        var (num, _, x2) = logList.first()
        for (i in 1 until logList.size) {
            val log = logList[i]
            if (log.x1 <= x2) {
                union(num, log.num)
                if (log.x2 > x2) {
                    x2 = log.x2
                }
            } else {
                log.let {
                    num = it.num
                    x2 = it.x2
                }
            }
        }
        val sb = StringBuilder()
        repeat(q) {
            val (n1, n2) = readLine().splitToInt()
            val reply = if (find(n1) == find(n2)) 1 else 0
            sb.appendLine(reply)
        }
        print(sb)
    }

    private fun String.splitToInt() = split(" ").map { it.toInt() }
}

fun main() {
    `17619`().solution()
}