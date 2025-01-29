package boj.string

class `23304` {
    private fun recur(s: String): Boolean {
        if (s.length == 1) {
            return true
        }
        val half = s.length shr 1
        val prefix = s.substring(0, half)
        val suffix = s.substring(s.length - half, s.length)
        return if (prefix == suffix) {
            recur(prefix)
        } else {
            false
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val isPalindrome = recur(s)
        if (isPalindrome) {
            print("AKARAKA")
        } else {
            print("IPSELENTI")
        }
    }
}

fun main() {
    `23304`().solution()
}