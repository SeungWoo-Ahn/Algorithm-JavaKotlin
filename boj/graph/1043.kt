package boj.graph

import java.util.StringTokenizer

class `1043` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        var st: StringTokenizer
        st = StringTokenizer(readLine())
        val truthSize = st.nextToken().toInt()
        if (truthSize == 0) {
            println(m)
            return
        }
        val checked = BooleanArray(n + 1)
        repeat(truthSize) {
            checked[st.nextToken().toInt()] = true
        }
        val knowTruth = ArrayDeque<Int>()
        val party = mutableListOf<Pair<Int, IntArray>>()
        repeat(m) { idx ->
            st = StringTokenizer(readLine())
            val size = st.nextToken().toInt()
            val members = IntArray(size) { st.nextToken().toInt() }
            val canLie = !members.any { checked[it] }
            if (canLie) party.add(idx to members)
            else {
                members.forEach {
                    if (!checked[it]) {
                        knowTruth.add(it)
                    }
                }
            }
        }
        while (knowTruth.isNotEmpty()) {
            val num = knowTruth.removeFirst()
            checked[num] = true
            val removedParties = mutableListOf<Int>()
            for (i in party.indices) {
                if (party[i].second.contains(num)) {
                    removedParties.add(party[i].first)
                    for (member in party[i].second) {
                        if (!checked[member])
                            knowTruth.add(member)
                    }
                }
            }
            removedParties.forEach { idx ->
                party.removeIf { it.first == idx }
            }
        }
        println(party.size)
    }
}

fun main() {
    `1043`().solution()
}