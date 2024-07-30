package programmers.StackQueue

import java.util.LinkedList
import java.util.Queue

class KPassingBridge {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var time = 0
        var truckIdx = 0
        var weightSum = 0
        var arrivedCnt = 0
        val bridge = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        while (arrivedCnt < truck_weights.size) {
            if (bridge.isNotEmpty()) {
                val (truckWeight, startTime) = bridge.peek()
                if (startTime + bridge_length == time) {
                    weightSum -= truckWeight
                    arrivedCnt++
                    bridge.poll()
                }
            }
            if (truckIdx < truck_weights.size) {
                if (bridge.size < bridge_length && weightSum + truck_weights[truckIdx] <= weight) {
                    weightSum += truck_weights[truckIdx]
                    bridge += truck_weights[truckIdx] to time
                    truckIdx++
                }
            }
            time++
        }
        return time
    }
}

fun main() {
    val answer = KPassingBridge().solution(
        bridge_length = 2,
        weight = 10,
        truck_weights = intArrayOf(7,4,5,6)
    )
    print(answer)
}