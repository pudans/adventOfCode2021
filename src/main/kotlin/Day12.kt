package day01

import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Cave(val name: String) {
    val nodes = mutableSetOf<Cave>()

    override fun toString(): String {
        return "${nodes.size}"
    }
}

class Day12 : Base<List<Cave>, Int>(12) {

    override fun mapInputData(file: File): List<Cave> {
        val map = HashMap<String, Cave>()
        file.readLines().forEach { line ->
            val start = line.split("-")[0]
            val dest = line.split("-")[1]

            val startCave = map[start] ?: Cave(start)
            val destCave = map[dest] ?: Cave(dest)

            startCave.nodes.add(destCave)
            destCave.nodes.add(startCave)

            map[start] = startCave
            map[dest] = destCave
        }
        return map.values.toList()
    }

    override fun part1(input: List<Cave>): Int {
        val start = input.find { it.name == "start" }!!
        return start.nodes.sumOf { traverse(it, ArrayList()) }
    }

    private fun traverse(cave: Cave, map: ArrayList<String>): Int {
        if (cave.name == "end") return 1
        if (cave.name == "start") return 0
        if (cave.nodes.size == 0) return 0
        if (cave.name.first().isLowerCase()) {
            if (map.find { it == cave.name } != null) return 0
            map.add(cave.name)
        }
        return cave.nodes.sumOf { traverse(it, ArrayList(map)) }
    }

    override fun part2(input: List<Cave>): Int {
        val start = input.find { it.name == "start" }!!
        return start.nodes.sumOf { traverseWithCheckingOneSmallCaveTwice(it, ArrayList()) }
    }

    private fun traverseWithCheckingOneSmallCaveTwice(cave: Cave, map: ArrayList<String>): Int {
        if (cave.name == "end") return 1
        if (cave.name == "start") return 0
        if (cave.nodes.size == 0) return 0
        if (cave.name.first().isLowerCase()) {
            val contains = map.find { it == cave.name } != null
            map.forEach { smallCave ->
                val count = map.count { it == smallCave }
                if (count == 2 && contains) return 0
            }
            map.add(cave.name)
        }
        return cave.nodes.sumOf { traverseWithCheckingOneSmallCaveTwice(it, ArrayList(map)) }
    }
}

fun main() {
    Day12().submitAll()
}