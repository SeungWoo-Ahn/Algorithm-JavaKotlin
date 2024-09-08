package programmers.Practice.Level2

class DiscountEvent {
    private fun check(number: IntArray, cnt: IntArray): Boolean {
        for (i in number.indices) {
            if (number[i] != cnt[i]) {
                return false
            }
        }
        return true
    }
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val code = mutableMapOf<String, Int>()
        val cnt = IntArray(number.size)
        want.forEachIndexed { i, s -> code[s] = i }
        for (i in 0 until 10) {
            val idx = code[discount[i]] ?: continue
            cnt[idx]++
        }
        var result = 0
        var prevCorrect = false
        if (check(number, cnt)) {
            result++
            prevCorrect = true
        }
        for (i in 1..discount.size - 10) {
            val prev = discount[i - 1]
            val nxt = discount[i + 9]
            code[prev]?.let { cnt[it]-- }
            code[nxt]?.let { cnt[it]++ }
            val correct = if (prevCorrect && prev == nxt && code[prev] != null) {
                true
            } else {
                check(number, cnt)
            }
            if (correct) {
                result++
            }
            prevCorrect = correct
        }
        return result
    }
}

fun main() {
    val want = arrayOf("banana", "apple", "rice", "pork", "pot")
    val number = intArrayOf(3, 2, 2, 2, 1)
    val discount = arrayOf("chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana")
    val answer = DiscountEvent().solution(want, number, discount)
    println(answer)
}