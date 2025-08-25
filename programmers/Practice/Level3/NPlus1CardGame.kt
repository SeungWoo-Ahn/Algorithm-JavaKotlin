package programmers.Practice.Level3

import java.util.PriorityQueue

class NPlus1CardGame {
    private lateinit var requiredCoinsByRound: Array<MutableList<Int>>

    private fun setRequiredCoinsByRound(cards: IntArray) {
        val n = cards.size
        val roundArr = IntArray(n + 1)
        val needCoin = BooleanArray(n + 1)
        for (i in cards.indices) {
            roundArr[cards[i]] = calcRound(n, i)
            needCoin[cards[i]] = i >= n / 3
        }
        requiredCoinsByRound = Array(n / 3 + 1) { mutableListOf() }
        for (i in 1..n / 2) {
            val matchedRound = maxOf(roundArr[i], roundArr[n + 1 - i])
            var requiredCoins = 0
            if (needCoin[i]) requiredCoins++
            if (needCoin[n + 1 - i]) requiredCoins++
            requiredCoinsByRound[matchedRound] += requiredCoins
        }
    }

    private fun calcRound(n: Int, index: Int): Int {
        return if (index < n / 3) {
            0
        } else {
            (index - n / 3) / 2 + 1
        }
    }

    fun solution(coin: Int, cards: IntArray): Int {
        setRequiredCoinsByRound(cards)
        val pq = PriorityQueue<Int>()
        pq.addAll(requiredCoinsByRound.first())
        var round = 1
        var remainCoin = coin
        while (round <= cards.size / 3) {
            pq.addAll(requiredCoinsByRound[round])
            if (pq.isEmpty() || pq.peek() > remainCoin) {
                break
            }
            remainCoin -= pq.poll()
            round++
        }
        return round
    }
}

fun main() {
    val coin = 2
    val cards = intArrayOf(5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7)
    val answer = NPlus1CardGame().solution(coin, cards)
    print(answer)
}