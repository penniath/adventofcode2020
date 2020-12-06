package day2

import util.fetchData
import java.lang.Integer.parseInt

private fun parseLogs(lines: List<String>, policyFn: (MatchResult) -> Policy): List<Log> {
    return lines.map { parseLog(it, policyFn) }
}

private fun parseLog(line: String, policyFn: (MatchResult) -> Policy): Log {
    val pattern = "^(\\d+)-(\\d+) ([^:]+): (.+)$".toRegex()
    val match = pattern.matchEntire(line)
    val groups = match?.groupValues!!

    return Log(policyFn.invoke(match), groups[4])
}

private var createCountPolicy = { match: MatchResult ->
    val groups = match.groupValues

    CountPolicy(groups[3], parseInt(groups[1]), parseInt(groups[2]))
}

private var createPositionPolicy = { match: MatchResult ->
    val groups = match.groupValues

    PositionPolicy(groups[3], parseInt(groups[1]), parseInt(groups[2]))
}

fun main() {
    val lines = fetchData("src/main/resources/day2/input.txt")
    val countPolicyLogs = parseLogs(lines, createCountPolicy)
    println(countPolicyLogs.count { it.policyMatches })

    val positionPolicyLogs = parseLogs(lines, createPositionPolicy)
    println(positionPolicyLogs.count { it.policyMatches })
}

interface Policy {
    fun matches(password: String): Boolean
}

data class CountPolicy(val char: String, val min: Int, val max: Int) : Policy {
    override fun matches(password: String): Boolean {
        val count = password.split("").count { it == char }

        return count in min..max
    }
}

data class PositionPolicy(val char: String, val firstPosition: Int, val secondPosition: Int) : Policy {
    override fun matches(password: String): Boolean {
        val letters = password.split("")
        val matchesFirst = letters[firstPosition] == char
        val matchesSecond = letters[secondPosition] == char

        return matchesFirst.xor(matchesSecond)
    }
}

data class Log(val policy: Policy, val password: String) {
    val policyMatches: Boolean
        get() {
            return policy.matches(password)
        }
}
