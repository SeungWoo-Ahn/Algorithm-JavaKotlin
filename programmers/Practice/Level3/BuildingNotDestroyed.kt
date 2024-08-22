package programmers.Practice.Level3

class BuildingNotDestroyed {

    /**
     * @param board 건물의 맵, 각 칸에는 건물의 내구도가 있음
     * @param skill [type, r1, c1, r2, c2, degree]
     * type: 1 (공격), 2 (힐)
     * (r1, c1) ~ (r2, c2)에 해당하는 직사각형 영역에 degree 만큼 내구도를 증감함
     * @return 모든 skill 을 사용한 후 남은 내구도가 1 이상인 건물의 수
     *
     * 누적합 문제임, 힌트 봤음, 단순히 O(NMK)로 풀면 10^11 넘어 통과 못함
     * [0, 0, 0, 0, 0] 여기서 0 ~ 2 구간에 +n를 하고 싶다면
     * [n, 0, 0, -n, 0] 처럼 시작 구간엔 (+n), 끝 구간 + 1엔 (-n)을 두고 왼쪽부터 누적합을 진행하면
     * [n, n, n, 0, 0] 으로 필요한 구간안에만 +n이 배치됨
     * 마찬가지로 2차원 배열에서 (r1, c1) ~ (r2, c2) 구간에 +n을 하고 싶다면,
     * (r1, c1) 과 (r2 + 1, c2 + 1)에 +n을 배치하고, (r1, c2 + 1) 과 (r2 + 1, c1)에 -n을 배치한 후
     * 행방향 누적합, 열방향 누적합을 차례대로 진행하면 모든 구간의 증감을 한 번에 구할 수 있음
     */
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val n = board.size
        val m = board[0].size
        val sBoard = Array(n + 1) { IntArray(m + 1) } // 끝 구간인 n, m의 다음 칸을 활용하기 위해 board 크기보다 1씩 큼
        for (s in skill) {
            val num = if (s[0] == 1) -s[5] else s[5]
            sBoard[s[1]][s[2]] += num
            sBoard[s[3] + 1][s[4] + 1] += num
            sBoard[s[1]][s[4] + 1] += -num
            sBoard[s[3] + 1][s[2]] += -num
        }
        // 행방향 누적합
        for (i in 0..n) {
            for (j in 1..m) {
                sBoard[i][j] += sBoard[i][j - 1]
            }
        }
        // 열방향 누적합
        for (j in 0..m) {
            for (i in 1..n) {
                sBoard[i][j] += sBoard[i - 1][j]
            }
        }
        var answer = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] + sBoard[i][j] > 0) {
                    answer++
                }
            }
        }
        return answer
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    val skill = arrayOf(
        intArrayOf(1, 1, 1, 2, 2, 4),
        intArrayOf(1, 0, 0, 1, 1, 2),
        intArrayOf(2, 2, 0, 2, 0, 100)
    )
    val answer = BuildingNotDestroyed().solution(board, skill)
    print(answer)
}