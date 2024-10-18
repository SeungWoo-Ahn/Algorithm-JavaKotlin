package programmers.Practice.Level2

class CompressString {
    private fun compress(unit: Int, s: String): Int {
        var len = 0
        var st = 0
        while (st < s.length) {
            val target = s.substring(st, minOf(st + unit, s.length))
            var en = minOf(st + unit, s.length)
            while (en < s.length && s.substring(en, minOf(en + unit, s.length)) == target) {
                en = minOf(en + unit, s.length)
            }
            val cnt = (en - st) / unit
            val cntLen = if (cnt > 1) cnt.toString().length else 0
            len += cntLen + target.length
            st = en
        }
        return len
    }

    fun solution(s: String): Int {
        var result = s.length
        for (unit in 1..s.length / 2) {
            result = minOf(result, compress(unit, s))
        }
        return result
    }
}

fun main() {
    val s = "ababcdcdababcdcd"
    val answer = CompressString().solution(s)
    print(answer)
}