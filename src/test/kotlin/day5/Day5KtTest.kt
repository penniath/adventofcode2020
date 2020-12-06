package day5


import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class Day5KtTest {

    @Test
    fun parseBoardingPass() {
        var result = parseBoardingPass("BFFFBBFRRR")
        assertEquals(70, result.row)
        assertEquals(7, result.column)
        assertEquals(567, result.id)

        result = parseBoardingPass("FFFBBBFRRR")
        assertEquals(14, result.row)
        assertEquals(7, result.column)
        assertEquals(119, result.id)

        result = parseBoardingPass("BBFFBBFRLL")
        assertEquals(102, result.row)
        assertEquals(4, result.column)
        assertEquals(820, result.id)
    }
}