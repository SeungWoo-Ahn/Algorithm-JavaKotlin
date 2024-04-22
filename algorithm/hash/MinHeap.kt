package algorithm.hash

class MinHeap {
    private val heap = IntArray(10_005)
    private var size = 0

    fun push(x: Int) {
        heap[++size] = x
        var idx = size
        while (idx != 1) {
            val par = idx / 2
            if (heap[par] <= heap[idx]) break
            heap[idx] = heap[par]
            heap[par] = x
            idx  = par
        }
    }

    fun top(): Int {
        return heap[1]
    }

    fun pop() {
        heap[1] = heap[size--]
        var idx = 1
        while (idx * 2 <= size) {
            val lc = idx * 2
            val rc = idx * 2 + 1
            var minChild = lc
            if (rc <= size && heap[rc] < heap[lc])
                minChild = rc
            if (heap[idx] <= heap[minChild]) break
            val temp = heap[idx]
            heap[idx] = heap[minChild]
            heap[minChild] = temp
            idx = minChild
        }
    }

    fun printHeap() {
        for (i in 1 .. size) {
            print("${heap[i]} ")
        }
    }
}

fun main() {
    val heap = MinHeap()
    heap.push(20)
    heap.push(16)
    heap.push(21)
    heap.push(8)
    heap.push(12)
    heap.pop()
    heap.pop()
    println(heap.top())
    heap.printHeap()
}