package boj.math

import java.util.StringTokenizer

class `2824` {
    private fun getGcd(smallMap: MutableMap<Int, Int>, bigMap: MutableMap<Int, Int>): String {
        var gcd = 1L
        val mod = 10_000_000_000
        for (key in smallMap.keys) {
            if (bigMap.containsKey(key).not()) continue
            val cnt = minOf(smallMap[key]!!, bigMap[key]!!)
            repeat(cnt) {
                gcd = (gcd * key) % mod
            }
        }
        val result = gcd.toString()
        return if (result.length <= 9) result
        else result.substring(result.length - 9)
    }

    private fun putMap(num: Int, map: MutableMap<Int, Int>) {
        var cur = num
        for (i in 2 until num) {
            if (i * i > num) break
            var cnt = 0
            while (cur % i == 0) {
                cnt++
                cur /= i
            }
            if (cnt > 0) {
                map[i] = map.getOrDefault(i, 0) + cnt
            }
        }
        if (cur != 1) {
            map[cur] = map.getOrDefault(cur, 0) + 1
        }
    }

    private fun getMap(line1: String, line2: String): MutableMap<Int, Int> {
        val map = mutableMapOf<Int, Int>()
        val size = line1.toInt()
        val st = StringTokenizer(line2, " ")
        repeat(size) {
            val num = st.nextToken().toInt()
            putMap(num, map)
        }
        return map
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val aMap = getMap(readLine(), readLine())
        val bMap = getMap(readLine(), readLine())
        val smallMap: MutableMap<Int, Int>
        val bigMap: MutableMap<Int, Int>
        if (aMap.size < bMap.size) {
            smallMap = aMap
            bigMap = bMap
        } else {
            smallMap = bMap
            bigMap = aMap
        }
        val result = getGcd(smallMap, bigMap)
        print(result)
    }
}

fun main() {
    `2824`().solution()
}