package programmers.Practice.Level4

class SearchLyrics {
    private data class Node(
        val value: Char,
        var cnt: Int = 0,
        val children: MutableList<Node> = mutableListOf()
    ) {
        fun use() {
            cnt++
        }

        fun getChild(c: Char): Node? {
            for (child in children) {
                if (child.value == c) {
                    return child
                }
            }
            return null
        }

        fun next(c: Char): Node {
            val child = getChild(c) ?: run {
                val node = Node(c)
                children += node
                node
            }
            child.use()
            return child
        }
    }

    private val rootsByLen = hashMapOf<Int, Node>()
    private val rootsByLenReversed = hashMapOf<Int, Node>()

    private fun getTarget(word: String, reversed: Boolean): String {
        return if (reversed) word.reversed() else word
    }

    private fun getRoot(len: Int, roots: HashMap<Int, Node>): Node {
        if (roots[len] == null) {
            roots[len] = Node(ROOT)
        }
        return roots[len]!!
    }

    private fun getRoot(len: Int, reversed: Boolean): Node {
        return if (reversed) getRoot(len, rootsByLenReversed)
        else getRoot(len, rootsByLen)
    }

    private fun add(word: String, reversed: Boolean) {
        val target = getTarget(word, reversed)
        var cur = getRoot(target.length, reversed).apply { use() }
        for (value in target) {
            cur = cur.next(value)
        }
    }

    private fun find(query: String): Int {
        if (query.all { it == WILD_CARD }) { // 전부 ?인 경우, 쿼리 길이에 해당하는 개수를 반환
            val root = rootsByLen[query.length]
            return root?.cnt ?: 0
        }
        return if (query.first() == WILD_CARD) { // ?가 접두사로 사용된 경우, 뒤집어서 찾기
            find(query, true)
        } else { // ?가 접미사로 사용된 경우, 그대로 찾기
            find(query, false)
        }
    }

    private fun find(query: String, reversed: Boolean): Int {
        val target = getTarget(query, reversed)
        var cur = getRoot(target.length, reversed)
        for (value in target) {
            if (value == WILD_CARD) {
                return cur.cnt
            }
            val child = cur.getChild(value) ?: break
            cur = child
        }
        return 0
    }

    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        words.forEach { word ->
            add(word, false) // 문자 그대로 추가
            add(word, true) // 문자 뒤집어서 추가
        }
        return IntArray(queries.size) { find(queries[it]) }
    }

    companion object {
        private const val ROOT = ' '
        private const val WILD_CARD = '?'
    }
}

fun main() {
    val words = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao")
    val queries = arrayOf("fro??", "????o", "fr???", "fro???", "pro?")
    val answer = SearchLyrics().solution(words, queries)
    print(answer.joinToString())
}