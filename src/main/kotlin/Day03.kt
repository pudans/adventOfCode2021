package day01

import java.io.File

class Day03 : Base<Array<String>>(3) {

    override fun part1(input: Array<String>): Int {
        val counts = IntArray(input.first().length)

        input.forEach {
            it.forEachIndexed { index, char ->
                if (char == '1') counts[index]++
            }
        }

        val gamma = IntArray(input.first().length)
        val epsilon = IntArray(input.first().length)

        counts.forEachIndexed { index, i ->
            gamma[index] = if (i > input.size / 2) 1 else 0
            epsilon[index] = if (i > input.size / 2) 0 else 1
        }
        return gamma.joinToString("").toInt(2) * epsilon.joinToString("").toInt(2)
    }

    override fun part2(input: Array<String>): Int {
        var oxygen = input.toList()
        var i = 0

        while (oxygen.size > 1) {
            val count = oxygen.count { it[i] == '1' }
            val sign = when {
                count == oxygen.size / 2 && oxygen.size % 2 == 0 -> '1'
                count > oxygen.size / 2 -> '1'
                else -> '0'
            }
            oxygen = oxygen.filter { it[i] == sign }
            i++
        }

        var co2 = input.toList()
        var j = 0

        while (co2.size > 1) {
            val count = co2.count { it[j] == '1' }
            val sign = when {
                count == co2.size / 2 && co2.size % 2 == 0 -> '0'
                count > co2.size / 2 -> '0'
                else -> '1'
            }
            co2 = co2.filter { it[j] == sign }
            j++
        }

        return oxygen.first().toInt(2) * co2.first().toInt(2)
    }

    override fun mapInputData(file: File): Array<String> =
        file.readLines()
            .map { it.trim() }
            .toTypedArray()

}

fun main() {
    Day03().submit()
}