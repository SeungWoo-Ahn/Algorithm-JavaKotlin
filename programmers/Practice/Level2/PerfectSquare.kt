package programmers.Practice.Level2

class PerfectSquare {
    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) {
            return a
        }
        return gcd(b, a % b)
    }

    fun solution(w: Int, h: Int): Long {
        val total = w.toLong() * h.toLong()
        val gcd = gcd(maxOf(w, h), minOf(w, h))
        val wBase = w / gcd
        val hBase = h / gcd
        val cutCnt = wBase + hBase - 1
        return total - cutCnt * gcd
    }
}

fun main() {
    val w = 8
    val h = 12
    val answer = PerfectSquare().solution(w, h)
    print(answer)
}