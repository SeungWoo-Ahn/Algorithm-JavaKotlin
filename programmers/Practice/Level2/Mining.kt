package programmers.Practice.Level2

import java.util.PriorityQueue

class Mining {
    private data class MineralSet(val set: List<Int>) : Comparable<MineralSet> {
        override fun compareTo(other: MineralSet): Int {
            return if (set[0] != other.set[0]) {
                other.set[0] - set[0]
            } else if (set[1] != other.set[1]) {
                other.set[1] - set[1]
            } else {
                other.set[2] - set[2]
            }
        }
    }
    private val fatigue = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(5, 1, 1),
        intArrayOf(25, 5, 1)
    )
    private val pq = PriorityQueue<MineralSet>()

    private fun addMineralSets(pickSum: Int, minerals: Array<String>) {
        var st = 0
        val maxCnt = minOf(pickSum * 5, minerals.size)
        while (st < maxCnt) {
            val set = IntArray(3)
            val en = minOf(st + 5, maxCnt)
            for (i in st until en) {
                when (minerals[i]) {
                    "diamond" -> set[0]++
                    "iron" -> set[1]++
                    "stone" -> set[2]++
                }
            }
            st = en
            pq += MineralSet(set.toList())
        }
    }

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        addMineralSets(picks.sum(), minerals)
        var pickIdx = 0
        var result = 0
        while (pq.isNotEmpty()) {
            if (pickIdx > picks.lastIndex) {
                break
            }
            if (picks[pickIdx] == 0) {
                pickIdx++
                continue
            }
            val set = pq.poll().set
            for (mineralIdx in set.indices) {
                result += set[mineralIdx] * fatigue[pickIdx][mineralIdx]
            }
            picks[pickIdx]--
        }
        return result
    }
}

fun main() {
    val picks = intArrayOf(1, 3, 2)
    val minerals = arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")
    val answer = Mining().solution(picks, minerals)
    print(answer)
}