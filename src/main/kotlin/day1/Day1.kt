package day1

import util.fetchData
import java.lang.Integer.parseInt

private fun parseExpenseReport(lines: List<String>): ExpenseReport {
    return ExpenseReport(lines.map { Item(parseInt(it)) })
}


private fun findMatch(combinations: List<Combination>) =
    combinations.find { it.sums2020() }

fun main() {
    val lines = fetchData("src/main/resources/day1/input.txt")
    val expenseReport = parseExpenseReport(lines)

    val combinationsTakingTwo = expenseReport.getCombinations(2)
    val matchTakingTwo = findMatch(combinationsTakingTwo)

    println(matchTakingTwo?.result)

    val combinationsTakingThree = expenseReport.getCombinations(3)
    val matchTakingThree = findMatch(combinationsTakingThree)

    println(matchTakingThree?.result)
}

data class ExpenseReport(val items: List<Item>) {
    fun getCombinations(elements: Int): List<Combination> {
        if (elements == 0) {
            return listOf()
        }

        val rest = getCombinations(elements - 1)
        if (rest.isNotEmpty()) {
            return items.flatMap { item -> rest.map { Combination(listOf(item) + it.items) } }
        }

        return items.map { Combination(listOf(it)) }
    }
}

data class Combination(val items: List<Item>) {

    fun sums2020(): Boolean {
        return items.sumBy { it.value } == 2020
    }


    val result: Int
        get() {
            return items.map { it.value }.reduce { acc, i -> acc * i }
        }
}

data class Item(val value: Int)
