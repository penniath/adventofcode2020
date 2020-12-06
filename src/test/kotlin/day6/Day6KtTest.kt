package day6

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class Day6KtTest {

    @Test
    fun sumGroupAnsweredQuestions() {
        val craftAnswers = createCraftAnswers()

        assertEquals(3, craftAnswers.answers[0].differentMarkedAnswers())
        assertEquals(3, craftAnswers.answers[1].differentMarkedAnswers())
        assertEquals(3, craftAnswers.answers[2].differentMarkedAnswers())
        assertEquals(1, craftAnswers.answers[3].differentMarkedAnswers())
        assertEquals(1, craftAnswers.answers[4].differentMarkedAnswers())
        assertEquals(11, craftAnswers.sumGroupAnsweredQuestions())
    }

    @Test
    fun everyGroupAnsweredQuestions() {
        val craftAnswers = createCraftAnswers()

        assertEquals(3, craftAnswers.answers[0].everyPassengerMarkedAnswers())
        assertEquals(0, craftAnswers.answers[1].everyPassengerMarkedAnswers())
        assertEquals(1, craftAnswers.answers[2].everyPassengerMarkedAnswers())
        assertEquals(1, craftAnswers.answers[3].everyPassengerMarkedAnswers())
        assertEquals(1, craftAnswers.answers[4].everyPassengerMarkedAnswers())
        assertEquals(6, craftAnswers.sumGroupEveryPassengerAnsweredQuestions())
    }

    private fun createCraftAnswers(): CraftAnswers {
        val craftAnswers = CraftAnswers()
        val group1 = GroupAnswers()
        group1.add(PassengerAnswers(listOf(Answer("a"), Answer("b"), Answer("c"))))
        craftAnswers.add(group1)

        val group2 = GroupAnswers()
        group2.add(PassengerAnswers(listOf(Answer("a"))))
        group2.add(PassengerAnswers(listOf(Answer("b"))))
        group2.add(PassengerAnswers(listOf(Answer("c"))))
        craftAnswers.add(group2)

        val group3 = GroupAnswers()
        group3.add(PassengerAnswers(listOf(Answer("a"), Answer("b"))))
        group3.add(PassengerAnswers(listOf(Answer("a"), Answer("c"))))
        craftAnswers.add(group3)

        val group4 = GroupAnswers()
        group4.add(PassengerAnswers(listOf(Answer("a"))))
        group4.add(PassengerAnswers(listOf(Answer("a"))))
        group4.add(PassengerAnswers(listOf(Answer("a"))))
        group4.add(PassengerAnswers(listOf(Answer("a"))))
        craftAnswers.add(group4)

        val group5 = GroupAnswers()
        group5.add(PassengerAnswers(listOf(Answer("b"))))
        craftAnswers.add(group5)

        return craftAnswers
    }
}