package day01

import java.io.File
import kotlin.math.abs

class Day07 : Base<List<Int>, Int>(7) {

    override fun part1(input: List<Int>): Int {
        var result = Int.MAX_VALUE
        val min = input.minOf { it }
        val max = input.maxOf { it }
        (min..max).forEach { pos ->
            result = minOf(result, input.sumOf { abs(it - pos) })
        }
        return result
    }

    override fun part2(input: List<Int>): Int {
        fun calcFuel(steps: Int): Int = if (steps == 0) 0 else steps + calcFuel(steps - 1)
        var result = Int.MAX_VALUE
        val min = input.minOf { it }
        val max = input.maxOf { it }
        (min..max).forEach { pos ->
            result = minOf(result, input.sumOf { calcFuel(abs(it - pos)) })
        }
        return result
    }

    override fun mapInputData(file: File): List<Int> =
        file.readLines().first().split(",").map { it.toInt() }
}

fun main() {
    Day07().submitAll()
}