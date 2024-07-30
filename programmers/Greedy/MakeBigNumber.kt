package programmers.Greedy

class MakeBigNumber {
    fun solution(number: String, k: Int): String {
        val sb = StringBuilder()
        var st = 0
        var en = k
        while (en < number.length) {
            var max = -1
            var maxIdx = st
            for (i in st..en) {
                if (number[i] - '0' > max) {
                    max = number[i] - '0'
                    maxIdx = i
                }
            }
            sb.append(max)
            st = maxIdx + 1
            en++
        }
        return sb.toString()
    }
}

fun main() {
    val answer = MakeBigNumber().solution(
        number = "4177252841",
        k = 4
    )
    print(answer)
}