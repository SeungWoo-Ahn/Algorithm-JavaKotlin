package programmers.Practice.Level2

class InterceptionSystem {
    fun solution(targets: Array<IntArray>): Int {
        targets.sortBy { it[0] }
        var cnt = 0
        var last = -1
        for ((s, e) in targets) {
            if (s > last) {
                cnt++
                last = e - 1
            } else if (e - 1 < last) {
                last = e - 1
            }
        }
        return cnt
    }
}

fun main() {
    val targets = arrayOf(
        intArrayOf(4, 5),
        intArrayOf(4, 8),
        intArrayOf(10, 14),
        intArrayOf(11, 13),
        intArrayOf(5, 12),
        intArrayOf(3, 7),
        intArrayOf(1, 4)
    )
    val answer = InterceptionSystem().solution(targets)
    print(answer)
}