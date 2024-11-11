package programmers.Practice.Level3

class ExpressibleBinaryTree {
    private val treeSize = IntArray(6)

    private fun makeTreeSize() {
        treeSize[0] = 2
        for (i in 1 until treeSize.size) {
            treeSize[i] = treeSize[i - 1] shl 1
        }
        for (i in treeSize.indices) {
            treeSize[i]--
        }
    }

    private fun canExpressBinaryTree(number: Long): Boolean {
        val binaryString = number.toString(2)
        val lengthDiff = getClosestTreeSize(binaryString.length) - binaryString.length
        val candidate = getCandidate(binaryString, lengthDiff)
        return check(candidate)
    }

    private fun getClosestTreeSize(length: Int): Int {
        for (size in treeSize) {
            if (size >= length) {
                return size
            }
        }
        return -1
    }

    private fun getCandidate(binaryString: String, diff: Int): String {
        return StringBuilder().apply {
            repeat(diff) { append('0') }
            append(binaryString)
        }.toString()
    }

    private fun check(s: String): Boolean {
        val mid = s.length / 2
        return if (s[mid] == '0' && '1' in s) {
            false
        } else {
            val checkLeft = if (mid > 0) check(s.substring(0, mid)) else true
            val checkRight = if (mid + 1 < s.length) check(s.substring(mid + 1)) else true
            checkLeft && checkRight
        }
    }

    fun solution(numbers: LongArray): IntArray {
        makeTreeSize()
        return IntArray(numbers.size) {
            if (canExpressBinaryTree(numbers[it])) 1
            else 0
        }
    }
}

fun main() {
    val numbers = longArrayOf(63, 111, 95)
    val answer = ExpressibleBinaryTree().solution(numbers)
    print(answer.joinToString())
}