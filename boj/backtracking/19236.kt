package boj.backtracking

import java.util.StringTokenizer

class `19236` {
    private data class Node(val x: Int, val y: Int)
    private data class Fish(val pos: Node, var dir: Int, val num: Int = 0)

    private val d = listOf(
        Node(-1, 0),
        Node(-1, -1),
        Node(0, -1),
        Node(1, -1),
        Node(1, 0),
        Node(1, 1),
        Node(0, 1),
        Node(-1, 1),
    )
    private var answer = 0

    private fun backtracking(map: Array<IntArray>, fish: Array<Fish?>, shark: Fish, sum: Int) {
        val newMap = Array(4) { map[it].copyOf() }
        val movedFish = moveFish(newMap, fish, shark)
        var sharkMoved = false
        for (i in 1 until 4) {
            val nx = shark.pos.x + d[shark.dir].x * i
            val ny = shark.pos.y + d[shark.dir].y * i
            if (outOfBoundary(nx, ny) || newMap[nx][ny] == EMPTY) continue
            if (!sharkMoved) sharkMoved = true
            val fishNum = newMap[nx][ny]
            val eatenFish = movedFish[fishNum]!!.copy()
            val movedShark = Fish(Node(nx, ny), eatenFish.dir)
            newMap[nx][ny] = EMPTY
            movedFish[fishNum] = null
            backtracking(newMap, movedFish, movedShark, sum + fishNum)
            newMap[nx][ny] = fishNum
            movedFish[fishNum] = eatenFish
        }
        if (!sharkMoved) {
            if (sum > answer) {
                answer = sum
            }
            return
        }
    }

    private fun moveFish(map: Array<IntArray>, originFish: Array<Fish?>, shark: Fish): Array<Fish?> {
        val movedFish = Array(17) { originFish[it]?.copy() }
        for (num in 1 .. 16) {
            val fish = movedFish[num] ?: continue
            val dir = fish.dir
            for (i in d.indices) {
                val nx = fish.pos.x + d[(dir + i) % d.size].x
                val ny = fish.pos.y + d[(dir + i) % d.size].y
                if (outOfBoundary(nx, ny) || (shark.pos.x == nx && shark.pos.y == ny)) continue
                val targetFish = movedFish[map[nx][ny]]
                fish.dir = (dir + i) % d.size
                if (targetFish != null) swapFish(map, movedFish, fish, targetFish)
                else moveToEmpty(map, movedFish, fish, nx, ny)
                break
            }
        }
        return movedFish
    }

    private fun swapFish(map: Array<IntArray>, fish: Array<Fish?>, fromFish: Fish, toFish: Fish) {
        map[fromFish.pos.x][fromFish.pos.y] = toFish.num
        map[toFish.pos.x][toFish.pos.y] = fromFish.num
        fish[fromFish.num] = fromFish.copy(pos = toFish.pos)
        fish[toFish.num] = toFish.copy(pos = fromFish.pos)
    }

    private fun moveToEmpty(map: Array<IntArray>, fish: Array<Fish?>, fromFish: Fish, x: Int, y: Int) {
        map[fromFish.pos.x][fromFish.pos.y] = EMPTY
        map[x][y] = fromFish.num
        fish[fromFish.num] = fromFish.copy(pos = Node(x, y))
    }

    private fun outOfBoundary(x: Int, y: Int): Boolean {
        return x !in 0 until 4 || y !in 0 until 4
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val map = Array(4) { IntArray(4) }
        val fish: Array<Fish?> = Array(17) { null }
        val shark = Fish(Node(0, 0), 0)
        repeat(4) { x ->
            val st = StringTokenizer(readLine())
            repeat(4) { y ->
                val a = st.nextToken().toInt()
                val b = st.nextToken().toInt()
                if (x == 0 && y == 0) {
                    map[x][y] = EMPTY
                    shark.dir = b - 1
                    answer = a
                } else {
                    map[x][y] = a
                    fish[a] = Fish(Node(x, y), b - 1, a)
                }
            }
        }
        backtracking(map, fish, shark, answer)
        println(answer)
    }

    companion object {
        const val EMPTY = 0
    }
}

fun main() {
    `19236`().solution()
}