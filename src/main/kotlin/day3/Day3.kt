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

fun main() {
    val lines = fetchData()
    val map = buildMap(lines)
    val journey = Journey(map) { Coordinate(it.x + 3, it.y + 1) }
    journey.findTrees()

    print(journey.foundTrees.size)
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

class Journey(private val map: MapOfTrees, val stepFn: (Coordinate) -> Coordinate) {
    private var currentPosition = Coordinate(0, 0)
    val foundTrees = mutableListOf<Coordinate>()

    fun findTrees() {
        do {
            if (map.hasTree(currentPosition)) {
                foundTrees.add(currentPosition)
            }

            currentPosition = stepFn(currentPosition)
        } while (!hasReachedBottom())
    }

    private fun hasReachedBottom(): Boolean {
        return currentPosition.y > map.maxY
    }
}