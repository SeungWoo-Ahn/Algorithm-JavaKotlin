package boj.dp

class `10826` {
    private fun sum(a: String, b: String): String {
        val sb = StringBuilder()
        var flag = 0
        val aLen = a.length
        val bLen = b.length
        for (i in a.indices) {
            val aNum = a[aLen - i - 1].digitToInt()
            var sum: Int
            if (bLen - i - 1 >= 0) {
                val bNum = b[bLen - i - 1].digitToInt()
                sum = aNum + bNum
            } else {
                sum = aNum
            }
            sum += flag
            flag = sum / 10
            sum %= 10
            sb.append(sum)
        }
        if (flag > 0) {
            sb.append(flag)
        }
        return sb.toString().reversed()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n < 1) {
            print(0)
            return
        }
        val dp = Array(n + 1) { "" }
        dp[0] = "0"
        dp[1] = "1"
        for (i in 2..n) {
            dp[i] = sum(dp[i - 1], dp[i - 2])
        }
        print(dp[n])
    }
}

fun main() {
    `10826`().solution()
}