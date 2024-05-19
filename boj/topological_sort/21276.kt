package boj.topological_sort

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import java.util.StringTokenizer

class `21276` {
    private var people: Array<String> = arrayOf()
    private val peopleMap = HashMap<String, Int>()
    private var adj: Array<MutableList<Int>> = arrayOf()
    private var inDegree = intArrayOf()
    private val roots = mutableListOf<String>()
    private var child: Array<MutableList<String>> = arrayOf()

    private fun initProps(n: Int) {
        people = Array(n) { "" }
        adj = Array(n) { mutableListOf() }
        inDegree = IntArray(n)
        child = Array(n) { mutableListOf() }
    }

    private fun getIdx(name: String): Int {
        return peopleMap[name]!!
    }

    private fun investigateFamily() {
        val q = LinkedList<Int>() as Queue<Int>
        for (i in inDegree.indices) {
            if (inDegree[i] == 0) {
                roots.add(people[i])
                q.offer(i)
            }
        }
        while (q.isNotEmpty()) {
            val idx = q.poll()
            for (nxt in adj[idx]) {
                inDegree[nxt]--
                if (inDegree[nxt] == 0) {
                    child[idx].add(people[nxt])
                    q.offer(nxt)
                }
            }
        }
    }

    private fun printResult() {
        val sb = StringBuilder()
        sb.appendLine(roots.size)
        sb.appendLine(roots.joinToString(" "))
        for (i in child.indices) {
            val childArr = child[i].toList().sorted()
            sb.appendLine("${people[i]} ${childArr.size} ${childArr.joinToString(" ")}")
        }
        println(sb)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        initProps(n)
        val st = StringTokenizer(readLine())
        val pq = PriorityQueue<String>()
        repeat(n) { pq.add(st.nextToken()) }
        repeat(n) { idx ->
            val name = pq.poll()
            people[idx] = name
            peopleMap[name] = idx
        }
        val m = readLine().toInt()
        repeat(m) {
            val (a, b) = readLine().split(" ")
            adj[getIdx(b)].add(getIdx(a))
            inDegree[getIdx(a)]++
        }
        investigateFamily()
        printResult()
    }
}

fun main() {
    `21276`().solution()
}