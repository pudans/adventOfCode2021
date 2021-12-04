package day01

import java.io.File

typealias Board = ArrayList<MutableList<Int>>

class Day04 : Base<Data>(4) {

    override fun part1(input: Data): Int {
        for (i in input.nums.indices) {
            val num = input.nums[i]
            input.boards.forEachIndexed { index1, board ->
                board.forEachIndexed { index2, row ->
                    row.forEachIndexed { index3, item ->
                        if (item == num) {
                            input.boards[index1][index2][index3] = -1
                        }
                    }
                }
            }
            input.boards.forEach { board ->
                for (j in 0 until 5) {
                    val array1 = arrayOf(board[0][j], board[1][j], board[2][j], board[3][j], board[4][j])
                    val array2 = board[j]
                    if (array1.sumOf { it } == -5 || array2.sumOf { it } == -5) {
                        val sum = board.sumOf { row ->
                            row.sumOf { if (it != -1) it else 0 }
                        }
                        return sum * num
                    }
                }
            }
        }
        return 0
    }

    override fun part2(input: Data): Int {
        var result = 0
        for (i in input.nums.indices) {
            val num = input.nums[i]
            input.boards.forEachIndexed { index1, board ->
                board.forEachIndexed { index2, row ->
                    row.forEachIndexed { index3, item ->
                        if (item == num) {
                            input.boards[index1][index2][index3] = -1
                        }
                    }
                }
            }
            input.boards.forEachIndexed { index1, board ->
                repeat(5) { j ->
                    val array1 = arrayOf(board[0][j], board[1][j], board[2][j], board[3][j], board[4][j])
                    val array2 = board[j]
                    if (array1.sumOf { it } == -5 || array2.sumOf { it } == -5) {
                        val sum = board.sumOf { row ->
                            row.sumOf { if (it != -1) it else 0 }
                        }
                        board.forEachIndexed { index2, row ->
                            row.forEachIndexed { index3, _ ->
                                input.boards[index1][index2][index3] = -10
                            }
                        }
                        result = sum * num
                    }
                }
            }
        }
        return result
    }

    override fun mapInputData(file: File): Data {
        val lines = file.readLines()
        val nums = lines.first().split(',').map { it.toInt() }
        val n = (lines.size - 1) / 6
        val boards = ArrayList<Board>(n)

        var i = 1
        repeat(n) {
            i++
            val board = ArrayList<MutableList<Int>>(5)
            repeat(5) {
                val line = lines[i + it]
                val row = line.trim().replace("  "," ").split(" ").map { it.toInt() }.toMutableList()
                board.add(row)
            }
            boards.add(board)
            i+=5
        }

        return Data(nums, boards)
    }


}

data class Data(val nums: List<Int>, val boards: List<Board>)

fun main() {
    Day04().submitAll()
}