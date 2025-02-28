package boj.simulation

class `16637` {
    private interface CalcValue

    private data class Number(val num: Int) : CalcValue

    private sealed interface Operation : CalcValue {
        fun calc(num1: Number, num2: Number): Int

        data object Plus : Operation {
            override fun calc(num1: Number, num2: Number): Int {
                return num1.num + num2.num
            }
        }

        data object Minus : Operation {
            override fun calc(num1: Number, num2: Number): Int {
                return num1.num - num2.num
            }
        }

        data object Multiply : Operation {
            override fun calc(num1: Number, num2: Number): Int {
                return num1.num * num2.num
            }
        }
    }

    private val numbers = mutableListOf<Number>()
    private val operations = mutableListOf<Operation>()
    private var max = Int.MIN_VALUE

    private fun comb(depth: Int, used: BooleanArray) {
        if (depth == operations.size) {
            val calcValues = getCalcValues(used)
            val calcResult = calcCalcValues(calcValues)
            if (calcResult > max) {
                max = calcResult
            }
            return
        }
        if (depth == 0) {
            used[depth] = true
            comb(1, used)
            used[depth] = false
            comb(1, used)
        } else {
            if (used[depth - 1].not()) {
                used[depth] = true
                comb(depth + 1, used)
                used[depth] = false
            }
            comb(depth + 1, used)
        }
    }

    private fun getCalcValues(used: BooleanArray): ArrayDeque<CalcValue> {
        val calcValues = ArrayDeque<CalcValue>()
        for (i in used.indices) {
            if (used[i]) {
                val num1 = numbers[i]
                val num2 = numbers[i + 1]
                val op = operations[i]
                calcValues += Number(op.calc(num1, num2))
            } else {
                if (i == 0) {
                    calcValues += numbers[i]
                }
                calcValues += operations[i]
                if (i < used.lastIndex && used[i + 1].not() || i == used.lastIndex) {
                    calcValues += numbers[i + 1]
                }
            }
        }
        return calcValues
    }

    private fun calcCalcValues(calcValues: ArrayDeque<CalcValue>): Int {
        while (calcValues.size > 1) {
            val num1 = calcValues.removeFirst() as Number
            val op = calcValues.removeFirst() as Operation
            val num2 = calcValues.removeFirst() as Number
            val resultNum = Number(op.calc(num1, num2))
            calcValues.addFirst(resultNum)
        }
        val result = calcValues.first() as Number
        return result.num
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val formula = readLine()
        if (n == 1) {
            println(formula)
            return
        }
        for (i in 0 until n) {
            if (i % 2 == 0) {
                numbers += Number(formula[i].digitToInt())
            } else {
                operations += when (formula[i]) {
                    '+' -> Operation.Plus
                    '-' -> Operation.Minus
                    '*' -> Operation.Multiply
                    else -> throw IllegalArgumentException()
                }
            }
        }
        val used = BooleanArray(operations.size)
        comb(0, used)
        print(max)
    }
}

fun main() {
    `16637`().solution()
}