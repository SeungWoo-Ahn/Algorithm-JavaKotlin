package boj.simulation

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import java.util.StringTokenizer

class `19238` {
    private data class Node(var x: Int, var y: Int)
    private data class Passenger(val num: Int, val start: Node, val end: Node)
    private data class Taxi(val pos: Node, var fuel: Long = 0) {
        fun movePos(x: Int, y: Int, dis: Int = 0) {
            pos.x = x
            pos.y = y
            fuel -= dis
        }
        fun fuelFilling(dis: Int) {
            fuel += dis * 2
        }
    }

    private var map: Array<IntArray> = arrayOf()
    private var passengers: Array<Passenger?> = arrayOf()
    private val taxi = Taxi(Node(0, 0))
    private val d = listOf(Node(1, 0), Node(-1, 0), Node(0, 1), Node(0, -1))
    private var remain = 0

    private fun bfs(): Array<IntArray> {
        val disMap = Array(map.size) { map[it].copyOf() }
        val visited = Array(map.size) { BooleanArray(map[it].size) }
        val q = LinkedList<Node>() as Queue<Node>
        visited[taxi.pos.x][taxi.pos.y] = true
        q.offer(taxi.pos)
        while (q.isNotEmpty()) {
            val (x, y) = q.poll()
            for (i in d.indices) {
                val nx = x + d[i].x
                val ny = y + d[i].y
                if (nx !in map.indices || ny !in map[0].indices || visited[nx][ny]) continue
                if (map[nx][ny] == WALL) continue
                visited[nx][ny] = true
                disMap[nx][ny] = disMap[x][y] + 1
                q.offer(Node(nx, ny))
            }
        }
        return disMap
    }

    private fun findNearestPassengerInfo(): Triple<Int, Int, Node>? {
        val pq = PriorityQueue<Triple<Int, Int, Node>> { o1, o2 ->
            if (o1.second != o2.second) o1.second - o2.second
            else if (o1.third.x != o2.third.x) o1.third.x - o2.third.x
            else o1.third.y - o2.third.y
        }
        val disMap = bfs()
        for (passenger in passengers) {
            if (passenger == null) continue
            val dis = disMap[passenger.start.x][passenger.start.y]
            if (dis == 0 && !isSamePos(passenger.start)) continue
            pq.add(Triple(passenger.num, dis, passenger.start))
        }
        return if (pq.isEmpty()) null else pq.poll()
    }

    private fun moveTaxi(info: Triple<Int, Int, Node>): Boolean {
        if (taxi.fuel < info.second) return false
        taxi.movePos(info.third.x, info.third.y, info.second)

        val endPos = passengers[info.first]!!.end
        val dis = bfs()[endPos.x][endPos.y]
        if (dis == 0 && !isSamePos(endPos)) return false
        if (taxi.fuel < dis) return false
        taxi.movePos(endPos.x, endPos.y, dis)
        taxi.fuelFilling(dis)
        passengers[info.first] = null
        remain--
        return true
    }

    private fun isSamePos(node: Node): Boolean = node.x == taxi.pos.x && node.y == taxi.pos.y

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, initFuel) = splitToInt(readLine())
        map = Array(n) {
            val st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }

        val (startX, startY) = splitToInt(readLine(), offset = 1)
        taxi.movePos(startX, startY)
        taxi.fuel = initFuel.toLong()

        passengers = Array(m) { num ->
            val (sX, sY, eX, eY) = splitToInt(readLine(), offset = 1)
            Passenger(num, Node(sX, sY), Node(eX, eY))
        }
        remain = m

        while (remain > 0) {
            val nearestPassengerInfo = findNearestPassengerInfo()
            if (nearestPassengerInfo == null) {
                println(-1)
                return
            }
            val moveResult = moveTaxi(nearestPassengerInfo)
            if (!moveResult) {
                println(-1)
                return
            }
        }
        println(taxi.fuel)
    }

    private fun splitToInt(input: String, offset: Int = 0): List<Int> = input.split(" ").map { it.toInt() - offset }

    companion object {
        const val WALL = 1
    }
}

fun main() {
    `19238`().solution()
}