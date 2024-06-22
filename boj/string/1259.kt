package boj.string

class `1259` {
    private fun check(text: String): String {
        val len = text.length
        for (i in 0 until len / 2) {
            if (text[i] != text[len - i - 1])
                return "no"
        }
        return "yes"
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()
        while (true) {
            val text = readLine()
            if (text == "0") break
            sb.appendLine(check(text))
        }
        print(sb)
    }
}

fun main() {
    `1259`().solution()
}