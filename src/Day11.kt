import java.math.BigDecimal

fun main() {
    fun part1(input: List<String>): Int {
        var stones: List<Stone> = input.flatMap { line -> line.split(" ").map { Stone(it.toLong()) } }
        repeat(25) {
            val newStones = mutableListOf<Stone>()
            stones.forEach { stone ->
                newStones.addAll(stone.applyRule())
            }
//            println(newStones)
            stones = newStones
        }
        return stones.size
    }

    fun part2(input: List<String>): Long {
        var stones: List<Stone> = input.flatMap { line -> line.split(" ").map { Stone(it.toLong()) } }
        val cache = mutableMapOf<Pair<Stone, Int>, Long>()
        fun sumForStone(stone: Stone, iteration: Int): Long {
            if(iteration == 75) {
                return 1L
            }
            return if (cache.contains(stone to iteration)) {
                    cache[stone to iteration]!!
                } else {
                    val sum = stone.applyRule().sumOf { sumForStone(it, iteration + 1) }
                    cache[stone to iteration] = sum
                    sum
                }
            }
//        sumForStone(stones.first(), 0)
        return stones.sumOf { stone ->
                sumForStone(stone, 0)
            }
//        return 1
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 55312)
//    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}

class Stone(val number: Long) {

    fun applyRule(): List<Stone> =
        when {
            number == 0L -> listOf(Stone(1))
            (number.toString().length % 2 == 0) -> {
                val numberString = number.toString()
                val leftHalf= numberString.take(numberString.length / 2)
                val rightHalf = numberString.drop(numberString.length / 2)
                listOf(Stone(leftHalf.toLong()), Stone(rightHalf.toLong()))
            }
            else -> listOf(Stone(number * 2024))
        }

    override fun toString(): String {
        return number.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stone

        return number == other.number
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }
}
