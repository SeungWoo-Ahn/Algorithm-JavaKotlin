package programmers.Practice.Level3

class SheepWolf {
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var maxCnt = 0

    private fun makeTree(n: Int, edges: Array<IntArray>) {
        adj = Array(n) { mutableListOf() }
        for ((parent, child) in edges) {
            adj[parent] += child
        }
    }

    private fun dfs(cur: Int, cnt: IntArray, info: IntArray, path: MutableList<Int>) {
        if (cnt[0] > maxCnt) {
            maxCnt = cnt[0]
        }
        if (cnt[0] == cnt[1]) {
            return
        }
        val nextPath = mutableListOf<Int>()
        for (p in path) {
            if (p == cur) continue
            nextPath += p
        }
        for (nxt in adj[cur]) {
            nextPath += nxt
        }
        for (nxt in nextPath) {
            cnt[info[nxt]]++
            dfs(nxt, cnt, info, nextPath)
            cnt[info[nxt]]--
        }
    }

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        makeTree(info.size, edges)
        val cnt = IntArray(2)
        val path = mutableListOf<Int>()
        cnt[0] = 1
        dfs(0, cnt, info, path)
        return maxCnt
    }
}

fun main() {
    val info = intArrayOf(0,0,1,1,1,0,1,0,1,0,1,1)
    val edges = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(1, 4),
        intArrayOf(0, 8),
        intArrayOf(8, 7),
        intArrayOf(9, 10),
        intArrayOf(9, 11),
        intArrayOf(4, 3),
        intArrayOf(6, 5),
        intArrayOf(4, 6),
        intArrayOf(8, 9)
    )
    val answer = SheepWolf().solution(info, edges)
    print(answer)
}