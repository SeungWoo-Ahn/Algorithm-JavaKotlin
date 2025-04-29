package programmers.Practice.Level3

class DonutAndBarGraph {
    private val inDegree = hashMapOf<Int, Int>()
    private val outDegree = hashMapOf<Int, Int>()

    private fun fillDegrees(edges: Array<IntArray>) {
        for ((u, v) in edges) {
            inDegree[v] = inDegree.getOrDefault(v, 0) + 1
            outDegree[u] = outDegree.getOrDefault(u, 0) + 1
        }
    }

    private fun getCreatedNode(): Pair<Int, Int> {
        var node = 0
        var cnt = 0
        for (key in outDegree.keys) {
            if (inDegree[key] == null) {
                outDegree[key]?.let { size ->
                    if (size > cnt) {
                        node = key
                        cnt = size
                    }
                }
            }
        }
        return node to cnt
    }

    fun solution(edges: Array<IntArray>): IntArray {
        fillDegrees(edges)
        val result = IntArray(4)
        val (createdNode, cnt) = getCreatedNode()
        result[0] = createdNode
        for (key in inDegree.keys) {
            if (outDegree[key] == null) {
                result[2]++
                continue
            }
            var total = 0
            inDegree[key]?.let { total += it }
            outDegree[key]?.let { total += it }
            if (total >= 4) {
                result[3]++
            }
        }
        result[1] = cnt - result[2] - result[3]
        return result
    }
}

fun main() {
    val edges = arrayOf(
        intArrayOf(4, 11),
        intArrayOf(1, 12),
        intArrayOf(8, 3),
        intArrayOf(12, 7),
        intArrayOf(4, 2),
        intArrayOf(7, 11),
        intArrayOf(4, 8),
        intArrayOf(9, 6),
        intArrayOf(10, 11),
        intArrayOf(6, 10),
        intArrayOf(3, 5),
        intArrayOf(11, 1),
        intArrayOf(5, 3),
        intArrayOf(11, 9),
        intArrayOf(3, 8)
    )
    val answer = DonutAndBarGraph().solution(edges)
    print(answer.joinToString())
}