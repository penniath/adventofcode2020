import java.io.File

private fun fetchData(): List<String> {
    val file = File("src/main/resources/day4/input.txt")

    return file.readLines()
}

private fun parsePassports(lines: List<String>): List<Passport> {
    val passportLines = mutableListOf<String>()
    var currentPassportLines = mutableListOf<String>()

    for (line in lines) {
        if (line.isNotBlank()) {
            currentPassportLines.add(line)
        } else {
            passportLines.add(currentPassportLines.joinToString(" "))
            currentPassportLines = mutableListOf()
        }
    }

    if (currentPassportLines.isNotEmpty()) {
        passportLines.add(currentPassportLines.joinToString(" "))
    }

    return passportLines.map(::parsePassport)
}

fun parsePassport(line: String): Passport {
    val birthYear = readAttribute(line, "byr")
    val issueYear = readAttribute(line, "iyr")
    val expirationYear = readAttribute(line, "eyr")
    val height = readAttribute(line, "hgt")
    val hairColor = readAttribute(line, "hcl")
    val eyeColor = readAttribute(line, "ecl")
    val passportId = readAttribute(line, "pid")
    val countryId = readAttribute(line, "cid")

    return Passport(
        line,
        birthYear,
        issueYear,
        expirationYear,
        height,
        hairColor,
        eyeColor,
        passportId,
        countryId,
    )
}

private fun readAttribute(line: String, attr: String): String? {
    val match = "$attr:([0-9a-zA-Z#]+)".toRegex().find(line)

    return match?.groupValues?.get(1)
}

fun main() {
    val lines = fetchData()
    val passports = parsePassports(lines)
    val validPassports = passports.count { it.isValid() }

    println(validPassports)
}

data class Passport(
    val original: String,
    val birthYear: String?,
    val issueYear: String?,
    val expirationYear: String?,
    val height: String?,
    val hairColor: String?,
    val eyeColor: String?,
    val passportId: String?,
    val countryId: String?
) {
    fun isValid(): Boolean {
        return (birthYear?.isNotBlank() ?: false) &&
                (issueYear?.isNotBlank() ?: false) &&
                (expirationYear?.isNotBlank() ?: false) &&
                (height?.isNotBlank() ?: false) &&
                (hairColor?.isNotBlank() ?: false) &&
                (eyeColor?.isNotBlank() ?: false) &&
                (passportId?.isNotBlank() ?: false)
    }
}