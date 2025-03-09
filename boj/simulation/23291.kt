package boj.simulation

import java.util.StringTokenizer

class `23291` {
    private data class Pos(val x: Int, val y: Int)

    private data class MoveInfo(val from: Pos, val to: Pos, val amount: Int)

    private var n = 0
    private var k = 0
    private lateinit var map: Array<IntArray>
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private fun increaseMinTankFish() {
        val minFish = map[0].min()
        for (i in 0 until n) {
            if (map[0][i] == minFish) {
                map[0][i]++
            }
        }
    }

    private fun stackTankFromLeft(): Pair<Int, Int> {
        map[1][1] = map[0][0]
        map[0][0] = BLANK
        var idx = 1
        var left = n - 1
        var (width, hegiht) = 1 to 2
        while (left >= hegiht) {
            val originBlock = getOriginBlock(idx, width, hegiht)
            val rotated90 = rotate90(originBlock)
            idx += width
            width = rotated90[0].size
            hegiht  = rotated90.size + 1
            left = (n - 1) - (idx + width - 1)
            putBlock(idx, 1, rotated90)
        }
        return idx to hegiht
    }

    private fun getOriginBlock(st: Int, width: Int, height: Int): Array<IntArray> {
        return Array(height) { x ->
            IntArray(width) { y ->
                val origin = map[x][y + st]
                map[x][y + st] = BLANK
                origin
            }
        }
    }

    private fun rotate90(block: Array<IntArray>): Array<IntArray> {
        return Array(block[0].size) { x ->
            IntArray(block.size) { y ->
                block[y][block[0].size - x - 1]
            }
        }
    }

    private fun rotate180(block: Array<IntArray>): Array<IntArray> {
        return Array(block.size) { x ->
            IntArray(block[0].size) { y ->
                block[block.size - x - 1][block[0].size - y - 1]
            }
        }
    }

    private fun putBlock(idx: Int, height: Int, block: Array<IntArray>) {
        for (x in block.indices) {
            for (y in block[x].indices) {
                map[x + height][y + idx] = block[x][y]
            }
        }
    }

    private fun moveFish(idx: Int, height: Int) {
        val moveInfos = ArrayDeque<MoveInfo>()
        for (x in 0 until height) {
            for (y in idx until n) {
                if (map[x][y] == BLANK) continue
                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (outOfBoundary(nx, ny, idx, height) || map[nx][ny] == BLANK) continue
                    if (map[x][y] - map[nx][ny] >= 5) {
                        val d = (map[x][y] - map[nx][ny]) / 5
                        moveInfos += MoveInfo(
                            from = Pos(x, y),
                            to = Pos(nx, ny),
                            amount = d
                        )
                    }
                }
            }
        }
        while (moveInfos.isNotEmpty()) {
            val (from, to, amount) = moveInfos.removeFirst()
            map[from.x][from.y] -= amount
            map[to.x][to.y] += amount
        }
    }

    private fun outOfBoundary(x: Int, y: Int, idx: Int, height: Int): Boolean {
        return x !in 0 until height || y !in idx until n
    }

    private fun alignTanks(idx: Int, height: Int) {
        var cur = 0
        for (y in idx until n) {
            for (x in 0 until height) {
                if (map[x][y] != BLANK) {
                    val origin = map[x][y]
                    map[x][y] = BLANK
                    map[0][cur++] = origin
                }
            }
        }
    }

    private fun flipTanks(): Pair<Int, Int> {
        var idx = 0
        var (width, height) = (n shr 1) to 1
        repeat(2) {
            val originBlock = getOriginBlock(idx, width, height)
            val rotated180 = rotate180(originBlock)
            idx += width
            putBlock(idx, height, rotated180)
            width = width shr 1
            height = height shl 1
        }
        return idx to height
    }

    private fun getMaxMinDiff(): Int {
        return map[0].max() - map[0].min()
    }

    fun solution() {
        input()
        var simulationCnt = 0
        while (true) {
            simulationCnt++
            increaseMinTankFish()
            val (idx1, height1) = stackTankFromLeft()
            moveFish(idx1, height1)
            alignTanks(idx1, height1)
            val (idx2, height2) = flipTanks()
            moveFish(idx2, height2)
            alignTanks(idx2, height2)
            if (getMaxMinDiff() <= k) {
                break
            }
        }
        print(simulationCnt)
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        k = st.nextToken().toInt()
        map = Array(n) { IntArray(n) { BLANK } }
        st = StringTokenizer(readLine(), " ")
        for (i in 0 until n) {
            map[0][i] = st.nextToken().toInt()
        }
    }

    private fun printMap() {
        val sb = StringBuilder()
        for (i in map.lastIndex downTo 0) {
            sb.appendLine(map[i].joinToString(" "))
        }
        print(sb)
    }

    companion object {
        private const val BLANK = -1
    }
}

fun main() {
    `23291`().solution()
}