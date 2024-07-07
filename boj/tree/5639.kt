package boj.tree

class `5639` {
    private data class Node(
        val num: Int,
        var lc: Node? = null,
        var rc: Node? = null
    ) {
        fun insert(num: Int) {
            if (num < this.num) {
                if (lc == null) lc = Node(num)
                else lc!!.insert(num)
            } else {
                if (rc == null) rc = Node(num)
                else rc!!.insert(num)
            }
        }
    }

    private val sb = StringBuilder()

    private fun postOrder(cur: Node) {
        if (cur.lc != null) postOrder(cur.lc!!)
        if (cur.rc != null) postOrder(cur.rc!!)
        sb.appendLine(cur.num)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val root = Node(readLine().toInt())
        while (true) {
            val num = readLine() ?: break
            root.insert(num.toInt())
        }
        postOrder(root)
        print(sb)
    }
}

fun main() {
    `5639`().solution()
}