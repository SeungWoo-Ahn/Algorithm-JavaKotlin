package programmers.Practice.Level2

class DecodeSecretCode {
    private var matchCnt = 0
    private lateinit var question: Array<IntArray>
    private lateinit var answer: IntArray

    private fun combination(n: Int, depth: Int, start: Int, arr: IntArray) {
        if (depth == arr.size) {
            if (check(arr)) matchCnt++
            return
        }
        for (num in start..n) {
            arr[depth] = num
            combination(n, depth + 1, num + 1, arr)
        }
    }

    private fun check(arr: IntArray): Boolean {
        val arrSet = arr.toSet()
        for (i in question.indices) {
            var correct = 0
            for (num in question[i]) {
                if (arrSet.contains(num)) correct++
            }
            if (correct != answer[i]) {
                return false
            }
        }
        return true
    }

    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        question = q
        answer = ans
        combination(n, 0, 1, IntArray(5))
        return matchCnt
    }
}

fun main() {
    val n = 15
    val q = arrayOf(
        intArrayOf(2, 3, 9, 12, 13),
        intArrayOf(1, 4, 6, 7, 9),
        intArrayOf(1, 2, 8, 10, 12),
        intArrayOf(6, 7, 11, 13, 15),
        intArrayOf(1, 4, 10, 11, 14)
    )
    val ans = intArrayOf(2, 1, 3, 0, 1)
    val answer = DecodeSecretCode().solution(n, q, ans)
    print(answer)
}