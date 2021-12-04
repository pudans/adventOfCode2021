package day01

import java.io.File

abstract class Base<T>(day : Int) {

    private val testInputFile = File("test_inputs/day${"%02d".format(day)}.txt")
    private val inputFile = File("inputs/day${"%02d".format(day)}.txt")

    abstract fun part1(input :T): Int

    abstract fun part2(input :T): Int

    abstract fun mapInputData(file: File): T

    fun submit() {
        val testInput = mapInputData(testInputFile)
        val input = mapInputData(inputFile)

        println("Part1 test: ${part1(testInput)}")
        println("Part1: ${part1(input)}")

        println("Part2 test: ${part2(testInput)}")
        println("Part2: ${part2(input)}")
    }
}