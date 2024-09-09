package programmers.Practice.Level3

class ExteriorInspection {
    private var weakCnt = 0
    private val weakPoints = mutableListOf<Int>()
    private var arr = intArrayOf()
    private var found = false

    private fun init(n: Int, weak: IntArray) {
        weakCnt = weak.size
        for (i in weak.indices) {
            weakPoints += weak[i]
        }
        for (i in 0 until weak.size - 1) {
            weakPoints += weak[i] + n
        }
    }

    private fun comb(cnt: Int, depth: Int, dist: IntArray, visited: BooleanArray) {
        if (found) {
            return
        }
        if (depth == cnt) {
            found = check()
            return
        }
        for (i in dist.indices) {
            if (visited[i]) continue
            visited[i] = true
            arr[depth] = dist[i]
            comb(cnt, depth + 1, dist, visited)
            visited[i] = false
        }
    }

    private fun check(): Boolean {
        for (st in 0 until weakCnt) {
            var idx = st
            var clear = 0
            for (j in arr.indices) {
                val d = arr[j]
                val originIdx = idx
                clear++
                while (idx < st + weakCnt - 1) {
                    if (weakPoints[idx + 1] - weakPoints[originIdx] <= d) {
                        idx++
                        clear++
                    } else break
                }
                if (idx != originIdx) {
                    idx++
                }
            }
            if (clear == weakCnt) {
                return true
            }
        }
        return false
    }

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        init(n, weak)
        var friendCnt = 1
        while (friendCnt <= dist.size) {
            arr = IntArray(friendCnt)
            comb(friendCnt, 0, dist, BooleanArray(dist.size))
            if (found) {
                return friendCnt
            }
            friendCnt++
        }
        return -1
    }
}

fun main() {
    val n = 12
    val weak = intArrayOf(1, 5, 6, 10)
    val dist = intArrayOf(1, 2, 3, 4)
    val answer = ExteriorInspection().solution(n, weak, dist)
    print(answer)
}