package programmers.Practice.Level2

import java.util.LinkedList
import java.util.Queue

class ServerExpansionsNumber {
    private data class Server(val count: Int, val returnTime: Int)

    fun solution(players: IntArray, m: Int, k: Int): Int {
        val serverQ = LinkedList<Server>() as Queue<Server>
        var serverCnt = 0
        var addCnt = 0
        for (i in players.indices) {
            while (serverQ.isNotEmpty() && serverQ.peek().returnTime <= i) {
                serverCnt -= serverQ.poll().count
            }
            val needCnt = players[i] / m - serverCnt
            if (needCnt > 0) {
                addCnt += needCnt
                serverCnt += needCnt
                serverQ += Server(needCnt, i + k)
            }
        }
        return addCnt
    }
}

fun main() {
    val players = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
    val m = 3
    val k = 5
    val answer = ServerExpansionsNumber().solution(players, m, k)
    print(answer)
}