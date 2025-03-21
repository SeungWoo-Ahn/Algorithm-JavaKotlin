package programmers.Practice.Level3

class SealedOrder {
    private fun String.toNum(): Long {
        var num = 0L
        for (ch in this) {
            val chNum = ch - 'a' + 1
            num = num * 26 + chNum
        }
        return num
    }

    private fun Long.toAlpha(): String {
        val sb = StringBuilder()
        var num = this
        while (num-- > 0) {
            val ch = 'a' + (num % 26).toInt()
            sb.append(ch)
            num /= 26
        }
        return sb.reverse().toString()
    }

    fun solution(n: Long, bans: Array<String>): String {
        val nums = bans.map { it.toNum() }.sorted()
        var target = n
        for (num in nums) {
            if (target >= num) {
                target++
            } else {
                break
            }
        }
        return target.toAlpha()
    }
}

fun main() {
    val n = 7388L
    val bans = arrayOf("gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc")
    val answer = SealedOrder().solution(n, bans)
    print(answer)
}