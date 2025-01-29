package boj.string

class `17413` {
    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine()
        val result = StringBuilder()
        var st = 0
        var en: Int
        while (st < s.length) {
            when (s[st]) {
                '<' -> {
                    en = s.indexOf('>', st + 1) + 1
                    val tag = s.substring(st, en)
                    result.append(tag)
                }
                ' ' -> {
                    en = st + 1
                    result.append(' ')
                }
                else -> {
                    en = st + 1
                    while (en < s.length && s[en] != '<' && s[en] != ' ') {
                        en++
                    }
                    val word = s.substring(st, en).reversed()
                    result.append(word)
                }
            }
            st = en
        }
        print(result)
    }
}

fun main() {
    `17413`().solution()
}