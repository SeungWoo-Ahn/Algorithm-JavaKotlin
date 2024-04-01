package boj.simulation

import java.util.HashMap
import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max

class `17140` {
    private var a: Array<IntArray> = arrayOf()
    private val map = HashMap<Int, Int>()
    private val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 ->
        if (o1.second != o2.second) o1.second - o2.second
        else o1.first - o2.first
    }

    private fun rCalc() {
        var maxSize = 0
        val arr: Array<List<Pair<Int, Int>>?> = Array(a.size) { null }
        for (col in a.indices) {
            map.clear()
            for (row in a[0].indices) {
                if (a[col][row] == 0) continue
                map[a[col][row]] = map.getOrDefault(a[col][row], 0) + 1
            }
            maxSize = max(maxSize, map.size * 2)
            arr[col] = map.toList()
        }
        val newA = Array(a.size) { IntArray(maxSize) }
        arr.forEachIndexed { col, list ->
            pq.clear()
            for (pair in list!!) {
                pq.add(pair)
            }
            for (i in 0 until maxSize step 2) {
                if (i == 100) break
                if (pq.isNotEmpty()) {
                    val pair = pq.poll()
                    newA[col][i] = pair.first
                    newA[col][i + 1] = pair.second
                } else {
                    newA[col][i] = 0.also { newA[col][i + 1] = 0 }
                }
            }
        }
        a = newA
    }

    private fun cCalc() {
        var maxSize = 0
        val arr: Array<List<Pair<Int, Int>>?> = Array(a[0].size) { null }
        for (row in a[0].indices) {
            map.clear()
            for (col in a.indices) {
                if (a[col][row] == 0) continue
                map[a[col][row]] = map.getOrDefault(a[col][row], 0) + 1
            }
            maxSize = max(maxSize, map.size * 2)
            arr[row] = map.toList()
        }
        val newA = Array(maxSize) { IntArray(a[0].size) }
        arr.forEachIndexed { row, list ->
            pq.clear()
            for (pair in list!!) {
                pq.add(pair)
            }
            for (i in 0 until maxSize step 2) {
                if (i == 100) break
                if (pq.isNotEmpty()) {
                    val pair = pq.poll()
                    newA[i][row] = pair.first
                    newA[i + 1][row] = pair.second
                } else {
                    newA[i][row] = 0.also { newA[i + 1][row] = 0 }
                }
            }
        }
        a = newA
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (r, c, k) = readLine().split(" ").map { it.toInt() }
        a = Array(3) {
            val st = StringTokenizer(readLine())
            IntArray(3) { st.nextToken().toInt() }
        }
        var time = 0
        while (time <= 100) {
            if (a.size >= r && a[0].size >= c && a[r - 1][c - 1] == k) {
                println(time)
                return
            }
            if (a.size >= a[0].size) rCalc()
            else cCalc()
            time++
        }
        println(-1)
    }
}

fun main() {
    `17140`().solution()
}