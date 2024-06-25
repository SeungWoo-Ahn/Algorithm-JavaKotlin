package boj.string

class `28702` {
    private fun String.isInt() = first() in '0' .. '9'

    private fun Int.toFizzBuzz(): String {
        var outPut = ""
        if (this % 3 == 0) outPut += "Fizz"
        if (this % 5 == 0) outPut += "Buzz"
        return outPut.ifEmpty { this.toString() }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val input = Array(3) { readLine() }
        for ((i, text) in input.withIndex()) {
            if (!text.isInt()) continue
            val target = text.toInt() + (3 - i)
            print(target.toFizzBuzz())
            return
        }
    }
}

fun main() {
    `28702`().solution()
}