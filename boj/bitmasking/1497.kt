package boj.bitmasking

class `1497` {
    private fun Char.toBit(): Long {
        return if (this == 'Y') 1L else 0L
    }

    private fun Long.toBitCnt(n: Int, m: Int): Int {
        var ret = 0
        for (i in 0 until maxOf(n, m)) {
            ret += ((this shr i) and 1).toInt()
        }
        return ret
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val state = LongArray(n)
        for (i in state.indices) {
            val (_, info) = readLine().split(" ")
            for (j in m - 1 downTo 0) {
                state[i] = (state[i] shl 1) or info[j].toBit()
            }
        }
        var result = 0 to -1 // 연주 가능 곡 수, 필요 기타 수
        for (tmp in 0 until (1 shl n)) {
            var comb = 0L
            for (i in state.indices) {
                if ((tmp and (1 shl i)) == 0) continue
                comb = comb or state[i]
            }
            val songCnt = comb.toBitCnt(n, m)
            val guitarCnt = tmp.toLong().toBitCnt(n, m)
            if (result.first < songCnt) {
                result = songCnt to guitarCnt
            } else if (result.first == songCnt && result.second > guitarCnt) {
                result = songCnt to guitarCnt
            }
        }
        print(result.second)
    }
}

fun main() {
    `1497`().solution()
}