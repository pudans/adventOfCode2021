package day01

import java.io.File

data class Point(val x: Int, val y: Int)
private typealias Data = List<Pair<Point, Point>>

class Day05 : Base<Data>(5) {

    private fun getRangeX(pair: Pair<Point, Point>) =
        if (pair.first.x > pair.second.x) {
            (pair.first.x downTo pair.second.x)
        } else {
            (pair.first.x..pair.second.x)
        }

    private fun getRangeY(pair: Pair<Point, Point>) =
        if (pair.first.y > pair.second.y) {
            (pair.first.y downTo pair.second.y)
        } else {
            (pair.first.y..pair.second.y)
        }

    override fun part1(input: Data): Int {
        val map = Array(1000) { IntArray(1000) }
        var result = 0

        input.forEach { pair ->
            if (pair.first.x == pair.second.x) {
                getRangeY(pair).forEach {
                    map[pair.first.x][it]++
                    if (map[pair.first.x][it] == 2) result++
                }
            }
            if (pair.first.y == pair.second.y) {
                getRangeX(pair).forEach {
                    map[it][pair.first.y]++
                    if ( map[it][pair.first.y] == 2) result++
                }
            }
        }
        return result
    }

    override fun part2(input: Data): Int {
        val map = Array(1000) { IntArray(1000) }
        var result = 0

        input.forEach { pair ->
            if (pair.first.x == pair.second.x) {
                getRangeY(pair).forEach {
                    map[pair.first.x][it]++
                    if (map[pair.first.x][it] == 2) result++
                }
            } else if (pair.first.y == pair.second.y) {
                getRangeX(pair).forEach {
                    map[it][pair.first.y]++
                    if ( map[it][pair.first.y] == 2) result++
                }
            } else {
                val rangeX = getRangeX(pair)
                val rangeY = getRangeY(pair)
                for (i in 0 until rangeX.count()) {
                    map[rangeX.elementAt(i)][rangeY.elementAt(i)]++
                    if (map[rangeX.elementAt(i)][rangeY.elementAt(i)] == 2) result++
                }
            }
        }

        return result
    }

    override fun mapInputData(file: File): Data {
        val lines = file.readLines()
        val result = ArrayList<Pair<Point, Point>>()
        fun String.toPoint() = split(",").map { it.toInt() }.let { Point(it[0],it[1]) }
        lines.forEach { line ->
            val points = line.split(" -> ")
            result.add(Pair(points[0].toPoint(), points[1].toPoint()))
        }
        return result
    }
}

fun main() {
    Day05().submitInput()
}