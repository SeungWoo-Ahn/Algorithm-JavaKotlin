package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class `13335` {
    data class Truck(val weight: Int, val index: Int)

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, w, L) = readLine().split(" ").map { it.toInt() }
        val st = StringTokenizer(readLine())
        val trucks: Queue<Truck> = LinkedList()
        repeat(n) { i -> trucks.offer(Truck(st.nextToken().toInt(), i)) }
        val bridge = arrayListOf<Truck>()
        val distance = IntArray(n)
        var totalWeight = 0
        var crossTruckIdx = -1
        var time = 0
        while (crossTruckIdx < n - 1) {
            if (trucks.isNotEmpty() && trucks.peek().weight + totalWeight <= L) {
                trucks.poll().let {
                    bridge.add(it)
                    totalWeight += it.weight
                }
            }
            var needOut = false
            for (t in bridge) {
                distance[t.index]++
                if (distance[t.index] == w) {
                    totalWeight -= bridge[0].weight
                    crossTruckIdx = t.index
                    needOut = true
                }
            }
            time++
            if (needOut) bridge.removeFirst()
        }
        println(time + 1)
    }
}

fun main() {
    `13335`().solution()
}