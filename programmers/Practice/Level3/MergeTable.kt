package programmers.Practice.Level3

class MergeTable {
    private val parent = IntArray(SIZE) { it }
    private val table = Array(SIZE) { EMPTY }
    private val result = mutableListOf<String>()

    private fun process(command: String) {
        val sp = command.split(" ")
        when (sp[0]) {
            "UPDATE" -> {
                when (sp.size) {
                    4 -> update(getIndex(sp[1], sp[2]), sp[3])
                    3 -> updateAll(sp[1], sp[2])
                }
            }
            "MERGE" -> merge(getIndex(sp[1], sp[2]), getIndex(sp[3], sp[4]))
            "UNMERGE" -> unMerge(getIndex(sp[1], sp[2]))
            "PRINT" -> print(getIndex(sp[1], sp[2]))
        }
    }

    private fun getIndex(r: String, c: String): Int {
        return getIndex(r.toInt(), c.toInt())
    }

    private fun getIndex(r: Int, c: Int): Int {
        return (r - 1) * 50 + (c - 1)
    }

    private fun find(index: Int): Int {
        if (parent[index] != index) {
            parent[index] = find(parent[index])
        }
        return parent[index]
    }

    private fun getValue(index: Int): String {
        val root = find(index)
        return table[root]
    }

    private fun update(index: Int, value: String) {
        val root = find(index)
        table[root] = value
    }

    private fun updateAll(value1: String, value2: String) {
        if (value1 == value2) return
        for (index in table.indices) {
            if (value1 == getValue(index)) {
                update(index, value2)
            }
        }
    }

    private fun merge(index1: Int, index2: Int) {
        if (index1 == index2) return
        val root1 = find(index1)
        val root2 = find(index2)
        if (getValue(root1) != EMPTY) {
            parent[root2] = root1
        } else {
            parent[root1] = root2
        }
    }

    private fun unMerge(index: Int) {
        val root = find(index)
        val value = getValue(root)
        val indexList = mutableListOf<Int>()
        for (tableIdx in table.indices) {
            if (root == find(tableIdx)) {
                indexList += tableIdx
                table[tableIdx] = if (tableIdx == index) value else EMPTY
            }
        }
        indexList.forEach { parent[it] = it }
    }

    private fun print(index: Int) {
        val value = getValue(index)
        result += if (value != EMPTY) value else "EMPTY"
    }

    fun solution(commands: Array<String>): Array<String> {
        commands.forEach(::process)
        return result.toTypedArray()
    }

    companion object {
        private const val SIZE = 2_500
        private const val EMPTY = ""
    }
}

fun main() {
    val commands = arrayOf(
        "UPDATE 1 1 menu",
        "UPDATE 1 2 category",
        "UPDATE 2 1 bibimbap",
        "UPDATE 2 2 korean",
        "UPDATE 2 3 rice",
        "UPDATE 3 1 ramyeon",
        "UPDATE 3 2 korean",
        "UPDATE 3 3 noodle",
        "UPDATE 3 4 instant",
        "UPDATE 4 1 pasta",
        "UPDATE 4 2 italian",
        "UPDATE 4 3 noodle",
        "MERGE 1 2 1 3",
        "MERGE 1 3 1 4",
        "UPDATE korean hansik",
        "UPDATE 1 3 group",
        "UNMERGE 1 4",
        "PRINT 1 3",
        "PRINT 1 4"
    )
    val answer = MergeTable().solution(commands)
    print(answer.joinToString())
}