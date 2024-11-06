package programmers.Practice.Level2

class SearchRank {
    private val conditions = listOf(
        listOf("cpp", "java", "python"),
        listOf("backend", "frontend"),
        listOf("junior", "senior"),
        listOf("chicken", "pizza")
    )
    private val infoMap = hashMapOf<String, MutableList<Int>>()
    private var cntByQuery = 0

    private fun String.toKeyAndScore(offset: Int): Pair<String, Int> {
        val splitter = split(" ")
        val sb = StringBuilder().apply {
            (0..3).forEach {
                val word = splitter[it * offset]
                if (word == "-") {
                    append(word)
                } else {
                    append(conditions[it].indexOf(word))
                }
            }
        }
        val key = sb.toString()
        val score = splitter.last().toInt()
        return key to score
    }

    private fun addInfos(infos: Array<String>) {
        infos.forEach { info -> addInfo(info) }
    }

    private fun addInfo(info: String) {
        val (key, score) = info.toKeyAndScore(1)
        infoMap[key] = infoMap
            .getOrDefault(key, mutableListOf())
            .also { it.add(score) }
    }

    private fun sortScoreByKeys() {
        infoMap.keys.forEach { key ->
            infoMap[key]?.sort()
        }
    }

    private fun processQuery(query: String): Int {
        val (key, targetScore) = query.toKeyAndScore(2)
        val keyArr = CharArray(key.length)
        cntByQuery = 0
        keyComb(0, key, targetScore, keyArr)
        return cntByQuery
    }

    private fun keyComb(depth: Int, key: String, targetScore: Int, keyArr: CharArray) {
        if (depth == keyArr.size) {
            val createdKey = keyArr.joinToString("")
            infoMap[createdKey]?.let { scoreList ->
                val cnt = scoreList.size - lowerBound(targetScore, scoreList)
                cntByQuery += cnt
            }
            return
        }
        if (key[depth] == '-') {
            for (i in conditions[depth].indices) {
                keyArr[depth] = i.digitToChar()
                keyComb(depth + 1, key, targetScore, keyArr)
            }
        } else {
            keyArr[depth] = key[depth]
            keyComb(depth + 1, key, targetScore, keyArr)
        }
    }

    private fun lowerBound(targetScore: Int, scoreList: MutableList<Int>): Int {
        var st = 0
        var en = scoreList.size
        while (st < en) {
            val mid = (st + en) / 2
            if (scoreList[mid] < targetScore) {
                st = mid + 1
            } else {
                en = mid
            }
        }
        return st
    }

    fun solution(infos: Array<String>, query: Array<String>): IntArray {
        addInfos(infos)
        sortScoreByKeys()
        return IntArray(query.size) { index ->
            processQuery(query[index])
        }
    }
}

fun main() {
    val infos = arrayOf(
        "java backend junior pizza 150",
        "python frontend senior chicken 210",
        "python frontend senior chicken 150",
        "cpp backend senior pizza 260",
        "java backend junior chicken 80",
        "python backend senior chicken 50"
    )
    val query = arrayOf(
        "java and backend and junior and pizza 100",
        "python and frontend and senior and chicken 200",
        "cpp and - and senior and pizza 250",
        "- and backend and senior and - 150",
        "- and - and - and chicken 100",
        "- and - and - and - 150"
    )
    val answer = SearchRank().solution(infos, query)
    print(answer.joinToString())
}