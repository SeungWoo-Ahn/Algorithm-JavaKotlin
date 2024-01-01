package programmers.Practice.Level2

class LongJump {

    fun solution(n: Int): Long {
        if (n == 1) return 1
        if (n == 2) return 2
        val d = LongArray(n+1).apply {
            this[0] = 0L
            this[1] = 1L
            this[2] = 2L
        }
        for (i in 3..n) {
            d[i] = (d[i-1] + d[i-2]) % 1234567
        }
        return d[n]
    }
}

fun main() {
    println(LongJump().solution(4))
}

