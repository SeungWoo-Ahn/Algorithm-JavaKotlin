package programmers.Practice.Level3

class LockAndKey {
    private fun getBox(arr: Array<IntArray>, target: Int): Pair<Int, Array<IntArray>> {
        var minX = arr.size - 1
        var minY = arr.size - 1
        var maxX = 0
        var maxY = 0
        var cnt = 0
        for (x in arr.indices) {
            for (y in arr.indices) {
                if (arr[x][y] == target) {
                    minX = minOf(minX, x)
                    minY = minOf(minY, y)
                    maxX = maxOf(maxX, x)
                    maxY = maxOf(maxY, y)
                    cnt++
                }
            }
        }
        if (cnt == 0) {
            return 0 to arrayOf()
        }
        val h = maxX - minX + 1
        val w = maxY - minY + 1
        return cnt to Array(h) { x ->
            IntArray(w) { y ->
                arr[x + minX][y + minY]
            }
        }
    }

    private fun getLockBoxList(lockBox: Array<IntArray>): List<Array<IntArray>> {
        val lockBoxList = mutableSetOf<Array<IntArray>>()
        var box = lockBox
        lockBoxList += box
        repeat(3) {
            box = rotate(box)
            lockBoxList += box
        }
        return lockBoxList.toList()
    }

    private fun rotate(arr: Array<IntArray>): Array<IntArray> {
        return Array(arr[0].size) { x ->
            IntArray(arr.size) { y ->
                arr[arr.size - y - 1][x]
            }
        }
    }

    private fun isAvailable(keyBox: Array<IntArray>, lockBox: Array<IntArray>): Boolean {
        return lockBox.size <= keyBox.size && lockBox[0].size <= keyBox[0].size
    }

    private fun canUnlock(
        sx: Int,
        sy: Int,
        hCnt: Int,
        keyBox: Array<IntArray>,
        lockBox: Array<IntArray>
    ): Boolean {
        val h = lockBox.size
        val w = lockBox[0].size
        var pCnt = 0
        val key = Array(h) { x ->
            IntArray(w) { y ->
                if (keyBox[x + sx][y + sy] == 1) {
                    pCnt++
                    1
                } else {
                    0
                }
            }
        }
        return if (pCnt != hCnt) false
        else isFit(key, lockBox)
    }

    private fun isFit(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        for (x in key.indices) {
            for (y in key[0].indices) {
                if (key[x][y] == lock[x][y]) {
                    return false
                }
            }
        }
        return true
    }

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val (pCnt, keyBox) = getBox(key, 1)
        val (hCnt, lockBox) = getBox(lock, 0)
        if (hCnt == 0) {
            return true
        }
        if (pCnt == 0 || pCnt < hCnt) {
            return false
        }
        val lockBoxList = getLockBoxList(lockBox)
        for (lb in lockBoxList) {
            if (!isAvailable(keyBox, lb)) continue
            for (sx in 0..keyBox.size - lb.size) {
                for (sy in 0..keyBox[0].size - lb[0].size) {
                    if (canUnlock(sx, sy, hCnt, keyBox, lb)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}

fun main() {
    val key = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(1, 0, 0),
        intArrayOf(0, 1, 1)
    )
    val lock = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 1, 0),
        intArrayOf(1, 0, 1)
    )
    val answer = LockAndKey().solution(key, lock)
    println(answer)
}