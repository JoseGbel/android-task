package com.example.degree53androidtest.utils

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GitHubRoundifierTest {

    @Test
    fun should_round_three_digits_number() {
        val int = 305
        val expected = "305"

        val actual = int.kRoundify()

        assertEquals(expected,actual)
    }

    @Test
    fun should_round_three_digits_number_successfully_on_edge_case() {
        val int = 999
        val expected = "999"

        val actual = int.kRoundify()

        assertEquals(expected,actual)
    }

    @Test
    fun should_round_four_digits_number_successfully_on_edge_case() {
        val int = 1000
        val expected = "1.0k"

        val actual = int.kRoundify()

        assertEquals(expected,actual)
    }

    @Test
    fun should_round_four_digits_number() {
        val int = 1295
        val expected = "1.2k"

        val actual = int.kRoundify()

        assertEquals(expected,actual)
    }

    @Test
    fun should_round_five_digits_number() {
        val int = 10000
        val expected = "10.0k"

        val actual = int.kRoundify()

        assertEquals(expected,actual)
    }

    @Test
    fun should_round_six_digits_number() {
        val int = 123456
        val expected = "123.4k"

        val actual = int.kRoundify()

        assertEquals(expected,actual)
    }
}