package boj.math

import java.util.StringTokenizer

class `21275` {
    private data class Case(val result: String) {
        val arr = IntArray(result.length) { result[it].toNum() }
        val minRadix = maxOf(2, arr.max() + 1)

        private fun Char.toNum(): Int {
            return if (isDigit()) this - '0'
            else this - 'a' + 10
        }
    }

    private val xRadixMap = mutableMapOf<Long, MutableList<Int>>()

    private fun putMap(case: Case) {
        for (radix in case.minRadix..36) {
            val x = getX(case, radix)
            if (x < 0) continue
            if (xRadixMap[x] == null) {
                xRadixMap[x] = mutableListOf()
            }
            xRadixMap[x]!! += radix
        }
    }

    private fun getResult(case: Case): String {
        var resultX = -1L
        var a = -1
        var b = -1
        for (radix in case.minRadix..36) {
            val x = getX(case, radix)
            if (x < 0 || xRadixMap.containsKey(x).not()) continue
            when (xRadixMap[x]!!.size) {
                1 -> if (xRadixMap[x]!!.first() != radix) {
                    if (resultX > 0) return "Multiple"
                    resultX = x
                    a = xRadixMap[x]!!.first()
                    b = radix
                }
                2 -> {
                    val radixList = xRadixMap[x]!!
                    if (radixList.contains(radix)) {
                        if (resultX > 0) return "Multiple"
                        resultX = x
                        a = if (radixList[0] != radix) radixList[0] else radixList[1]
                        b = radix
                    } else {
                        return "Multiple"
                    }
                }
                else -> return "Multiple"
            }
        }
        return if (resultX < 0) "Impossible"
        else "$resultX $a $b"
    }

    private fun getX(case: Case, radix: Int): Long {
        var x = 0L
        var base = 1L
        for (i in case.arr.lastIndex downTo 0) {
            x += case.arr[i] * base
            base *= radix
            if (x < 0) {
                return x
            }
        }
        return x
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine(), " ")
        val aCase = Case(st.nextToken())
        val bCase = Case(st.nextToken())
        val bigCase: Case
        val smallCase: Case
        if (aCase.minRadix >= bCase.minRadix) {
            bigCase = aCase
            smallCase = bCase
        } else {
            bigCase = bCase
            smallCase = aCase
        }
        putMap(bigCase)
        val result = getResult(smallCase)
        print(result)
    }
}

fun main() {
    `21275`().solution()
}