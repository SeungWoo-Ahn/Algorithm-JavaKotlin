package programmers.Practice.Level2

class FindCollisionHazards {
    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        val history = Array(101) { Array(101) {  hashMapOf<Int, Int>() } }
        for (route in routes) {
            var time = 0
            for (i in 1 until route.size) {
                var (cx, cy) = points[route[i - 1] - 1]
                val (ex, ey) = points[route[i] - 1]
                while ((cx == ex && cy == ey).not()) {
                    history[cx][cy][time] = (history[cx][cy][time] ?: 0) + 1
                    if (cx != ex) {
                        if (cx > ex) cx--
                        else cx++
                    } else {
                        if (cy > ey) cy--
                        else cy++
                    }
                }
                time++
            }
            val (fx, fy) = points[route.last() - 1]
            history[fx][fy][time] = (history[fx][fy][time] ?: 0) + 1
        }
        var collistions = 0
        for (x in 1..100) {
            for (y in 1..100) {
                collistions += history[x][y].values.count { it > 1 }
            }
        }
        return collistions
    }
}

fun main() {
    val points = arrayOf(
        intArrayOf(3, 2),
        intArrayOf(6, 4),
        intArrayOf(4, 7),
        intArrayOf(1, 4)
    )
    val routes = arrayOf(
        intArrayOf(4, 2),
        intArrayOf(1, 3),
        intArrayOf(4, 2),
        intArrayOf(4, 3)
    )
    val answer = FindCollisionHazards().solution(points, routes)
    print(answer)
}