package programmers.Practice.Level2

class CuttingRollCake {
    private fun giveTopping(
        t: Int,
        mine: MutableMap<Int, Int>,
        brothers: MutableMap<Int, Int>
    ) {
        mine[t] = mine.getOrDefault(t, 0) - 1
        if (mine[t] == 0) {
            mine.remove(t)
        }
        brothers[t] = brothers.getOrDefault(t, 0) + 1
    }

    fun solution(topping: IntArray): Int {
        val mine = mutableMapOf<Int, Int>()
        val brothers = mutableMapOf<Int, Int>()
        topping.forEach { t ->
            mine[t] = mine.getOrDefault(t, 0) + 1
        }
        var answer = 0
        topping.forEach { t ->
            giveTopping(t, mine, brothers)
            if (mine.size == brothers.size) {
                answer++
            }
        }
        return answer
    }
}

fun main() {
    val topping = intArrayOf(1, 2, 1, 3, 1, 4, 1, 2)
    val answer = CuttingRollCake().solution(topping)
    print(answer)
}