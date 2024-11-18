package programmers.Practice.Level3

class SelectHikingCourse {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {

    }
}

fun main() {
    val n = 6
    val paths = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(2, 3, 5),
        intArrayOf(2, 4, 2),
        intArrayOf(2, 5, 4),
        intArrayOf(3, 4, 4),
        intArrayOf(4, 5, 3),
        intArrayOf(4, 6, 1),
        intArrayOf(5, 6, 1)
    )
    val gates = intArrayOf(1, 3)
    val summits = intArrayOf(5)
    val answer = SelectHikingCourse().solution(n, paths, gates, summits)
    print(answer.joinToString())
}