package day01

import java.io.File

fun day2(input: List<Pair<String, Int>>): Int {
    var depth = 0
    var distance = 0

    input.forEach {

        when (it.first) {
            "up" -> depth -= it.second
            "down" -> depth += it.second
            else -> distance += it.second
        }
    }

    return depth * distance
}

fun day2part2(input: List<Pair<String, Int>>): Int {
    var depth = 0
    var distance = 0
    var aim = 0

    input.forEach {

        when (it.first) {
            "up" -> aim -= it.second
            "down" -> aim += it.second
            else -> {
                distance += it.second
                depth += aim * it.second
            }
        }
    }

    return depth * distance
}

fun main() {

    val test = listOf("forward" to 5, "down" to 5, "forward" to 8, "up" to 3, "down" to 8, "forward" to 2)
    val input = File("inputs/day02.txt")
        .readLines()
        .map { it.split(" ") }
        .map { it.first() to it[1].toInt() }

    println(day2(test))
    println(day2(input))

    println(day2part2(test))
    println(day2part2(input))
}