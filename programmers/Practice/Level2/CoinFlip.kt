package programmers.Practice.Level2

class CoinFlip {
    private fun compare(beginning: Array<IntArray>, target: Array<IntArray>, row: Int, col: Int): Boolean {
        for (r in beginning.indices) {
            for (c in beginning[r].indices) {
                val diff = ((row shr r) % 2 + (col shr c) % 2) % 2
                if ((beginning[r][c] + diff) % 2 != target[r][c]) {
                    return false
                }
            }
        }
        return true
    }

    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        val (n, m) = beginning.size to beginning[0].size
        val max = 21
        var flipCnt = max
        for (row in 0 until (1 shl n)) {
            for (col in 0 until (1 shl m)) {
                val cnt = Integer.bitCount(row) + Integer.bitCount(col)
                if (cnt < flipCnt && compare(beginning, target, row, col)) {
                    flipCnt = cnt
                }
            }
        }
        return if (flipCnt < max) flipCnt else -1
    }
}

fun main() {
    val beginning = arrayOf(
        intArrayOf(0, 1, 0, 0, 0),
        intArrayOf(1, 0, 1, 0, 1),
        intArrayOf(0, 1, 1, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 1, 0)
    )
    val target = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 1),
        intArrayOf(0, 0, 1, 0, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 1)
    )
    val answer = CoinFlip().solution(beginning, target)
    print(answer)
}