package programmers.Practice.Level3

class PulseSubsequences {
    fun solution(sequence: IntArray): Long {
        val n = sequence.size
        val d1 = LongArray(n)
        val d2 = LongArray(n)
        var sign = 1L
        for (i in sequence.indices) {
            d1[i] = sequence[i] * sign
            d2[i] = sequence[i] * -sign
            sign *= -1
        }
        for (i in 1 until n) {
            if (d1[i] + d1[i - 1] > d1[i]) {
                d1[i] += d1[i - 1]
            }
            if (d2[i] + d2[i - 1] > d2[i]) {
                d2[i] += d2[i - 1]
            }
        }
        var answer = -100_000L
        for (i in sequence.indices) {
            answer = maxOf(answer, d1[i])
            answer = maxOf(answer, d2[i])
        }
        return answer
    }
}

fun main() {
    val sequence = intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)
    val answer = PulseSubsequences().solution(sequence)
    print(answer)
}