package boj.binary_search

class `2295` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = IntArray(n)
        val two = mutableListOf<Int>()
        repeat(n) { arr[it] = readLine().toInt() }
        arr.sort()
        for (i in arr.indices) {
            for (j in i until n) {
                two.add(arr[i] + arr[j])
            }
        }
        two.sort()
        for (i in n - 1 downTo 1) {
            for (j in 0 until i) {
                if (two.binarySearch(arr[i] - arr[j]) >= 0) {
                    println(arr[i])
                    return
                }
            }
        }
    }
}

fun main() {
    `2295`().solution()
}