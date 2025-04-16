package boj.math

import java.util.StringTokenizer

class `21920` {
    private val targetSet = hashSetOf<Int>()
    private lateinit var seoRoSo: BooleanArray

    private fun fillTargetSet(x: Int) {
        for (i in 1..x) {
            if (i * i > x) break
            if (x % i == 0) {
                targetSet += i
                targetSet += (x / i)
            }
        }
        targetSet.remove(1)
    }

    private fun findSeoRoSo(a: IntArray) {
        for (i in a.indices) {
            seoRoSo[i] = isSeoRoSo(a[i])
        }
    }

    private fun isSeoRoSo(num: Int): Boolean {
        for (i in 1..num) {
            if (i * i > num) break
            if (num % i == 0) {
                if (targetSet.contains(i) || targetSet.contains(num / i)) {
                    return false
                }
            }
        }
        return true
    }

    private fun calcAvg(a: IntArray): Double {
        var sum = 0.0
        var cnt = 0
        for (i in seoRoSo.indices) {
            if (seoRoSo[i]) {
                sum += a[i]
                cnt++
            }
        }
        return sum / cnt
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val st = StringTokenizer(readLine(), " ")
        val a = IntArray(n) { st.nextToken().toInt() }
        val x = readLine().toInt()
        seoRoSo = BooleanArray(n)
        fillTargetSet(x)
        findSeoRoSo(a)
        val avg = calcAvg(a)
        print(avg)
    }
}

fun main() {
    `21920`().solution()
}