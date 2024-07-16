package boj.hash

import java.util.StringTokenizer

class `2143` {
    private fun String.toArr(size: Int): IntArray {
        val st = StringTokenizer(this)
        return IntArray(size) { st.nextToken().toInt() }
    }

    private fun IntArray.toGroup(): HashMap<Int, Long> {
        val group = HashMap<Int, Long>()
        var sum: Int
        for (s in indices) {
            sum = 0
            for (e in s until size) {
                sum += this[e]
                group[sum] = group.getOrDefault(sum, 0) + 1
            }
        }
        return group
    }

    private fun cntSumIsTarget(
        target: Int,
        group1: HashMap<Int, Long>,
        group2: HashMap<Int, Long>
    ): Long {
        var cnt = 0L
        val iter = group1.iterator()
        while (iter.hasNext()) {
            val (num, cnt1) = iter.next()
            val cnt2 = group2[target - num] ?: continue
            cnt += cnt1 * cnt2
        }
        return cnt
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val target = readLine().toInt()
        val n = readLine().toInt()
        val a = readLine().toArr(n)
        val aGroup = a.toGroup()
        val m = readLine().toInt()
        val b = readLine().toArr(m)
        val bGroup = b.toGroup()
        val result = cntSumIsTarget(target, aGroup, bGroup)
        print(result)
    }
}

fun main() {
    `2143`().solution()
}