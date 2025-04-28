package boj.dp

import java.util.StringTokenizer
import kotlin.math.abs

class `22869` {
    fun solution() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()
        st = StringTokenizer(readLine(), " ")
        val a = IntArray(n) { st.nextToken().toInt() }
        val canMove = BooleanArray(n)
        canMove[0] = true
        for (i in a.indices) {
            if (canMove[i].not()) continue
            for (j in i + 1 until minOf(n, i + k + 1)) {
                if (canMove[j]) continue
                canMove[j] = (j - i) * (1 + abs(a[i] - a[j])) <= k
            }
            if (canMove[n - 1]) break
        }
        if (canMove[n - 1]) {
            print("YES")
        } else {
            print("NO")
        }
    }
}

fun main() {
    `22869`().solution()
}