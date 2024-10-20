package programmers.Practice.Level2

class CandidateKey {
    private val candidateKeys = mutableListOf<List<Int>>()
    private var result = 0

    private fun comb(depth: Int, startIdx: Int, arr: IntArray, relation: Array<Array<String>>) {
        if (depth == arr.size) {
            if (satisfyCandidateKey(arr, relation)) {
                candidateKeys += arr.toList()
                result++
            }
            return
        }
        for (idx in startIdx until relation[0].size) {
            arr[depth] = idx
            comb(depth + 1, idx + 1, arr, relation)
        }
    }

    private fun satisfyCandidateKey(arr: IntArray, relation: Array<Array<String>>): Boolean {
        for (key in candidateKeys) {
            var sameCnt = 0
            for (col in key) {
                if (arr.contains(col)) {
                    sameCnt++
                }
            }
            if (sameCnt == key.size) {
                return false
            }
        }
        val set = mutableSetOf<String>()
        for (row in relation.indices) {
            val sb = StringBuilder()
            for (col in arr) {
                sb.append(relation[row][col])
                sb.append('|')
            }
            val key = sb.toString()
            if (set.add(key).not()) {
                return false
            }
        }
        return true
    }

    fun solution(relation: Array<Array<String>>): Int {
        for (combCnt in 1..relation[0].size) {
            val arr = IntArray(combCnt)
            comb(0, 0, arr, relation)
        }
        return result
    }
}

fun main() {
    val relation = arrayOf(
        arrayOf("100", "ryan", "music", "2"),
        arrayOf("200", "apeach", "math", "2"),
        arrayOf("300", "tube", "computer", "3"),
        arrayOf("400", "con", "computer", "4"),
        arrayOf("500", "muzi", "music", "3"),
        arrayOf("600", "apeach", "music", "2")
    )
    val answer = CandidateKey().solution(relation)
    print(answer)
}