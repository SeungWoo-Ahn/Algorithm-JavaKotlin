package boj.queue

class `18258` {
    private val q = ArrayDeque<Int>()
    private val sb = StringBuilder()

    private fun String.execute(): Int? {
        return when (split(" ")[0]) {
            "pop" -> {
                if (q.isEmpty()) -1
                else q.removeFirst()
            }
            "size" -> q.size
            "empty" -> {
                if (q.isEmpty()) 1
                else 0
            }
            "front" -> {
                if (q.isEmpty()) -1
                else q.first()
            }
            "back" -> {
                if (q.isEmpty()) -1
                else q.last()
            }
            else -> {
                q += split(" ")[1].toInt()
                null
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        repeat(n) {
            val result = readLine().execute()
            result?.let { sb.appendLine(it) }
        }
        print(sb)
    }
}

fun main() {
    `18258`().solution()
}