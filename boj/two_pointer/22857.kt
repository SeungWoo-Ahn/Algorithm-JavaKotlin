package boj.two_pointer

import java.util.StringTokenizer

class `22857` {
    private fun getResult(k: Int, arr: IntArray): Int {
        var st = 0
        var en = 0
        val cnt = IntArray(2)
        cnt[arr[0] % 2]++
        var result = cnt[0]
        while (en < arr.lastIndex) {
            while (en < arr.lastIndex) {
                if (arr[en + 1] % 2 == 1 && cnt[1] + 1 > k) {
                    break
                } else {
                    cnt[arr[en + 1] % 2]++
                }
                en++
            }
            result = maxOf(result, cnt[0])
            cnt[arr[st] % 2]--
            st++
        }
        return result
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()
        st = StringTokenizer(readLine(), " ")
        val arr = IntArray(n) { st.nextToken().toInt() }
        print(getResult(k, arr))
    }
}

fun main() {
    `22857`().solution()
}