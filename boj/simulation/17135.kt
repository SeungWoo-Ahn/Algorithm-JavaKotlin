package boj.simulation

import java.util.StringTokenizer

class `17135` {
    private data class Pos(val x: Int, val y: Int)

    private var n = 0
    private var m = 0
    private var d = 0
    private var enemyCnt = 0
    private lateinit var map: Array<IntArray>
    private lateinit var range: Array<MutableList<Pos>>
    private val dx = intArrayOf(0, -1, 0, 1)
    private val dy = intArrayOf(-1, 0, 1, 0)
    private var maxKillCnt = 0

    private fun combination(depth: Int, cnt: Int, idxList: IntArray) {
        if (depth == m || cnt == idxList.size) {
            val killCnt = simulation(idxList)
            if (killCnt > maxKillCnt) {
                maxKillCnt = killCnt
            }
            return
        }
        idxList[cnt] = depth
        combination(depth + 1, cnt + 1, idxList)
        if (m - depth > idxList.size - cnt) {
            combination(depth + 1, cnt, idxList)
        }
    }

    private fun simulation(idxList: IntArray): Int {
        val copyMap = Array(n) { i -> map[i].copyOf() }
        val killPosSet = mutableSetOf<Pos>()
        var remainEnemy = enemyCnt
        var killCnt = 0
        while (remainEnemy > 0) {
            for (y in idxList) {
                loop@ for (distance in 1..d) {
                    for (pos in range[distance]) {
                        val targetX = n + pos.x
                        val targetY = y + pos.y
                        if (outOfBoundary(targetX, targetY)) continue
                        if (copyMap[targetX][targetY] == ENEMY) {
                            killPosSet += Pos(targetX, targetY)
                            break@loop
                        }
                    }
                }
            }
            killCnt += killPosSet.size
            remainEnemy -= killPosSet.size
            for (pos in killPosSet) {
                copyMap[pos.x][pos.y] = EMPTY
            }
            killPosSet.clear()
            for (y in 0 until m) {
                if (copyMap[n - 1][y] == ENEMY) {
                    copyMap[n - 1][y] = EMPTY
                    remainEnemy--
                }
            }
            for (x in n - 1 downTo 1) {
                for (y in copyMap[x].indices) {
                    if (copyMap[x - 1][y] == ENEMY) {
                        copyMap[x - 1][y] = EMPTY
                        copyMap[x][y] = ENEMY
                    }
                }
            }
        }
        return killCnt
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in map.indices || y !in map[x].indices
    }

    private fun makeRange() {
        range = Array(d + 1) { mutableListOf() }
        range[1] += Pos(dx[1], dy[1])
        for (distance in 2..d) {
            val midIdx = range[distance - 1].size shr 1
            for (i in range[distance - 1].indices) {
                val pos = range[distance - 1][i]
                when {
                    i < midIdx -> range[distance] += Pos(pos.x + dx[0], pos.y + dy[0])
                    i > midIdx -> range[distance] += Pos(pos.x + dx[2], pos.y + dy[2])
                    else -> {
                        for (dir in 0 until 3) {
                            range[distance] += Pos(pos.x + dx[dir], pos.y + dy[dir])
                        }
                    }
                }
            }
        }
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        d = st.nextToken().toInt()
        map = Array(n) {
            st = StringTokenizer(readLine(), " ")
            IntArray(m) {
                val info = st.nextToken().toInt()
                if (info == ENEMY) enemyCnt++
                info
            }
        }
    }

    fun solution() {
        input()
        makeRange()
        val idxList = IntArray(3)
        combination(0, 0, idxList)
        print(maxKillCnt)
    }

    companion object {
        private const val EMPTY = 0
        private const val ENEMY = 1
    }
}

fun main() {
    `17135`().solution()
}