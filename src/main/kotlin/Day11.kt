package day01

import java.io.File
import java.util.*

class Day11 : Base<Array<IntArray>, Int>(11) {

    override fun mapInputData(file: File): Array<IntArray> =
        file.readLines().map { it.toCharArray().map { it.digitToInt() }.toIntArray() }.toTypedArray()

    private fun Array<IntArray>.increase() {
        for (i in indices) { for (j in first().indices) { this[i][j]++ } }
    }

    private fun Array<IntArray>.print() {
        for (i in indices) { println(this[i].joinToString(" ")) }
    }

    private fun Array<IntArray>.check() : Int {
        val afterFlash = mutableSetOf<Point>()
        val toFlash = mutableSetOf<Point>()
        var lastCount = true
        while (lastCount) {
            lastCount = false
            for (i in indices) {
                for (j in first().indices) {
                    if (this[i][j] >= 10) {
                        if (toFlash.add(Point(i,j))) {
                            afterFlash.add(Point(i,j))
                            lastCount = true
                        }
                    }
                }
            }
            toFlash.forEach { this.flash(it) }
            toFlash.forEach { this[it.x][it.y] = 0 }
            toFlash.clear()
        }

        afterFlash.forEach { this[it.x][it.y] = 0 }
        return afterFlash.size
    }

    private fun Array<IntArray>.flash(point: Point) {
        if ((point.x + 1) in this.indices) {
            this[point.x + 1][point.y]++
            if ((point.y + 1) in this.first().indices) {
                this[point.x + 1][point.y + 1]++
            }
            if ((point.y - 1) in this.first().indices) {
                this[point.x + 1][point.y - 1]++
            }
        }
        if ((point.x - 1) in this.indices) {
            this[point.x - 1][point.y]++
            if ((point.y + 1) in this.first().indices) {
                this[point.x - 1][point.y + 1]++
            }
            if ((point.y - 1) in this.first().indices) {
                this[point.x - 1][point.y - 1]++
            }
        }
        if ((point.y + 1) in this.first().indices) {
            this[point.x][point.y + 1]++
        }
        if ((point.y - 1) in this.first().indices) {
            this[point.x][point.y - 1]++
        }
    }

    override fun part1(input: Array<IntArray>): Int {
//        input.print()
        val steps = 100
        var result = 0
        repeat(steps) {
            input.increase()
            result += input.check()
//            println("----- STEP ${it + 1} -------")
//            input.print()
        }
        return result
    }

    override fun part2(input: Array<IntArray>): Int {
//        input.print()
        var step = 0
        while (true) {
            input.increase()
            val flashes = input.check()
//            println("----- STEP ${step + 1} -------")
//            input.print()
            if (flashes == input.size * input.first().size) {
                return step + 1
            }
            step++
        }
    }
}

fun main() {
    Day11().submitAll()
}