package day01

import java.io.File
import java.util.*

class Day10 : Base<Array<String>, Long>(10) {

    override fun mapInputData(file: File): Array<String> = file.readLines().toTypedArray()

    private fun mapToScore1(ch: Char): Long =
        when (ch) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            else -> 25137
        }
    private fun mapToScore2(ch: Char): Long =
        when (ch) {
            '(' -> 1
            '[' -> 2
            '{' -> 3
            else -> 4
        }

    private fun closeToOpen(ch: Char): Char =
        when (ch) {
            ')' -> '('
            ']' -> '['
            '}' -> '{'
            else -> '<'
        }

    private fun isOpen(ch: Char): Boolean =
        when (ch) {
            ')' -> false
            ']' -> false
            '}' -> false
            '>' -> false
            else -> true
        }

    override fun part1(input: Array<String>): Long {
        var result = 0L
        input.forEach { line ->
            val error = findError(line)
            if (error != null) {
                result += mapToScore1(error)
            }
        }
        return result
    }

    private fun findError(line: String) : Char? {
        val stack = Stack<Char>()
        for (i in line.indices) {
            val ch = line[i]
            if (isOpen(ch)) {
                stack.add(ch)
                continue
            }
            if (stack.isEmpty() || stack.peek() != closeToOpen(ch)) {
                return ch
            }
            stack.pop()
        }
        return null
    }

    override fun part2(input: Array<String>): Long {
        val result = mutableListOf<Long>()
        for (j in input.indices) {
            val line = input[j]
            if (findError(line) != null) continue
            var resultLine = 0L
            val stack = Stack<Char>()
            for (i in line.indices) {
                val ch = line[i]
                if (isOpen(ch)) stack.add(ch) else stack.pop()
            }
            while (stack.isNotEmpty()) {
                val pp = stack.pop()
                resultLine *= 5
                resultLine += mapToScore2(pp)
            }
            result.add(resultLine)
            println(resultLine)
        }
        return result.sorted()[result.size / 2]
    }
}

fun main() {
    Day10().submitAll()
}