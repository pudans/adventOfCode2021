package day01

import java.io.File

fun day1(input: List<Int>): Int {
    return input.windowed(2).count { (i1, i2) -> i2 > i1 }
}

fun day1part2(input: List<Int>): Int {
    return input.windowed(3).map { it.sum() }.windowed(2).count { (i1, i2) -> i2 > i1 }
}

fun main() {

    val test = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    val input = File("inputs/day01.txt").readLines().map { it.toInt() }

    println(day1(test))
    println(day1(input))

    println(day1part2(test))
    println(day1part2(input))
}