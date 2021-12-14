package day01

import java.io.File

data class Fold(val ch: Char, val ind: Int)

data class InputData(val field: MutableSet<Point>, val folds: MutableList<Fold>)

class Day13 : Base<InputData, Int>(13) {

    override fun mapInputData(file: File): InputData {
        val field = mutableSetOf<Point>()
        val folds = mutableListOf<Fold>()
        var isFolds = false
        file.readLines().forEach { line ->
            if (line.isEmpty()) {
                isFolds = true
            } else {
                if (isFolds) {
                    val splited = line.split("=")
                    folds.add(Fold(splited[0].last(), splited[1].toInt()))
                } else {
                    val splited = line.split(",").map { it.toInt() }
                    field.add(Point(splited[0],splited[1]))
                }
            }
        }
        return InputData(field, folds)
    }

    override fun part1(input: InputData): Int {
        println("${input.field.size} ${input.field}")
        when (input.folds.first().ch) {
            'y' -> foldY(input.field, input.folds.first().ind)
            'x' -> foldX(input.field, input.folds.first().ind)
        }
        println("${input.field.size} ${input.field}")
        input.field.print()
        return input.field.size
    }

    private fun foldX(field: MutableSet<Point>, value: Int) {
        val toRemove = mutableListOf<Point>()
        val toAdd = mutableListOf<Point>()
        field.forEach { point ->
            if (point.x > value) {
                toRemove.add(point)
                toAdd.add(Point(point.x - 2 * (point.x - value), point.y))
            }
        }
        toAdd.forEach { field.add(it) }
        toRemove.forEach { field.remove(it) }
    }

    private fun foldY(field: MutableSet<Point>, value: Int) {
        val toRemove = mutableListOf<Point>()
        val toAdd = mutableListOf<Point>()
        field.forEach { point ->
            if (point.y > value) {
                toRemove.add(point)
                toAdd.add(Point(point.x, point.y - 2 * (point.y - value)))
            }
        }
        toAdd.forEach { field.add(it) }
        toRemove.forEach { field.remove(it) }
    }

    override fun part2(input: InputData): Int {
        println("${input.field.size} ${input.field}")
        input.folds.forEach {
            when (it.ch) {
                'y' -> foldY(input.field, it.ind)
                'x' -> foldX(input.field, it.ind)
            }
            println("${input.field.size} ${input.field}")
        }
        input.field.print()
        return input.field.size
    }

    private fun MutableSet<Point>.print() {
        val maxX = this.maxOf { it.x }
        val maxY = this.maxOf { it.y }
        val map = Array(maxY + 1) { BooleanArray(maxX + 1) }
        this.forEach { map[it.y][it.x] = true }
        map.forEach {
            println(it.map { if (it) "#" else "." }.joinToString(""))
        }
    }
}

fun main() {
    Day13().submitPart2Input()
}