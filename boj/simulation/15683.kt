package boj.simulation

import java.util.StringTokenizer

class `15683` {
    data class Node(val num: Int, val x: Int, val y: Int, val wayCnt: Int)

    private var map = Array(0) { intArrayOf() }
    private var visited = Array(0) { booleanArrayOf() }
    private val cctvList = ArrayList<Node>()
    private var arr = intArrayOf()
    private var answer = 0

    private fun combination(depth: Int, len: Int) {
        if (depth == len) {
            check()
            return
        }
        val way = cctvList[depth].wayCnt
        for (i in 1 .. way) {
            arr[depth] = i
            combination(depth + 1, len)
        }
    }

    private fun check() {
        val checkVisited = Array(visited.size) { i -> visited[i].copyOf() }
        for (idx in 0 until cctvList.size) {
            val mode = arr[idx]
            val cur = cctvList[idx]
            when (cur.num) {
                1 -> {
                    when (mode) {
                        1 -> filledRight(cur, checkVisited)
                        2 -> filledUp(cur, checkVisited)
                        3 -> filledLeft(cur, checkVisited)
                        4 -> filledDown(cur, checkVisited)
                    }
                }
                2 -> {
                    when (mode) {
                        1 -> {
                            filledRight(cur, checkVisited)
                            filledLeft(cur, checkVisited)
                        }
                        2 -> {
                            filledUp(cur, checkVisited)
                            filledDown(cur, checkVisited)
                        }
                    }
                }
                3 -> {
                    when (mode) {
                        1 -> {
                            filledRight(cur, checkVisited)
                            filledUp(cur, checkVisited)
                        }
                        2 -> {
                            filledUp(cur, checkVisited)
                            filledLeft(cur, checkVisited)
                        }
                        3 -> {
                            filledLeft(cur, checkVisited)
                            filledDown(cur, checkVisited)
                        }
                        4 -> {
                            filledDown(cur, checkVisited)
                            filledRight(cur, checkVisited)
                        }
                    }
                }
                4 -> {
                    when (mode) {
                        1 -> {
                            filledRight(cur, checkVisited)
                            filledUp(cur, checkVisited)
                            filledLeft(cur, checkVisited)
                        }
                        2 -> {
                            filledUp(cur, checkVisited)
                            filledLeft(cur, checkVisited)
                            filledDown(cur, checkVisited)
                        }
                        3 -> {
                            filledLeft(cur, checkVisited)
                            filledDown(cur, checkVisited)
                            filledRight(cur, checkVisited)
                        }
                        4 -> {
                            filledDown(cur, checkVisited)
                            filledRight(cur, checkVisited)
                            filledUp(cur, checkVisited)
                        }
                    }
                }
                5 -> {
                    filledRight(cur, checkVisited)
                    filledUp(cur, checkVisited)
                    filledLeft(cur, checkVisited)
                    filledDown(cur, checkVisited)
                }
            }
        }
        var cnt = 0
        for (i in checkVisited.indices) {
            for (j in checkVisited[0].indices) {
                if (!checkVisited[i][j]) cnt++
            }
        }
        if (cnt < answer) {
            answer = cnt
        }
    }

    private fun filledRight(node: Node, visited: Array<BooleanArray>) {
        for (i in node.y until visited[0].size) {
            if (map[node.x][i] == 6) break
            visited[node.x][i] = true
        }
    }

    private fun filledLeft(node: Node, visited: Array<BooleanArray>) {
        for (i in node.y downTo  0) {
            if (map[node.x][i] == 6) break
            visited[node.x][i] = true
        }
    }

    private fun filledUp(node: Node, visited: Array<BooleanArray>) {
        for (i in node.x downTo 0) {
            if (map[i][node.y] == 6) break
            visited[i][node.y] = true
        }
    }

    private fun filledDown(node: Node, visited: Array<BooleanArray>) {
        for (i in node.x until visited.size) {
            if (map[i][node.y] == 6) break
            visited[i][node.y] = true
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M) = readLine().split(" ").map { it.toInt() }
        map = Array(N) { IntArray(M) }
        visited = Array(N) { BooleanArray(M) }
        answer = N * M
        for (i in 0 until N) {
            val st = StringTokenizer(readLine())
            for (j in 0 until M) {
                st.nextToken().toInt().let {
                    map[i][j] = it
                    when (it) {
                        1, 3, 4 -> cctvList.add(Node(it, i, j, 4))
                        2 -> cctvList.add(Node(it, i ,j, 2))
                        5 -> cctvList.add(Node(it, i, j, 1))
                        6 -> visited[i][j] = true
                        else -> null
                    }
                }
            }
        }
        arr = IntArray(cctvList.size)
        combination(0, cctvList.size)
        println(answer)
    }
}

fun main() {
    `15683`().solution()
}