package programmers.Practice.Level3

import kotlin.math.abs

class NumberTypingContest {
    private data class Pos(val x: Int, val y: Int)

    private fun getPos(num: Int): Pos =
        if (num == 0) {
            Pos(3, 1)
        } else {
            val x = (num - 1) / 3
            val y = (num - 1) % 3
            Pos(x, y)
        }

    private fun getWeight(from: Int, to: Int): Int =
        if (from == to) {
            1
        } else {
            val (fromX, fromY) = getPos(from)
            val (toX, toY) = getPos(to)
            val xDiff = abs(fromX - toX)
            val yDiff = abs(fromY - toY)
            val jumpCnt = minOf(xDiff, yDiff)
            val moveCnt = maxOf(xDiff - jumpCnt, yDiff - jumpCnt)
            jumpCnt * 3 + moveCnt * 2
        }

    fun solution(numbers: String): Int {
        val n = numbers.length
        val dp = Array(n + 1) { Array(10) { IntArray(10) { INF } } }
        dp[0][4][6] = 0
        for (i in 0 until n) {
            val target = numbers[i].digitToInt()
            for (left in 0..9) {
                for (right in 0..9) {
                    if (left == right || dp[i][left][right] == INF) continue
                    dp[i + 1][target][right] = minOf(
                        dp[i + 1][target][right],
                        dp[i][left][right] + getWeight(left, target)
                    )
                    dp[i + 1][left][target] = minOf(
                        dp[i + 1][left][target],
                        dp[i][left][right] + getWeight(right, target)
                    )
                }
            }
        }
        return dp.last().minOf { arr -> arr.minOf { it } }
    }

    private companion object {
        private const val INF = Int.MAX_VALUE / 2
    }
}

fun main() {
    val numbers = "1756"
    val answer = NumberTypingContest().solution(numbers)
    print(answer)
}