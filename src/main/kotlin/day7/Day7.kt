package day7

import util.fetchData
import java.lang.Integer.parseInt

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
            .map {
                val content = "(\\d+) (.+) bag".toRegex().find(it)!!.groupValues

                Content(content[2], parseInt(content[1]))
            }
    }

    return Bag(id, children)
}

fun countBagsCanContainShinyGold(bags: List<Bag>): List<Bag> {
    val bagsById = bags.map { it.id to it }.toMap()

    return bags.filter { it.canContainBag("shiny gold", bagsById) }
}

fun countBagsContainingShinyGold(bags: List<Bag>): Int {
    val bagsById = bags.map { it.id to it }.toMap()
    val shinyGold = bags.find { it.id == "shiny gold" }!!

    return shinyGold.sumBags(bagsById)
}

fun main() {
    val lines = fetchData("src/main/resources/day7/input.txt")
    val bags = parseBags(lines)
    val bagsThanCanContainShinyGold = countBagsCanContainShinyGold(bags)

    println(bagsThanCanContainShinyGold.size)

    val bagCount = countBagsContainingShinyGold(bags)
    println(bagCount)
}

data class Bag(val id: String, val children: List<Content>) {

    fun canContainBag(id: String, bags: Map<String, Bag>): Boolean {
        return children.any {
            it.id == id || children.map { bag -> bags[bag.id] }
                .any { bag -> bag?.canContainBag(id, bags) ?: false }
        }
    }

    fun sumBags(bags: Map<String, Bag>): Int {
        return children.map {
            val bag = bags[it.id]!!

            it.amount * (1 + bag.sumBags(bags))
        }.sum()
    }
}

data class Content(val id: String, val amount: Int)