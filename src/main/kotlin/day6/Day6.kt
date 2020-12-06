package day6

import util.fetchData

private fun parseCraftAnswers(lines: List<String>): CraftAnswers {
    val craftAnswers = CraftAnswers()
    var group = GroupAnswers()

    for (line in lines) {
        if (line.isNotBlank()) {
            group.add(PassengerAnswers(line.map { Answer(it.toString()) }))
        } else {
            craftAnswers.add(group)
            group = GroupAnswers()
        }
    }

    craftAnswers.add(group)

    return craftAnswers
}

fun main() {
    val lines = fetchData("src/main/resources/day6/input.txt")
    val craftAnswers = parseCraftAnswers(lines)

    println(craftAnswers.sumGroupAnsweredQuestions())
    println(craftAnswers.sumGroupEveryPassengerAnsweredQuestions())
}

class CraftAnswers {
    val answers: MutableList<GroupAnswers> = mutableListOf()

    fun add(answer: GroupAnswers) {
        answers.add(answer)
    }

    fun sumGroupAnsweredQuestions(): Int {
        return answers.sumBy { it.differentMarkedAnswers() }
    }

    fun sumGroupEveryPassengerAnsweredQuestions(): Int {
        return answers.sumBy { it.everyPassengerMarkedAnswers() }
    }
}

class GroupAnswers {
    private val answers: MutableList<PassengerAnswers> = mutableListOf()

    fun add(answer: PassengerAnswers) {
        answers.add(answer)
    }

    fun differentMarkedAnswers(): Int {
        return answers.flatMap { it.answers }.distinctBy { it.question }.count()
    }

    fun everyPassengerMarkedAnswers(): Int {
        return answers.map { it.answers }.reduce { every, answers -> every.intersect(answers).toList() }.size
    }
}

data class PassengerAnswers(val answers: List<Answer>)

data class Answer(val question: String)
