package programmers.Practice.Level2

class PrimeCntInKBase {
    private fun isPrime(num: Long): Boolean {
        if (num <= 1) return false
        for (i in 2..num) {
            if (i * i > num) break
            if (num % i == 0L) {
                return false
            }
        }
        return true
    }

    fun solution(n: Int, k: Int): Int {
        val numStr = n.toString(k)
        val arr = numStr.split("0").filter { it.isNotEmpty() }
        return arr.count { isPrime(it.toLong()) }
    }
}

fun main() {
    val n = 437674
    val k = 3
    val answer = PrimeCntInKBase().solution(n, k)
    print(answer)
}