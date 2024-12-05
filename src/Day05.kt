fun main() {
    fun checkForValidUpdate(
        line: String,
        pageRulesShouldBeBefore: MutableMap<Int, List<Int>>,
        pageRulesShouldBeAfter: MutableMap<Int, List<Int>>
    ): Long {
        val update = line.split(",")
        outer@ for(index in update.indices) {
            val page = update[index].toInt()
            for(followingPage in update.drop(index+1)) {
                if(pageRulesShouldBeBefore[page]?.contains(followingPage.toInt()) != false &&
                    !(pageRulesShouldBeBefore[followingPage.toInt()]?.contains(page) ?: false)
                    ) {
                    continue
                } else {
                    return 0
                }
            }
        }
        return update[(update.size / 2)].toLong()
    }

    fun part1(input: List<String>): Long {
        var readUpdates = false
        val pageRulesShouldBeBefore = mutableMapOf<Int, List<Int>>()
        val pageRulesShouldBeAfter = mutableMapOf<Int, List<Int>>()
        var middleNumberSum = 0L
        input.forEach { line ->
            if(line.isEmpty()) {
                readUpdates = true
            } else if(readUpdates) {
                middleNumberSum += checkForValidUpdate(line, pageRulesShouldBeBefore, pageRulesShouldBeAfter)
            } else {
                val (page, afterPage) = line.split("|")
                pageRulesShouldBeBefore.merge(page.toInt(), listOf(afterPage.toInt())) { a, b -> a + b }
                pageRulesShouldBeAfter.merge(afterPage.toInt(), listOf(page.toInt())) { a, b -> a + b }
            }

        }
        return middleNumberSum
    }

    fun reorder(update: List<Int>, pageRulesShouldBeBefore: MutableMap<Int, List<Int>>): Long {
        val newList = mutableListOf<Int>()
        outer@ for(index in update.indices) {
            val page = update[index]
            for(followingPage in update.drop(index+1)) {
                if(pageRulesShouldBeBefore[page]?.contains(followingPage) != false &&
                    !(pageRulesShouldBeBefore[followingPage]?.contains(page) ?: false)
                ) {
                    continue
                } else {
                    newList.add(followingPage)
                    newList.add(page)
                    newList.addAll(update.filterNot { it == followingPage || it==page || newList.contains(it) }.map { it })
                    return reorder(newList, pageRulesShouldBeBefore)
                }
            }
            newList.add(page)
        }
        return update[(update.size / 2)].toLong()
    }

    fun part2(input: List<String>): Long {
        var readUpdates = false
        val pageRulesShouldBeBefore = mutableMapOf<Int, List<Int>>()
        val pageRulesShouldBeAfter = mutableMapOf<Int, List<Int>>()
        var middleNumberSum = 0L
        input.forEach { line ->
            if(line.isEmpty()) {
                readUpdates = true
            } else if(readUpdates) {
                if(checkForValidUpdate(line, pageRulesShouldBeBefore, pageRulesShouldBeAfter) == 0L) {
                    middleNumberSum += reorder(line.split(",").map { it.toInt() }, pageRulesShouldBeBefore)
                }
            } else {
                val (page, afterPage) = line.split("|")
                pageRulesShouldBeBefore.merge(page.toInt(), listOf(afterPage.toInt())) { a, b -> a + b }
                pageRulesShouldBeAfter.merge(afterPage.toInt(), listOf(page.toInt())) { a, b -> a + b }
            }

        }
        return middleNumberSum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143L)
    check(part2(testInput) == 123L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
