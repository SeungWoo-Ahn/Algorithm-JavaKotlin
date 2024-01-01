package programmers.Practice.Level1

class GreatestCommons {
    fun solution(n: Int, m: Int): IntArray {
        val nDividers = arrayListOf<Int>()
        val mDividers = arrayListOf<Int>()

        for(i in 1..n) {
            if(n % i == 0) nDividers.add(i)
        }
        for(i in 1..m) {
            if(m % i == 0) mDividers.add(i)
        }

        val gcd = nDividers.filter { it -> mDividers.contains(it) }.maxOf { it }
        val lcm = n * m / gcd

        return intArrayOf(gcd, lcm)
    }
}

fun main() {
    println(GreatestCommons().solution(3, 12).contentToString())
}