package boj.Implement

import java.util.PriorityQueue
import java.util.StringTokenizer

class `21608` {
    private data class Node(val x: Int, val y: Int)
    private data class Seat(val pos: Node, val adjLikeCnt: Int, val adjEmptyCnt: Int)
    private data class Student(val num: Int, val likeNums: List<Int>)

    private var seats: Array<Array<Student?>> = arrayOf()
    private val d = listOf(Node(1, 0), Node(-1, 0), Node(0, 1), Node(0, -1))
    private val seatQ = PriorityQueue<Seat>() { o1, o2 ->
        if (o1.adjLikeCnt != o2.adjLikeCnt) o2.adjLikeCnt - o1.adjLikeCnt
        else if (o1.adjEmptyCnt != o2.adjEmptyCnt) o2.adjEmptyCnt - o1.adjEmptyCnt
        else if (o1.pos.x != o2.pos.x) o1.pos.x - o2.pos.x
        else o1.pos.y - o2.pos.y
    }
    private val satisfyScore = intArrayOf(0, 1, 10, 100, 1000)

    private fun findSeat(student: Student) {
        seatQ.clear()
        for (x in seats.indices) {
            for (y in seats.indices) {
                if (seats[x][y] == null) {
                    seatQ.add(checkAdjSeat(x, y, student))
                }
            }
        }
        val (x, y) = seatQ.poll().pos
        seats[x][y] = student
    }

    private fun checkAdjSeat(x: Int, y: Int, student: Student): Seat {
        var likeCnt = 0
        var emptyCnt = 0
        for (i in d.indices) {
            val nx = x + d[i].x
            val ny = y + d[i].y
            if (nx !in seats.indices || ny !in seats.indices) continue
            if (seats[nx][ny] == null) emptyCnt++
            else if(student.likeNums.contains(seats[nx][ny]!!.num)) likeCnt++
        }
        return Seat(Node(x, y), likeCnt, emptyCnt)
    }

    private fun calcSatisfySum(): Int {
        var sum = 0
        for (x in seats.indices) {
            for (y in seats.indices) {
                sum += calcSatisfy(x, y)
            }
        }
        return sum
    }

    private fun calcSatisfy(x: Int, y: Int): Int {
        val student = seats[x][y]!!
        var likeCnt = 0
        for (i in d.indices) {
            val nx = x + d[i].x
            val ny = y + d[i].y
            if (nx !in seats.indices || ny !in seats.indices) continue
            if (student.likeNums.contains(seats[nx][ny]!!.num)) likeCnt++
        }
        return satisfyScore[likeCnt]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        seats = Array(n) { Array(n) { null } }
        repeat(n * n) {
            val st = StringTokenizer(readLine())
            val num = st.nextToken().toInt()
            val likeNums = List(4) { st.nextToken().toInt() }
            findSeat(Student(num, likeNums))
        }
        println(calcSatisfySum())
    }
}

fun main() {
    `21608`().solution()
}
