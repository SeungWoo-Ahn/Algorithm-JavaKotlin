package programmers.Practice.Level3

class SelectDice {
    private lateinit var map: Array<Map<Int, Int>>
    private lateinit var result: IntArray
    private var maxWin = 0

    private fun setMap(dice: Array<IntArray>) {
        map = Array(dice.size) { idx ->
            dice[idx].toList().groupingBy { it }.eachCount()
        }
    }

    private fun combination(n: Int, startIdx: Int, selected: ArrayDeque<Int>) {
        if (selected.size == n / 2) {
            play(n, selected)
            return
        }
        for (i in startIdx until n) {
            selected.addLast(i)
            combination(n, i + 1, selected)
            selected.removeLast()
        }
    }

    private fun play(n: Int, selected: ArrayDeque<Int>) {
        val aSelect = selected.toList()
        val bSelect = (0 until n).toList().filterNot { it in aSelect }
        val a = getDiceSum(aSelect)
        val b = getDiceSum(bSelect)
        var win = 0
        var draw = 0
        var loss = 0
        for ((sumA, cntA) in a) {
            for ((sumB, cntB) in b) {
                val score = cntA * cntB
                when {
                    sumA > sumB -> win += score
                    sumA == sumB -> draw += score
                    else -> loss += score
                }
            }
        }
        if (win > maxWin) {
            maxWin = win
            result = aSelect.map { it + 1 }.toIntArray()
        }
        if (loss > maxWin) {
            maxWin = loss
            result = bSelect.map { it + 1 }.toIntArray()
        }
    }

    private fun getDiceSum(select: List<Int>): Map<Int, Int> {
        var diceSum = map[select[0]].toMutableMap()
        for (i in 1 until select.size) {
            val tempSum = mutableMapOf<Int, Int>()
            for ((k1, v1) in diceSum) {
                for ((k2, v2) in map[select[i]]) {
                    tempSum[k1 + k2] = tempSum.getOrDefault(k1 + k2, 0) + v1 * v2
                }
            }
            diceSum = tempSum
        }
        return diceSum
    }

    fun solution(dice: Array<IntArray>): IntArray {
        val n = dice.size
        val selected = ArrayDeque<Int>().apply { addLast(0) }
        setMap(dice)
        combination(n, 1, selected)
        return result
    }
}

fun main() {
    val dice = arrayOf(
        intArrayOf(1, 2, 3, 4, 5, 6),
        intArrayOf(3, 3, 3, 3, 4, 4),
        intArrayOf(1, 3, 3, 4, 4, 4),
        intArrayOf(1, 1, 4, 4, 5, 5)
    )
    val answer = SelectDice().solution(dice)
    print(answer.joinToString())
}