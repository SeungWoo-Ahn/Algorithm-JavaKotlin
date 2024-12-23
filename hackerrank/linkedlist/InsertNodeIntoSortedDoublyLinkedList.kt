package hackerrank.linkedlist

class InsertNodeIntoSortedDoublyLinkedList {
    class DoublyLinkedListNode(nodeData: Int) {
        val data: Int = nodeData
        var next: DoublyLinkedListNode? = null
        var prev: DoublyLinkedListNode? = null
    }

    class DoublyLinkedList {
        var head: DoublyLinkedListNode? = null
        private var tail: DoublyLinkedListNode? = null

        fun insertNode(nodeData: Int) {
            val node = DoublyLinkedListNode(nodeData)
            if (head == null) {
                head = node
            } else {
                tail?.next = node
                node.prev = tail
            }
            tail = node
        }
    }

    private fun printDoublyLinkedList(head: DoublyLinkedListNode) {
        var node: DoublyLinkedListNode? = head;
        val sb = StringBuilder()
        while (node != null) {
            sb.append("${node.data} ")
            node = node.next
        }
        println(sb)
    }

    private fun sortedInsert(list: DoublyLinkedListNode?, data: Int): DoublyLinkedListNode {
        val node = DoublyLinkedListNode(data)
        if (list == null || data <= list.data) {
            node.next = list
            list?.prev = node
            return node
        }
        var current = list
        var prev: DoublyLinkedListNode? = null
        while (current != null) {
            if (data <= current.data) {
                node.prev = prev
                node.next = current
                prev?.next = node
                current.prev = node
                break
            }
            prev = current
            current = current.next
        }
        if (current == null) {
            node.prev = prev
            prev?.next = node
        }
        return list
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        repeat(t) {
            val n = readLine().toInt()
            val list = DoublyLinkedList()
            repeat(n) {
                val nodeData = readLine().toInt()
                list.insertNode(nodeData)
            }
            val data = readLine().toInt()
            val result = sortedInsert(list.head, data)
            printDoublyLinkedList(result)
        }
    }
}

fun main() {
    InsertNodeIntoSortedDoublyLinkedList().solution()
}