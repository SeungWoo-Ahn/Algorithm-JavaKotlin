package boj.sort

class `2751` {
    private var arr = intArrayOf()
    private var tempArr = intArrayOf()

    private fun mergeSort(s: Int, e: Int) {
        if (s + 1 == e) return
        val mid = (s + e) / 2
        mergeSort(s, mid)
        mergeSort(mid, e)
        merge(s, e)
    }

    private fun merge(s: Int, e: Int) {
        val mid = (s + e) / 2
        var lIdx = s
        var rIdx = mid
        for (i in s until e) {
            if (rIdx == e) tempArr[i] = arr[lIdx++]
            else if (lIdx == mid) tempArr[i] = arr[rIdx++]
            else if (arr[lIdx] <= arr[rIdx]) tempArr[i] = arr[lIdx++]
            else tempArr[i] = arr[rIdx++]
        }
        for (i in s until e) arr[i] = tempArr[i]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        arr = IntArray(n)
        tempArr = IntArray(n)
        repeat(n) { arr[it] = readLine().toInt() }
        mergeSort(0, n)
        val sb = StringBuilder()
        for (i in arr) { sb.append("$i\n") }
        println(sb)
    }
}

fun main() {
    `2751`().solution()
}