package boj.binary_search_tree

import java.util.TreeMap

class `7662` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        val treeMap = TreeMap<Int, Int>()
        repeat(t) {
            treeMap.clear()
            repeat(readLine().toInt()) {
                val (command, num) = readLine().split(" ")
                val number = num.toInt()
                when (command) {
                    "I" -> {
                        treeMap[number] = treeMap.getOrDefault(number, 0) + 1
                    }
                    "D" -> {
                        if (treeMap.isNotEmpty()) {
                            val key = if (number == 1) treeMap.lastKey() else treeMap.firstKey()
                            val keyCnt = treeMap[key]
                            if (keyCnt == 1) treeMap.remove(key)
                            else treeMap[key] = keyCnt!! - 1
                        }
                    }
                }
            }
            if (treeMap.isEmpty()) {
                sb.append("EMPTY\n")
            } else {
                sb.append("${treeMap.lastKey()} ${treeMap.firstKey()}\n")
            }
        }
        println(sb)
    }
}

fun main() {
    `7662`().solution()
}