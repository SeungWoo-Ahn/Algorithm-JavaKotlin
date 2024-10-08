package programmers.Practice.Level2

import kotlin.math.abs

class MaximizeFormula {
    private interface Expression

    private data class Operand(val num: Long) : Expression

    private sealed interface Operator : Expression {
        fun proceed(a: Operand, b: Operand): Operand

        data object Plus : Operator {
            override fun proceed(a: Operand, b: Operand): Operand {
                return Operand(a.num + b.num)
            }
        }

        data object Minus : Operator {
            override fun proceed(a: Operand, b: Operand): Operand {
                return Operand(a.num - b.num)
            }
        }

        data object Multiply : Operator {
            override fun proceed(a: Operand, b: Operand): Operand {
                return Operand(a.num * b.num)
            }
        }
    }

    private class ExpressionParser(private val expression: String) {
        private val parsedExpression = mutableListOf<Expression>()
        private val operatorSet = mutableSetOf<Operator>()

        fun parse() {
            var st = 0
            var en = 0
            while (true) {
                if (en == expression.length) {
                    parsedExpression += expression.toOperand(st, en)
                    break
                }
                if (expression[en].isDigit()) {
                    en++
                } else {
                    val operation = when (expression[en]) {
                        '+' -> Operator.Plus
                        '-' -> Operator.Minus
                        else -> Operator.Multiply
                    }
                    parsedExpression += expression.toOperand(st, en)
                    parsedExpression += operation
                    operatorSet += operation
                    st = ++en
                }
            }
        }

        private fun Char.isDigit() = (this - '0') in 0..9

        private fun String.toOperand(st: Int, en: Int) = Operand(this.substring(st, en).toLong())

        fun getParsedExpression() = parsedExpression.toList()

        fun getOperations() = operatorSet.toList()

        fun getOperationSize() = operatorSet.size
    }

    private class PriorityGenerator(private val size: Int) {
        private val priorities = mutableListOf<IntArray>()
        private val visited = BooleanArray(size)

        fun generate(
            depth: Int,
            priority: IntArray = IntArray(size)
        ) {
            if (depth == size) {
                priorities += priority.copyOf()
                return
            }
            for (i in 0 until size) {
                if (visited[i]) continue
                priority[depth] = i
                visited[i] = true
                generate(depth + 1, priority)
                visited[i] = false
            }
        }

        fun getPriorities() = priorities.toList()
    }

    private class ExpressionCalculator(
        private val parsedExpression: List<Expression>,
        private val operators: List<Operator>,
    ) {
        fun calc(priority: IntArray): Long {
            val readyQ = ArrayDeque<Expression>()
            val resultQ = ArrayDeque<Expression>()
            readyQ.addAll(parsedExpression)
            for (p in priority) {
                resultQ.clear()
                val operator = operators[p]
                while (readyQ.isNotEmpty()) {
                    val cur = readyQ.removeFirst()
                    if (cur == operator) {
                        val a = resultQ.removeLast() as Operand
                        val b = readyQ.removeFirst() as Operand
                        resultQ.addLast(operator.proceed(a, b))
                    } else {
                        resultQ.addLast(cur)
                    }
                }
                readyQ.addAll(resultQ)
            }
            return (resultQ.first() as Operand).num
        }
    }

    fun solution(expression: String): Long {
        val parser = ExpressionParser(expression).apply { parse() }
        val priorityGenerator = PriorityGenerator(parser.getOperationSize()).apply { generate(0) }

        val parsedExpression = parser.getParsedExpression()
        val operators = parser.getOperations()
        val calculator = ExpressionCalculator(parsedExpression, operators)

        var max = 0L
        for (priority in priorityGenerator.getPriorities()) {
            val result = calculator.calc(priority)
            max = maxOf(max, abs(result))
        }
        return max
    }
}

fun main() {
   val expression = "100-200*300-500+20"
    val answer = MaximizeFormula().solution(expression)
    print(answer)
}