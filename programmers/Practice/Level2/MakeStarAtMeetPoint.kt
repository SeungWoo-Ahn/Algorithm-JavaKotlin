package programmers.Practice.Level2

class MakeStarAtMeetPoint {
    private val xRange = intArrayOf(MIN_POS, MAX_POS) // [x 최대값, x 최소값]
    private val yRange = intArrayOf(MIN_POS, MAX_POS) // [y 최대값, y 최소값]
    private val points = hashMapOf<Int, MutableList<Int>>() // <y 좌표, x 좌표 리스트>

    private fun getMeetPoint(equation1: IntArray, equation2: IntArray): Pair<Int, Int>? {
        val (a, b, e) = equation1.map { it.toLong() }
        val (c, d, f) = equation2.map { it.toLong() }
        val parent = a * d - b * c
        if (parent == 0L) {
            return null
        }
        val xChild = b * f - e * d
        val yChild = e * c - a * f
        if (xChild % parent != 0L || yChild % parent != 0L) {
            return null
        }
        return (xChild / parent).toInt() to (yChild / parent).toInt()
    }

    private fun addMeetPoints(line: Array<IntArray>) {
        for (i in 0 until line.size - 1) {
            for (j in i + 1 until line.size) {
                val (x, y) = getMeetPoint(line[i], line[j]) ?: continue
                xRange[0] = maxOf(xRange[0], x)
                xRange[1] = minOf(xRange[1], x)
                yRange[0] = maxOf(yRange[0], y)
                yRange[1] = minOf(yRange[1], y)
                points[y] = points
                    .getOrDefault(y, mutableListOf())
                    .also { it.add(x) }
            }
        }
    }

    private fun makeMap(): Array<String> {
        val width = xRange[0] - xRange[1] + 1
        val height = yRange[0] - yRange[1] + 1
        return Array(height) { idx ->
            val line = CharArray(width) { BLANK }
            val y = yRange[0] - idx
            points[y]?.let { xList ->
                xList.forEach { x ->
                    line[x - xRange[1]] = STAR
                }
            }
            line.joinToString("")
        }
    }

    fun solution(line: Array<IntArray>): Array<String> {
        addMeetPoints(line)
        return makeMap()
    }

    companion object {
        private const val MAX_POS = Int.MAX_VALUE
        private const val MIN_POS = Int.MIN_VALUE
        private const val BLANK = '.'
        private const val STAR = '*'
    }
}

fun main() {
    val line = arrayOf(
        intArrayOf(2, -1, 4),
        intArrayOf(-2, -1, 4),
        intArrayOf(0, -1, 1),
        intArrayOf(5, -8, -12),
        intArrayOf(5, 8, 12)
    )
    val answer = MakeStarAtMeetPoint().solution(line)
    print(answer.joinToString("\n"))
}