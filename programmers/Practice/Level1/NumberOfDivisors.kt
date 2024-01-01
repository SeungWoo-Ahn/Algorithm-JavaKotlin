package programmers.Practice.Level1

class NumberOfDivisors {
    fun solution(left: Int, right: Int): Int {
        var answer = 0
        for(i in left..right) {
            if(isNumberOfDivisorsOdd(i)) {
                answer += i
            } else answer -= i
        }
        return answer
    }

    fun isNumberOfDivisorsOdd(num: Int): Boolean {
        if (num < 2) return false
        var numberOfDivisors = 2
        for(i in 2 until num) {
            if (num % i == 0) {
                numberOfDivisors++
            }
        }
        return numberOfDivisors % 2 == 0
    }
}

fun main() {
    println(NumberOfDivisors().solution(13, 17))
}