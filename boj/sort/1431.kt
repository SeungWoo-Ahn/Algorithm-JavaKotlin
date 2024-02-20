package boj.sort

class `1431` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = Array(n) { readLine() }
        arr.sortWith { o1, o2 ->
            if (o1.length != o2.length) o1.length.compareTo(o2.length)
            else {
                var o1Sum = 0
                var o2Sum = 0
                o1.forEach { if ((it - '0') in 1 .. 9) o1Sum += (it - '0') }
                o2.forEach { if ((it - '0') in 1 .. 9) o2Sum += (it - '0') }
                if (o1Sum != o2Sum) o1Sum.compareTo(o2Sum)
                else o1.compareTo(o2)
            }
        }
        val sb = StringBuilder()
        for (s in arr) { sb.append("$s\n") }
        println(sb)
    }
}

fun main() {
    `1431`().solution()
}