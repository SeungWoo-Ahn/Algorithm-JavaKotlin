package boj.sort

import kotlin.math.roundToInt

class `18110` {
    private var countArr = IntArray(31)
    private var total = 0.0

    private fun calcLevel(n: Int): Int {
        val except15 = (n * 0.15).roundToInt()
        val targetCnt = n - except15 * 2
        except(except15, 1 .. 30)
        except(except15, 30 downTo 1)
        return (total / targetCnt).roundToInt()
    }

    private fun except(exceptCnt: Int, range: IntProgression) {
        var restCnt = exceptCnt
        for (i in range) {
            if (countArr[i] == 0) continue
            if (countArr[i] >= restCnt) {
                total -= i * restCnt
                break
            } else {
                restCnt -= countArr[i]
                total -= i * countArr[i]
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        var level = 0
        if (n > 0) {
            repeat(n) {
                val opinion = readLine().toInt()
                total += opinion
                countArr[opinion]++
            }
            level = calcLevel(n)
        }
        print(level)
    }
}

fun main() {
    `18110`().solution()
}