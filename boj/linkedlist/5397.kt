package boj.linkedlist

import java.util.LinkedList

class `5397` {
    private class KeyLogger {
        private val linkedList = LinkedList<Char>()
        private val cursor = linkedList.listIterator()

        fun insert(value: Char) {
            cursor.add(value)
        }

        fun delete() {
            if (cursor.hasPrevious()) {
                cursor.previous()
                cursor.remove()
            }
        }

        fun moveCursorLeft() {
            if (cursor.hasPrevious())
                cursor.previous()
        }

        fun moveCursorRight() {
            if (cursor.hasNext())
                cursor.next()
        }

        fun printLog(): String {
            return linkedList.joinToString("")
        }
    }

    private fun findPassword(log: String): String {
        val keyLogger = KeyLogger()
        for (command in log) {
            when (command) {
                '<' -> keyLogger.moveCursorLeft()
                '>' -> keyLogger.moveCursorRight()
                '-' -> keyLogger.delete()
                else -> keyLogger.insert(command)
            }
        }
        return keyLogger.printLog()
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        repeat(t) {
            val password = findPassword(readLine())
            sb.appendLine(password)
        }
        print(sb)
    }
}

fun main() {
    `5397`().solution()
}