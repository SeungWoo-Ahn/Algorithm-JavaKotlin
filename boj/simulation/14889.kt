package boj.simulation

import java.util.StringTokenizer
import kotlin.math.abs

class `14889` {
    private var s: Array<IntArray> = arrayOf()
    private var teamOne = intArrayOf()
    private var sArr = intArrayOf()
    private var combinationCnt = 1.0
    private var cnt = 0.0
    private var answer = Int.MAX_VALUE

    private fun combination(n: Int, depth: Int, startIdx: Int) {
        if (cnt == combinationCnt) return
        if (depth == n / 2) {
            cnt++
            makeTeams(n)
            return
        }
        for (i in startIdx until n) {
            teamOne[depth] = i
            combination(n, depth + 1, i + 1)
        }
    }

    private fun makeTeams(n: Int) {
        var idx = 0
        val teamTwo = arrayListOf<Int>()
        for (i in 0 until n) {
            if (idx < n / 2 && i == teamOne[idx]) idx++
            else teamTwo.add(i)
        }
        val score = IntArray(2)
        matchPlayer(score, 0, teamOne, 0, 0)
        matchPlayer(score, 1, teamTwo.toIntArray(), 0, 0)
        val gap = abs(score[0] - score[1])
        if (gap < answer) answer = gap
    }

    private fun matchPlayer(score: IntArray, teamIdx: Int, team: IntArray, depth: Int, startIdx: Int) {
        if (depth == 2) {
            score[teamIdx] += calculate()
            return
        }
        for (i in startIdx until team.size) {
            sArr[depth] = team[i]
            matchPlayer(score, teamIdx, team, depth + 1, i + 1)
        }
    }

    private fun calculate(): Int {
        return s[sArr[0]][sArr[1]] + s[sArr[1]][sArr[0]]
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        s = Array(n) {
            val st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        teamOne = IntArray(n / 2)
        sArr = IntArray(2)
        for (i in n downTo (n / 2 + 1)) { combinationCnt = combinationCnt * i / (i - n / 2) }
        also { combinationCnt /= 2 }
        combination(n, 0, 0)
        println(answer)
    }
}

fun main() {
    `14889`().solution()
}