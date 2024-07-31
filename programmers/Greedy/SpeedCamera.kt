package programmers.Greedy

class KSpeedCamera {
    fun solution(routes: Array<IntArray>): Int {
        routes.sortWith(compareBy({ it[0] }, { it[1] }))
        var answer = 1
        var top = 30_000
        var bottom = -30_000
        for ((from, to) in routes) {
            if (from in bottom..top) {
                top = minOf(top, to)
                bottom = from
            } else {
                top = to
                bottom = from
                answer++
            }
        }
        return answer
    }
}

fun main() {
    val routes = arrayOf(
        intArrayOf(-20, -15),
        intArrayOf(-14, -5),
        intArrayOf(-18, -13),
        intArrayOf(-5, -3)
    )
    val answer = KSpeedCamera().solution(routes)
    print(answer)
}