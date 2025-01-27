package boj.string

class `16113` {
    private val map = mutableMapOf<String, Int>().apply {
        put("#.##.##.#", 0)
        put("..#####..", 2)
        put("..####..#", 3)
        put("#..###..#", 5)
        put("#..####.#", 6)
        put("..#..#..#", 7)
        put("#.#####.#", 8)
        put("#.####..#", 9)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val signal = readLine()
        val unit = n / 5
        var idx = 0
        val result = StringBuilder()
        while (idx < unit) {
            if (signal[idx] == '.') {
                idx++
                continue
            }
            if (idx == unit - 1) { // 마지막 열의 1임
                result.append(1)
                break
            }
            if (signal[idx + 1] == '.') { // 1 or 4인 경우
                val targetIdx = unit * 2 + idx + 1 // 4의 정중앙에 있는 값
                if (signal[targetIdx] == '.') { // 1임
                    result.append(1)
                    idx += 2
                    continue
                } else { // 4임
                    result.append(4)
                }
            } else {
                val key = StringBuilder()
                for (line in 1..3) {
                    val st = unit * line + idx
                    val en = st + 3
                    key.append(signal.substring(st, en))
                }
                result.append(map[key.toString()])
            }
            idx += 4
        }
        print(result)
    }
}

fun main() {
    `16113`().solution()
}