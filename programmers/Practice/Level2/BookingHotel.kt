package programmers.Practice.Level2

class BookingHotel {
    private data class Reservation(
        val startTime: Int,
        val endTime: Int
    )

    private fun String.toMinute(): Int {
        val (hh, mm) = split(":").map { it.toInt() }
        return hh * 60 + mm
    }

    private fun Array<String>.toReservation(): Reservation {
        return Reservation(
            this[0].toMinute(),
            this[1].toMinute() + 10
        )
    }

    fun solution(bookTimes: Array<Array<String>>): Int {
        val maxMinute = "24:00".toMinute()
        val sumArr = IntArray(maxMinute + 10)
        val reservations = bookTimes.map { it.toReservation() }
        for (reservation in reservations) {
            sumArr[reservation.startTime]++
            sumArr[reservation.endTime]--
        }
        var roomCnt = 0
        for (i in 1 until sumArr.size) {
            sumArr[i] += sumArr[i - 1]
            roomCnt = maxOf(roomCnt, sumArr[i])
        }
        return roomCnt
    }
}

fun main() {
    val bookTimes = arrayOf(
        arrayOf("15:00", "17:00"),
        arrayOf("16:40", "18:20"),
        arrayOf("14:20", "15:20"),
        arrayOf("14:10", "19:20"),
        arrayOf("18:20", "21:20")
    )
    val answer = BookingHotel().solution(bookTimes)
    print(answer)
}