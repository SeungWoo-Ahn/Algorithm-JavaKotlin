package programmers.Practice.Level2

class TicTacToeAlone {
    private fun cntTarget(target: Char, board: Array<String>): Int {
        return board.sumOf {
            it.count { ch ->
                ch == target
            }
        }
    }

    private fun isTargetWin(target: Char, board: Array<String>): Boolean {
        for (y in board.indices) {
            var cnt = 0
            for (x in board.indices) {
                if (board[x][y] == target) {
                    cnt++
                }
            }
            if (cnt == 3) {
                return true
            }
        }
        for (x in board.indices) {
            var cnt = 0
            for (y in board.indices) {
                if (board[x][y] == target) {
                    cnt++
                }
            }
            if (cnt == 3) {
                return true
            }
        }
        if (board[0][0] == target && board[1][1] == target && board[2][2] == target) {
            return true
        }
        if (board[0][2] == target && board[1][1] == target && board[2][0] == target) {
            return true
        }
        return false
    }

    fun solution(board: Array<String>): Int {
        val oTurns = cntTarget('O', board)
        val xTurns = cntTarget('X', board)
        if (xTurns > oTurns) { // 1. X가 차례를 어김
            return 0
        }
        if (oTurns - xTurns > 1) { // 2. O가 차례를 어김
            return 0
        }
        if (isTargetWin('O', board) && oTurns != xTurns + 1) { // 3. O가 이미 이겼는데, 게임 진행
            return 0
        }
        if (isTargetWin('X', board) && xTurns != oTurns) { // 4. X가 이미 이겼는데, 게임 진행
            return 0
        }
        return 1
    }
}

fun main() {
    val board = arrayOf(
        "XXO",
        ".OX",
        "O.."
    )
    val answer = TicTacToeAlone().solution(board)
    println(answer)
}