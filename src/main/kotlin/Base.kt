package day01

import java.io.File

abstract class Base<Input, Output>(day : Int) {

    private val testInputFile = File("test_inputs/day${"%02d".format(day)}.txt")
    private val inputFile = File("inputs/day${"%02d".format(day)}.txt")

    abstract fun part1(input :Input): Output

    abstract fun part2(input :Input): Output

    abstract fun mapInputData(file: File): Input

    fun submitAll() {
        submitPart1TestInput()
        submitPart1Input()
        submitPart2TestInput()
        submitPart2Input()
    }

    fun submitPart1Input() {
        val input = mapInputData(inputFile)
        println("Part1: ${part1(input)}")
    }

    fun submitPart1TestInput() {
        val testInput = mapInputData(testInputFile)
        println("Part1 test: ${part1(testInput)}")
    }

    fun submitPart2Input() {
        val input = mapInputData(inputFile)
        println("Part2: ${part2(input)}")
    }

    fun submitPart2TestInput() {
        val testInput = mapInputData(testInputFile)
        println("Part2 test: ${part2(testInput)}")
    }
}