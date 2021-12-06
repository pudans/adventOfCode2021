package day01

import java.io.File

abstract class Base<Input, Output>(day : Int) {

    private val testInputFile = File("test_inputs/day${"%02d".format(day)}.txt")
    private val inputFile = File("inputs/day${"%02d".format(day)}.txt")

    abstract fun part1(input :Input): Output

    abstract fun part2(input :Input): Output

    abstract fun mapInputData(file: File): Input

    fun submitAll() {
        submitTestInput()
        submitInput()
    }

    fun submitTestInput() {
        val testInput = mapInputData(testInputFile)
        println("Part1 test: ${part1(testInput)}")
        println("Part2 test: ${part2(testInput)}")
    }

    fun submitInput() {
        val input = mapInputData(inputFile)
        println("Part1: ${part1(input)}")
        println("Part2: ${part2(input)}")
    }
}