package boj.string

class `3613` {
    private enum class VariableStyle {
        Java, Cpp, Error
    }

    private sealed interface Converter {
        fun convert(name: String): String

        data object ToJava : Converter {
            override fun convert(name: String): String {
                val sb = StringBuilder()
                var prevUnderScore = false
                for (ch in name) {
                    if (ch == '_') {
                        prevUnderScore = true
                        continue
                    }
                    if (prevUnderScore) {
                        sb.append(ch.uppercaseChar())
                        prevUnderScore = false
                    } else {
                        sb.append(ch)
                    }
                }
                return sb.toString()
            }
        }

        data object ToCpp : Converter {
            override fun convert(name: String): String {
                val sb = StringBuilder()
                for (ch in name) {
                    if (ch.isUpperCase()) {
                        sb.append('_').append(ch.lowercaseChar())
                    } else {
                        sb.append(ch)
                    }
                }
                return sb.toString()
            }
        }
    }

    private fun String.check(): VariableStyle {
        if (first().isUpperCase() || first() == '_' || last() == '_') {
            return VariableStyle.Error
        }
        var lastUnderScoreIdx = -1
        var containUnderScore = false
        var containUpperCase = false
        for (i in indices) {
            when {
                this[i] == ' ' -> return VariableStyle.Error
                this[i].isUpperCase() -> containUpperCase = true
                this[i] == '_' -> {
                    if (i == lastUnderScoreIdx + 1) {
                        return VariableStyle.Error
                    } else {
                        lastUnderScoreIdx = i
                        containUnderScore = true
                    }
                }
            }
        }
        if (containUnderScore && containUpperCase) {
            return VariableStyle.Error
        }
        return if (containUnderScore) VariableStyle.Cpp
        else VariableStyle.Java
    }

    private fun Char.isUpperCase(): Boolean {
        return code in 'A'.code..'Z'.code
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val name = readLine()
        val result = when (name.check()) {
            VariableStyle.Java -> Converter.ToCpp.convert(name)
            VariableStyle.Cpp -> Converter.ToJava.convert(name)
            VariableStyle.Error -> "Error!"
        }
        print(result)
    }
}

fun main() {
    `3613`().solution()
}