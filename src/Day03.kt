import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val regEx = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        return input.flatMap { line ->
            regEx.findAll(line).map {
                val (first, second) = it.destructured
                first.toInt() * second.toInt()
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val regEx = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        return input.flatMap { line ->
            line.split("do()")
        }.map { line ->
            if(line.contains("don't()")) {
                val firstDont = line.indexOf("don't()")
                line.take(firstDont + 1)
            } else {
                line
            }
        }.flatMap { line ->
            regEx.findAll(line).map {
                val (first, second) = it.destructured
                first.toInt() * second.toInt()
            }
        }.sum()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    val testInput2 = readInput("Day03_test2")
    check(part2(testInput2) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
