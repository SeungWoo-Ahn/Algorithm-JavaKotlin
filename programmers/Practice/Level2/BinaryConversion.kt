package programmers.Practice.Level2

import java.lang.StringBuilder

class BinaryConversion {

    var conversionCnt = 0
    var zeroCnt = 0

    fun solution(s: String): IntArray {
        var input = s
        while (input != "1") {
            input = filterZero(input)
            input = makeBinary(input)
        }
        return intArrayOf(conversionCnt, zeroCnt)
    }

    private fun filterZero(s: String): String {
        val sb = StringBuilder()
        for(index in s.indices) {
            if (s[index] == '0') zeroCnt++
            else sb.append(s[index])
        }
        return sb.toString()
    }

    private fun makeBinary(s: String): String {
        val len = s.length
        conversionCnt++
        return len.toString(2)
    }
}

fun main() {
    println(BinaryConversion().solution("110010101001").contentToString())
}

