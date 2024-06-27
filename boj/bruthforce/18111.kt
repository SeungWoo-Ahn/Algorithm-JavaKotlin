package boj.bruthforce

import java.util.PriorityQueue
import java.util.StringTokenizer

class `18111` {
    private val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.first }, { -it.second }))
    private val map = hashMapOf<Int, Int>()
    private var inventory = 0
    private var time = 0

    private fun levelingLand(b: Int, targetHeight: Int) {
        inventory = b
        time = 0
        for ((height, cnt) in map) {
            if (height > targetHeight) takeBlocks(height - targetHeight, cnt)
            if (height < targetHeight) putBlocks(targetHeight - height, cnt)
        }
        if (inventory >= 0) pq += time to targetHeight
    }

    private fun putBlocks(heightDiff: Int, cnt: Int) {
        inventory -= heightDiff * cnt
        time += heightDiff * cnt
    }

    private fun takeBlocks(heightDiff: Int, cnt: Int) {
        inventory += heightDiff * cnt
        time += heightDiff * cnt * 2
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, b) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        var minHeight = 256
        var maxHeight = 0
        repeat(n) {
            st = StringTokenizer(readLine())
            repeat(m) {
                val height = st.nextToken().toInt()
                minHeight = minOf(minHeight, height)
                maxHeight = maxOf(maxHeight, height)
                map[height] = map.getOrDefault(height, 0) + 1
            }
        }
        (minHeight .. maxHeight).forEach { levelingLand(b, it) }
        val (time, height) = pq.poll()
        print("$time $height")
    }
}

fun main() {
    `18111`().solution()
}