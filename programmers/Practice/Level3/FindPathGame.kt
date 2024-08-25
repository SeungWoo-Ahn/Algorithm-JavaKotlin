package programmers.Practice.Level3

class FindPathGame {
    private data class Node(val num: Int, val level: Int)

    private var lc = intArrayOf()
    private var rc = intArrayOf()
    private val pre = mutableListOf<Int>()
    private val post = mutableListOf<Int>()

    private fun makeTree(nodeInfo: Array<IntArray>): Int {
        val n = nodeInfo.size
        val nodes = Array(n) { idx -> Node(idx + 1, nodeInfo[idx][1]) }
        nodes.sortBy { -it.level }
        lc = IntArray(n + 1) { -1 }
        rc = IntArray(n + 1) { -1 }
        val root = nodes[0].num
        for (i in 1 until n) {
            findTreePos(nodes[i].num, root, nodeInfo)
        }
        return root
    }

    private fun findTreePos(num: Int, parent: Int, nodeInfo: Array<IntArray>) {
        if (nodeInfo[num - 1][0] < nodeInfo[parent - 1][0]) {
            if (lc[parent] == -1) {
                lc[parent] = num
                return
            }
            findTreePos(num, lc[parent], nodeInfo)
        } else {
            if (rc[parent] == -1) {
                rc[parent] = num
                return
            }
            findTreePos(num, rc[parent], nodeInfo)
        }
    }

    private fun preOrder(cur: Int) {
        pre += cur
        if (lc[cur] != -1) preOrder(lc[cur])
        if (rc[cur] != -1) preOrder(rc[cur])
    }

    private fun postOrder(cur: Int) {
        if (lc[cur] != -1) postOrder(lc[cur])
        if (rc[cur] != -1) postOrder(rc[cur])
        post += cur
    }

    fun solution(nodeInfo: Array<IntArray>): Array<IntArray> {
        val root = makeTree(nodeInfo)
        preOrder(root)
        postOrder(root)
        return arrayOf(pre.toIntArray(), post.toIntArray())
    }
}

fun main() {
    val nodeInfo = arrayOf(
        intArrayOf(5, 3),
        intArrayOf(11, 5),
        intArrayOf(13, 3),
        intArrayOf(3, 5),
        intArrayOf(6, 1),
        intArrayOf(1, 3),
        intArrayOf(8, 6),
        intArrayOf(7, 2),
        intArrayOf(2, 2)
    )
    val answer = FindPathGame().solution(nodeInfo)
    for (i in answer.indices) {
        println(answer[i].joinToString(" "))
    }
}