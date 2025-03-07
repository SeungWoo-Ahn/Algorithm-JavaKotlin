package boj.simulation

import java.util.StringTokenizer

class `15898` {
    private enum class Element(val score: Int) {
        Red(7),
        Blue(5),
        Green(3),
        Yellow(2),
        White(0),
    }

    private data class Block(val efficacy: Int, val element: Element) {
        fun getScore(): Int {
            return efficacy * element.score
        }
    }

    private data class Ingredient(val info: List<List<Block>>) {
        fun getRotated90(): Ingredient {
            val rotated90Info = List(info[0].size) { x ->
                List(info.size) { y ->
                    info[y][info.size - x - 1]
                }
            }
            return Ingredient(rotated90Info)
        }
    }

    private var n = 0
    private lateinit var ingredientList: Array<MutableList<Ingredient>>
    private val startPosList = listOf(
        0 to 0,
        0 to 1,
        1 to 0,
        1 to 1,
    )
    private var maxQuality = 0

    private fun permutation(depth: Int, order: IntArray, used: BooleanArray) {
        if (depth == 3) {
            makeBomb(0, order, getEmptyGamma())
            return
        }
        for (i in ingredientList.indices) {
            if (used[i]) continue
            used[i] = true
            order[depth] = i
            permutation(depth + 1, order, used)
            used[i] = false
        }
    }

    private fun getEmptyGamma(): Array<Array<Block>> {
        return Array(5) {
            Array(5) { Block(efficacy = 0, element = Element.White) }
        }
    }

    private fun makeBomb(depth: Int, order: IntArray, gamma: Array<Array<Block>>) {
        if (depth == order.size) {
            val quality = calcBombQuality(gamma)
            if (quality > maxQuality) {
                maxQuality = quality
            }
            return
        }
        for (rotateCnt in 0 until 4) {
            for (st in startPosList) {
                val copiedGamma = Array(gamma.size) { gamma[it].copyOf() }
                val ingredient = ingredientList[order[depth]][rotateCnt]
                putIngredient(copiedGamma, ingredient, st)
                makeBomb(depth + 1, order, copiedGamma)
            }
        }
    }

    private fun calcBombQuality(gamma: Array<Array<Block>>): Int {
        return gamma.sumOf { arr -> arr.sumOf { it.getScore() } }
    }

    private fun putIngredient(gamma: Array<Array<Block>>, ingredient: Ingredient, st: Pair<Int, Int>) {
        for (x in ingredient.info.indices) {
            for (y in ingredient.info[x].indices) {
                val nx = x + st.first
                val ny = y + st.second
                val (ief, iel) = ingredient.info[x][y]
                val (gef, gel) = gamma[nx][ny]
                var nef = ief + gef
                if (nef > 9) {
                    nef = 9
                } else if (nef < 0) {
                    nef = 0
                }
                val nel = if (iel == Element.White) gel else iel
                gamma[nx][ny] = Block(nef, nel)
            }
        }
    }

    private fun addRotatedIngredients() {
        for (i in ingredientList.indices) {
            for (j in 0 until 3) {
                ingredientList[i] += ingredientList[i][j].getRotated90()
            }
        }
    }

    fun solution() {
        input()
        addRotatedIngredients()
        permutation(0, IntArray(3), BooleanArray(n))
        print(maxQuality)
    }

    private fun input() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()
        ingredientList = Array(n) { mutableListOf() }
        var st: StringTokenizer
        repeat(n) {
            val efficacyInfo = Array(4) {
                st = StringTokenizer(readLine(), " ")
                IntArray(4) { st.nextToken().toInt() }
            }
            val elementInfo = Array(4) {
                st = StringTokenizer(readLine(), " ")
                Array(4) { st.nextToken() }
            }
            val info = List(4) { x ->
                List(4) { y ->
                    Block(
                        efficacy = efficacyInfo[x][y],
                        element = elementInfo[x][y].toElement()
                    )
                }
            }
            ingredientList[it] += Ingredient(info)
        }
    }

    private fun String.toElement(): Element {
        return when (this) {
            "R" -> Element.Red
            "B" -> Element.Blue
            "G" -> Element.Green
            "Y" -> Element.Yellow
            "W" -> Element.White
            else -> throw IllegalArgumentException("missing: $this")
        }
    }
}

fun main() {
    `15898`().solution()
}