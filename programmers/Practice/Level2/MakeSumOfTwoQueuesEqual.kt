package programmers.Practice.Level2

class MakeSumOfTwoQueuesEqual {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val sum1 = queue1.sumOf { it.toLong() }
        val sum2 = queue2.sumOf { it.toLong() }
        if ((sum1 + sum2) % 2 == 1L) {
            return -1
        }
        val size = queue1.size
        val total = (sum1 + sum2) / 2
        val totalArr = IntArray(size * 2)
        for (i in 0 until size) {
            totalArr[i] = queue1[i]
            totalArr[i + size] = queue2[i]
        }
        var st = 0
        var en = size
        var sum = sum1
        var workCnt = 0
        while (st < en) {
            if (sum == total) {
                return workCnt
            }
            if (sum < total && en == totalArr.lastIndex) {
                break
            }
            if (sum > total) {
                sum -= totalArr[st++]
            } else if (sum < total) {
                sum += totalArr[en++]
            }
            workCnt++
        }
        return -1
    }
}

fun main() {
    val queue1 = intArrayOf(1, 2, 1, 2)
    val queue2 = intArrayOf(1, 10, 1, 2)
    val answer = MakeSumOfTwoQueuesEqual().solution(queue1, queue2)
    print(answer)
}
