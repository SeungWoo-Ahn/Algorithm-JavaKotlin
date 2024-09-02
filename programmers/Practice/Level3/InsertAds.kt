package programmers.Practice.Level3

class InsertAds {
    private fun String.toSec(): Int {
        var sec = 0
        sec += substring(0..1).toInt() * 60 * 60
        sec += substring(3..4).toInt() * 60
        sec += substring(6..7).toInt()
        return sec
    }

    private fun Int.toTime(): String {
        var hh = (this / (60 * 60)).toString()
        if (hh.length == 1) hh = "0$hh"
        var mm = (this % (60 * 60) / 60).toString()
        if (mm.length == 1) mm = "0$mm"
        var ss = (this % 60).toString()
        if (ss.length == 1) ss = "0$ss"
        return "$hh:$mm:$ss"
    }

    fun solution(playTime: String, advTime: String, logs: Array<String>): String {
        val playSec = playTime.toSec()
        val advSec = advTime.toSec()
        val video = IntArray(playSec)
        for (log in logs) {
            val startSec = log.substring(0..7).toSec()
            val endSec = log.substring(9..16).toSec()
            for (sec in startSec until endSec) {
                video[sec]++
            }
        }
        var sum = 0L
        for (sec in 0 until advSec) {
            sum += video[sec]
        }
        var max = sum
        var advStart = 0
        for (st in 1..playSec - advSec) {
            sum -= video[st - 1]
            sum += video[st + advSec - 1]
            if (sum > max) {
                max = sum
                advStart = st
            }
        }
        return advStart.toTime()
    }
}

fun main() {
    val playTime = "02:03:55"
    val advTime = "00:14:15"
    val logs = arrayOf("01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30")
    val answer = InsertAds().solution(playTime, advTime, logs)
    print(answer)
}