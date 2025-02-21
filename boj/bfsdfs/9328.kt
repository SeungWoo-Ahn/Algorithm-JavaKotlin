package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `9328` {
    private data class Pos(val x: Int, val y: Int)

    private interface Indexable {
        fun getIdx(): Int
    }

    private sealed interface Node {
        fun canEnter(): Boolean

        data class Door(private val alpha: Char) : Node, Indexable {
            override fun canEnter() = false
            override fun getIdx() = alpha - 'A'
        }

        data class Key(private val alpha: Char) : Node, Indexable {
            override fun canEnter() = true
            override fun getIdx() = alpha - 'a'
        }

        data object Wall : Node {
            override fun canEnter() = false
        }

        data object Empty : Node {
            override fun canEnter() = true
        }

        data object Document : Node {
            override fun canEnter() = true
        }
    }

    private class MapFactory(private val inputMap: Array<CharArray>) {
        fun get(): Array<Array<Node>> {
            return Array(inputMap.size) { x ->
                Array(inputMap[x].size) { y ->
                    val info = inputMap[x][y]
                    when {
                        info.isDoor() -> Node.Door(info)
                        info.isKey() -> Node.Key(info)
                        info == '*' -> Node.Wall
                        info == '.' -> Node.Empty
                        info == '$' -> Node.Document
                        else -> throw IllegalArgumentException()
                    }
                }
            }
        }

        private fun Char.isDoor(): Boolean {
            return 'A'.code <= code && code <= 'Z'.code
        }

        private fun Char.isKey(): Boolean {
            return 'a'.code <= code && code <= 'z'.code
        }
    }

    private class MapManger(
        private val map: Array<Array<Node>>,
        private val keys: String
    ) {
        private val q = LinkedList<Pos>() as Queue<Pos>
        private val visited = Array(map.size) { x -> BooleanArray(map[x].size) }
        private val doorPosListByIdx = Array(IDX_SIZE) { mutableListOf<Pos>() }
        private val keyEnabled = BooleanArray(IDX_SIZE)
        private val d = listOf(
            Pos(1, 0),
            Pos(-1, 0),
            Pos(0, 1),
            Pos(0, -1),
        )

        init {
            findDoorsPos()
            openDoorsByKeys()
        }

        private fun findDoorsPos() {
            for (x in map.indices) {
                for (y in map[x].indices) {
                    val node = map[x][y]
                    if (node is Node.Door) {
                        doorPosListByIdx[node.getIdx()] += Pos(x, y)
                    }
                }
            }
        }

        private fun openDoorsByKeys() {
            if (keys == "0") return
            keys.forEach { openDoor(key = Node.Key(it)) }
        }

        private fun openDoor(key: Node.Key) {
            keyEnabled[key.getIdx()] = true
            for (pos in doorPosListByIdx[key.getIdx()]) {
                markEmpty(pos)
            }
        }

        fun getDocumentCnt(pos: Pos): Int {
            if (cantEnter(pos)) {
                return 0
            }
            var documentCnt = 0
            visit(pos)
            handleNodeIfKey(pos)
            while (q.isNotEmpty()) {
                val curPos = q.poll()
                handleNodeIfDocument(curPos) {
                    documentCnt++
                }
                for ((dx, dy) in d) {
                    val nextPos = Pos(curPos.x + dx, curPos.y + dy)
                    if (cantEnter(nextPos)) continue
                    visit(nextPos)
                    handleNodeIfKey(nextPos)
                }
            }
            return documentCnt
        }

        private fun getNode(pos: Pos): Node {
            return map[pos.x][pos.y]
        }

        private fun cantEnter(pos: Pos): Boolean {
            return outOfBoundary(pos) ||
                    visited[pos.x][pos.y] ||
                    getNode(pos).canEnter().not()
        }

        private fun visit(pos: Pos) {
            q += pos
            visited[pos.x][pos.y] = true
        }

        private fun handleNodeIfKey(pos: Pos) {
            val node = getNode(pos)
            if (node is Node.Key) {
                if (keyEnabled[node.getIdx()].not()) {
                    openDoor(node)
                    visitAvailableDoorPosList(node)
                }
                markEmpty(pos)
            }
        }

        private fun visitAvailableDoorPosList(key: Node.Key) {
            for (pos in doorPosListByIdx[key.getIdx()]) {
                if (isEdge(pos) || isAdj(pos)) {
                    visit(pos)
                }
            }
        }

        private fun handleNodeIfDocument(pos: Pos, onTrue: () -> Unit) {
            val node = getNode(pos)
            if (node is Node.Document) {
                markEmpty(pos)
                onTrue()
            }
        }

        private fun markEmpty(pos: Pos) {
            map[pos.x][pos.y] = Node.Empty
        }

        private fun outOfBoundary(pos: Pos): Boolean {
            return pos.x !in map.indices || pos.y !in map[pos.x].indices
        }

        private fun isEdge(pos: Pos): Boolean {
            return pos.x == 0 || pos.x == map.lastIndex || pos.y == 0 || pos.y == map[pos.x].lastIndex
        }

        private fun isAdj(pos: Pos): Boolean {
            for ((dx, dy) in d) {
                val nx = pos.x + dx
                val ny = pos.y + dy
                if (outOfBoundary(pos = Pos(nx, ny))) continue
                if (visited[nx][ny]) return true
            }
            return false
        }

        companion object {
            private const val IDX_SIZE = 26
        }
    }

    private fun solve(inputMap: Array<CharArray>, keys: String): Int {
        val map = MapFactory(inputMap).get()
        val manager = MapManger(map, keys)
        var documentCnt = 0
        for (y in map[0].indices) {
            documentCnt += manager.getDocumentCnt(Pos(0, y))
            documentCnt += manager.getDocumentCnt(Pos(map.lastIndex, y))
        }
        for (x in map.indices) {
            documentCnt += manager.getDocumentCnt(Pos(x, 0))
            documentCnt += manager.getDocumentCnt(Pos(x, map[0].lastIndex))
        }
        return documentCnt
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val st = StringTokenizer(readLine(), " ")
            val h = st.nextToken().toInt()
            val inputMap = Array(h) { readLine().toCharArray() }
            val keys = readLine()
            val maxDocuments = solve(inputMap, keys)
            sb.appendLine(maxDocuments)
        }
        print(sb)
    }
}

fun main() {
    `9328`().solution()
}