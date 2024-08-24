package programmers.Practice.Level3

import java.util.Stack

class EditTable {
    private data class Row(val prev: Int, val cur: Int, val nxt: Int)

    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val result = CharArray(n) { 'O' }
        val prev = IntArray(n)
        val nxt = IntArray(n)
        var cursor = k
        val removed = Stack<Row>()
        for (i in 0 until n) {
            prev[i] = i - 1
            nxt[i] = i + 1
        }
        nxt[n - 1] = -1
        for (c in cmd) {
            val o = c.split(" ")
            when (o[0]) {
                "U" -> {
                    val x = o[1].toInt()
                    repeat(x) {
                        cursor = prev[cursor]
                    }
                }
                "D" -> {
                    val x = o[1].toInt()
                    repeat(x) {
                        cursor = nxt[cursor]
                    }
                }
                "C" -> {
                    val front = prev[cursor]
                    val back = nxt[cursor]
                    val row = Row(front, cursor, back)
                    removed.push(row)
                    result[cursor] = 'X'
                    if (back < 0) {
                        nxt[front] = back
                        cursor = prev[cursor]
                    } else {
                        if (front >= 0) {
                            nxt[front] = back
                        }
                        prev[back] = front
                        cursor = nxt[cursor]
                    }
                }
                "Z" -> {
                    val (front, cur, back) = removed.pop()
                    result[cur] = 'O'
                    if (front >= 0) {
                        nxt[front] = cur
                    }
                    if (back >= 0) {
                        prev[back] = cur
                    }
                    prev[cur] = front
                    nxt[cur] = back
                }
            }
        }
        return result.joinToString("")
    }
}

fun main() {
    val n = 8
    val k = 2
    val cmd = arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C")
    val answer = EditTable().solution(n, k, cmd)
    print(answer)
}