package programmers.Practice.Level2

import kotlin.math.ceil

class CalcParkingFee {
    private fun String.toMinute(): Int {
        val (hh, mm) = split(":").map { it.toInt() }
        return hh * 60 + mm
    }

    private fun calcFee(usedTime: Int, fees: IntArray): Int {
        if (usedTime <= fees[0]) {
            return fees[1]
        }
        val overTime = (usedTime - fees[0]).toDouble()
        val overPrice = ceil(overTime / fees[2]).toInt() * fees[3]
        return fees[1] + overPrice
    }

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val parkingLot = mutableMapOf<String, Int>()
        val timeSum = mutableMapOf<String, Int>()
        for (record in records) {
            val (time, carNum, type) = record.split(" ")
            val minute = time.toMinute()
            when (type) {
                "IN" -> {
                    parkingLot[carNum] = minute
                }
                "OUT" -> {
                    timeSum[carNum] = timeSum.getOrDefault(carNum, 0) + (minute - parkingLot[carNum]!!)
                    parkingLot.remove(carNum)
                }
            }
        }
        val closedTime = "23:59".toMinute()
        for (carNum in parkingLot.keys) {
            timeSum[carNum] = timeSum.getOrDefault(carNum, 0) + (closedTime - parkingLot[carNum]!!)
        }
        val keys = timeSum.keys.sorted()
        return IntArray(timeSum.size) { idx -> calcFee(timeSum[keys[idx]]!!, fees) }
    }
}

fun main() {
    val fees = intArrayOf(180, 5000, 10, 600)
    val records = arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT")
    val answer = CalcParkingFee().solution(fees, records)
    print(answer.joinToString())
}