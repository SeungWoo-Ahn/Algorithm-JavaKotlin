package programmers.Practice.Level3

class HotelRoomAssignment {
    fun solution(k: Long, roomNumber: LongArray): LongArray {
        val n = roomNumber.size
        val result = LongArray(n)
        val rooms = mutableMapOf<Long, Long>()
        for (i in 0 until n) {
            var key = roomNumber[i]
            val checked = mutableListOf<Long>()
            while (rooms[key] != null) {
                checked += key
                key = rooms[key]!!
            }
            checked += key
            for (num in checked) {
                rooms[num] = key + 1
            }
            result[i] = key
        }
        return result
    }
}

fun main() {
    val k = 10L
    val roomNumber = longArrayOf(1,3,4,1,3,1)
    val answer = HotelRoomAssignment().solution(k, roomNumber)
    print(answer.joinToString(" "))
}