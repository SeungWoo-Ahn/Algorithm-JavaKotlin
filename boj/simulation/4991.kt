package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `4991` {
    data class Pos(val x: Int, val y: Int)

    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)
    private var minShortCut = 0

    private fun simulation(
        w: Int,
        h: Int,
        startPos: Pos,
        dirtyPosList: List<Pos>,
        furniture: Array<BooleanArray>,
    ): Int {
        val targetPosList = listOf(startPos) + dirtyPosList
        val shortCuts = Array(targetPosList.size) { IntArray(targetPosList.size) }
        for (from in 0 until targetPosList.size - 1) {
            val map = bfs(w, h, targetPosList[from], furniture)
            for (to in from + 1 until targetPosList.size) {
                val endPos = targetPosList[to]
                val shortCut = map[endPos.x][endPos.y]
                if (shortCut < 0) return -1
                shortCuts[from][to] = shortCut
                shortCuts[to][from] = shortCut
            }
        }
        val order = IntArray(targetPosList.size)
        val used = BooleanArray(targetPosList.size)
        used[0] = true
        minShortCut = MAX_SHORTCUT
        findOrder(1, order, used, shortCuts)
        return minShortCut
    }

    private fun findOrder(depth: Int, order: IntArray, used: BooleanArray, shortCuts: Array<IntArray>) {
        if (depth == order.size) {
            val shortCutSum = calcShortCutSum(order, shortCuts)
            if (shortCutSum < minShortCut) {
                minShortCut = shortCutSum
            }
            return
        }
        for (i in 1 until order.size) {
            if (used[i]) continue
            used[i] = true
            order[depth] = i
            findOrder(depth + 1, order, used, shortCuts)
            used[i] = false
        }
    }

    private fun calcShortCutSum(order: IntArray, shortCuts: Array<IntArray>): Int {
        var shortCutSum = 0
        for (i in 0 until order.size - 1) {
            val st = order[i]
            val en = order[i + 1]
            shortCutSum += shortCuts[st][en]
        }
        return shortCutSum
    }

    private fun bfs(w: Int, h: Int, startPos: Pos, furniture: Array<BooleanArray>): Array<IntArray> {
        val q = LinkedList<Pos>() as Queue<Pos>
        val map = Array(h) { IntArray(w) { -1 } }
        q += startPos
        map[startPos.x][startPos.y] = 0
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny, map) || furniture[nx][ny]) continue
                if (map[nx][ny] < 0) {
                    q += Pos(nx, ny)
                    map[nx][ny] = map[x][y] + 1
                }
            }
        }
        return map
    }

    private fun outOfBoundary(x: Int, y: Int, map: Array<IntArray>): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        var st: StringTokenizer
        val sb = StringBuilder()
        while (true) {
            st = StringTokenizer(readLine(), " ")
            val w = st.nextToken().toInt()
            val h = st.nextToken().toInt()
            if (w == 0 && h == 0) {
                break
            }
            var startPos = Pos(0, 0)
            val dirtyPosList = mutableListOf<Pos>()
            val furniture = Array(h) { x ->
                val line = readLine()
                BooleanArray(w) { y ->
                    when (line[y]) {
                        '.' -> false
                        '*' -> {
                            dirtyPosList += Pos(x, y)
                            false
                        }
                        'x' -> true
                        'o' -> {
                            startPos = Pos(x, y)
                            false
                        }
                        else -> throw IllegalArgumentException()
                    }
                }
            }
            val result = simulation(w, h, startPos, dirtyPosList, furniture)
            sb.appendLine(result)
        }
        print(sb)
    }

    companion object {
        private const val MAX_SHORTCUT = 4_001
    }
}

fun main() {
    `4991`().solution()
}