package programmers.BruthForce

class Carpet {
    private fun check(n: Int, m: Int, yellow: Int): Boolean {
        return (n - 2) * (m - 2) == yellow
    }

    fun solution(brown: Int, yellow: Int): IntArray {
        val total = brown + yellow
        for (i in 1 .. total) {
            if (i * i > total) break
            if (total % i == 0) {
                if (check(total / i, i, yellow)) {
                    return intArrayOf(total / i, i)
                }
            }
        }
        return intArrayOf(0, 0)
    }
}

fun main() {
    println(Carpet().solution(24, 24).joinToString())
}