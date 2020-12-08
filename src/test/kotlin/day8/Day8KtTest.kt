package day8

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class Day8KtTest {

    @Test
    fun execute() {
        val execution = createExecution()

        execution.execute()

        assertEquals(5, execution.value)
    }

    private fun createExecution(): Execution {
        val instructions = mutableListOf<Instruction>()
        instructions.add(NoOperationInstruction())
        instructions.add(AddingInstruction(1))
        instructions.add(JumpingInstruction(4))
        instructions.add(AddingInstruction(3))
        instructions.add(JumpingInstruction(-3))
        instructions.add(AddingInstruction(-99))
        instructions.add(AddingInstruction(1))
        instructions.add(JumpingInstruction(-4))
        instructions.add(AddingInstruction(6))

        return Execution(instructions)
    }
}