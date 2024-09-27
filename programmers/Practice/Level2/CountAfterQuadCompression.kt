package programmers.Practice.Level2

class CountAfterQuadCompression {
    private class QuadCompression(private val arr: Array<IntArray>) {
        private val cnt = IntArray(2)
        private val dx = intArrayOf(0, 0, 1, 1)
        private val dy = intArrayOf(0, 1, 0, 1)

        fun execute(): IntArray {
            compress(arr.size, 0, 0)
            return cnt
        }

        private fun compress(size: Int, sx: Int, sy: Int) {
            if (size == 1 || isCompressible(size, sx, sy)) {
                cnt[arr[sx][sy]]++
                return
            }
            val halfSize = size shr 1
            for (i in 0 until 4) {
                val nx = sx + dx[i] * halfSize
                val ny = sy + dy[i] * halfSize
                compress(halfSize, nx, ny)
            }
        }

        private fun isCompressible(size: Int, sx: Int, sy: Int): Boolean {
            val target = arr[sx][sy]
            for (x in sx until sx + size) {
                for (y in sy until sy + size) {
                    if (arr[x][y] != target) {
                        return false
                    }
                }
            }
            return true
        }
    }

    fun solution(arr: Array<IntArray>): IntArray {
        return QuadCompression(arr).execute()
    }
}

fun main() {
    val arr = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
        intArrayOf(0, 1, 0, 0, 1, 1, 1, 1),
        intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 1, 1, 1, 1)
    )
    val answer = CountAfterQuadCompression().solution(arr)
    print(answer.joinToString())
}