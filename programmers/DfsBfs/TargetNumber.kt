package programmers.DfsBfs

class KTargetNumber {

    private var answer = 0

    private fun dfs(numbers: IntArray, target: Int, index: Int, sum: Int) {
        if (index == numbers.size) {
            if (sum == target) answer++
            return
        }
        dfs(numbers, target, index + 1, sum + numbers[index])
        dfs(numbers, target, index + 1, sum - numbers[index])
    }

    fun solution(numbers: IntArray, target: Int): Int {
        dfs(numbers, target, 0, 0)
        return answer
    }
}

fun main() {
    val numbers = intArrayOf(1, 1, 1, 1, 1)
    val target = 3
    println(KTargetNumber().solution(numbers, target))
}