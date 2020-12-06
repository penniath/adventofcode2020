package day5

import util.fetchData
import kotlin.math.ceil
import kotlin.math.floor

private fun parseBoardingPasses(lines: List<String>): List<BoardingPass> {
    return lines.map(::parseBoardingPass)
}

fun parseBoardingPass(line: String): BoardingPass {
    val row = calculateRow(line.substring(0 until 7))
    val column = calculateColumn(line.substring(7 until line.length))

    return BoardingPass(row, column)
}

fun main() {
    val lines = fetchData("src/main/resources/day5/input.txt")
    val boardingPasses = parseBoardingPasses(lines)
    val max = boardingPasses.maxByOrNull { it.id }

    println(max?.id)
}

data class BoardingPass(val row: Int, val column: Int) {
    val id: Int
        get() {
            return row * 8 + column
        }
}

private fun calculateRow(definition: String): Int {
    return calculateValue(definition, 'F', 0..127)
}

private fun calculateColumn(definition: String): Int {
    return calculateValue(definition, 'L', 0..7)
}

private fun calculateValue(definition: String, lowerChar: Char, startingRange: IntRange): Int {
    var range = startingRange

    for (def in definition) {
        val diff = (range.last - range.first) / 2.0
        range = if (def == lowerChar) {
            val end: Int = floor(diff).toInt() + range.first
            range.first..end
        } else {
            val start: Int = ceil(diff).toInt() + range.first
            start..range.last
        }
    }

    return range.first
}