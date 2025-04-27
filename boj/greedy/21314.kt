package boj.greedy

class `21314` {
    private fun getMaxValue(s: String): String {
        val sb = StringBuilder()
        var mCnt = 0
        for (ch in s) {
            when (ch) {
                'M' -> mCnt++
                'K' -> {
                    sb.append(5)
                    repeat(mCnt) {
                        sb.append(0)
                    }
                    mCnt = 0
                }
            }
        }
        if (mCnt > 0) {
            repeat(mCnt) {
                sb.append(1)
            }
        }
        return sb.toString()
    }

    private fun getMinValue(s: String): String {
        val sb = StringBuilder()
        var mCnt = 0
        for (ch in s) {
            when (ch) {
                'M' -> mCnt++
                'K' -> {
                    if (mCnt > 0) {
                        sb.append(1)
                        repeat(mCnt - 1) {
                            sb.append(0)
                        }
                        mCnt = 0
                    }
                    sb.append(5)
                }
            }
        }
        if (mCnt > 0) {
            sb.append(1)
            repeat(mCnt - 1) {
                sb.append(0)
            }
        }
        return sb.toString()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        print("${getMaxValue(s)}\n${getMinValue(s)}")
    }
}

fun main() {
    `21314`().solution()
}