package programmers.Practice.Level3

import java.util.LinkedList
import java.util.Queue

class MatchingCards {
    private data class Pos(val x: Int, val y: Int)

    private data class Info(val pos: Pos, val cost: Int)

    private val posByCard = Array(7) { mutableListOf<Pos>() }
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)
    private var result = Int.MAX_VALUE

    private fun setPosByCard(board: Array<IntArray>) {
        for (x in board.indices) {
            for (y in board[x].indices) {
                if (board[x][y] != 0) {
                    posByCard[board[x][y]] += Pos(x, y)
                }
            }
        }
    }

    private fun getExistCards(): List<Int> {
        val existCards = mutableListOf<Int>()
        for (card in posByCard.indices) {
            if (posByCard[card].isNotEmpty()) {
                existCards += card
            }
        }
        return existCards
    }

    private fun backtracking(
        depth: Int,
        cost: Int,
        cursor: Pos,
        existCards: List<Int>,
        used: BooleanArray,
        board: Array<IntArray>,
    ) {
        if (cost >= result) {
            return
        }
        if (depth == existCards.size) {
            result = cost
            return
        }
        for (i in existCards.indices) {
            if (used[i]) continue
            val pos1 = posByCard[existCards[i]][0]
            val pos2 = posByCard[existCards[i]][1]
            val cost1 = getMinCost(cursor, pos1, board) + getMinCost(pos1, pos2, board) + 2
            val cost2 = getMinCost(cursor, pos2, board) + getMinCost(pos2, pos1, board) + 2
            val nextCost = minOf(cost1, cost2)
            val nextCursor = if (nextCost == cost1) pos2 else pos1
            used[i] = true
            board[pos1.x][pos1.y] = 0
            board[pos2.x][pos2.y] = 0
            backtracking(depth + 1, cost + nextCost, nextCursor, existCards, used, board)
            used[i] = false
            board[pos1.x][pos1.y] = existCards[i]
            board[pos2.x][pos2.y] = existCards[i]
        }
    }

    private fun getMinCost(from: Pos, to: Pos, board: Array<IntArray>): Int {
        if (from == to) {
            return 0
        }
        val q = LinkedList<Info>() as Queue<Info>
        val visited = Array(board.size) { BooleanArray(board[it].size) }
        q += Info(from, 0)
        visited[from.x][from.y] = true
        while (q.isNotEmpty()) {
            val (pos, cost) = q.poll()
            if (pos == to) {
                return cost
            }
            val (x, y) = pos
            for (i in 0 until 4) { // ctrl 이동
                var r = 0
                while (r < board.size) {
                    val cx = x + dx[i] * (r + 1)
                    val cy = y + dy[i] * (r + 1)
                    if (outOfBoundary(cx, cy, board)) break
                    r++
                    if (board[cx][cy] != 0) break
                }
                val nx = x + dx[i] * r
                val ny = y + dy[i] * r
                if (visited[nx][ny]) continue
                q += Info(Pos(nx, ny), cost + 1)
                visited[nx][ny] = true
            }
            for (i in 0 until 4) { // 한 칸 이동
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (outOfBoundary(nx, ny, board) || visited[nx][ny]) continue
                q += Info(Pos(nx, ny), cost + 1)
                visited[nx][ny] = true
            }
        }
        return -1
    }

    private fun outOfBoundary(x: Int, y: Int, board: Array<IntArray>): Boolean {
        return x !in board.indices || y !in board[x].indices
    }

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        setPosByCard(board)
        val existCards = getExistCards()
        val used = BooleanArray(existCards.size)
        backtracking(0, 0, Pos(r, c), existCards, used, board)
        return result
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(1, 0, 0, 3),
        intArrayOf(2, 0, 0, 0),
        intArrayOf(0, 0, 0, 2),
        intArrayOf(3, 0, 1, 0)
    )
    val r = 1
    val c = 0
    val answer = MatchingCards().solution(board, r, c)
    print(answer)
}