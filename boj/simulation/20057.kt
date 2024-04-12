package boj.simulation

import java.util.StringTokenizer

class `20057` {
    private data class Node(var x: Int, var y: Int, val rate: Int = 0)
    private data class Tornado(val pos: Node, var dir: Int = 0, var length: Int = 1)

    private var map: Array<IntArray> = arrayOf()
    private val tornado = Tornado(pos= Node(0, 0))
    private val d = listOf(
        Node(0, -1),
        Node(1, 0),
        Node(0, 1),
        Node(-1, 0),
    )
    private val relativePosList = listOf(
        getRelativePosList(0),
        getRelativePosList(1),
        getRelativePosList(2),
        getRelativePosList(3)
    )
    private var outSideSandAmount = 0

    private fun moveTornado() {
        val (x, y, _) = tornado.pos
        val (dx, dy, _) = d[tornado.dir]
        val sandAmount = map[x + dx][y + dy].also { map[x + dx][y + dy] = 0 }
        var remainSand = sandAmount
        for (pos in relativePosList[tornado.dir]) {
            val nx = x + pos.x
            val ny = y + pos.y
            val movedSand = sandAmount * pos.rate / 100
            remainSand -= movedSand
            blowSand(nx, ny, movedSand)
        }
        blowSand(x + dx * 2, y + dy * 2, remainSand)
        tornado.pos.x = x + dx
        tornado.pos.y = y + dy
    }

    private fun blowSand(x: Int, y: Int, sandAmount: Int) {
        if (x !in map.indices || y !in map.indices)
            outSideSandAmount += sandAmount
        else
            map[x][y] += sandAmount
    }

    private fun getRelativePosList(curDir: Int): List<Node> {
        val posList = mutableListOf<Node>()
        val (x, y, _) = d[curDir]
        val (leftX, leftY, _) = d[nxtDirIdx(curDir)]
        val (rightX, rightY, _) = d[preDirIdx(curDir)]

        posList.apply {
            add(Node(x * 3, y * 3, 5))
            add(Node(rightX, rightY, 1))
            add(Node(leftX, leftY, 1))
            add(Node(x + rightX, y + rightY, 7))
            add(Node(x + leftX, y + leftY, 7))
            add(Node(x + rightX * 2, y + rightY * 2, 2))
            add(Node(x + leftX * 2, y + leftY * 2, 2))
            add(Node(x * 2 + rightX, y * 2 + rightY, 10))
            add(Node(x * 2 + leftX, y * 2 + leftY, 10))
        }
        return posList.toList()
    }

    private fun isTornadoGone(): Boolean {
        return tornado.pos.x == 0 && tornado.pos.y == 0
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        map = Array(n) {
            val st = StringTokenizer(readLine())
            IntArray(n) { st.nextToken().toInt() }
        }
        tornado.pos.x = n / 2
        tornado.pos.y = n / 2
        loop@ while (true) {
            var length = tornado.length
            while (length-- > 0) {
                moveTornado()
                if (isTornadoGone()) break@loop
            }
            tornado.dir = nxtDirIdx(tornado.dir)
            if (tornado.dir % 2 == 0) tornado.length++
        }
        println(outSideSandAmount)
    }

    private fun nxtDirIdx(dir: Int): Int = (dir + 1) % d.size

    private fun preDirIdx(dir: Int): Int = if (dir == 0) d.lastIndex else dir - 1
}

fun main() {
    `20057`().solution()
}