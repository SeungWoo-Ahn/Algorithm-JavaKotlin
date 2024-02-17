package boj.simulation

import java.util.Deque
import java.util.LinkedList
import java.util.StringTokenizer

class `3190` {
    data class Node(val x: Int, val y: Int)

    private var map: Array<BooleanArray> = arrayOf()
    private var shiftList = Array(10001) { "" }
    private var time = 0
    private val d = listOf(Node(0, 1), Node(1, 0), Node(0, -1), Node(-1, 0))

    private fun play(n: Int) {
        val snake: Deque<Node> = LinkedList()
        snake.add(Node(0, 0))
        var direction = 0
        loop@ while (true) {
            time++
            val nx = snake.peek().x + d[direction].x
            val ny = snake.peek().y + d[direction].y
            snake.addFirst(Node(nx, ny)) //몸 길이를 늘려 머리를 다음칸에 위치
            var size = snake.size
            while (size-- > 0) {
                val s = snake.poll()
                if (s.x !in 0 until n || s.y !in 0 until n) break@loop //벽에 부딪히면 게임 끝
                if (size != snake.size && s.x == nx && s.y == ny) break@loop //자기 자신에 부딪히면 게임 끝
                snake.add(s)
            }
            if (map[nx][ny]) {
                map[nx][ny] = false //사과가 있다면 없어짐
            } else {
                snake.pollLast() //사과가 없다면 꼬리 칸을 비움
            }
            when (shiftList[time]) {
                "L" -> direction = (direction + 3) % 4
                "D" -> direction = (direction + 1) % 4
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        map = Array(n) { BooleanArray(n) }
        val k = readLine().toInt()
        repeat(k) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            map[x - 1][y - 1] = true
        }
        val l = readLine().toInt()
        repeat(l) {
            val st = StringTokenizer(readLine())
            shiftList[st.nextToken().toInt()] = st.nextToken()
        }
        play(n)
        println(time)
    }
}

fun main() {
    `3190`().solution()
}