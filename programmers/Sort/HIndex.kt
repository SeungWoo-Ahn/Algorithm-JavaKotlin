package programmers.Sort

class HIndex {
    fun solution(citations: IntArray): Int {
        citations.sort()
        for(i in citations.indices) {
            if (citations[i] >= citations.size - i) {
                return citations.size - i
            }
        }
        return 0
    }
}

fun main() {
    val citations = intArrayOf(3, 0, 6, 1, 5)
    println(HIndex().solution(citations))
}