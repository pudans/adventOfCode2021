package day01

import java.io.File

fun day3(input: Array<String>): Int {
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

//    println(counts.toList())
//    println(gamma.toList())
//    println(epsilon.toList())
//
//    println(gamma.joinToString("").toInt(2))
//    println(epsilon.joinToString("").toInt(2))

    return gamma.joinToString("").toInt(2) * epsilon.joinToString("").toInt(2)
}

fun day3part2(input: Array<String>): Int {

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

//        println(count)
//        println(sign)
//        println(oxygen.map { it.toList() })

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

//        println(count)
//        println(sign)
//        println(co2.map { it.toList() })

        j++
    }

    return oxygen.first().toInt(2) * co2.first().toInt(2)
}

fun main() {

    val test = arrayOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )
    val input = File("inputs/day03.txt")
        .readLines()
        .map { it.trim() }
        .toTypedArray()

    println(day3(test))
    println(day3(input))

    println(day3part2(test))
    println(day3part2(input))
}