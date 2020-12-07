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

    @Test
    fun countBagsContainingShinyGold() {
        val bags = createBagsPart2()

        val result = countBagsContainingShinyGold(bags)

        assertEquals(126, result)
    }

    private fun createBags(): List<Bag> {
        val bags = mutableListOf<Bag>()
        bags.add(Bag("light red", listOf(Content("bright white", 1), Content("muted yellow", 2))))
        bags.add(Bag("dark orange", listOf(Content("bright white", 3), Content("muted yellow", 4))))
        bags.add(Bag("bright white", listOf(Content("shiny gold", 1))))
        bags.add(Bag("muted yellow", listOf(Content("shiny gold", 2), Content("faded blue", 9))))
        bags.add(Bag("shiny gold", listOf(Content("dark olive", 1), Content("vibrant plum", 2))))
        bags.add(Bag("dark olive", listOf(Content("faded blue", 3), Content("dotted black", 4))))
        bags.add(Bag("vibrant plum", listOf(Content("faded blue", 5), Content("dotted black", 6))))
        bags.add(Bag("faded blue", listOf()))
        bags.add(Bag("dotted black", listOf()))

        return bags
    }

    private fun createBagsPart2(): List<Bag> {
        val bags = mutableListOf<Bag>()
        bags.add(Bag("shiny gold", listOf(Content("dark red", 2))))
        bags.add(Bag("dark red", listOf(Content("dark orange", 2))))
        bags.add(Bag("dark orange", listOf(Content("dark yellow", 2))))
        bags.add(Bag("dark yellow", listOf(Content("dark green", 2))))
        bags.add(Bag("dark green", listOf(Content("dark blue", 2))))
        bags.add(Bag("dark blue", listOf(Content("dark violet", 2))))
        bags.add(Bag("dark violet", listOf()))

        return bags
    }
}