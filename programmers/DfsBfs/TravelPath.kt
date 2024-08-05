package programmers.DfsBfs

import java.util.PriorityQueue

class TravelPath {
    private val map = HashMap<String, Pair<Int, Int>>()
    private var visited = booleanArrayOf()
    private val pq = PriorityQueue<String>()

    private fun backtracking(
        depth: Int,
        port: String,
        result: String,
        tickets: Array<Array<String>>
    ) {
        if (depth == tickets.size) {
            pq += result
            return
        }
        val (s, e) = map[port] ?: return
        for (i in s..e) {
            if (visited[i]) continue
            visited[i] = true
            backtracking(depth + 1, tickets[i][1], result + tickets[i][1], tickets)
            visited[i] = false
        }
    }

    private fun getPath(): Array<String> {
        val path = pq.poll()
        val result = Array(path.length / 3) {
            path.substring(it * 3, it * 3 + 3)
        }
        return result
    }

    fun solution(tickets: Array<Array<String>>): Array<String> {
        tickets.sortBy { it[0] }
        var target: String
        var s = 0
        var e: Int
        while (s < tickets.size) {
            target = tickets[s][0]
            e = s
            while (e < tickets.size - 1 && tickets[e + 1][0] == target) e++
            map[target] = s to e
            s = e + 1
        }
        visited = BooleanArray(tickets.size)
        backtracking(0, "ICN", "ICN", tickets)
        return getPath()
    }
}

fun main() {
    val tickets = arrayOf(
        arrayOf("ICN", "SFO"),
        arrayOf("ICN", "ATL"),
        arrayOf("SFO", "ATL"),
        arrayOf("ATL", "ICN"),
        arrayOf("ATL","SFO")
    )
    val answer = TravelPath().solution(tickets)
    print(answer.joinToString(" "))
}