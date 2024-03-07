package programmers.BruthForce

class KVowelDictionary {
    private val vowels = listOf("A", "E", "I", "O", "U")
    private val dictionary = mutableListOf<String>()

    private fun makeDictionary(str: String, len: Int) {
        dictionary.add(str)
        if (len == 5) return
        for (i in vowels.indices) {
            makeDictionary(str + vowels[i], len + 1)
        }
    }

    fun solution(word: String): Int {
        makeDictionary("", 0)
        for (i in dictionary.indices) {
            if (dictionary[i] == word) {
                return i
            }
        }
        return 0
    }
}

fun main() {
    println(KVowelDictionary().solution("I"))
}