package programmers.Greedy

class GymSuit {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val table = BooleanArray(n + 1) { true }
        val isReserve = BooleanArray(n + 1)
        for (num in lost) {
            table[num] = false
        }
        for (num in reserve) {
            if (!table[num]) {
                table[num] = true
                isReserve[num] = false
            } else isReserve[num] = true
        }
        for (i in 1 .. n) {
            if (i > 1 && !table[i - 1] && isReserve[i]) {
                table[i - 1] = true
            } else if (i < n && !table[i + 1] && isReserve[i]) {
                table[i + 1] = true
            }
        }
        return table.count { it } - 1
    }
}

fun main() {
    val n = 5
    val lost = intArrayOf(2, 4)
    val reserve = intArrayOf(1, 3, 5)
    println(GymSuit().solution(n, lost, reserve))
}