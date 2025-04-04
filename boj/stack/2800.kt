package boj.stack

class `2800` {
    private data class Brace(val open: Int, val close: Int)

    private fun getBraceList(s: String): List<Brace> {
        val braceList = mutableListOf<Brace>()
        val stack = ArrayDeque<Brace>()
        for (idx in s.indices) {
            when (s[idx]) {
                '(' -> {
                    stack.addLast(Brace(idx, 0))
                }
                ')' -> {
                    val brace = stack.removeLast()
                    braceList += brace.copy(close = idx)
                }
            }
        }
        return braceList
    }

    private fun String.getResult(bit: Int, braceList: List<Brace>): String {
        val except = BooleanArray(length)
        var cur = bit
        var idx = 0
        while (cur > 0) {
            if (cur and 1 == 1) {
                except[braceList[idx].open] = true
                except[braceList[idx].close] = true
            }
            cur = cur shr 1
            idx++
        }
        val sb = StringBuilder()
        for (i in indices) {
            if (except[i].not()) {
                sb.append(this[i])
            }
        }
        return sb.toString()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val braceList = getBraceList(s)
        val resultSet = mutableSetOf<String>()
        for (bit in 1 until (1 shl braceList.size)) {
            resultSet += s.getResult(bit, braceList)
        }
        val sb = StringBuilder()
        for (result in resultSet.sorted()) {
            sb.appendLine(result)
        }
        print(sb)
    }
}

fun main() {
    `2800`().solution()
}