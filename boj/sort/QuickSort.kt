package boj.sort

private val arr = IntArray(10_000_001)

private fun quickSort(s: Int, e: Int) {
    if (s + 1 == e) return
    val pivot = arr[s]
    var left = s + 1
    var right = e - 1
    while (true) {
        while (left <= right && arr[left] <= pivot) left++
        while (left <= right && arr[right] >= pivot) right++
        if (left > right) break
        swap(left, right)
    }
    swap(s, right)
    quickSort(s, right)
    quickSort(right + 1, e)
}

private fun swap(a: Int, b: Int) {
    val temp = arr[a]
    arr[a] = arr[b]
    arr[b] = temp
}

