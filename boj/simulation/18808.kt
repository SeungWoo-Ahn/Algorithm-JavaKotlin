package boj.simulation

import java.util.StringTokenizer

class `18808` {
    private var notebook = Array(0) { intArrayOf() }

    private fun check(partNotebook: Array<IntArray>, sticker: Array<IntArray>): Boolean {
        for (i in partNotebook.indices) {
            for (j in partNotebook[i].indices) {
                if (partNotebook[i][j] == 1 && sticker[i][j] == 1)
                    return false
            }
        }
        return true
    }

    private fun makePartNotebook(startX: Int, startY: Int, sticker: Array<IntArray>): Array<IntArray> {
        return Array(sticker.size) { idx ->
            notebook[startX + idx].copyOfRange(startY, startY + sticker[0].size)
        }
    }

    private fun rotate(sticker: Array<IntArray>): Array<IntArray> {
        val rotatedSticker = Array(sticker[0].size) { IntArray(sticker.size) }
        for (row in sticker.indices) {
            for (col in sticker[row].indices) {
                rotatedSticker[col][row] = sticker[sticker.size - 1 - row][col]
            }
        }
        return rotatedSticker
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (N, M, K) = readLine().split(" ").map { it.toInt() }
        notebook = Array(N) { IntArray(M) }
        val stickers = Array(K) {
            val (R, C) = readLine().split(" ").map { it.toInt() }
            val sticker = Array(R) { IntArray(C) }
            for (i in 0 until R) {
                val st = StringTokenizer(readLine())
                for (j in 0 until C) {
                    sticker[i][j] = st.nextToken().toInt()
                }
            }
            sticker
        }

        for (sticker in stickers) {
            var rotatedSticker = sticker
            loop@ for(repeat in 1 .. 4) {
                for (row in notebook.indices) {
                    for (col in notebook[0].indices) {
                        if (rotatedSticker.size + row > notebook.size || rotatedSticker[0].size + col > notebook[0].size) continue
                        val partNotebook = makePartNotebook(row, col, rotatedSticker)
                        if (!check(partNotebook, rotatedSticker)) continue
                        for (i in row until row + rotatedSticker.size) {
                            for (j in col until col + rotatedSticker[0].size) {
                                notebook[i][j] = notebook[i][j] or rotatedSticker[i - row][j - col]
                            }
                        }
                        break@loop
                    }
                }
                rotatedSticker = rotate(rotatedSticker)
            }
        }

        var cnt = 0
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (notebook[i][j] == 1) cnt++
            }
        }
        println(cnt)
    }
}

fun main() {
    `18808`().solution()
}