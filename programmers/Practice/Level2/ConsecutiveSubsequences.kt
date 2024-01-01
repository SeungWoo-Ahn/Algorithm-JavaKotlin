package programmers.Practice.Level2

class ConsecutiveSubsequences {
    fun solution(elements: IntArray): Int {
        val set = HashSet<Int>()
        for (len in 1..elements.size) {
            for (i in elements.indices) {
                set.add(sumByLen(elements, len, i))
            }
        }
        return set.size
    }

    private fun sumByLen(elements: IntArray, len: Int, i: Int): Int {
        var cnt = 0
        var index = i
        var sum = 0
        while (cnt < len) {
            sum += elements[index]
            cnt++
            index = if (index + 1 == elements.size) 0 else index + 1
        }
        return sum
    }
}

fun main() {
    val elements = intArrayOf(7,9,1,1,4)
    println(ConsecutiveSubsequences().solution(elements))
}