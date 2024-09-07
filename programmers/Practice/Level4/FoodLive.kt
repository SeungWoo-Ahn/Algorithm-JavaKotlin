package programmers.Practice.Level4

class FoodLive {
    fun solution(foodTimes: IntArray, k: Long): Int {
        val foods = sortedMapOf<Int, MutableList<Int>>()
        val n = foodTimes.size
        var sum = 0L
        for (i in 0 until n) {
            val time = foodTimes[i]
            if (foods[time] == null) {
                foods[time] = mutableListOf()
            }
            sum += time
            foods[time]!! += i + 1
        }
        if (sum <= k) {
            return -1
        }
        var cnt = n
        var remain = k + 1
        var prevMinTime = 0
        while (true) {
            if (cnt >= remain) {
                break
            }
            val minTime = foods.firstKey()
            var delTime = minTime
            for (time in delTime downTo prevMinTime + 1) {
                if (remain > cnt * (time - prevMinTime).toLong()) {
                    delTime = time
                    break
                }
            }
            remain -= cnt * (delTime - prevMinTime).toLong()
            prevMinTime = delTime
            if (delTime == minTime) {
                cnt -= foods[minTime]!!.size
                foods.remove(minTime)
            }
        }
        val idx = (remain - 1).toInt()
        val remainArr = mutableListOf<Int>()
        for (time in foods.keys) {
            for (id in foods[time]!!) {
                remainArr += id
            }
        }
        remainArr.sort()
        return remainArr[idx]
    }
}

fun main() {
    val foodTimes = intArrayOf(3, 1, 2)
    val k = 5L
    val answer = FoodLive().solution(foodTimes, k)
    print(answer)
}