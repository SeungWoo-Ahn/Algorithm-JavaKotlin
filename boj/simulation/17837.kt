package boj.simulation

import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import java.util.StringTokenizer

class `17837` {
    private data class Node(val x: Int, val y: Int)
    private data class Piece(val num: Int, var dir: Int, var pos: Node)
    private data class Square(val color: Int, val pieces: Stack<Piece> = Stack())

    private var board: Array<Array<Square>> = arrayOf()
    private var pieces: Array<Piece> = arrayOf()
    private val d = listOf(
        Node(0, 1),
        Node(0, -1),
        Node(-1, 0),
        Node(1, 0)
    )
    private val whiteStack = Stack<Piece>()
    private val redQueue = LinkedList<Piece>() as Queue<Piece>
    private var turn = 0

    private fun movePiece(num: Int, time: Int = 1): Boolean {
        val piece = pieces[num]
        val (x, y) = piece.pos
        val nx = x + d[piece.dir].x
        val ny = y + d[piece.dir].y
        if (nx !in board.indices || ny !in board.indices || board[nx][ny].color == BLUE) {
            if (time == 1) {
                pieces[num].dir = if (piece.dir % 2 == 0) piece.dir + 1 else piece.dir - 1
                return movePiece(num, time + 1)
            } else return true
        }
        when (board[nx][ny].color) {
            WHITE -> {
                while (true) {
                    val pop = board[x][y].pieces.pop()
                    whiteStack.push(pop)
                    pieces[pop.num].pos = Node(nx, ny)
                    if (pop.num == num) break
                }
                while (whiteStack.isNotEmpty()) {
                    board[nx][ny].pieces.push(pieces[whiteStack.pop().num])
                }
            }
            RED -> {
                while (true) {
                    val pop = board[x][y].pieces.pop()
                    redQueue.offer(pop)
                    pieces[pop.num].pos = Node(nx, ny)
                    if (pop.num == num) break
                }
                while (redQueue.isNotEmpty()) {
                    board[nx][ny].pieces.push(pieces[redQueue.poll().num])
                }
            }
        }
        return board[nx][ny].pieces.size < 4
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        board = Array(n) {
            val st = StringTokenizer(readLine())
            Array(n) { Square(st.nextToken().toInt()) }
        }
        pieces = Array(k) { num ->
            val (x, y, dir) = readLine().split(" ").map { it.toInt() }
            val piece = Piece(num = num, dir = dir - 1, pos = Node(x - 1, y - 1))
            board[x - 1][y - 1].pieces.push(piece)
            piece
        }
        while (turn <= 1000) {
            turn++
            for (num in pieces.indices) {
                if (!movePiece(num)) {
                    println(turn)
                    return
                }
            }
        }
        println(-1)
    }

    companion object {
        const val WHITE = 0
        const val RED = 1
        const val BLUE = 2
    }
}

fun main() {
    `17837`().solution()
}