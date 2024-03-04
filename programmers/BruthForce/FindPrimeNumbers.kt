package programmers.BruthForce

class FindPrimeNumbers {
    private val arr = IntArray(7)
    private val visited = BooleanArray(7)
    private val set = HashSet<Int>()

    private fun combination(numbers: String, n: Int, m: Int, depth: Int = 0) {
        if (depth == m) {
            val sb = StringBuilder()
            for (i in 0 until m) {
                sb.append(numbers[arr[i]])
            }
            set.add(sb.toString().toInt())
            return
        }
        for (i in 0 until  n) {
            if (visited[i]) continue
            visited[i] = true
            arr[depth] = i
            combination(numbers, n, m, depth + 1)
            visited[i] = false
        }
    }

    private fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        for (i in 2 .. num) {
            if (i * i > num) break
            if (num % i == 0) return false
        }
        return true
    }

    fun solution(numbers: String): Int {
        for (i in 1 .. numbers.length) {
            combination(numbers, numbers.length, i)
        }
        var answer = 0
        set.forEach { if (isPrime(it)) answer++ }
        return answer
    }
}

fun main() {
    println(FindPrimeNumbers().solution("17"))
}