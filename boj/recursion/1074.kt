package boj.recursion

class `1074` {

    private fun recursion(n: Int, r: Int, c: Int): Int {
        if (n == 0) return 0
        val half = 1 shl (n-1)
        if (r < half && c < half) return recursion(n - 1, r, c)
        if (half in (r + 1)..c) return half * half + recursion(n - 1, r, c - half)
        if (half in (c + 1)..r) return 2 * half * half + recursion(n - 1, r - half, c)
        return 3 * half * half + recursion(n - 1, r - half, c - half)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, r, c) = readLine().split(" ").map { it.toInt() }
        println(recursion(N, r, c))
    }
}

fun main() {
    `1074`().solution()
}