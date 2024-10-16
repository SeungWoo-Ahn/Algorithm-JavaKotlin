package programmers.Practice.Level2

class HailSequenceIntegral {
    private fun makeSequence(k: Int): IntArray {
        val sequence = mutableListOf<Int>()
        var cur = k
        while (cur > 1) {
            sequence += cur
            if (cur % 2 == 0) {
                cur /= 2
            } else {
                cur = cur * 3 + 1
            }
        }
        sequence += cur
        return sequence.toIntArray()
    }

    private fun calcTrapezoidArea(a: Int, b: Int): Double {
        return (a.toDouble() + b.toDouble()) / 2
    }

    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        val sequence = makeSequence(k)
        val size = sequence.size
        val areaPrefixSum = DoubleArray(size)
        for (i in 1 until size) {
            areaPrefixSum[i] = areaPrefixSum[i - 1] + calcTrapezoidArea(sequence[i - 1], sequence[i])
        }
        val result = DoubleArray(ranges.size)
        val n = size - 1
        for (i in ranges.indices) {
            val from = ranges[i][0]
            val to = n + ranges[i][1]
            result[i] = if (from > to) {
                -1.0
            } else {
                areaPrefixSum[to] - areaPrefixSum[from]
            }
        }
        return result
    }
}

fun main() {
    val k = 5
    val ranges = arrayOf(
        intArrayOf(0, 0),
        intArrayOf(0, -1),
        intArrayOf(2, -3),
        intArrayOf(3, -3),
    )
    val answer = HailSequenceIntegral().solution(k, ranges)
    print(answer.joinToString())
}