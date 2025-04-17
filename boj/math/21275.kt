package boj.math

import java.util.StringTokenizer

class `21275` {
    private fun String.getX(radix: Int): Long {
        var idx = lastIndex
        var x = 0L
        var base = 1L
        while (idx >= 0) {
            x += this[idx].toNum() * base
            if (x < 0) {
                return -1
            }
            base *= radix
            idx--
        }
        return x
    }

    private fun String.getMinRadix(): Int {
        var maxNum = 0
        for (ch in this) {
            maxNum = maxOf(maxNum, ch.toNum())
        }
        return maxOf(2, maxNum + 1)
    }

    private fun Char.toNum(): Int {
        return if (isDigit()) this - '0'
        else this - 'a' + 10
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val st = StringTokenizer(readLine(), " ")
        val aResult = st.nextToken()
        val bResult = st.nextToken()
        val resultList = mutableListOf<Triple<Long, Int, Int>>()
        for (aRadix in aResult.getMinRadix()..36) {
            for (bRadix in bResult.getMinRadix()..36) {
                if (aRadix == bRadix) continue
                val aX = aResult.getX(aRadix)
                val bX = bResult.getX(bRadix)
                if (aX < 0 || bX < 0) continue
                if (aX == bX) {
                    resultList += Triple(aX, aRadix, bRadix)
                }
            }
        }
        val result = if (resultList.isEmpty()) {
            "Impossible"
        } else if (resultList.size > 1) {
            "Multiple"
        } else {
            val (x, a, b) = resultList.first()
            "$x $a $b"
        }
        print(result)
    }
}

fun main() {
    `21275`().solution()
}