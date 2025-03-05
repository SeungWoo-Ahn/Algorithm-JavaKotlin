package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.abs

class `17471` {
    private var n = 0
    private lateinit var population: IntArray
    private lateinit var adj: Array<MutableList<Int>>

    private fun getGroupDiff(isGroupA: BooleanArray): Int {
        var aPopulation = 0
        var bPopulation = 0
        for (idx in isGroupA.indices) {
            if (isGroupA[idx]) aPopulation += population[idx]
            else bPopulation += population[idx]
        }
        return abs(aPopulation - bPopulation)
    }

    private fun getLinkedCnt(st: Int, target: Boolean, visited: BooleanArray, isGroupA: BooleanArray): Int {
        val q = LinkedList<Int>() as Queue<Int>
        var linkedCnt = 0
        q += st
        visited[st] = true
        while (q.isNotEmpty()) {
            val cur = q.poll()
            linkedCnt++
            for (nxt in adj[cur]) {
                if (visited[nxt] || isGroupA[nxt] != target) continue
                q += nxt
                visited[nxt] = true
            }
        }
        return linkedCnt
    }

    private fun splitGroup(comb: Int): Pair<Int, BooleanArray> {
        var groupACnt = 0
        val isGroupA = BooleanArray(n)
        var ret = comb
        var idx = 0
        while (ret > 0) {
            if (ret and 1 == 1) {
                isGroupA[idx] = true
                groupACnt++
            }
            ret = ret shr 1
            idx++
        }
        return groupACnt to isGroupA
    }

    private fun getPopulationSumByGroup(): List<Int> {
        val q = LinkedList<Int>() as Queue<Int>
        val visited = BooleanArray(n)
        val populationSumByGroup = mutableListOf<Int>()
        for (st in 0 until n) {
            if (visited[st]) continue
            var populationSum = 0
            q += st
            visited[st] = true
            while (q.isNotEmpty()) {
                val cur = q.poll()
                populationSum += population[cur]
                for (nxt in adj[cur]) {
                    if (visited[nxt]) continue
                    q += nxt
                    visited[nxt] = true
                }
            }
            populationSumByGroup += populationSum
        }
        return populationSumByGroup
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()
        var st = StringTokenizer(readLine(), " ")
        population = IntArray(n) { st.nextToken().toInt() }
        adj = Array(n) { mutableListOf() }
        repeat(n) { u ->
            st = StringTokenizer(readLine(), " ")
            val cnt = st.nextToken().toInt()
            repeat(cnt) {
                val v = st.nextToken().toInt() - 1
                adj[u] += v
            }
        }
    }

    fun solution() {
        input()
        val populationSumByGroup = getPopulationSumByGroup()
        if (populationSumByGroup.size > 2) {
            print(-1)
            return
        }
        if (populationSumByGroup.size == 2) {
            val diff = abs(populationSumByGroup[0] - populationSumByGroup[1])
            print(diff)
            return
        }
        val maxComb = (1 shl n) - 1
        var minDiff = 1_001
        for (comb in 1..(maxComb shr 1)) {
            val (groupACnt, isGroupA) = splitGroup(comb)
            val visited = BooleanArray(n)
            val aSt = isGroupA.indexOf(true)
            val aLinkedCnt = getLinkedCnt(aSt, true, visited, isGroupA)
            if (aLinkedCnt != groupACnt) continue
            val bSt = isGroupA.indexOf(false)
            val bLinkedCnt = getLinkedCnt(bSt, false, visited, isGroupA)
            if (bLinkedCnt != (n - groupACnt)) continue
            val groupDiff = getGroupDiff(isGroupA)
            if (groupDiff < minDiff) {
                minDiff = groupDiff
            }
        }
        print(minDiff)
    }
}

fun main() {
    `17471`().solution()
}