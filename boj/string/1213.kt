package boj.string

class `1213` {
    private val cnt = IntArray(26)

    private fun counting(name: String) {
        for (ch in name) {
            cnt[ch - 'A']++
        }
    }

    private fun isPossibleForEven(): Boolean {
        for (n in cnt) {
            if (n % 2 != 0) {
                return false
            }
        }
        return true
    }

    private fun isPossibleForOdd(): Char? {
        var oddChar: Char? = null
        var oddCnt = 0
        for (i in cnt.indices) {
            if (cnt[i] % 2 != 0) {
                oddChar = (i + 'A'.code).toChar()
                oddCnt++
                if (oddCnt > 1) {
                    return null
                }
            }
        }
        return oddChar
    }

    private fun getPrefix(): String {
        val sb = StringBuilder()
        for (i in cnt.indices) {
            if (cnt[i] < 2) continue
            val ch = (i + 'A'.code).toChar()
            repeat(cnt[i] / 2) {
                sb.append(ch)
            }
        }
        return sb.toString()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val name = readLine()
        val result = StringBuilder()
        counting(name)
        if (name.length % 2 == 0) {
            if (isPossibleForEven().not()) {
                print(IMPOSSIBLE)
                return
            }
            val prefix = getPrefix()
            val suffix = prefix.reversed()
            result
                .append(prefix)
                .append(suffix)
        } else {
            val oddChar = isPossibleForOdd()
            if (oddChar == null) {
                print(IMPOSSIBLE)
                return
            }
            val prefix = getPrefix()
            val suffix = prefix.reversed()
            result
                .append(prefix)
                .append(oddChar)
                .append(suffix)
        }
        print(result)
    }

    companion object {
        private const val IMPOSSIBLE = "I'm Sorry Hansoo"
    }
}

fun main() {
    `1213`().solution()
}