package boj.bitmasking

import java.util.*

class `2064` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val netIp = IntArray(n)
        var netAddress = 0
        var netMask = 0
        for (i in 0 until n) {
            val st = StringTokenizer(readLine(), ".")
            var temp = 0
            for (j in 0 until 4) {
                val m = st.nextToken().toInt()
                temp = (temp shl 8) + m
            }
            netIp[i] = temp
        }
        for (i in 31 downTo 0) {
            val bit = 1 shl i
            var check = false
            for (j in 1 until n) {
                if ((netIp[0] and bit) != (netIp[j] and bit)) {
                    check = true
                    break
                }
            }
            if (check) break
            netMask = netMask or bit
        }
        netAddress = netIp[0] and netMask
        val check = (1 shl 8) - 1
        var k = 3
        val address = mutableListOf<String>()
        val mask = mutableListOf<String>()
        while (true) {
            val anum = (netAddress shr (8 * k)) and check
            val mnum = (netMask shr (8 * k)) and check
            address.add(anum.toString())
            mask.add(mnum.toString())
            netAddress = netAddress and ((1 shl (8 * k)) - 1)
            netMask = netMask and ((1 shl (8 * k)) - 1)
            k--
            if (k == -1) break
        }
        println(address.joinToString("."))
        println(mask.joinToString("."))
    }
}

fun main() {
    `2064`().solution()
}