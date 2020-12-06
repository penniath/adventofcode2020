package day4

import util.fetchData
import java.lang.Integer.parseInt

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
    val lines = fetchData("src/main/resources/day4/input.txt")
    val passports = parsePassports(lines)
    val validPassports = passports.count { it.isValid() }

    println(validPassports)

    val totallyValidPassports = passports.count { it.isTotallyValid() }

    println(totallyValidPassports)
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

    fun isTotallyValid(): Boolean {
        return validateBirthYear(birthYear) &&
                validateIssueYear(issueYear) &&
                validateExpirationYear(expirationYear) &&
                validateHeight(height) &&
                validateHairColor(hairColor) &&
                validateEyeColor(eyeColor) &&
                validatePassportId(passportId)
    }
}

fun validateBirthYear(birthYear: String?): Boolean {
    return validateYear(birthYear, 1920..2002)
}

fun validateIssueYear(issueYear: String?): Boolean {
    return validateYear(issueYear, 2010..2020)
}

fun validateExpirationYear(expirationYear: String?): Boolean {
    return validateYear(expirationYear, 2020..2030)
}

fun validateHeight(height: String?): Boolean {
    if (height.isNullOrBlank()) {
        return false
    }

    val heightData = "(\\d+)(cm|in)".toRegex().matchEntire(height)?.groupValues
    val heightNumber = if (heightData?.get(1) != null) parseInt(heightData[1]) else null
    val heightMagnitude = heightData?.get(2)

    return heightNumber != null && heightMagnitude != null && ((heightMagnitude == "cm" && heightNumber in 150..193) || (heightMagnitude == "in" && heightNumber in 59..76))
}

fun validateHairColor(hairColor: String?): Boolean {
    return !hairColor.isNullOrBlank() && "^#[0-9a-f]{6}$".toRegex().matches(hairColor)
}

fun validateEyeColor(eyeColor: String?): Boolean {
    val validColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    return !eyeColor.isNullOrBlank() && validColors.contains(eyeColor)
}

fun validatePassportId(passportId: String?): Boolean {
    return !passportId.isNullOrBlank() && "^[0-9]{9}$".toRegex().matches(passportId)
}

private fun validateYear(year: String?, range: IntRange): Boolean {
    return year?.isNotEmpty() ?: false && year?.length ?: 0 == 4 && parseInt(year) in range
}