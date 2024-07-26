package boj.greedy

class `10775` {
    private var docked = booleanArrayOf()
    private var lastDocked = intArrayOf()

    private fun docking(limit: Int): Boolean {
        var upperBound = limit
        if (lastDocked[limit] != 0) {
            upperBound = lastDocked[limit] - 1
        }
        for (i in upperBound downTo 1) {
            if (!docked[i]) {
                docked[i] = true
                lastDocked[limit] = i
                return true
            }
        }
        return false
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val g = readLine().toInt()
        val p = readLine().toInt()
        docked = BooleanArray(g + 1)
        lastDocked = IntArray(g + 1)
        repeat(p) {
            val limit = readLine().toInt()
            if (!docking(limit)) {
                print(it)
                return
            }
        }
        print(p)
    }
}

fun main() {
    `10775`().solution()
}