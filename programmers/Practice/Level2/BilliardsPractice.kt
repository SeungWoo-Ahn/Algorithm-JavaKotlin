package programmers.Practice.Level2

class BilliardsPractice {
    fun solution(m: Int, n: Int, sx: Int, sy: Int, balls: Array<IntArray>): IntArray {
        return IntArray(balls.size) {
            val (ex, ey) = balls[it]
            var minCost = 1_000_000
            if (sx != ex || ey > sy) {
                minCost = minOf(minCost, (sx - ex) * (sx - ex) + (sy + ey) * (sy + ey))
            }
            if (sy != ey || ex > sx) {
                minCost = minOf(minCost, (sx + ex) * (sx + ex) + (sy - ey) * (sy - ey))
            }
            if (sx != ex || ey < sy) {
                minCost = minOf(minCost, (sx - ex) * (sx - ex) + (2 * n - ey - sy) * (2 * n - ey - sy))
            }
            if (sy != ey || ex < sx) {
                minCost = minOf(minCost, (2 * m - ex - sx) * (2 * m - ex - sx) + (sy - ey) * (sy - ey))
            }
            minCost
        }
    }
}

fun main() {
    val m = 10
    val n = 10
    val sx  = 3
    val sy = 7
    val balls = arrayOf(
        intArrayOf(7, 7),
        intArrayOf(2, 7),
        intArrayOf(7, 3)
    )
    val answer = BilliardsPractice().solution(m, n, sx, sy, balls)
    print(answer)
}