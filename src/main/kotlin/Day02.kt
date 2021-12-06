package day01

import java.io.File

class Day02 : Base<List<Pair<String, Int>>, Int>(2) {

    override fun part1(input: List<Pair<String, Int>>): Int {
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

    override fun part2(input: List<Pair<String, Int>>): Int {
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

    override fun mapInputData(file: File): List<Pair<String, Int>> =
        file.readLines()
            .map { it.split(" ") }
            .map { it.first() to it[1].toInt() }
}

fun main() {
    Day02().submitAll()
}