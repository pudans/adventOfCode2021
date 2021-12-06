package day01

import java.io.File

class Day01 : Base<List<Int>, Int>(1) {

    override fun part1(input: List<Int>): Int {
        return input.windowed(2).count { (i1, i2) -> i2 > i1 }
    }

    override fun part2(input: List<Int>): Int {
        return input.windowed(3).map { it.sum() }.windowed(2).count { (i1, i2) -> i2 > i1 }
    }

    override fun mapInputData(file: File): List<Int> = file.readLines().map { it.toInt() }

}

fun main() {
    Day01().submitAll()
}