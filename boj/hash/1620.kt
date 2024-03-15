package boj.hash

class `1620` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val arr = Array(n + 1) { "" }
        val map = HashMap<String, Int>()
        repeat(n) { idx ->
            val name = readLine()
            arr[idx + 1] = name
            map[name] = idx + 1
        }
        val sb = StringBuilder()
        repeat(m) {
            val input = readLine()
            if (input.toIntOrNull() != null) {
                sb.append("${arr[input.toInt()]}\n")
            } else {
                sb.append("${map[input]}\n")
            }
        }
        println(sb)
    }
}

fun main() {
    `1620`().solution()
}