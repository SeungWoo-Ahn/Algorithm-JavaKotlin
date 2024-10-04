package programmers.Practice.Level2

class SplitNumberCard {
    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) {
            return a
        }
        return gcd(b, a % b)
    }

    private fun IntArray.gcd(): Int {
        var result = this[0]
        for (i in 1 until size) {
            result = if (result > this[i]) {
                gcd(result, this[i])
            } else {
                gcd(this[i], result)
            }
            if (result == 1) break
        }
        return result
    }

    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        val gcdA = arrayA.gcd()
        val gcdB = arrayB.gcd()
        if (gcdA > gcdB) {
            if (gcdA > 1 && arrayB.all { it % gcdA != 0 }) {
                return gcdA
            }
        } else {
            if (gcdB > 1 && arrayA.all { it % gcdB != 0 }) {
                return gcdB
            }
        }
        return 0
    }
}

fun main() {
    val arrayA = intArrayOf(14, 35, 119)
    val arrayB = intArrayOf(18, 30, 102)
    val answer = SplitNumberCard().solution(arrayA, arrayB)
    print(answer)
}