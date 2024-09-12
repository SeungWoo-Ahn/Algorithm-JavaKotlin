package programmers.Practice.Level2

class Tuple {
    fun solution(s: String): IntArray {
        val tuples = Array(501) { listOf<Int>() }
        val innerS = s.substring(1, s.length - 1)
        var st = 0
        var en = 0
        var cnt = 0
        for (i in innerS.indices) {
            when (innerS[i]) {
                '{' -> {
                    st = i + 1
                }
                '}' -> {
                    en = i - 1
                    val tuple = innerS.toTuple(st..en)
                    tuples[tuple.size] = tuple
                    cnt++
                }
            }
        }
        val result = IntArray(cnt)
        val exist = BooleanArray(100_001)
        for (size in 1..cnt) {
            for (num in tuples[size]) {
                if (!exist[num]) {
                    exist[num] = true
                    result[size - 1] = num
                    break
                }
            }
        }
        return result
    }

    private fun String.toTuple(range: IntRange)
            = substring(range).split(",").map { it.toInt() }
}

fun main() {
    val s = "{{4,2,3},{3},{2,3,4,1},{2,3}}"
    val answer = Tuple().solution(s)
    print(answer.joinToString())
}