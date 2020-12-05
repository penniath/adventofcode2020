package day4

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import parsePassport

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
}