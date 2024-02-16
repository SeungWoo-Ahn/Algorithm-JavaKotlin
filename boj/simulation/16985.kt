package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `16985` {
    data class Node(val x: Int, val y: Int, val z: Int, val dis: Int = 0)

    private var cube: Array<Array<IntArray>> = arrayOf()
    private val d = listOf(
        Node(1, 0, 0),
        Node(-1, 0, 0),
        Node(0, 1, 0),
        Node(0, -1, 0),
        Node(0, 0, 1),
        Node(0, 0, -1),
    )
    private val maxDis = 5 * 5 * 5
    private var minDis = maxDis
    private val orderArr = IntArray(5)
    private val orderVisited = BooleanArray(5)
    private val rotateArr = IntArray(5)

    private fun setCubeOrder(depth: Int) {
        if (depth == 5) {
            setCubeRotate(0)
            return
        }
        for (i in 0 until 5) {
            if (!orderVisited[i]) {
                orderVisited[i] = true
                orderArr[depth] = i
                setCubeOrder(depth + 1)
                orderVisited[i] = false
            }
        }
    }

    private fun setCubeRotate(depth: Int) {
        if (depth == 5) {
            makeCube()
            return
        }
        for (i in 0 .. 3) {
            rotateArr[depth] = i
            setCubeRotate(depth + 1)
        }
    }

    private fun makeCube() {
        val madeCube = Array(5) { Array(5) { IntArray(5) } }
        for (i in 0 until 5) {
            var rotatedMap = cube[orderArr[i]]
            repeat(rotateArr[i]) { rotatedMap = rotate(rotatedMap) }
            madeCube[i] = rotatedMap
        }
        val startNodes = listOf(Node(0, 0, 0), Node(0, 0, 4), Node(0, 4, 0), Node(0, 4, 4))
        for (s in startNodes) {
            if (madeCube[s.x][s.y][s.z] == 1) {
                bfs(s, madeCube)
            }
        }
    }

    private fun rotate(map: Array<IntArray>): Array<IntArray> {
        val rotatedMap = Array(5) { IntArray(5) }
        for (row in 0 until 5) {
            for (col in 0 until 5) {
                rotatedMap[col][row] = map[4 - row][col]
            }
        }
        return rotatedMap
    }

    private fun bfs(startNode: Node, madeCube: Array<Array<IntArray>>) {
        val q: Queue<Node> = LinkedList()
        val visited = Array(5) { Array(5) { BooleanArray(5) } }
        val cube = Array(5) { x -> Array(5) { y -> madeCube[x][y].copyOf() } }
        q.offer(startNode)
        visited[startNode.x][startNode.y][startNode.z] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            for (i in 0 until 6) {
                val nx = cur.x + d[i].x
                val ny = cur.y + d[i].y
                val nz = cur.z + d[i].z
                if (nx !in 0 until 5 || ny !in 0 until 5 || nz !in 0 until 5 || visited[nx][ny][nz]) continue
                if (cube[nx][ny][nz] == 1) {
                    q.offer(Node(nx, ny, nz, cur.dis + 1))
                    visited[nx][ny][nz] = true
                    cube[nx][ny][nz] = cur.dis + 1
                }
            }
        }
        val dis = cube[4][4 - startNode.y][4 - startNode.z]
        if (dis in 2 until minDis) minDis = dis
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        cube = Array(5) { Array(5) { IntArray(5) } }
        for (k in 0 until 5) {
            val map = Array(5) { IntArray(5) }
            for (i in 0 until 5) {
                val st = StringTokenizer(readLine())
                for (j in 0 until 5) {
                    map[i][j] = st.nextToken().toInt()
                }
            }
            cube[k] = map
        }
        setCubeOrder(0)
        if (minDis == maxDis) println(-1)
        else println(minDis)
    }
}

fun main() {
    `16985`().solution()
}