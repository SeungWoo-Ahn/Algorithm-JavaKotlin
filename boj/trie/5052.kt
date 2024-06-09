package boj.trie

class `5052` {
    private var isValid = true
    private var unused = 2
    private var chk = booleanArrayOf()
    private var nxt: Array<IntArray> = arrayOf()

    private fun initProps() {
        isValid = true
        unused = 2
        chk = BooleanArray(MX)
        nxt = Array(MX) { IntArray(10) { -1 } }
    }

    private fun Char.toIdx() = this - '0'

    private fun insert(s: String): Boolean {
        var cur = ROOT
        for (c in s) {
            if (nxt[cur][c.toIdx()] == -1)
                nxt[cur][c.toIdx()] = unused++
            cur = nxt[cur][c.toIdx()]
            if (chk[cur]) return false
        }
        if (cur != unused - 1) return false
        chk[cur] = true
        return true
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val n = readLine().toInt()
            initProps()
            repeat(n) {
                val phoneNumber = readLine()
                if (!insert(phoneNumber)) isValid = false
            }
            if (isValid) sb.appendLine("YES")
            else sb.appendLine("NO")
        }
        print(sb)
    }

    companion object {
        private const val ROOT = 1
        private const val MX = 10_000 * 10 + 2
    }
}

fun main() {
    `5052`().solution()
}