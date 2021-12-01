package me.sackcastellon.aoc.y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.io.path.toPath

internal class Day01Test {
    private val input = javaClass.getResource("input01.txt").toURI().toPath()

    @Test
    fun solvePartOne() = assertEquals(1121, Day01.solve1(input))

    @Test
    fun solvePartTwo() = assertEquals(1065, Day01.solve2(input))
}
