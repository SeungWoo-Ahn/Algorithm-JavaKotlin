package boj.bfsdfs

class `16724` {
    private var map: Array<CharArray> = arrayOf()
    private var group: Array<IntArray> = arrayOf()
    private var safeZoneCnt = 0

    private fun dfs(groupNum: Int, sx: Int, sy: Int) {
        var curX = sx
        var curY = sy
        while (group[curX][curY] == 0) {
            group[curX][curY] = groupNum
            when (map[curX][curY]) {
                'U' -> curX--
                'D' -> curX++
                'L' -> curY--
                'R' -> curY++
            }
        }
        if (group[curX][curY] == groupNum) safeZoneCnt++
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        map = Array(n) { readLine().toCharArray() }
        group = Array(n) { IntArray(m) }
        var groupNum = 0
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (group[x][y] != 0) continue
                dfs(++groupNum, x, y)
            }
        }
        print(safeZoneCnt)
    }
}

fun main() {
    `16724`().solution()
}