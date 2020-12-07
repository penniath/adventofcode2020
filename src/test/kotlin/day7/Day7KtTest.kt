package day7

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class Day7KtTest {

    @Test
    fun countBagsCanContainShinyGold() {
        val bags = createBags()

        val result = countBagsCanContainShinyGold(bags)

        assertEquals(4, result.size)
    }

    private fun createBags(): List<Bag> {
        val bags = mutableListOf<Bag>()
        bags.add(Bag("light red", listOf("white bags", "muted yellow")))
        bags.add(Bag("dark orange", listOf("bright white", "muted yellow")))
        bags.add(Bag("bright white", listOf("shiny gold")))
        bags.add(Bag("muted yellow", listOf("shiny gold", "faded blue")))
        bags.add(Bag("shiny gold", listOf("dark olive", "vibrant plum")))
        bags.add(Bag("dark olive", listOf("faded blue", "dotted black")))
        bags.add(Bag("vibrant plum", listOf("faded blue", "dotted black")))
        bags.add(Bag("faded blue", listOf()))
        bags.add(Bag("dotted black", listOf()))

        return bags
    }
}