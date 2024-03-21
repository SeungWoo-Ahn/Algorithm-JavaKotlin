package boj.hash

class `19583` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (s, e, q) = readLine().split(" ")
        val enterSet = hashSetOf<String>()
        val exitSet = hashSetOf<String>()
        var line: String?
        while (true) {
            line = readLine()
            if (line.isNullOrEmpty()) break
            val (time, name) = line.split(" ")
            if (time <= s) {
                enterSet.add(name)
            } else if (time in e .. q) {
                exitSet.add(name)
            }
        }
        var answer = 0
        enterSet.forEach {
            if (exitSet.contains(it)) answer++
        }
        println(answer)
    }
}

fun main() {
    `19583`().solution()
}