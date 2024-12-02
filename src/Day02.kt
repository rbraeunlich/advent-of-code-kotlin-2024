import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            val report = line.split(" ").map { it.toInt() }
            val isDecreasing = report.first() > report[1]
            val isSafe = report.windowed(2).map { (first, second) ->
                if(isDecreasing) {
                    val diff = first - second
                    diff in 1..3
                } else {
                    val diff = second - first
                    diff in 1..3
                }
            }.reduce(Boolean::and)
            if(isSafe) {1} else {0}
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            val report = line.split(" ").map { it.toInt() }
            val permutations: List<List<Int>> = report.indices.map { index ->
                val newReport = report.toMutableList()
                newReport.removeAt(index)
                newReport
            }
            val safePermutations = (permutations).map {
                val isDecreasing = it.first() > it[1]
                it.windowed(2).map { (first, second) ->
                    if (isDecreasing) {
                        val diff = first - second
                        diff in 1..3
                    } else {
                        val diff = second - first
                        diff in 1..3
                    }
                }.reduce(Boolean::and)
            }
            val isSafe = safePermutations.reduce(Boolean::or)
            if(isSafe) {1} else {0}
        }.sum()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
