package programmers.Practice.Level2

class SumOfConsecutiveSubsequences {
    fun solution(sequence: IntArray, k: Int): IntArray {
        val result = intArrayOf(0, sequence.size)
        var en = 0
        var sum = sequence[0]
        for (st in sequence.indices) {
            while (en < sequence.lastIndex && sum + sequence[en + 1] <= k) {
                sum += sequence[++en]
            }
            if (sum == k) {
                if (getLen(st, en) < getLen(result[0], result[1])) {
                    result[0] = st
                    result[1] = en
                }
            }
            sum -= sequence[st]
        }
        return result
    }

    private fun getLen(from: Int, to: Int) = to - from + 1
}

fun main() {
    val sequence = intArrayOf(1, 2, 3, 4, 5)
    val k = 7
    val answer = SumOfConsecutiveSubsequences().solution(sequence, k)
    print(answer.joinToString())
}