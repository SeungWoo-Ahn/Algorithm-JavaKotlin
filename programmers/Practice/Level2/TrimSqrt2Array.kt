package programmers.Practice.Level2

class TrimSqrt2Array {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        val startCol = (left / n).toInt()
        val endCol = (right / n).toInt()
        if (startCol == endCol) return makeColArr(startCol, n, (left % n).toInt(), (right % n).toInt())
        var answer = intArrayOf()
        for (i in startCol..endCol) {
            answer += when (i) {
                startCol -> makeColArr(i, n, startIdx = (left % n).toInt())
                endCol -> makeColArr(i, n, endIdx = (right % n).toInt())
                else -> makeColArr(i, n)
            }
        }
        return answer
    }

    private fun makeColArr(colNum: Int, n: Int, startIdx: Int = 0, endIdx: Int = n - 1): IntArray {
        val first = colNum + 1
        val colArr = arrayListOf<Int>().apply {
            repeat(first) { add(first) }
            if (first < n) {
                for (i in first + 1..n) add(i)
            }
        }
        return colArr.slice(startIdx..endIdx).toIntArray()
    }
}

fun main() {
    println(TrimSqrt2Array().solution(4, 7, 14).contentToString())
}