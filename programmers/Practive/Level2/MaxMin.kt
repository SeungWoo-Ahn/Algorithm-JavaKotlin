package programmers.Practive.Level2

class MaxMin {
    fun solution(s: String): String {
        val sToIntArr = s.split(" ").map { it.toInt() }
        return "${sToIntArr.minOf { it }} ${sToIntArr.maxOf { it }}"
    }
}

fun main() {
    println(MaxMin().solution("1 2 3 4"))
}