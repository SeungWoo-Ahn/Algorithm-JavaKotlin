package programmers.Practice.Level3

class PersonnelEvaluation {
    fun solution(scores: Array<IntArray>): Int {
        val wanHo = scores[0]
        scores.sortWith(compareBy({ -it[0] }, { it[1] }))
        var ranking = 1
        var maxB = 0
        for ((a, b) in scores) {
            if (b < maxB) {
                if (a == wanHo[0] && b == wanHo[1]) {
                    return -1
                }
            } else {
                maxB = maxOf(maxB, b)
                if (a + b > wanHo.sum()) {
                    ranking++
                }
            }
        }
        return ranking
    }
}

fun main() {
    val scores = arrayOf(
        intArrayOf(2, 2),
        intArrayOf(1, 4),
        intArrayOf(3, 2),
        intArrayOf(3, 2),
        intArrayOf(2, 1)
    )
    val answer = PersonnelEvaluation().solution(scores)
    print(answer)
}