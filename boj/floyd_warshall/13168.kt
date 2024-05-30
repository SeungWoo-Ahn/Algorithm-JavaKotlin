package boj.floyd_warshall

import java.util.StringTokenizer

class `13168` {
    private var map: Array<IntArray> = arrayOf()
    private var naeilloMap: Array<IntArray> = arrayOf()
    private var tripOrder = intArrayOf()
    private val cityMap = HashMap<String, Int>()

    private fun initMap(size: Int): Array<IntArray> {
        return Array(size) { x ->
            IntArray(size) { y -> if (x != y) INF else 0 }
        }
    }

    private fun getNaeilloCost(type: String, cost: Int): Int {
        return when (type) {
            "Mugunghwa",
            "ITX-Saemaeul",
            "ITX-Cheongchun" -> 0
            "S-Train",
            "V-Train" -> cost / 2
            else -> cost
        }
    }

    private fun getCityIdx(name: String): Int {
        return cityMap[name]!!
    }

    private fun Array<IntArray>.putMinCost(s: Int, e: Int, cost: Int) {
        this[s][e] = minOf(this[s][e], cost)
        this[e][s] = minOf(this[e][s], cost)
    }

    private fun Array<IntArray>.floyd() {
        for (k in indices) {
            for (i in indices) {
                for (j in indices) {
                    this[i][j] = minOf(this[i][j], this[i][k] + this[k][j])
                }
            }
        }
    }

    private fun Array<IntArray>.calcCost(): Int {
        var cost = 0
        for (i in 0 until tripOrder.lastIndex) {
            cost += this[tripOrder[i]][tripOrder[i + 1]]
        }
        return cost
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, r) = readLine().split(" ").map { it.toInt() }
        var st = StringTokenizer(readLine())
        var idx = 0
        repeat(n) {
            val cityName = st.nextToken()
            if (!cityMap.containsKey(cityName)) {
                cityMap[cityName] = idx++
            }
        }

        val m = readLine().toInt()
        st = StringTokenizer(readLine())
        tripOrder = IntArray(m) { getCityIdx(st.nextToken()) }

        val k = readLine().toInt()
        map = initMap(cityMap.size)
        naeilloMap = initMap(cityMap.size)
        repeat(k) {
            st = StringTokenizer(readLine())
            val type = st.nextToken()
            val sIdx = getCityIdx(st.nextToken())
            val eIdx = getCityIdx(st.nextToken())
            val cost = st.nextToken().toInt() * 2
            map.putMinCost(sIdx, eIdx, cost)
            naeilloMap.putMinCost(sIdx, eIdx, getNaeilloCost(type, cost))
        }
        map.floyd()
        naeilloMap.floyd()

        val generalCost = map.calcCost()
        val naeilloCost = naeilloMap.calcCost() + r * 2
        if (naeilloCost < generalCost) println("Yes")
        else println("No")
    }

    companion object {
        private const val INF = 1_000_001
    }
}

fun main() {
    `13168`().solution()
}