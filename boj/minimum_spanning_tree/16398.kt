package boj.minimum_spanning_tree

import java.util.PriorityQueue
import java.util.StringTokenizer

class `16398_Prim` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val adj = Array(n + 1) { mutableListOf<Pair<Int, Int>>() } // 각 노드의 인접리스트, (거리, 노드 번호)
        val check = BooleanArray(n + 1) // 각 노드의 방문 여부
        var st: StringTokenizer
        for (i in 1 .. n) {
            st = StringTokenizer(readLine())
            for (j in 1 .. n) {
                val cost = st.nextToken().toInt()
                if (i >= j) continue
                adj[i].add(cost to j)
                adj[j].add(cost to i)
            }
        }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        adj[1].forEach { pq.add(it) } // 임의의 노드 중 1번 노드를 선택
        check[1] = true

        var cnt = 0 // MST 에 포함된 노드 개수
        var answer = 0L // 최소 비용
        while (cnt < n - 1) {
            val (cost, node) = pq.poll()
            if (check[node]) continue
            check[node] = true
            answer += cost
            cnt++
            for (nxt in adj[node]) {
                if (!check[nxt.second])
                    pq.add(nxt)
            }
        }
        println(answer)
    }
}

class `16398_Kruscal` {
    private var parent = intArrayOf() // 부모 노드

    private fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    private fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX < rootY) parent[rootY] = rootX
        else parent[rootX] = rootY
    }

    private fun isSameGroup(x: Int, y: Int): Boolean {
        return find(x) == find(y)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val edge = mutableListOf<Triple<Int, Int, Int>>() // 간선 리스트, (거리, u 노드, v 노드)
        var st: StringTokenizer
        for (i in 0 until n) {
            st = StringTokenizer(readLine())
            for (j in 0 until n) {
                val cost = st.nextToken().toInt()
                if (i >= j) continue
                edge.add(Triple(cost, i, j))
            }
        }
        edge.sortBy { it.first }
        parent = IntArray(n) { it }

        var cnt = 0 // MST 에 포함된 노드 개수
        var answer = 0L // 최소 비용
        for ((cost, u, v) in edge) {
            if (isSameGroup(u, v)) continue
            union(u, v)
            answer += cost
            if (++cnt == n - 1) break
        }
        println(answer)
    }
}

fun main() {
    `16398_Prim`().solution() // 프림 알고리즘 풀이, 980ms
    `16398_Kruscal`().solution() // 크루스칼 알고리즘 풀이, 1204ms
}