package boj.simulation

class `20061` {
    private var blueBoard = Array(4) { IntArray(6) }
    private var greenBoard = Array(6) { IntArray(4) }
    private var score = 0

    private fun moveBlueBoard(t: Int, x: Int) {
        val lastIdx = when (t) {
            TWO_ONE -> {
                var idx = 0
                for (j in blueBoard[x].indices) {
                    if (j == blueBoard[x].lastIndex) {
                        idx = j
                        break
                    }
                    if (blueBoard[x][j + 1] == FILL || blueBoard[x + 1][j + 1] == FILL) {
                        idx = j
                        break
                    }
                }
                idx
            }
            else -> {
                var idx = 0
                for (j in blueBoard[x].indices) {
                    if (j == blueBoard[x].lastIndex) {
                        idx = j
                        break
                    }
                    if (blueBoard[x][j + 1] == FILL) {
                        idx = j
                        break
                    }
                }
                idx
            }
        }
        when (t) {
            ONE_ONE -> blueBoard[x][lastIdx] = FILL
            ONE_TWO -> blueBoard[x][lastIdx] = FILL.also { blueBoard[x][lastIdx - 1] = FILL }
            TWO_ONE -> blueBoard[x][lastIdx] = FILL.also { blueBoard[x + 1][lastIdx] = FILL }
        }
        val filledRows = checkBlueBoard(t, lastIdx)
        if (filledRows.isNotEmpty()) {
            removeBlueBoardTiles(filledRows)
            score += filledRows.size
        }
        val removeRow = checkBlueBoardSpecialArea()
        if (removeRow.isNotEmpty()) {
            removeBlueBoardTiles(removeRow)
        }
    }

    private fun checkBlueBoard(t: Int, lastIdx: Int): IntArray {
        val filledRows = mutableListOf<Int>()
        if (blueBoardRowFilled(lastIdx)) filledRows.add(lastIdx)
        if (t == ONE_TWO && blueBoardRowFilled(lastIdx - 1)) filledRows.add(lastIdx - 1)
        return filledRows.toIntArray()
    }

    private fun blueBoardRowFilled(rowNum: Int): Boolean {
        for (i in blueBoard.indices) {
            if (blueBoard[i][rowNum] == EMPTY)
                return false
        }
        return true
    }

    private fun checkBlueBoardSpecialArea(): IntArray {
        val removedRows = mutableListOf<Int>()
        if (tileExistInBlueSpecialArea(1)) removedRows.add(blueBoard[0].lastIndex)
        if (tileExistInBlueSpecialArea(0)) removedRows.add(blueBoard[0].lastIndex - 1)
        return removedRows.toIntArray()
    }

    private fun tileExistInBlueSpecialArea(rowNum: Int): Boolean {
        for (i in blueBoard.indices) {
            if (blueBoard[i][rowNum] == FILL)
                return true
        }
        return false
    }

    private fun removeBlueBoardTiles(rows: IntArray) {
        val newBoard = Array(4) { IntArray(6) }
        for (j in blueBoard[0].lastIndex downTo rows.first() + 1) {
            for (i in blueBoard.indices) {
                newBoard[i][j] = blueBoard[i][j]
            }
        }
        for (j in rows.last() - 1 downTo 0) {
            for (i in blueBoard.indices) {
                newBoard[i][j + rows.size] = blueBoard[i][j]
            }
        }
        blueBoard = newBoard
    }

    private fun moveGreenBoard(t: Int, y: Int) {
        val lastIdx = when (t) {
            ONE_TWO -> {
                var idx = 0
                for (i in greenBoard.indices) {
                    if (i == greenBoard.lastIndex) {
                        idx = i
                        break
                    }
                    if (greenBoard[i + 1][y] == FILL || greenBoard[i + 1][y + 1] == FILL) {
                        idx = i
                        break
                    }
                }
                idx
            }
            else -> {
                var idx = 0
                for (i in greenBoard.indices) {
                    if (i == greenBoard.lastIndex) {
                        idx = i
                        break
                    }
                    if (greenBoard[i + 1][y] == FILL) {
                        idx = i
                        break
                    }
                }
                idx
            }
        }
        when (t) {
            ONE_ONE -> greenBoard[lastIdx][y] = FILL
            ONE_TWO -> greenBoard[lastIdx][y] = FILL.also { greenBoard[lastIdx][y + 1] = FILL }
            TWO_ONE -> greenBoard[lastIdx][y] = FILL.also { greenBoard[lastIdx - 1][y] = FILL }
        }
        val filledCols = checkGreenBoard(t, lastIdx)
        if (filledCols.isNotEmpty()) {
            removeGreenBoardTiles(filledCols)
            score += filledCols.size
        }
        val removedCols = checkGreenBoardSpecialArea()
        if (removedCols.isNotEmpty()) {
            removeGreenBoardTiles(removedCols)
        }
    }

    private fun checkGreenBoard(t: Int, lastIdx: Int): IntArray {
        val filledCols = mutableListOf<Int>()
        if (greenBoard[lastIdx].all { it == FILL }) filledCols.add(lastIdx)
        if (t == TWO_ONE && greenBoard[lastIdx - 1].all { it == FILL }) filledCols.add(lastIdx - 1)
        return filledCols.toIntArray()
    }

    private fun checkGreenBoardSpecialArea(): IntArray {
        val removedCols = mutableListOf<Int>()
        if (greenBoard[1].any { it == FILL }) removedCols.add(greenBoard.lastIndex)
        if (greenBoard[0].any { it == FILL }) removedCols.add(greenBoard.lastIndex - 1)
        return removedCols.toIntArray()
    }

    private fun removeGreenBoardTiles(cols: IntArray) {
        val newBoard = Array(6) { IntArray(4) }
        for (i in greenBoard.lastIndex downTo cols.first() + 1) {
            for (j in greenBoard[i].indices) {
                newBoard[i][j] = greenBoard[i][j]
            }
        }
        for (i in cols.last() - 1 downTo 0) {
            for (j in greenBoard[i].indices) {
                newBoard[i + cols.size][j] = greenBoard[i][j]
            }
        }
        greenBoard = newBoard
    }

    private fun countTiles(): Int {
        var cnt = 0
        for (tiles in blueBoard) {
            cnt += tiles.count { it == FILL }
        }
        for (tiles in greenBoard) {
            cnt += tiles.count { it == FILL }
        }
        return cnt
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        repeat(n) {
            val (t, x, y) = readLine().split(" ").map { it.toInt() }
            moveBlueBoard(t, x)
            moveGreenBoard(t, y)
        }
        println("$score\n${countTiles()}")
    }

    companion object {
        const val ONE_ONE = 1
        const val ONE_TWO = 2
        const val TWO_ONE = 3
        const val EMPTY = 0
        const val FILL = 9
    }
}

fun main() {
    `20061`().solution()
}