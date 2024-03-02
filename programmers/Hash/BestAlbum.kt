package programmers.Hash

import java.util.PriorityQueue
import kotlin.math.min

class BestAlbum {
    data class Music(val play: Int, val id: Int): Comparable<Music> {
        override fun compareTo(other: Music): Int {
            return if (play != other.play) other.play - play
            else id - other.id
        }
    }

    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val qMap = HashMap<String, PriorityQueue<Music>>()
        for (i in genres.indices) {
            genres[i].let { key ->
                if (qMap[key] == null) qMap[key] = PriorityQueue()
                qMap[key]!!.add(Music(plays[i], i))
            }
        }
        val rankQ = PriorityQueue<Pair<String, Int>> { o1, o2 -> o2.second - o1.second }
        qMap.forEach { (k, v) -> rankQ.add(Pair(k, v.sumOf { it.play })) }
        val answer = arrayListOf<Int>()
        while (rankQ.isNotEmpty()) {
            val key = rankQ.poll().first
            var cnt = min(qMap[key]!!.size, 2)
            while (cnt-- > 0) {
                answer.add(qMap[key]!!.poll().id)
            }
        }
        return answer.toIntArray()
    }
}

fun main() {
    val genres = arrayOf("classic", "pop", "classic", "classic", "pop")
    val plays = intArrayOf(500, 600, 150, 800, 2500)
    println(BestAlbum().solution(genres, plays).joinToString())
}