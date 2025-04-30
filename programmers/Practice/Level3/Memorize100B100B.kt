package programmers.Practice.Level3

class Memorize100B100B {
    private fun getCntList(e: Int): IntArray {
        val cntList = IntArray(e + 1) { 1 }
        for (base in 2..e) {
            for (num in base..e step base) {
                cntList[num]++
            }
        }
        return cntList
    }

    private fun getAccRev(cntList: IntArray): IntArray {
        val accRev = IntArray(cntList.size)
        var max = cntList.last()
        var maxIdx = cntList.lastIndex
        accRev[accRev.lastIndex] = maxIdx
        for (i in accRev.lastIndex - 1 downTo 0) {
            if (cntList[i] >= max) {
                max = cntList[i]
                maxIdx = i
            }
            accRev[i] = maxIdx
        }
        return accRev
    }

    fun solution(e: Int, starts: IntArray): IntArray {
        val cntList = getCntList(e)
        val accRev = getAccRev(cntList)
        return IntArray(starts.size) { idx -> accRev[starts[idx]] }
    }
}

fun main() {
    val e = 8
    val starts = intArrayOf(1, 3, 7)
    val answer = Memorize100B100B().solution(e, starts)
    print(answer.joinToString())
}