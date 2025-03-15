package boj.greedy

class `1439` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val cnt = IntArray(2)
        for (i in 1 until s.length) {
            if (s[i - 1] != s[i]) {
                cnt[s[i - 1] - '0']++
            }
        }
        cnt[s.last() - '0']++
        print(cnt.min())
    }
}

fun main() {
    `1439`().solution()
}