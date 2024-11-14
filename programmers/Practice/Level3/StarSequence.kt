package programmers.Practice.Level3

class StarSequence {
    fun solution(a: IntArray): Int {
        val map = hashMapOf<Int, Pair<Int, Int>>()
        for (index in a.indices) {
            val num = a[index]
            val (lastIndex, cnt) = map[num] ?: (-1 to 0)
            if (lastIndex >= index) {
                continue
            } else if (lastIndex < index - 1 && index > 0 && a[index - 1] != num) {
                map[num] = index to (cnt + 1)
            } else if (index < a.size - 1 && a[index + 1] != num) {
                map[num] = (index + 1) to (cnt + 1)
            }
        }
        return map.values.maxOf { it.second * 2 }
    }
}

fun main() {
    val a = intArrayOf(0,3,3,0,7,2,0,2,2,0)
    val answer = StarSequence().solution(a)
    print(answer)
}