package hackerrank.stack

import java.util.StringTokenizer

class EqualStacks {
    private fun equalStacks(h1: IntArray, h2: IntArray, h3: IntArray): Int {
        val stacks = arrayOf(h1, h2, h3)
        val idxes = intArrayOf(0, 0, 0)
        val heights = intArrayOf(h1.sum(), h2.sum(), h3.sum())
        while (true) {
            val minHeight = heights.min()
            val maxHeight = heights.max()
            val i = heights.indexOf(maxHeight)
            while (idxes[i] < stacks[i].size && heights[i] > minHeight) {
                heights[i] -= stacks[i][idxes[i]++]
            }
            if (heights.all { it == minHeight }) {
                return minHeight
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n1, n2, n3) = readLine().split(" ").map { it.toInt() }
        var st = StringTokenizer(readLine())
        val h1 = IntArray(n1) { st.nextToken().toInt() }
        st = StringTokenizer(readLine())
        val h2 = IntArray(n2) { st.nextToken().toInt() }
        st = StringTokenizer(readLine())
        val h3 = IntArray(n3) { st.nextToken().toInt() }
        val result = equalStacks(h1, h2, h3)
        print(result)
    }
}

fun main() {
    EqualStacks().solution()
}