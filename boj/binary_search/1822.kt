package boj.binary_search

import java.util.HashMap
import java.util.StringTokenizer

class `1822` {
    fun solutionWithMap() = with(System.`in`.bufferedReader()) {
        val (nA, nB) = readLine().split(" ").map { it.toInt() }
        var st = StringTokenizer(readLine())
        val arrA = mutableListOf<Int>()
        repeat(nA) { arrA.add(st.nextToken().toInt()) }
        arrA.sort()
        st = StringTokenizer(readLine())
        val map = HashMap<Int, Boolean>()
        repeat(nB) { map[st.nextToken().toInt()] = true }
        var cnt = 0
        val sb = StringBuilder()
        for (a in arrA) {
            if (map[a] == null) {
                cnt++
                sb.append("$a ")
            }
        }
        println("$cnt\n$sb")
    }
}

fun main() {
    `1822`().solutionWithMap()
}