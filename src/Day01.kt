import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.forEach{ line ->
            val (l, r) = line.split("   ")
            left.add(l.toInt())
            right.add(r.toInt())
        }
        return left.sorted().zip(right.sorted()).map { (l,r) -> abs(l-r) }.sum()
//        return input.size
    }

    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.forEach{ line ->
            val (l, r) = line.split("   ")
            left.add(l.toInt())
            right.add(r.toInt())
        }
        var sum = 0
        left.forEach{ num ->
            sum += num * (right.count { it == num })
        }
        return sum
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 11)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
