package programmers.Sort

class MaxNumber {
    fun solution(numbers: IntArray): String {
        val arr = numbers.map { it.toString() }.sortedWith { o1, o2 -> (o2 + o1).compareTo(o1 + o2) }
        if (arr.first() == "0") return "0"
        val sb = StringBuilder()
        arr.forEach { sb.append(it) }
        return sb.toString()
    }
}

fun main() {
    val numbers = intArrayOf(3, 30, 34, 5, 9)
    println(MaxNumber().solution(numbers))
}