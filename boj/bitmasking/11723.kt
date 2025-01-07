package boj.bitmasking

class `11723` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val m = readLine().toInt()
        val sb = StringBuilder()
        var state = 0
        repeat(m) {
            val command = readLine().split(" ")
            when (command.first()) {
                "add" -> {
                    val x = command[1].toInt()
                    state = state or (1 shl (x - 1))
                }
                "remove" -> {
                    val x = command[1].toInt()
                    state = state and (1 shl (x - 1)).inv()
                }
                "check" -> {
                    val x = command[1].toInt()
                    sb.appendLine((state shr (x - 1)) and 1)
                }
                "toggle" -> {
                    val x = command[1].toInt()
                    state = state xor (1 shl  (x - 1))
                }
                "all" -> {
                    state = -1
                }
                "empty" -> {
                    state = 0
                }
            }
        }
        print(sb)
    }
}

fun main() {
    `11723`().solution()
}