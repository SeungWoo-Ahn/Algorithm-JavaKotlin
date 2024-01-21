package boj.recursion

import java.lang.StringBuilder

class `17478` {
    private val sb = StringBuilder()
    private val prefix = "____"
    private val line1 = "\"재귀함수가 뭔가요?\"\n"
    private val line2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n"
    private val line3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n"
    private val line4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n"
    private val line5 = "라고 답변하였지.\n"
    private val answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n"

    private fun recursion(N: Int, n: Int) {
        if (n == 0) {
            repeat(N) { sb.append(prefix) }
            sb.append(line1)
            repeat(N) { sb.append(prefix) }
            sb.append(answer)
            repeat(N) { sb.append(prefix) }
            sb.append(line5)
            return
        }
        repeat(N - n) { sb.append(prefix) }
        sb.append(line1)
        repeat(N - n) { sb.append(prefix) }
        sb.append(line2)
        repeat(N - n) { sb.append(prefix) }
        sb.append(line3)
        repeat(N - n) { sb.append(prefix) }
        sb.append(line4)
        recursion(N, n - 1)
        repeat(N - n) { sb.append(prefix) }
        sb.append(line5)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val N = readLine().toInt()
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n")
        recursion(N, N)
        println(sb)
    }
}

fun main() {
    `17478`().solution()
}