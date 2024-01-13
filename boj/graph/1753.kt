package boj.graph

import java.lang.StringBuilder
import java.util.PriorityQueue

const val INF = Int.MAX_VALUE

data class Node(val index: Int, val dist: Int): Comparable<Node> {
    override fun compareTo(other: Node): Int = dist - other.dist
}

class `1753` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (V, E) = readLine().split(" ").map { it.toInt() }
        val k = readLine().toInt()
        val dis = Array(V + 1) { INF }
        val sb = StringBuilder()

        val adj = ArrayList<ArrayList<Node>>()
        for (i in  0 .. V) {
            adj.add(ArrayList())
        }

        for (i in 0 until E) {
            val (u, v, w) = readLine().split(" ").map { it.toInt() }
            adj[u].add(Node(v, w))
        }

        dijkstra(k, V, dis, adj)

        for (i in 1 .. V) {
            if (dis[i] == INF) sb.append("INF\n")
            else sb.append("${dis[i]}\n")
        }
        println(sb)
    }

    private fun dijkstra(start: Int, V: Int, dis: Array<Int>, adj: ArrayList<ArrayList<Node>>) {
        val q = PriorityQueue<Node>()
        val check = BooleanArray(V + 1)
        dis[start] = 0
        q.add(Node(start, 0))

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if (check[cur.index]) continue
            check[cur.index] = true
            adj[cur.index].forEach {
                if (!check[it.index] && dis[it.index] > (dis[cur.index] + it.dist)) {
                    dis[it.index] = dis[cur.index] + it.dist
                    q.add(Node(it.index, dis[it.index]))
                }
            }
        }
    }
}

fun main() {
    `1753`().solution()
}

