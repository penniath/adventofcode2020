package day7

import util.fetchData

private fun parseBags(lines: List<String>): List<Bag> {
    return lines.map(::parseBag)
}

private fun parseBag(line: String): Bag {
    val pattern = "^(.+) bags contain (.+)\\.$".toRegex()
    val groups = pattern.matchEntire(line)!!.groupValues
    val id = groups[1]
    val children = if (groups[2] == "no other bags") {
        listOf()
    } else {
        groups[2].split(",")
            .map { "(\\d+) (.+) bag".toRegex().find(it)!!.groupValues[2] }
    }

    return Bag(id, children)
}

fun countBagsCanContainShinyGold(bags: List<Bag>): List<Bag> {
    val bagsById = bags.map { it.id to it }.toMap()

    return bags.filterIndexed { index, bag ->
        println(index)
        bag.canContainBag("shiny gold", bagsById)
    }
}

fun main() {
    val lines = fetchData("src/main/resources/day7/input.txt")
    val bags = parseBags(lines)
    val bagsThanCanContainShinyGold = countBagsCanContainShinyGold(bags)

    println(bagsThanCanContainShinyGold.size)
}

data class Bag(val id: String, val children: List<String>) {

    fun canContainBag(id: String, bags: Map<String, Bag>): Boolean {
        return children.any {
            it == id || children.map { bagId -> bags[bagId] }
                .any { bag -> bag?.canContainBag(id, bags) ?: false }
        }
    }
}