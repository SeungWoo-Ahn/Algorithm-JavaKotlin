package programmers.BruthForce

class KFatigue {
    private val arr = IntArray(9)
    private val visited = BooleanArray(9)
    private var max = 0

    private fun backtracking(k: Int, dungeons: Array<IntArray>, depth: Int = 0) {
        if (depth == dungeons.size) {
            check(k, dungeons)
            return
        }
        for (i in dungeons.indices) {
            if (visited[i]) continue
            visited[i] = true
            arr[depth] = i
            backtracking(k, dungeons, depth + 1)
            visited[i] = false
        }
    }

    private fun check(k: Int, dungeons: Array<IntArray>) {
        var cnt = 0
        var fatigue = k
        for (i in dungeons.indices) {
            val dungeon = dungeons[arr[i]]
            if (dungeon[0] > fatigue) break
            fatigue -= dungeon[1]
            cnt++
        }
        if (cnt > max) max = cnt
    }

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        backtracking(k, dungeons)
        return max
    }
}

fun main() {
    val k = 80
    val dungeons = arrayOf(
        intArrayOf(80, 20),
        intArrayOf(50, 40),
        intArrayOf(30, 10),
    )
    println(KFatigue().solution(k, dungeons))
}