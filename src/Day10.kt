fun main() {
    fun findAllTrails(row: Int, column: Int, map: Array<CharArray>, ninesFound: MutableSet<Pair<Int, Int>> = mutableSetOf<Pair<Int, Int>>()): Set<Pair<Int, Int>> {
        val currentPosition = map.getOrNull(row)?.getOrNull(column)
        if(currentPosition == null) {
            return emptySet()
        }
        if (currentPosition == '9') {
            ninesFound.add(Pair(row, column))
            return ninesFound
        } else {
            if(map.getOrNull(row +1)?.getOrNull(column)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                ninesFound.addAll(findAllTrails(row+1, column, map))
            }
            if(map.getOrNull(row -1)?.getOrNull(column)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                ninesFound.addAll(findAllTrails(row-1, column, map))
            }
            if(map.getOrNull(row )?.getOrNull(column+1)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                ninesFound.addAll(findAllTrails(row, column+1, map))
            }
            if(map.getOrNull(row)?.getOrNull(column-1)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                ninesFound.addAll(findAllTrails(row, column-1, map))
            }
            return ninesFound
        }
    }

    fun part1(input: List<String>): Int {
        val map = Array<CharArray>(input.size) { CharArray(input.size) { ' ' } }
        input.forEachIndexed { index, line ->
            map[index] = line.toCharArray()
        }
        var sum = 0
        map.forEachIndexed { row, line ->
            line.forEachIndexed { column, c ->
                if (c == '0') {
                    sum += findAllTrails(row, column, map).size
                }
            }
        }
        return sum
    }

    fun findAllTrailRatings(row: Int, column: Int, map: Array<CharArray>): Int {
        val currentPosition = map.getOrNull(row)?.getOrNull(column)
        if(currentPosition == null) {
            return 0
        }
        if (currentPosition == '9') {
            return 1
        } else {
            var trails = 0
            if(map.getOrNull(row +1)?.getOrNull(column)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                trails += findAllTrailRatings(row+1, column, map)
            }
            if(map.getOrNull(row -1)?.getOrNull(column)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                trails += findAllTrailRatings(row-1, column, map)
            }
            if(map.getOrNull(row )?.getOrNull(column+1)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                trails += findAllTrailRatings(row, column+1, map)
            }
            if(map.getOrNull(row)?.getOrNull(column-1)?.toString()?.toInt() == (currentPosition.toString().toInt() + 1)) {
                trails += findAllTrailRatings(row, column-1, map)
            }
            return trails
        }
    }

    fun part2(input: List<String>): Int {
        val map = Array<CharArray>(input.size) { CharArray(input.size) { ' ' } }
        input.forEachIndexed { index, line ->
            map[index] = line.toCharArray()
        }
        var sum = 0
        map.forEachIndexed { row, line ->
            line.forEachIndexed { column, c ->
                if (c == '0') {
                    sum += findAllTrailRatings(row, column, map)
                }
            }
        }
        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 36)
    check(part2(testInput) == 81)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
