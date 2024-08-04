package programmers.DfsBfs

class TransformWord {
    private var answer = 51

    private fun backtracking(
        depth: Int,
        text: String,
        target: String,
        words: Array<String>,
        visited: BooleanArray
    ) {
        if (text == target) {
            answer = minOf(answer, depth)
            return
        }
        if (depth == words.size) return
        for (i in words.indices) {
            if (visited[i]) continue
            if (!text.diffOnlyOne(words[i])) continue
            visited[i] = true
            backtracking(depth + 1, words[i], target, words, visited)
            visited[i] = false
        }
    }

    private fun String.diffOnlyOne(text: String): Boolean {
        var diffCnt = 0
        for (i in indices) {
            if (this[i] == text[i]) continue
            if (++diffCnt > 1) return false
        }
        return diffCnt == 1
    }

    fun solution(begin: String, target: String, words: Array<String>): Int {
        val visited = BooleanArray(words.size)
        backtracking(0, begin, target, words, visited)
        return if (answer <= 50) answer
        else 0
    }
}

fun main() {
    val begin = "hit"
    val target = "cog"
    val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")
    val answer = TransformWord().solution(begin, target, words)
    print(answer)
}