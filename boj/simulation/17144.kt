package boj.simulation

import java.util.StringTokenizer

class `17144` {
    private var map: Array<IntArray> = arrayOf()
    private var cleanerUp = -1
    private var cleanerDown = -1
    private val dx = listOf(1, -1, 0, 0)
    private val dy = listOf(0 , 0, 1, -1)

    private fun spread(r: Int, c: Int) {
        val spreadMap = Array(r) { IntArray(c) }
        for (i in spreadMap.indices) {
            for (j in spreadMap[i].indices) {
                if (map[i][j] >= 5) {
                    val amount = map[i][j] / 5
                    var spreadCnt = 0
                    for (k in 0 until 4) {
                        val nx = i + dx[k]
                        val ny = j + dy[k]
                        if (nx !in 0 until r || ny !in 0 until c) continue
                        if ((ny == 0 && nx == cleanerUp) || (ny == 0 && nx == cleanerDown)) continue
                        spreadMap[nx][ny] += amount
                        spreadCnt++
                    }
                    map[i][j] -= spreadCnt * amount
                }
            }
        }
        for (i in spreadMap.indices) {
            for (j in spreadMap[i].indices) {
                map[i][j] += spreadMap[i][j]
            }
        }
    }

    private fun cleaning(r: Int, c: Int) {
        val upCorners = listOf(map[cleanerUp][c - 1], map[0][c - 1], map[0][0])
        val downCorners = listOf(map[cleanerDown][c - 1], map[r - 1][c - 1], map[r - 1][0])

        for (y in c - 1 downTo 1) {
            map[cleanerUp][y] = map[cleanerUp][y - 1]
            map[cleanerDown][y] = map[cleanerDown][y - 1]
        }

        for (x in 0 until cleanerUp - 1) {
            map[x][c - 1] = map[x + 1][c - 1]
        }
        for (x in r - 1 downTo  cleanerDown + 2) {
            map[x][c - 1] = map[x - 1][c - 1]
        }
        map[cleanerUp - 1][c - 1] = upCorners[0]
        map[cleanerDown + 1][c - 1] = downCorners[0]

        for (y in 0 until c - 2) {
            map[0][y] = map[0][y + 1]
            map[r - 1][y] = map[r - 1][y + 1]
        }
        map[0][c - 2] = upCorners[1]
        map[r - 1][c - 2] = downCorners[1]

        for (x in cleanerUp - 1 downTo 2) {
            map[x][0] = map[x - 1][0]
        }
        for (x in cleanerDown + 1 until r - 2) {
            map[x][0] = map[x + 1][0]
        }
        map[1][0] = upCorners[2]
        map[r - 2][0] = downCorners[2]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (r, c, t) = readLine().split(" ").map { it.toInt() }
        map = Array(r) { x ->
            val st = StringTokenizer(readLine())
            IntArray(c) {
                var dust = st.nextToken().toInt()
                if (dust == -1) {
                    if (cleanerUp == -1) cleanerUp = x
                    else cleanerDown = x
                    dust = 0
                }
                dust
            }
        }
        repeat(t) {
            spread(r, c)
            cleaning(r, c)
        }
        var answer = 0
        for (i in map.indices) {
            for (j in map[i].indices) {
                answer += map[i][j]
            }
        }
        println(answer)
    }
}

fun main() {
    `17144`().solution()
}