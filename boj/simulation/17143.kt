package boj.simulation

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

class `17143` {
    private data class Node(var x: Int, var y: Int)
    private data class Shark(val pos: Node, val speed: Int, var dir: Int, val size: Int)

    private var map: Array<IntArray> = arrayOf()
    private val sharks = LinkedList<Shark>() as Queue<Shark>
    private val d = listOf(
        Node(-1, 0),
        Node(1, 0),
        Node(0, 1),
        Node(0, -1)
    )
    private var answer = 0

    private fun fishing(y: Int) {
        for (x in map.indices) {
            if (map[x][y] != 0) {
                var cnt = sharks.size
                while (cnt-- > 0) {
                    val shark = sharks.poll()
                    if (shark.pos.x == x && shark.pos.y == y) {
                        answer += shark.size
                        break
                    } else {
                        sharks.offer(shark)
                    }
                }
                map[x][y] = 0
                return
            }
        }
    }

    private fun sharksMoving(R: Int, C: Int) {
        val cnt = sharks.size
        repeat(cnt) {
            val shark = sharks.poll()
            map[shark.pos.x][shark.pos.y]--
            var dir = shark.dir
            if (dir < 2) {
                var x = shark.pos.x
                repeat(shark.speed) {
                    x += d[dir].x
                    if (x < 0) {
                        x = 1
                        dir = 1
                    } else if (x > R - 1) {
                        x = R - 2
                        dir = 0
                    }
                }
                shark.pos.x = x
            } else {
                var y = shark.pos.y
                repeat(shark.speed) {
                    y += d[dir].y
                    if (y < 0) {
                        y = 1
                        dir = 2
                    } else if (y > C - 1) {
                        y = C - 2
                        dir = 3
                    }
                }
                shark.pos.y = y
            }
            shark.dir = dir
            sharks.offer(shark)
            map[shark.pos.x][shark.pos.y]++
        }
    }

    private fun checkSamePosSharks() {
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] > 1) eat(x, y)
            }
        }
    }

    private fun eat(x: Int, y: Int) {
        val pq = PriorityQueue<Shark> { o1, o2 -> o2.size - o1.size }
        val cnt = sharks.size
        repeat(cnt) {
            val shark = sharks.poll()
            if (shark.pos.x == x && shark.pos.y == y) {
                pq.add(shark)
            } else {
                sharks.offer(shark)
            }
        }
        sharks.offer(pq.poll())
        map[x][y] = 1
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (R, C, M) = readLine().split(" ").map { it.toInt() }
        if (M == 0) {
            println(0)
            return
        }
        map = Array(R) { IntArray(C) }
        repeat(M) {
            val (r, c, s, d, z) = readLine().split(" ").map { it.toInt() }
            map[r - 1][c - 1]++
            sharks.offer(
                Shark(
                    pos = Node(r - 1, c - 1),
                    speed = s,
                    dir = d - 1,
                    size = z
                )
            )
        }
        for (king in 0 until C) {
            fishing(king)
            sharksMoving(R, C)
            checkSamePosSharks()
        }
        println(answer)
    }
}

fun main() {
    `17143`().solution()
}