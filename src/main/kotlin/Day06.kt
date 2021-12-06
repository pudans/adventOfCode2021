package day01

import java.io.File

class Day06 : Base<List<Int>, Long>(6) {

    private fun HashMap<Int,Long>.add(key: Int, count: Long) { put(key, getOrDefault(key, 0) + count) }

    override fun part1(input: List<Int>): Long {
        val fishes = input.toMutableList()
        val days = 80
        var count = fishes.size.toLong()
        val map = HashMap<Int, Long>(days + 10)
        fishes.forEach { map.add(it, 1) }
        repeat(days) { day ->
            val newFishes = map[day] ?: 0
            count += newFishes
            map.add(day + 7, newFishes)
            map.add(day + 9, newFishes)
        }
        return count
    }


    override fun part2(input: List<Int>): Long {
        val fishes = input.toMutableList()
        val days = 256
        var count = fishes.size.toLong()
        val map = HashMap<Int, Long>(days + 10)
        fishes.forEach { map.add(it, 1) }
        repeat(days) { day ->
            val newFishes = map[day] ?: 0
            count += newFishes
            map.add(day + 7, newFishes)
            map.add(day + 9, newFishes)
        }
        return count
    }

    override fun mapInputData(file: File): List<Int> =
        file.readLines().first().split(",").map { it.toInt() }
}

fun main() {
    Day06().submitAll()
}