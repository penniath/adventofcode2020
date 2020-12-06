package day4

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import parsePassport
import validateBirthYear
import validateEyeColor
import validateHairColor
import validateHeight
import validatePassportId

internal class Day4KtTest {

    @Test
    fun passport_is_valid_when_all_params_are_filled() {
        val line = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
        val passport = parsePassport(line)

        assertTrue(passport.isValid())
    }

    @Test
    fun passport_is_not_valid_when_height_is_missing() {
        val line = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
        val passport = parsePassport(line)

        assertFalse(passport.isValid())
    }

    @Test
    fun passport_is_valid_when_only_country_id_is_missing() {
        val line = "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"
        val passport = parsePassport(line)

        assertTrue(passport.isValid())
    }

    @Test
    fun passport_is_not_valid_when_country_id_and_a_second_attribute_is_missing() {
        val line = "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"
        val passport = parsePassport(line)

        assertFalse(passport.isValid())
    }

    @Test
    fun birth_year_is_not_valid_if_null() {
        val birthYear = null
        val valid = validateBirthYear(birthYear)

        assertFalse(valid)
    }

    @Test
    fun birth_year_is_valid_if_is_in_range() {
        val birthYear = "2002"
        val valid = validateBirthYear(birthYear)

        assertTrue(valid)
    }

    @Test
    fun birth_year_is_not_valid_if_is_not_in_range() {
        val birthYear = "2003"
        val valid = validateBirthYear(birthYear)

        assertFalse(valid)
    }

    @Test
    fun height_is_not_valid_if_null() {
        val height = null
        val valid = validateHeight(height)

        assertFalse(valid)
    }

    @Test
    fun height_is_valid_if_length_is_in_range() {
        val heightInCms = "190cm"
        val heightInInches = "60in"
        val validInCms = validateHeight(heightInCms)
        val validInInches = validateHeight(heightInInches)

        assertTrue(validInCms)
        assertTrue(validInInches)
    }

    @Test
    fun height_is_not_valid_if_is_not_in_range() {
        val heightInCms = "22cm"
        val heightInInches = "190in"
        val validInCms = validateHeight(heightInCms)
        val validInInches = validateHeight(heightInInches)

        assertFalse(validInCms)
        assertFalse(validInInches)
    }

    @Test
    fun height_is_not_valid_if_has_not_magnitude() {
        val height = "190"
        val valid = validateHeight(height)

        assertFalse(valid)
    }

    @Test
    fun hair_color_is_not_valid_if_null() {
        val hairColor = null
        val valid = validateHairColor(hairColor)

        assertFalse(valid)
    }

    @Test
    fun hair_color_is_valid_if_in_range() {
        val hairColor = "#123abc"
        val valid = validateHairColor(hairColor)

        assertTrue(valid)
    }

    @Test
    fun hair_color_is_not_valid_if_character_is_not_in_range() {
        val hairColor = "123abz"
        val valid = validateHairColor(hairColor)

        assertFalse(valid)
    }

    @Test
    fun hair_color_is_not_valid_if_missing_hash() {
        val hairColor = "123abc"
        val valid = validateHairColor(hairColor)

        assertFalse(valid)
    }

    @Test
    fun eye_color_is_not_valid_if_null() {
        val eyeColor = null
        val valid = validateEyeColor(eyeColor)

        assertFalse(valid)
    }

    @Test
    fun eye_color_is_valid_if_in_range() {
        val eyeColor = "brn"
        val valid = validateEyeColor(eyeColor)

        assertTrue(valid)
    }

    @Test
    fun eye_color_is_not_valid_if_is_not_in_range() {
        val eyeColor = "wat"
        val valid = validateEyeColor(eyeColor)

        assertFalse(valid)
    }

    @Test
    fun passport_id_is_not_valid_if_null() {
        val passportId = null
        val valid = validatePassportId(passportId)

        assertFalse(valid)
    }

    @Test
    fun passport_id_is_valid_if_in_range() {
        val passportId = "000000001"
        val valid = validatePassportId(passportId)

        assertTrue(valid)
    }

    @Test
    fun passport_id_is_not_valid_if_is_not_in_range() {
        val passportId = "0123456789"
        val valid = validatePassportId(passportId)

        assertFalse(valid)
    }
}