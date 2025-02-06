package boj.union_find

import java.util.StringTokenizer

class `1976` {
    private lateinit var parent: IntArray

    private fun find(x: Int): Int {
        if (parent[x] == x) {
            return x
        }
        parent[x] = find(parent[x])
        return parent[x]
    }

    private fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX < rootY) parent[rootY] = rootX
        else parent[rootX] = rootY
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        // input
        val n = readLine().toInt()
        val m = readLine().toInt()
        var st: StringTokenizer
        val path = Array(n) {
            st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        st = StringTokenizer(readLine())
        val plan = IntArray(m) { st.nextToken().toInt() - 1 }

        // init
        parent = IntArray(n) { it }

        for (x in 0 until n - 1) {
            for (y in x + 1 until n) {
                if (path[x][y] == 1) {
                    union(x, y)
                }
            }
        }
        var possible = true
        val startRoot = find(plan.first())
        for (i in 1 until m) {
            if (find(plan[i]) != startRoot) {
                possible = false
                break
            }
        }
        val result = if (possible) "YES" else "NO"
        print(result)
    }
}

fun main() {
    `1976`().solution()
}