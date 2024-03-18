package boj.hash

class `20166` {
    data class Node(val x: Int, val y: Int)

    private var map: Array<CharArray> = arrayOf()
    private val d = listOf(
        Node(1, 0),
        Node(-1, 0),
        Node(0, 1),
        Node(0, -1),
        Node(1, 1),
        Node(1, -1),
        Node(-1, 1),
        Node(-1, -1),
    )
    private val hashMap = HashMap<String, Int>()

    private fun dfs(target: String, node: Node, depth: Int = 0) {
        if (map[node.x][node.y] != target[depth]) {
            return
        }
        if (depth == target.length - 1) {
            hashMap[target] = hashMap.getOrDefault(target, 0) + 1
            return
        }
        for (i in d.indices) {
            var nx = node.x + d[i].x
            var ny = node.y + d[i].y
            if (nx < 1) nx = map.size - 1
            else if (nx == map.size) nx = 1
            if (ny < 1) ny = map[0].size - 1
            else if (ny == map[0].size) ny = 1
            dfs(target, Node(nx, ny), depth + 1)
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readLine().split(" ").map { it.toInt() }
        map = Array(n + 1) { CharArray(m + 1) }
        for (i in 1 .. n) {
            val line = readLine()
            for (j in 1 .. m) {
                map[i][j] = line[j - 1]
            }
        }
        val sb = StringBuilder()
        repeat(k) {
            val target = readLine()
            if (hashMap[target] != null) {
                sb.append("${hashMap[target]}\n")
            } else {
                for (i in 1 .. n) {
                    for (j in 1 .. m) {
                        dfs(target, Node(i, j))
                    }
                }
                sb.append("${hashMap.getOrDefault(target, 0)}\n")
            }
        }
        println(sb)
    }
}

fun main() {
    `20166`().solution()
}