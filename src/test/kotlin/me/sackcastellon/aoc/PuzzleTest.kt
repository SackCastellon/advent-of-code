package me.sackcastellon.aoc

import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.io.path.toPath
import kotlin.test.assertEquals

internal class PuzzleTest {

    private val puzzles = listOf(
        // Year 2020
        Solution(me.sackcastellon.aoc.y2020.Day01, result1 = 471019, result2 = 103927824),
        Solution(me.sackcastellon.aoc.y2020.Day02, result1 = 655, result2 = 673),
        Solution(me.sackcastellon.aoc.y2020.Day03, result1 = 289, result2 = 5522401584),
        Solution(me.sackcastellon.aoc.y2020.Day04, result1 = 170, result2 = 103),
        // Year 2021
        Solution(me.sackcastellon.aoc.y2021.Day01, result1 = 1121, result2 = 1065),
        Solution(me.sackcastellon.aoc.y2021.Day02, result1 = 1524750, result2 = 1592426537),
        Solution(me.sackcastellon.aoc.y2021.Day03, result1 = 3901196, result2 = 4412188),
        Solution(me.sackcastellon.aoc.y2021.Day04, result1 = 82440, result2 = 20774),
        Solution(me.sackcastellon.aoc.y2021.Day05, result1 = 5632, result2 = 22213),
    )

    @TestFactory
    internal fun puzzles(): List<DynamicNode> {
        return puzzles.map { puzzle ->
            dynamicContainer(
                "Year ${puzzle.year}", listOf(
                    dynamicContainer(
                        "Day ${puzzle.day}", listOf(
                            dynamicTest("Day ${puzzle.day} Part 1", puzzle::testPartOne),
                            dynamicTest("Day ${puzzle.day} Part 2", puzzle::testPartTwo)
                        )
                    )
                )
            )
        }
    }

    internal data class Solution<R>(
        private val puzzle: Puzzle<R>, private val result1: R, private val result2: R
    ) {
        val day = puzzle.javaClass.simpleName.removePrefix("Day")
        val year = puzzle.javaClass.packageName.substringAfterLast('.').removePrefix("y")

        private val input by lazy {
            val pkg = puzzle.javaClass.packageName.replace('.', '/')
            ClassLoader.getSystemClassLoader().getResource("$pkg/input$day.txt")!!.toURI().toPath()
        }

        fun testPartOne() = assertEquals(result1, puzzle.solve1(input))
        fun testPartTwo() = assertEquals(result2, puzzle.solve2(input))
    }
}
