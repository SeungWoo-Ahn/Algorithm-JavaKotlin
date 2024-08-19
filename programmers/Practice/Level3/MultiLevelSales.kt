package programmers.Practice.Level3

class MultiLevelSales {
    private var parent = intArrayOf()
    private var profit = intArrayOf()
    private val map = mutableMapOf<String, Int>()

    private fun separateProfit(cur: Int, remain: Int) {
        if (parent[cur] == -1) return
        val rest = remain / 10
        if (rest < 1) {
            profit[cur] += remain
            return
        } else {
            profit[cur] += remain - rest
            separateProfit(parent[cur], rest)
        }
    }

    fun solution(
        enroll: Array<String>,
        referral: Array<String>,
        seller: Array<String>,
        amount: IntArray
    ): IntArray {
        val n = enroll.size
        parent = IntArray(n + 1) { -1 }
        profit = IntArray(n)
        for (i in 0 until n) {
            map[enroll[i]] = i
            if (referral[i] == "-") {
                parent[i] = n
            } else {
                parent[i] = map[referral[i]]!!
            }
        }
        for (i in seller.indices) {
            separateProfit(
                cur = map[seller[i]]!!,
                remain = amount[i] * 100
            )
        }
        return profit
    }
}

fun main() {
    val enroll = arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young")
    val referral = arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward")
    val seller = arrayOf("young", "john", "tod", "emily", "mary")
    val amount = intArrayOf(12, 4, 2, 5, 10)
    val answer = MultiLevelSales().solution(enroll, referral, seller, amount)
    print(answer.joinToString(" "))
}