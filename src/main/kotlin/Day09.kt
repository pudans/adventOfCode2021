package day01

import java.io.File
import java.lang.StringBuilder

class Day09 : Base<Array<IntArray>, Int>(9) {

    override fun mapInputData(file: File): Array<IntArray> {
        val lines = file.readLines()
        return Array(lines.size) { pos ->
            lines[pos].trim().toCharArray().map { it.digitToInt() }.toIntArray()
        }
    }

    override fun part1(input: Array<IntArray>): Int {
        var result = 0

        input.forEachIndexed { index1, ints ->
            ints.forEachIndexed { index2, item ->

                if (item < (input[index1].getOrNull(index2 + 1) ?: Int.MAX_VALUE)
                    && item < (input[index1].getOrNull(index2 - 1) ?: Int.MAX_VALUE)
                    && item < (input.getOrNull(index1 - 1)?.getOrNull(index2) ?: Int.MAX_VALUE)
                    && item < (input.getOrNull(index1 + 1)?.getOrNull(index2) ?: Int.MAX_VALUE)
                ) {
                    result+= item + 1
                }
            }
        }

        return result
    }

    override fun part2(input: Array<IntArray>): Int {
        var result = 1

        val saved = HashSet<Point>()
        val map = HashMap<Int,Int>()

        fun find(point: Point, index: Int) {
            if (point.x in 0..input.lastIndex && point.y in 0..input.first().lastIndex) {
                if (!saved.contains(point) && input[point.x][point.y] != 9) {
                    saved.add(point)
                    map[index] = (map[index] ?: 0) + 1
                    find(Point(point.x+1, point.y), index)
                    find(Point(point.x-1, point.y), index)
                    find(Point(point.x, point.y+1), index)
                    find(Point(point.x, point.y-1), index)
                }
            }
        }

        while (result != 0) {
            result = 0
            input.forEachIndexed { index1, ints ->
                ints.forEachIndexed { index2, item ->
                    if (item < (input[index1].getOrNull(index2 + 1) ?: Int.MAX_VALUE)
                        && item < (input[index1].getOrNull(index2 - 1) ?: Int.MAX_VALUE)
                        && item < (input.getOrNull(index1 - 1)?.getOrNull(index2) ?: Int.MAX_VALUE)
                        && item < (input.getOrNull(index1 + 1)?.getOrNull(index2) ?: Int.MAX_VALUE)
                    ) {
                        val point = Point(index1, index2)
                        if (item != 9 && !saved.contains(point)) {
                            find(point, map.size)
                        }
                    }
                }
            }
        }
        return map.map { it.value }.sortedDescending().take(3).reduce { acc, i -> acc * i }
    }
}

fun main() {
    Day09().submitAll()
}