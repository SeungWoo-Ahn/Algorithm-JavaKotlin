package programmers.Practice.Level3

class Move110 {
    private fun move110(s: String): String {
        val sb = StringBuilder()
        var oneCnt = 0
        var unitCnt = 0
        for (ch in s) {
            if (ch == '1') {
                oneCnt++
                continue
            }
            if (oneCnt >= 2) {
                unitCnt++
                oneCnt -= 2
            } else {
                repeat(oneCnt) { sb.append('1') }
                sb.append('0')
                oneCnt = 0
            }
        }
        repeat(unitCnt) { sb.append("110") }
        repeat(oneCnt) { sb.append('1') }
        return sb.toString()
    }

    fun solution(s: Array<String>): Array<String> {
        return Array(s.size) { move110(s[it]) }
    }
}

fun main() {
    val s = arrayOf("1110","100111100","0111111010")
    val answer = Move110().solution(s)
    print(answer.joinToString())
}