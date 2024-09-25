package programmers.Practice.Level2

import java.util.LinkedList
import java.util.Queue

class ConvertNumber {
    private data class Node(val num: Int, val cnt: Int)

    private sealed interface Command {
        fun proceed(num: Int): Int

        data class PlusN(private val n: Int): Command {
            override fun proceed(num: Int): Int {
                return num + n
            }
        }

        data object MultiplyTwo : Command {
            override fun proceed(num: Int): Int {
                return num * 2
            }
        }

        data object MultiplyThree : Command {
            override fun proceed(num: Int): Int {
                return num * 3
            }
        }
    }

    fun solution(x: Int, y: Int, n: Int): Int {
        val q = LinkedList<Node>() as Queue<Node>
        val visited = BooleanArray(y + 1)
        val commands = listOf(Command.PlusN(n), Command.MultiplyTwo, Command.MultiplyThree)
        q += Node(x, 0)
        visited[x] = true
        while (q.isNotEmpty()) {
            val (num, cnt) = q.poll()
            if (num == y) {
                return cnt
            }
            for (command in commands) {
                val nextNum = command.proceed(num)
                if (nextNum > y || visited[nextNum]) continue
                visited[nextNum] = true
                q += Node(nextNum, cnt + 1)
            }
        }
        return -1
    }
}

fun main() {
    val x = 10
    val y = 40
    val n = 5
    val answer = ConvertNumber().solution(x, y, n)
    print(answer)
}