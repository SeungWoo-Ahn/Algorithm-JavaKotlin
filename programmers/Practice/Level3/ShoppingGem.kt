package programmers.Practice.Level3

class ShoppingGem {
    private val set = mutableSetOf<String>()

    private fun makeGemSet(gems: Array<String>) {
        for (gem in gems) {
            set.add(gem)
        }
    }

    private fun findShortestRange(gems: Array<String>): IntArray {
        var left = 0
        var right = 0
        var from = 0
        var to = 0
        var size = Int.MAX_VALUE
        val map = mutableMapOf<String, Int>()
        while (left < gems.size) {
            if (map.size == set.size) {
                if (right - left <= size) {
                    from = left
                    to = right - 1
                    size = to - from
                }
                if (map[gems[left]]!! - 1 == 0) {
                    map.remove(gems[left])
                } else {
                    map[gems[left]] = map[gems[left]]!! - 1
                }
                left++
            } else {
                if (right < gems.size) {
                    map[gems[right]] = map.getOrDefault(gems[right], 0) + 1
                    right++
                } else {
                    break
                }
            }
        }
        return intArrayOf(from + 1, to + 1)
    }

    fun solution(gems: Array<String>): IntArray {
        makeGemSet(gems)
        return findShortestRange(gems)
    }
}

fun main() {
    val gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")
    val answer = ShoppingGem().solution(gems)
    print(answer.joinToString("~"))
}