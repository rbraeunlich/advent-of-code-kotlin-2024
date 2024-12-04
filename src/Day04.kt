fun main() {
    fun findXmasDown(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            columnIndex + 1 < puzzle.size && puzzle[columnIndex + 1][rowIndex] == 'M' &&
            columnIndex + 2 < puzzle.size && puzzle[columnIndex + 2][rowIndex] == 'A' &&
            columnIndex + 3 < puzzle.size && puzzle[columnIndex + 3][rowIndex] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasDownRight(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            columnIndex + 1 < puzzle.size && rowIndex + 1 < puzzle.size &&
            puzzle[columnIndex + 1][rowIndex + 1] == 'M' &&
            columnIndex + 2 < puzzle.size && rowIndex + 2 < puzzle.size &&
            puzzle[columnIndex + 2][rowIndex + 2] == 'A' &&
            columnIndex + 3 < puzzle.size && rowIndex + 3 < puzzle.size &&
            puzzle[columnIndex + 3][rowIndex + 3] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasRight(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            rowIndex + 1 < puzzle.size &&
            puzzle[columnIndex][rowIndex + 1] == 'M' &&
            rowIndex + 2 < puzzle.size &&
            puzzle[columnIndex][rowIndex + 2] == 'A' &&
            rowIndex + 3 < puzzle.size &&
            puzzle[columnIndex][rowIndex + 3] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasUpRight(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            columnIndex - 1 >= 0 && rowIndex + 1 < puzzle.size &&
            puzzle[columnIndex - 1][rowIndex + 1] == 'M' &&
            columnIndex - 2 >= 0 && rowIndex + 2 < puzzle.size &&
            puzzle[columnIndex - 2][rowIndex + 2] == 'A' &&
            columnIndex - 3 >= 0 && rowIndex + 3 < puzzle.size &&
            puzzle[columnIndex - 3][rowIndex + 3] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasUp(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            columnIndex - 1 >= 0 && puzzle[columnIndex - 1][rowIndex] == 'M' &&
            columnIndex - 2 >= 0 && puzzle[columnIndex - 2][rowIndex] == 'A' &&
            columnIndex - 3 >= 0 && puzzle[columnIndex - 3][rowIndex] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasUpLeft(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            columnIndex - 1 >= 0 && rowIndex - 1 >= 0 &&
            puzzle[columnIndex - 1][rowIndex - 1] == 'M' &&
            columnIndex - 2 >= 0 && rowIndex - 2 >= 0 &&
            puzzle[columnIndex - 2][rowIndex - 2] == 'A' &&
            columnIndex - 3 >= 0 && rowIndex - 3 >= 0 &&
            puzzle[columnIndex - 3][rowIndex - 3] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasLeft(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            rowIndex - 1 >= 0 &&
            puzzle[columnIndex][rowIndex - 1] == 'M' &&
            rowIndex - 2 >= 0 &&
            puzzle[columnIndex][rowIndex - 2] == 'A' &&
            rowIndex - 3 >= 0 &&
            puzzle[columnIndex][rowIndex - 3] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasDownLeft(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            columnIndex + 1 < puzzle.size && rowIndex - 1 >= 0 &&
            puzzle[columnIndex + 1][rowIndex - 1] == 'M' &&
            columnIndex + 2 < puzzle.size && rowIndex - 2 >= 0 &&
            puzzle[columnIndex + 2][rowIndex - 2] == 'A' &&
            columnIndex + 3 < puzzle.size && rowIndex - 3 >= 0 &&
            puzzle[columnIndex + 3][rowIndex - 3] == 'S'
        ) {
            1
        } else {
            0
        }
    }

    fun findXmasses(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return findXmasDown(puzzle, columnIndex, rowIndex) +
                findXmasDownRight(puzzle, columnIndex, rowIndex) +
                findXmasRight(puzzle, columnIndex, rowIndex) +
                findXmasUpRight(puzzle, columnIndex, rowIndex) +
                findXmasUp(puzzle, columnIndex, rowIndex) +
                findXmasUpLeft(puzzle, columnIndex, rowIndex) +
                findXmasLeft(puzzle, columnIndex, rowIndex) +
                findXmasDownLeft(puzzle, columnIndex, rowIndex)
    }

    fun part1(input: List<String>): Int {
        val puzzle = Array<CharArray>(input.size) { CharArray(input.size) { ' ' } }
        input.forEachIndexed { index, line ->
            puzzle[index] = line.toCharArray()
        }
        var xmasses = 0
        puzzle.forEachIndexed { columnIndex, row ->
            row.forEachIndexed { rowIndex, c ->
                if (puzzle[columnIndex][rowIndex] == 'X') {
                    val found = findXmasses(puzzle, columnIndex, rowIndex)
//                    if(found > 0) {
//                        println("$columnIndex $rowIndex $found")
//                    }
                    xmasses += found
                }
            }
        }
        return xmasses
    }

    fun findMasses(puzzle: Array<CharArray>, columnIndex: Int, rowIndex: Int): Int {
        return if (
            (    // top left M and down right S
                    columnIndex - 1 >= 0 && rowIndex - 1 >= 0 &&
                            puzzle[columnIndex - 1][rowIndex - 1] == 'M' &&
                            columnIndex + 1 < puzzle.size && rowIndex + 1 < puzzle.size &&
                            puzzle[columnIndex + 1][rowIndex + 1] == 'S' ||
                            // top left S and down right M
                            columnIndex - 1 >= 0 && rowIndex - 1 >= 0 &&
                            puzzle[columnIndex - 1][rowIndex - 1] == 'S' &&
                            columnIndex + 1 < puzzle.size && rowIndex + 1 < puzzle.size &&
                            puzzle[columnIndex + 1][rowIndex + 1] == 'M'
                    ) &&
            (    // down left M and top right S
                    columnIndex + 1 < puzzle.size && rowIndex - 1 >= 0 &&
                            puzzle[columnIndex + 1][rowIndex - 1] == 'M' &&
                            columnIndex - 1 >= 0 && rowIndex + 1 < puzzle.size &&
                            puzzle[columnIndex - 1][rowIndex + 1] == 'S' ||
                            // down left S and top right M
                            columnIndex + 1 < puzzle.size && rowIndex - 1 >= 0 &&
                            puzzle[columnIndex + 1][rowIndex - 1] == 'S' &&
                            columnIndex - 1 >= 0 && rowIndex + 1 < puzzle.size &&
                            puzzle[columnIndex - 1][rowIndex + 1] == 'M'
                    )
        ) {
            1
        } else {
            0
        }
    }

    fun part2(input: List<String>): Int {
        val puzzle = Array<CharArray>(input.size) { CharArray(input.size) { ' ' } }
        input.forEachIndexed { index, line ->
            puzzle[index] = line.toCharArray()
        }
        var masses = 0
        puzzle.forEachIndexed { columnIndex, row ->
            row.forEachIndexed { rowIndex, c ->
                if (puzzle[columnIndex][rowIndex] == 'A') {
                    val found = findMasses(puzzle, columnIndex, rowIndex)
//                    if(found > 0) {
//                        println("$columnIndex $rowIndex $found")
//                    }
                    masses += found
                }
            }
        }
        return masses
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
