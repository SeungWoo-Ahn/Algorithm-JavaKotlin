package boj.tree

import java.util.StringTokenizer

class `4256` {
    private var root = 0
    private lateinit var preOrder: IntArray
    private lateinit var inOrder: IntArray
    private lateinit var left: IntArray
    private lateinit var right: IntArray
    private lateinit var seq: IntArray
    private lateinit var result: MutableList<Int>

    private fun fillSeq() {
        for (i in inOrder.indices) {
            seq[inOrder[i]] = i + 1
        }
    }

    private fun fillTree(idx: Int, st: Int, en: Int) {
        val node = preOrder[idx]
        val curSeq = seq[node]
        val leftCnt = curSeq - st
        val rightCnt = en - curSeq
        if (leftCnt > 0) {
            left[node] = preOrder[idx + 1]
            fillTree(idx + 1, st, curSeq - 1)
        }
        if (rightCnt > 0) {
            right[node] = preOrder[idx + leftCnt + 1]
            fillTree(idx + leftCnt + 1, curSeq + 1, en)
        }
    }

    private fun postOrder(node: Int) {
        if (left[node] > 0) postOrder(left[node])
        if (right[node] > 0) postOrder(right[node])
        result += node
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val n = readLine().toInt()
            var st = StringTokenizer(readLine(), " ")
            preOrder = IntArray(n) { st.nextToken().toInt() }
            st = StringTokenizer(readLine(), " ")
            inOrder = IntArray(n) { st.nextToken().toInt() }
            root = preOrder[0]
            left = IntArray(n + 1)
            right = IntArray(n + 1)
            seq = IntArray(n + 1)
            result = mutableListOf()
            fillSeq()
            fillTree(0, 1, n)
            postOrder(root)
            sb.appendLine(result.joinToString(" "))
        }
        print(sb)
    }
}

fun main() {
    `4256`().solution()
}