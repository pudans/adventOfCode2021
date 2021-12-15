package day01

import java.io.File
import java.lang.StringBuilder

class Day15 : Base<Array<IntArray>, Int>(15) {

    override fun mapInputData(file: File): Array<IntArray> {
        val lines = file.readLines()
        return Array(lines.size) { lines[it].toCharArray().map { it.digitToInt() }.toIntArray() }
    }

    override fun part1(input: Array<IntArray>): Int {
        return traverse(input, Point(0,0)) -input.first().first()
    }

    private val memory = HashMap<Point,Int>()

    private fun traverse(input: Array<IntArray>, point: Point): Int {
        if (point.x == input.lastIndex && point.y == input.first().lastIndex) return input.last().last()
        return listOf(
            Point(point.x + 1, point.y),
            Point(point.x, point.y + 1),
        )
//            .asSequence()
            .filter { it.x in input.indices }
            .filter { it.y in input.first().indices }
            .map { memory[it] ?: traverse(input, it) }
            .minOf { it }
            .let { it + input[point.x][point.y] }
            .also { memory[point] = it }
    }

    override fun part2(input: Array<IntArray>): Int {
        val newInput = Array(input.size * 5) { y ->
            IntArray(input.first().size * 5) { x ->
                val sum = y / input.size + x / input.first().size
                val d = input[y % input.size][x % input.first().size] + sum
                if (d > 9) d - 9 else d
            }
        }
//        newInput.forEach {
//            println(it.joinToString(""))
//        }

        val result = traverse(newInput, Point(0,0)) -newInput.first().first()

        for (i in newInput.indices) {
            for (j in newInput.first().indices) {
                println(newInput[i].joinToString { (memory[Point(i,j)] ?: 0).toString() } )
            }
        }

        return result
    }
}

fun main() {
    Day15().submitPart2Input()
}