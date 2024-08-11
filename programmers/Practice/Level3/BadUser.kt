package programmers.Practice.Level3

class BadUser {
    private var possible: Array<MutableList<Int>> = arrayOf()
    private val set = mutableSetOf<String>()

    private fun isPossible(id: String, bannedId: String): Boolean {
        if (id.length != bannedId.length) return false
        for (i in id.indices) {
            if (bannedId[i] == '*') continue
            if (id[i] != bannedId[i]) return false
        }
        return true
    }

    private fun backtracking(n: Int, depth: Int, used: IntArray) {
        if (depth == n) {
            val key = used.joinToString()
            set += key
            return
        }
        for (candidate in possible[depth]) {
            if (used[candidate] == 1) continue
            used[candidate] = 1
            backtracking(n, depth + 1, used)
            used[candidate] = 0
        }
    }

    fun solution(userId: Array<String>, bannedId: Array<String>): Int {
        val n = bannedId.size
        possible = Array(n) { mutableListOf() }
        for (i in bannedId.indices) {
            for (j in userId.indices) {
                if (isPossible(userId[j], bannedId[i])) {
                    possible[i] += j
                }
            }
        }
        val used = IntArray(userId.size)
        backtracking(n, 0, used)
        return set.size
    }
}

fun main() {
    val userId = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
    val bannedId = arrayOf("fr*d*", "*rodo", "******", "******")
    val answer = BadUser().solution(userId, bannedId)
    print(answer)
}