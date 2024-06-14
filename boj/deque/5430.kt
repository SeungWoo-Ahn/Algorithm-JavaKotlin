package boj.deque

class `5430` {
    private val dq = ArrayDeque<Int>()

    private fun testCase(commands: String, arr: String): String {
        insertArr(arr)
        var reversed = false
        for (command in commands) {
            when (command) {
                'R' -> reversed = !reversed
                'D' -> {
                    if (dq.isEmpty()) return "error"
                    if (reversed) dq.removeLast()
                    else dq.removeFirst()
                }
            }
        }
        val result = if (reversed) dq.reversed().joinToString(",")
                    else dq.joinToString(",")
        return "[$result]"
    }

    private fun insertArr(arr: String) {
        dq.clear()
        val arrWithoutBracket = arr.substring(1, arr.length - 1)
        if (arrWithoutBracket.isNotEmpty())
            dq.addAll(arrWithoutBracket.split(",").map { it.toInt() })
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val p = readLine()
            val n = readLine().toInt()
            val arr = readLine()
            val result = testCase(p, arr)
            sb.appendLine(result)
        }
        print(sb)
    }
}

fun main() {
    `5430`().solution()
}