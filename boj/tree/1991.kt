package boj.tree

class `1991` {
    private val lc = IntArray(30)
    private val rc = IntArray(30)
    private val sb = StringBuilder()

    private fun preOrder(cur: Int) {
        sb.append(cur.indexToChar())
        if (lc[cur] != 0) preOrder(lc[cur])
        if (rc[cur] != 0) preOrder(rc[cur])
    }

    private fun inOrder(cur: Int) {
        if (lc[cur] != 0) inOrder(lc[cur])
        sb.append(cur.indexToChar())
        if (rc[cur] != 0) inOrder(rc[cur])
    }

    private fun postOrder(cur: Int) {
        if (lc[cur] != 0) postOrder(lc[cur])
        if (rc[cur] != 0) postOrder(rc[cur])
        sb.append(cur.indexToChar())
    }

    private fun Int.indexToChar() = (this + 'A'.code - 1).toChar()

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        repeat(n) {
            val (c, l, r) = readLine().split(" ").map { it.single() }
            if (l != '.') lc[c - 'A' + 1] = l - 'A' + 1
            if (r != '.') rc[c - 'A' + 1] = r - 'A' + 1
        }
        preOrder(1)
        sb.append("\n")
        inOrder(1)
        sb.append("\n")
        postOrder(1)
        sb.append("\n")
        println(sb)
    }
}

fun main() {
    `1991`().solution()
}