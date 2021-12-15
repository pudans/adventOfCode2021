package day01

import java.io.File
import java.lang.StringBuilder

data class Polymerization(val template: CharArray, val map: MutableMap<String, Char>)

class Day14 : Base<Polymerization, Long>(14) {

    override fun mapInputData(file: File): Polymerization {
        val lines = file.readLines()
        val template = lines.first().toCharArray()
        val map = mutableMapOf<String, Char>()
        for (i in 2 until lines.size) {
            val splited = lines[i].split("->").map { it.trim() }
            map[splited[0]] = splited[1].first()
        }
        return Polymerization(template, map)
    }

    override fun part1(input: Polymerization): Long {
//        val map = input.map
//        var temp = input.template
//        val steps = 10
//        println(temp)
//        repeat(steps) { step ->
//            println("step $step, len=${temp.size}")
//            val tempResult = StringBuilder()
//            tempResult.append(temp.first())
//            for (i in 0 until temp.lastIndex) {
//                val f1 = temp[i]
//                val f2 = temp[i+1]
//                val f3 = map["$f1$f2"]!!
//                tempResult.append("$f3$f2")
//            }
//            temp = tempResult.toString()
//        }
//        val set = temp.toCharArray().toSet().associateWith { ch -> temp.count { it == ch } }
//        println(set)
//        val min = set.minOf { it.value }
//        val max = set.maxOf { it.value }
//        println(max)
//        println(min)
//        return (max - min).toLong()
        return 0L
    }

    /**
     * OOM
     * need more 16gb
     */
    override fun part2(input: Polymerization): Long {
        val map = input.map
        var temps = listOf<CharArray>(input.template)
        val steps = 30

        repeat(steps) { step ->
            println("step $step, temps ${temps.size}")
            val tempStringResult = ArrayList<Char>(5_100)
            val newTemps = mutableListOf<CharArray>()

            for (j in temps.indices) {
                val temp = temps[j]
                if (j == 0) {
                    tempStringResult.add(temp.first())
                } else {
                    val f1 = temps[j-1].last()
                    val f2 = temp.first()
                    val f3 = map["$f1$f2"]!!
                    tempStringResult.add(f3)
                    tempStringResult.add(f2)
                }

                for (i in 0 until temp.lastIndex) {
                    val f1 = temp[i]
                    val f2 = temp[i+1]
                    val f3 = map["$f1$f2"]!!
                    tempStringResult.add(f3)
                    tempStringResult.add(f2)
                    if (tempStringResult.size > 5_000) {
                        newTemps.add(tempStringResult.toCharArray())
                        tempStringResult.clear()
                    }
                }
                if (j % 10 == 0) {
                    System.gc()
                }
            }

            if (tempStringResult.isNotEmpty()) {
                newTemps.add(tempStringResult.toCharArray())
            }
            System.gc()

            temps = newTemps
        }

        println("CALCULATION")

        val set = HashMap<Char, Long>()
        temps.forEach { temp ->
            temp.toSet().forEach { ch ->
                set[ch] = set.getOrDefault(ch, 0L) + temp.count { it == ch }
            }
        }
        println(set)
        val min = set.minOf { it.value }
        val max = set.maxOf { it.value }
        println(max)
        println(min)
        return max - min
    }
}

fun main() {
    Day14().submitPart2Input()
}