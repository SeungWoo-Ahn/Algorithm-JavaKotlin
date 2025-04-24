package boj.greedy

class `1343` {
    private val sb = StringBuilder()
    private val sticks = arrayOf("AAAA", "BB")

    private fun search(s: String): Boolean {
        var st = 0
        for (en in s.indices) {
            if (s[en] == '.') {
                if (st < en && insert(en - st).not()) {
                    return false
                }
                sb.append('.')
                st = en + 1
            }
        }
        if (st <= s.lastIndex && insert(s.lastIndex - st + 1).not()) {
            return false
        }
        return true
    }

    private fun insert(cnt: Int): Boolean {
        if (cnt % 2 != 0) {
            return false
        }
        var left = cnt
        for (stick in sticks) {
            repeat(left / stick.length) {
                sb.append(stick)
            }
            left %= stick.length
        }
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        if (search(s).not()) {
            print(-1)
        } else {
            print(sb)
        }
    }
}

fun main() {
    `1343`().solution()
}