package day8

import util.fetchData
import java.lang.Integer.parseInt

private fun parseExecution(lines: List<String>): Execution {
    return Execution(lines.map(::parseInstruction))
}

private fun parseInstruction(line: String): Instruction {
    val pattern = "^(\\w{3}) ([\\-+][0-9]+)$".toRegex()
    val groups = pattern.matchEntire(line)!!.groupValues
    val op = groups[1]
    val value = parseInt(groups[2])

    if (op == "acc") {
        return AddingInstruction(value)
    } else if (op == "jmp") {
        return JumpingInstruction(value)
    }

    return NoOperationInstruction()
}

fun main() {
    val lines = fetchData("src/main/resources/day8/input.txt")
    val execution = parseExecution(lines)
    execution.execute()

    println(execution.value)
}

data class Execution(val instructions: List<Instruction>) {
    var value = 0
    var i = 0
    var executedInstructions = mutableListOf<Instruction>()

    fun execute() {
        var instruction = instructions[i]
        while (!executedInstructions.contains(instruction)) {
            when (instruction) {
                is AddingInstruction -> {
                    instruction.execute { value += it }
                    i++
                }
                is JumpingInstruction -> {
                    instruction.execute { i += it }
                }
                is NoOperationInstruction -> {
                    i++
                }
            }
            executedInstructions.add(instruction)

            instruction = instructions[i]
        }
    }
}

interface Instruction {
    fun execute(executeFn: (Int) -> Unit)
}

class AddingInstruction(private val value: Int) : Instruction {

    override fun execute(executeFn: (Int) -> Unit) {
        executeFn(value)
    }
}

class JumpingInstruction(private val value: Int) : Instruction {

    override fun execute(executeFn: (Int) -> Unit) {
        executeFn(value)
    }
}

class NoOperationInstruction : Instruction {

    override fun execute(executeFn: (Int) -> Unit) {
        return
    }
}