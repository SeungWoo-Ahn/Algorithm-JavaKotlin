package programmers.Practice.Level2

class TriangularSnail {
    private class Triangle(private val n: Int) {
        private val data = Array(n) { row -> IntArray(row + 1) }

        init {
            fillData()
        }

        private fun fillData() {
            val dx = intArrayOf(1, 0, -1)
            val dy = intArrayOf(0, 1, -1)
            val endNum = getSize()
            var curNum = 1
            var curX = 0
            var curY = 0
            var dir = 0
            var len = n
            var curLen = len
            while (curNum <= endNum) {
                data[curX][curY] = curNum++
                if (--curLen == 0) {
                    curLen = --len
                    dir = (dir + 1) % 3
                }
                curX += dx[dir]
                curY += dy[dir]
            }
        }

        private fun getSize(): Int = n * (n + 1) / 2

        fun getSequence(): IntArray {
            val sequence = IntArray(getSize())
            var idx = 0
            for (x in data.indices) {
                for (y in data[x].indices) {
                    sequence[idx++] = data[x][y]
                }
            }
            return sequence
        }
    }

    fun solution(n: Int): IntArray {
        return Triangle(n).getSequence()
    }
}

fun main() {
    val n = 6
    val answer = TriangularSnail().solution(n)
    print(answer.joinToString())
}