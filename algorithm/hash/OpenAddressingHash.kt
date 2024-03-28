package algorithm.hash

class OpenAddressingHash(
    private val M: Int = 1_000_003, // 해시 테이블의 크기
    private val a: Int = 1_000, // 원소 개수
) {
    private val status = IntArray(M) { EMPTY }
    private val key = Array(M) { "" }
    private val value = IntArray(M)

    private fun getHash(s: String): Int {
        var h = 0
        for (x in s)
            h = (h * a + (x - '0')) % M
        return h
    }

    /*
    * 1. index에 해당하는 해시값을 찾고, EMPTY가 아닐때까지 탐색을 이어나간다.
    * 2. status가 OCCUPY이고 key값이 k와 일치하는 index를 return한다.
    * 3. 없다면 -1을 return 한다.
    */
    fun find(k: String): Int {
        var idx = getHash(k)
        while (status[idx] != EMPTY) {
            if (status[idx] == OCCUPY && key[idx] == k) return idx
            idx = (idx + 1) % M
        }
        return -1
    }

    /*
    * 1. 해시값의 index를 찾는다.
    * 2. 이미 존재하면 값만 변경하고 끝낸다.
    * 3. 없다면 다음 EMPTY를 찾을 때까지 이동해서 key와 value를 넣는다.
    * 4. 해당 칸의 상태를 OCCUPY로 바꾼다.
    */
    fun insert(k: String, v: Int) {
        var idx = find(k)
        if (idx != -1) {
            value[idx] = v
            return
        }
        idx = getHash(k)
        while (status[idx] == OCCUPY)
            idx = (idx + 1) % M
        key[idx] = k
        value[idx] = v
        status[idx] = OCCUPY
    }

    /*
    * 1. 해시값의 index를 찾는다.
    * 2. 값이 있다면 상태를 DUMMY로 바꾼다.
    */
    fun erase(k: String) {
        val idx = find(k)
        if (idx != -1) status[idx] = DUMMY
    }

    companion object {
        const val EMPTY = -1 // 비어 있는 칸
        const val OCCUPY = 0 // 값이 있는 칸
        const val DUMMY = 1 // 값이 있었는데 제거된 칸
    }
}

fun main() {
    val hash = OpenAddressingHash()
    hash.insert("carrot", 40)
    hash.find("carrot")
    hash.erase("carrot")
}