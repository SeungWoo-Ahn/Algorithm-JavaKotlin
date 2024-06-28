package boj.bfsdfs

import java.util.LinkedList
import java.util.Queue

class `9019` {
    private data class Calculator(val register: Int, val history: String)

    private val commands = listOf('D', 'S', 'L', 'R')

    private fun bfs(a: Int, b: Int): String {
        val q = LinkedList<Calculator>() as Queue<Calculator>
        val visited = BooleanArray(10_000)
        q += Calculator(a, "")
        visited[a] = true
        while (q.isNotEmpty()) {
            val (register, history) = q.poll()
            if (register == b) return history
            for (command in commands) {
                val newRegister = when (command) {
                    'D' -> (register * 2) % 10_000
                    'S' -> if (register > 0) register - 1 else 9999
                    'L' -> (register % 1_000) * 10 + (register / 1_000)
                    'R' -> (register % 10) * 1_000 + (register / 10)
                    else -> throw IllegalArgumentException()
                }
                if (visited[newRegister]) continue
                visited[newRegister] = true
                q += Calculator(newRegister, history + command)
            }
        }
        return ""
    }



    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val sb = StringBuilder()
        repeat(n) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            sb.appendLine(bfs(a, b))
        }
        print(sb)
    }
}

fun main() {
    `9019`().solution()
}