package boj.bfsdfs

import java.io.BufferedReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `9328` {
    private data class Pos(val x: Int, val y: Int)

    private var h = 0
    private var w = 0
    private lateinit var map: Array<CharArray>
    private lateinit var keys: String
    private lateinit var visited: Array<BooleanArray>
    private lateinit var doors: Array<MutableList<Pos>>
    private lateinit var keyEnabled: BooleanArray
    private lateinit var q: Queue<Pos>
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun findDoors() {
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y].isDoor()) {
                    val idx = doorToIdx(map[x][y])
                    doors[idx] += Pos(x, y)
                }
            }
        }
    }

    private fun openDoorsByKeys() {
        if (keys == "0") return
        for (key in keys) {
            openDoorByKey(key)
        }
    }

    private fun openDoorByKey(key: Char) {
        val idx = keyToIdx(key)
        keyEnabled[idx] = true
        for (pos in doors[idx]) {
            map[pos.x][pos.y] = EMPTY
        }
    }

    private fun addAdjDoorsPosByKey(key: Char) {
        val idx = keyToIdx(key)
        for (pos in doors[idx]) {
            if (isEdge(pos) || isAdj(pos)) {
                q += pos
                visited[pos.x][pos.y] = true
            }
        }
    }

    private fun handleKey(x: Int, y: Int) {
        val key = map[x][y]
        if (keyEnabled[keyToIdx(key)].not()) {
            openDoorByKey(map[x][y])
            addAdjDoorsPosByKey(key)
        }
        map[x][y] = EMPTY
    }

    private fun isEdge(pos: Pos): Boolean {
        return pos.x == 0 || pos.x == h - 1 || pos.y == 0 || pos.y == w - 1
    }

    private fun isAdj(pos: Pos): Boolean {
        for (i in 0 until 4) {
            val nx = pos.x + dx[i]
            val ny = pos.y + dy[i]
            if (outOfBoundary(nx, ny)) continue
            if (visited[nx][ny]) return true
        }
        return false
    }

    private fun bfs(sx: Int, sy: Int): Int {
        var documentCnt = 0
        q += Pos(sx, sy)
        visited[sx][sy] = true
        if (map[sx][sy].isKey()) {
            handleKey(sx, sy)
        }
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            if (map[x][y] == DOCUMENT) {
                documentCnt++
                map[x][y] = EMPTY
            }
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny) || canEnter(nx, ny).not()) continue
                if (map[nx][ny].isKey()) {
                    handleKey(nx, ny)
                }
                visited[nx][ny] = true
                q += Pos(nx, ny)
            }
        }
        return documentCnt
    }

    private fun enterMap(x: Int, y: Int): Int {
        if (canEnter(x, y).not()) return 0
        return bfs(x, y)
    }

    private fun canEnter(x: Int, y: Int): Boolean {
        return visited[x][y].not() && map[x][y] != WALL && map[x][y].isDoor().not()
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun Char.isKey(): Boolean {
        return 'a'.code <= code && code <= 'z'.code
    }

    private fun Char.isDoor(): Boolean {
        return 'A'.code <= code && code <= 'Z'.code
    }

    private fun doorToIdx(door: Char): Int {
        return door - 'A'
    }

    private fun keyToIdx(key: Char): Int {
        return key - 'a'
    }

    private fun solve(): Int {
        findDoors()
        openDoorsByKeys()
        var documentCnt = 0
        for (y in 0 until w) {
            documentCnt += enterMap(0, y)
            documentCnt += enterMap(h - 1, y)
        }
        for (x in 0 until h) {
            documentCnt += enterMap(x, 0)
            documentCnt += enterMap(x, w - 1)
        }
        return documentCnt
    }

    private fun BufferedReader.input() {
        val st = StringTokenizer(readLine(), " ")
        h = st.nextToken().toInt()
        w = st.nextToken().toInt()
        map = Array(h) { readLine().toCharArray() }
        keys = readLine()
        visited = Array(h) { BooleanArray(w) }
        doors = Array(26) { mutableListOf() }
        keyEnabled = BooleanArray(26)
        q = LinkedList()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            input()
            val maxDocument = solve()
            sb.appendLine(maxDocument)
        }
        print(sb)
    }

    companion object {
        private const val DOCUMENT = '$'
        private const val WALL = '*'
        private const val EMPTY = '.'
    }
}

fun main() {
    `9328`().solution()
}