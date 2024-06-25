package boj.string

class `15829` {
    private fun Char.toNum() = this - 'a' + 1

    fun solution() = with(System.`in`.bufferedReader()) {
        val l = readLine().toInt()
        val input = readLine()
        var hash = 0L
        var r = 1L
        for (alpha in input) {
            hash = (hash + alpha.toNum() * r) % M
            r = (r * R) % M
        }
        print(hash)
    }

    companion object {
        private const val R = 31L
        private const val M = 1_234_567_891
    }
}

fun main() {
    `15829`().solution()
}