package boj.bitmasking

import java.util.StringTokenizer

class `15787` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val trains = IntArray(n + 1)
        var st: StringTokenizer
        repeat(m) {
            st = StringTokenizer(readLine())
            val command = st.nextToken().toInt()
            val i = st.nextToken().toInt()
            when (command) {
                1 -> {
                    val x = st.nextToken().toInt()
                    trains[i] = trains[i] or (1 shl x)
                }
                2 -> {
                    val x = st.nextToken().toInt()
                    trains[i] = trains[i] and (1 shl x).inv()
                }
                3 -> {
                    trains[i] = trains[i] shl 1
                    trains[i] = trains[i] and ((1 shl 21) - 1)
                }
                4 -> {
                    trains[i] = trains[i] shr 1
                    trains[i] = trains[i] and (1 shl 0).inv()
                }
            }
        }
        val set = mutableSetOf<Int>()
        (1..n).forEach { set += trains[it] }
        print(set.size)
    }
}

fun main() {
    `15787`().solution()
}