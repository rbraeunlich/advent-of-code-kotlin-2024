fun main() {
    fun part1(input: List<String>): Int {
        val map = Array<CharArray>(input.size) { CharArray(input.size) { ' ' } }
        var guard: Guard? = null
        input.forEachIndexed { index, line ->
            if (line.contains("^")) {
                guard = Guard(index to line.indexOf('^'), Direction.UP)
            }
            map[index] = line.replace("^", ".").toCharArray()
        }
        require(guard != null)
        do {
            guard!!.move(map)
        } while (guard!!.isInMap(map.size))
        return guard!!.visistedPositions.size
    }

    fun part2(input: List<String>): Int {
        val map = Array<CharArray>(input.size) { CharArray(input.size) { ' ' } }
        var initialPosition: Pair<Int, Int>? = null
        input.forEachIndexed { index, line ->
            if (line.contains("^")) {
                initialPosition = index to line.indexOf('^')
            }
            map[index] = line.replace("^", ".").toCharArray()
        }
        require(initialPosition != null)
        val obstacles = mutableSetOf<Pair<Int, Int>>()
        var guard = CycleGuard(initialPosition!!, Direction.UP)
        map.forEachIndexed { index, row ->
            row.forEachIndexed { index2, column ->
                if(map[index][index2] == '.') {
                    map[index][index2] = '#'
                    do {
                        val isInLoop = guard.move(map)
                        if(isInLoop) {
                            obstacles.add(index to index2)
                        }
                    } while (guard.isInMap(map.size) && !isInLoop)
                    map[index][index2] = '.'
                    guard = CycleGuard(initialPosition!!, Direction.UP)
                }
            }
        }
        return obstacles.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

class Guard(
    /**
     * Row to column
     */
    var currentPosition: Pair<Int, Int>,
    var direction: Direction
) {
    val visistedPositions: MutableSet<Pair<Int, Int>> = mutableSetOf()

    fun move(map: Array<CharArray>) {
        visistedPositions.add(currentPosition)
        val (row, column) = currentPosition
        val nextPosition = when (direction) {
            Direction.UP -> row - 1 to column
            Direction.DOWN -> row + 1 to column
            Direction.LEFT -> row to column - 1
            Direction.RIGHT -> row to column + 1
        }
        if (isFree(nextPosition, map)) {
            currentPosition = nextPosition
        } else {
            direction = direction.nextDirection()
        }
    }

    private fun isFree(nextPosition: Pair<Int, Int>, map: Array<CharArray>): Boolean {
        val (row, column) = nextPosition
        if((row < 0 || row >= map.size) || (column < 0 || column >= map[0].size)) {
            return true
        }
        return map[nextPosition.first][nextPosition.second] == '.'
    }

    fun isInMap(size: Int): Boolean {
        val (row, column) = currentPosition
        return (row >= 0 && row < size) && (column >= 0 && column < size)
    }
}

class CycleGuard(
    /**
     * Row to column
     */
    var currentPosition: Pair<Int, Int>,
    var direction: Direction
) {
    val visistedPositions: MutableSet<Visited> = mutableSetOf()
    val cyclePositions: MutableSet<Pair<Int, Int>> = mutableSetOf()

    fun move(map: Array<CharArray>): Boolean {
        visistedPositions.add(Visited(currentPosition.first, currentPosition.second, direction))
        val nextPosition = nextPosition(currentPosition, direction)
        if (isFree(nextPosition, map)) {
            if(hasBeenVisisted(nextPosition, direction)) {
                return true
            }
            currentPosition = nextPosition
        } else {
            direction = direction.nextDirection()
        }
        return false
    }

    private fun nextPosition(currentPosition: Pair<Int, Int>, direction: Direction): Pair<Int, Int> {
        val (row, column) = currentPosition
        val nextPosition = when (direction) {
            Direction.UP -> row - 1 to column
            Direction.DOWN -> row + 1 to column
            Direction.LEFT -> row to column - 1
            Direction.RIGHT -> row to column + 1
        }
        return nextPosition
    }

    private fun hasBeenVisisted(nextPosition: Pair<Int, Int>, direction: Direction): Boolean {
        return Visited(nextPosition.first, nextPosition.second, direction) in visistedPositions
    }

    private fun isFree(nextPosition: Pair<Int, Int>, map: Array<CharArray>): Boolean {
        val (row, column) = nextPosition
        if((row < 0 || row >= map.size) || (column < 0 || column >= map[0].size)) {
            return true
        }
        return map[nextPosition.first][nextPosition.second] == '.'
    }

    fun isInMap(size: Int): Boolean {
        val (row, column) = currentPosition
        return (row >= 0 && row < size) && (column >= 0 && column < size)
    }

    data class Visited(val column: Int, val row: Int, val direction: Direction)

}

enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    fun nextDirection() =
        when (this) {
            UP -> RIGHT
            DOWN -> LEFT
            LEFT -> UP
            RIGHT -> DOWN
        }
}