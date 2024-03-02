package programmers.Hash

import java.util.HashMap

class Costume {
    fun solution(clothes: Array<Array<String>>): Int {
        val map = HashMap<String, Int>()
        for (i in clothes.indices) {
            val sort = clothes[i][1]
            map[sort] = map.getOrDefault(sort, 0) + 1
        }
        var answer = 1
        val iter = map.iterator()
        while (iter.hasNext()) {
            answer *= iter.next().value + 1
        }
        return answer - 1
    }
}

fun main() {
    val clothes = arrayOf(
        arrayOf("yellow_hat", "headgear"),
        arrayOf("blue_sunglasses", "eyewear"),
        arrayOf("green_turban", "headgear")
    )
    println(Costume().solution(clothes))
}