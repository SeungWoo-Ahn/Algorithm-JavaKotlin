package programmers.Practice.Level3

class CrossSteppingStone {
    fun solution(stones: IntArray, k: Int): Int {
        var left = 0
        var right = stones.max()
        while (left <= right) {
            val mid = (left + right) / 2
            var seq = 0
            var maxSeq = 0
            for (i in stones.indices) {
                if (stones[i] <= mid) {
                    maxSeq = maxOf(maxSeq, ++seq)
                } else {
                    seq = 0
                }
            }
            if (maxSeq >= k) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }
}

fun main() {
    val stones = intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1)
    val k = 3
    val answer = CrossSteppingStone().solution(stones, k)
    print(answer)
}