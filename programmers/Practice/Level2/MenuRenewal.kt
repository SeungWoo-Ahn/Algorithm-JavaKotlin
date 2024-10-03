package programmers.Practice.Level2

class MenuRenewal {
    private class MenuPicker(private val cnt: Int) {
        private val mapper = mutableMapOf<String, Int>()
        private val arr = IntArray(cnt)

        fun pick(orders: Array<List<Char>>) {
            for (order in orders) {
                if (order.size < cnt) continue
                comb(order, 0, 0)
            }
        }

        private fun comb(order: List<Char>, depth: Int, startIdx: Int) {
            if (depth == cnt) {
                val key = makeKey(order)
                mapper[key] = (mapper[key] ?: 0) + 1
                return
            }
            for (idx in startIdx until order.size) {
                arr[depth] = idx
                comb(order, depth + 1, idx + 1)
            }
        }

        private fun makeKey(order: List<Char>): String {
            val sb = StringBuilder()
            for (idx in arr) {
                sb.append(order[idx])
            }
            return sb.toString()
        }

        fun getResult(): MutableList<String>? {
            if (mapper.isEmpty()) {
                return null
            }
            val values = mapper.values
            val maxCnt = values.max()
            if (maxCnt < 2) {
                return null
            }
            val result = mutableListOf<String>()
            for (key in mapper.keys) {
                if (mapper[key]!! == maxCnt) {
                    result += key
                }
            }
            return result
        }
    }

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val sortedOrder = Array(orders.size) { orders[it].toCharArray().sorted() }
        val answer = mutableListOf<String>()
        for (cnt in course) {
            val menuPicker = MenuPicker(cnt).apply { pick(sortedOrder) }
            val result = menuPicker.getResult() ?: continue
            answer.addAll(result)
        }
        return answer.sorted().toTypedArray()
    }
}

fun main() {
    val orders = arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH")
    val course = intArrayOf(2, 3, 4)
    val answer = MenuRenewal().solution(orders, course)
    print(answer.joinToString())
}