package boj.string

class `1543` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val doc = readLine()
        val target = readLine()
        var idx = doc.indexOf(target)
        var cnt = 0
        while (idx >= 0) {
            idx = doc.indexOf(target, idx + target.length)
            cnt++
        }
        print(cnt)
    }
}

fun main() {
    `1543`().solution()
}