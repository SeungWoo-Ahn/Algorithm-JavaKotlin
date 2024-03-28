package algorithm.hash

class ChainingHash(
    private val M: Int = 1_000_003, // 해시 테이블 크기
    private val a: Int = 1_000, // 원소 개수
    MX: Int = 500_005, // 최대 삽입 횟수
) {
    private val head = IntArray(M) { -1 }
    private val pre = IntArray(MX) { -1 }
    private val nxt = IntArray(MX) { -1 }
    private val key = Array(MX) { "" }
    private val value = IntArray(MX)
    private var unused = 0

    private fun getHash(s: String): Int {
        var h = 0
        for (x in s)
            h = (h * a + (x - '0')) % M
        return h
    }

    /*
    * 1. 해시값을 만들어 해당하는 연결리스트의 시작점(index)를 찾는다.
    * 2. nxt로 이동하면서 키가 k인 원소를 찾는다.
    * 3. 없다면 -1을 반환한다.
    */
    fun find(k: String): Int {
        val h = getHash(k)
        var idx = head[h]
        while (idx != -1) {
            if (key[idx] == k) return idx
            idx = nxt[idx]
        }
        return -1
    }

    /*
    * 1. k의 해시값이 해시 테이플에 있는지 확인한다.
    * 2. 있다면 값만 교체하는 것으로 끝난다.
    * 3. unused번째에 key와 value를 넣고 head[h]를 unused로 만든다.
    * 4. 기존에 head[h]값이 있다면, nxt와 pre를 바꿔준다.
    */
    fun insert(k: String, v: Int) {
        val idx = find(k)
        if (idx != -1) {
            value[idx] = v
            return
        }
        val h = getHash(k)
        key[unused] = k
        value[unused] = v
        if (head[h] != -1) {
            nxt[unused] = head[h]
            pre[head[h]] = unused
        }
        head[h] = unused
        unused++
    }

    /*
    * 1. k의 해시값을 찾고 없다면(-1) 끝난다.
    * 2. 해시값이 있다면 pre와 nxt를 처리한다.
    * 3. 해시값이 head에 있다면, head를 변경해준다.
    */
    fun erase(k: String) {
        val idx = find(k)
        if (idx == -1) return
        if (pre[idx] != -1) nxt[pre[idx]] = nxt[idx]
        if (nxt[idx] != -1) pre[nxt[idx]] = pre[idx]
        val h = getHash(k)
        if (head[h] == idx) head[h] = nxt[idx]
    }
}

fun main() {
    val hash = ChainingHash()
    hash.insert("carrot", 30)
    hash.find("carrot")
    hash.erase("carrot")
}