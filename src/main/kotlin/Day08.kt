package day01

import java.io.File
import java.lang.StringBuilder

typealias Numbers = Pair<List<String>, List<String>>

class Day08 : Base<List<Numbers>, Int>(8) {

    override fun part1(input: List<Numbers>): Int {
        return input.sumOf { numbers ->
            numbers.second.sumOf { if (it.length in (2..4) || it.length == 7) 1 else 0 as Int }
        }
    }

    override fun part2(input: List<Numbers>): Int {
        val result = input.sumOf { numbers ->
            val map = HashMap<Int,Char>()
            val nums = numbers.first
            map[1] = find1(nums)
            map[4] = find4(nums)
            map[2] = find2(nums)
            map[3] = find3(nums)
            map[6] = find6(nums)
            map[7] = find7(nums)
            map[5] = find5(nums)

            val all = map.map { it.value }.toSet()

            val f0 = all.subtract(setOf(map[4]))
            val f1 = setOf(map[3], map[6])
            val f2 = all.subtract(setOf(map[2],map[6]))
            val f3 = all.subtract(setOf(map[2],map[5]))
            val f4 = setOf(map[2], map[4]) + f1
            val f5 = all.subtract(setOf(map[3],map[5]))
            val f6 = all.subtract(setOf(map[3]))
            val f7 = setOf(map[3], map[6], map[1])
            val f8 = all
            val f9 = all.subtract(setOf(map[5]))

            val list = listOf(f0,f1,f2,f3,f4,f5,f6,f7,f8,f9)

            val builder = StringBuilder()
            numbers.second.forEach { num ->
                val result = list.indexOfFirst { it.size == num.length && it.containsAll(num.toSet()) }
                builder.append(result)
            }
            println(builder.toString())

            return@sumOf builder.toString().toInt()
        }

        return result
    }

    fun find1(input: List<String>): Char =
        input.find { it.length == 3 }!!.toCharArray().subtract(input.find { it.length == 2 }!!.toSet()).first()

    fun find4(input: List<String>): Char {
        val d7 = input.first { it.length == 3 }.toSet()
        val d4 = input.first { it.length == 4 }.toSet()
        val d8 = input.first { it.length == 7 }.toSet()
        val x6 = input.filter { it.length == 6 }.first { it.toSet().containsAll(d7) && !it.toSet().containsAll(d4) }
        return d8.subtract(x6.toSet()).first()
    }

    fun find2(input: List<String>): Char {
        val d4 = input.first { it.length == 4 }
        val d1 = input.first { it.length == 2 }
        return d4.toSet().subtract(d1.toSet()).first { it != find4(input) }
    }

    fun find3(input: List<String>): Char {
        val x5 = input.filter { it.length == 5 }
        val d5 = x5.first { it.toSet().contains(find2(input)) }
        val d1 = input.first { it.length == 2 }
        return d1.toSet().subtract(d5.toSet()).first()
    }

    fun find6(input: List<String>): Char {
        val d1 = input.first { it.length == 2 }
        return d1.toSet().first { it != find3(input) }
    }

    fun find7(input: List<String>): Char {
        val arr = setOf(find1(input), find2(input), find3(input), find4(input), find6(input))
        val x6 = input.filter { it.length == 6 }
        val d9 = x6.first { it.toSet().containsAll(arr) }
        return d9.toSet().subtract(arr).first()
    }

    fun find5(input: List<String>): Char {
        val arr = setOf(find1(input), find2(input), find3(input), find4(input), find6(input), find7(input))
        val d8 = input.first { it.length == 7 }
        return d8.toSet().subtract(arr).first()
    }

    override fun mapInputData(file: File): List<Numbers> {
        val result = ArrayList<Numbers>()
        file.readLines().forEach { line ->
            val (data1, data2) = line.trim().split('|')
            val nums1 = data1.trim().split(' ')
            val nums2 = data2.trim().split(' ')
            result.add(Numbers(nums1, nums2))
        }
        return result
    }
}

fun main() {
    Day08().submitAll()
}