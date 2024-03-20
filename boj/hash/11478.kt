package boj.hash

class `11478` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val set = HashSet<String>()
        for (start in s.indices) {
            for (end in start + 1 .. s.length) {
                set.add(s.substring(start, end))
            }
        }
        println(set.size)
    }
}

fun main() {
    `11478`().solution()
}