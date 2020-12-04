import java.io.File

private fun fetchData(): List<String> {
    val file = File("src/main/resources/day3/input.txt")

    return file.readLines()
}

private fun buildMap(lines: List<String>): MapOfTrees {
    val map = MapOfTrees()
    lines.forEachIndexed { y, line ->
        line.split("")
            .filter { it.isNotEmpty() }
            .forEachIndexed { x, column -> map.add(TreeCoordinate(x, y, column == "#")) }
    }

    return map
}

private val stepThreeRightOneDown: (Coordinate) -> Coordinate = { Coordinate(it.x + 3, it.y + 1) }
private val stepOneRightOneDown: (Coordinate) -> Coordinate = { Coordinate(it.x + 1, it.y + 1) }
private val stepFiveRightOneDown: (Coordinate) -> Coordinate = { Coordinate(it.x + 5, it.y + 1) }
private val stepSevenRightOneDown: (Coordinate) -> Coordinate = { Coordinate(it.x + 7, it.y + 1) }
private val stepOneRightTwoDown: (Coordinate) -> Coordinate = { Coordinate(it.x + 1, it.y + 2) }

fun main() {
    val lines = fetchData()
    val map = buildMap(lines)
    val journey = Journey(map)
    val foundTrees = journey.findTrees(stepThreeRightOneDown)
    println(foundTrees.size)

    val foundTreesOneRightOneDown = journey.findTrees(stepOneRightOneDown).size
    val foundTreesThreeRightOneDown = journey.findTrees(stepThreeRightOneDown).size
    val foundTreesFiveRightOneDown = journey.findTrees(stepFiveRightOneDown).size
    val foundTreesSevenRightOneDown = journey.findTrees(stepSevenRightOneDown).size
    val foundTreesOneRightTwoDown = journey.findTrees(stepOneRightTwoDown).size
    val result = listOf(
        foundTreesOneRightOneDown.toBigInteger(),
        foundTreesThreeRightOneDown.toBigInteger(),
        foundTreesFiveRightOneDown.toBigInteger(),
        foundTreesSevenRightOneDown.toBigInteger(),
        foundTreesOneRightTwoDown.toBigInteger(),
    ).reduce { acc, i -> acc * i }

    println("$foundTreesOneRightOneDown * $foundTreesThreeRightOneDown * $foundTreesFiveRightOneDown * $foundTreesSevenRightOneDown * $foundTreesOneRightTwoDown = $result")
}

class MapOfTrees {
    private var coordinates = mutableListOf<TreeCoordinate>()

    fun add(coordinate: TreeCoordinate) {
        coordinates.add(coordinate)
    }

    fun hasTree(coordinate: Coordinate): Boolean {
        val x = getRelativeX(coordinate.x)
        val y = coordinate.y
        val pos = coordinates.find { it.x == x && it.y == y }!!

        return pos.hasTree
    }

    private fun getRelativeX(x: Int): Int {
        return x % (maxX + 1)
    }

    private val maxX: Int
        get() {
            return coordinates.map { it.x }.maxOrNull()!!
        }

    val maxY: Int
        get() {
            return coordinates.map { it.y }.maxOrNull()!!
        }
}

open class Coordinate(val x: Int, val y: Int)

class TreeCoordinate(x: Int, y: Int, var hasTree: Boolean) : Coordinate(x, y)

class Journey(private val map: MapOfTrees) {
    private lateinit var currentPosition: Coordinate

    fun findTrees(stepFn: (Coordinate) -> Coordinate): MutableList<Coordinate> {
        currentPosition = Coordinate(0, 0)
        val foundTrees = mutableListOf<Coordinate>()

        do {
            if (map.hasTree(currentPosition)) {
                foundTrees.add(currentPosition)
            }

            currentPosition = stepFn(currentPosition)
        } while (!hasReachedBottom())

        return foundTrees
    }

    private fun hasReachedBottom(): Boolean {
        return currentPosition.y > map.maxY
    }
}