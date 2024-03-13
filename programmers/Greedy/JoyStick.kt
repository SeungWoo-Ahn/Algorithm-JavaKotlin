package programmers.Greedy

import kotlin.math.min

class JoyStick {
    fun solution(name: String): Int {
        var answer = 0
        val len = name.length
        var move = len - 1
        for (i in name.indices) {
            answer += min(name[i] - 'A', 'Z' - name[i] + 1)
            var next = i + 1
            while (next < len && name[next] == 'A') {
                next++
            }
            move = min(move, (i * 2) + len - next)
            move = min(move, (len - next) * 2 + i)
        }
        return answer + move
    }
}

fun main() {
    println(JoyStick().solution("JAN"))
}