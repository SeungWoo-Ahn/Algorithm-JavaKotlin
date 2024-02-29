package boj.greedy

class `2457` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val flowers = arrayListOf<Pair<Int, Int>>()
        repeat(n) {
            val (sMonth, sDay, eMonth, eDay) = readLine().split(" ").map { it.toInt() }
            flowers.add(Pair(sMonth * 100 + sDay, eMonth * 100 + eDay))
        }
        var t = 301
        var answer = 0
        while (t < 1201) {
            var nextT = t
            for (i in flowers.indices) {
                if (flowers[i].first <= t && flowers[i].second > nextT) {
                    nextT = flowers[i].second
                }
            }
            if (nextT == t) {
                println(0)
                return
            }
            answer++
            t = nextT
        }
        println(answer)
    }
}

fun main() {
    `2457`().solution()
}