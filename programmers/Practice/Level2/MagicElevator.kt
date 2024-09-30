package programmers.Practice.Level2

class MagicElevator {
    private var result = 100_000_000

    private fun dfs(str: String, idx: Int, offset: Int, usedRock: Int) {
        if (idx < 0) {
            result = minOf(result, usedRock + offset)
            return
        }
        val num = str[idx].digitToInt() + offset
        dfs(str, idx - 1, 0, usedRock + num)
        dfs(str, idx - 1, 1, usedRock + (10 - num))
    }

    fun solution(storey: Int): Int {
        val str = storey.toString()
        val firstIdx = str.lastIndex
        dfs(str, firstIdx, 0, 0)
        return result
    }
}

fun main() {
    val storey = 2554
    val answer = MagicElevator().solution(storey)
    print(answer)
}