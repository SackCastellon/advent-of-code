package me.sackcastellon.aoc.y2021

import me.sackcastellon.aoc.Puzzle
import me.sackcastellon.aoc.y2021.Day03.Criteria.LEAST_COMMON
import me.sackcastellon.aoc.y2021.Day03.Criteria.MOST_COMMON
import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.sign

object Day03 : Puzzle<Int> {

    override fun solve1(input: Path): Int {
        val lines = input.readLines()
        val bitCounts = IntArray(lines.first().length) { 0 }

        lines.forEach { line ->
            line.forEachIndexed { i, c ->
                when (c) {
                    '0' -> bitCounts[i] -= 1
                    '1' -> bitCounts[i] += 1
                }
            }
        }

        val gammaRate = bitCounts.fold(0) { acc, i -> (acc shl 1) or (if (i > 0) 1 else 0) }
        val epsilonRate = gammaRate.inv() and ((1 shl bitCounts.size) - 1)

        return gammaRate * epsilonRate
    }

    override fun solve2(input: Path): Int {
        val values: List<IntArray> = input.readLines().map { line -> IntArray(line.length) { line[it].digitToInt() } }

        val o2Rate = findRating(values, MOST_COMMON)
        val co2Rate = findRating(values, LEAST_COMMON)

        return o2Rate * co2Rate
    }

    private fun findRating(values: List<IntArray>, criteria: Criteria): Int {
        generateSequence(0) { it + 1 }.fold(values) { acc, i ->
            if (acc.size == 1)
                return acc.single()
                    .fold(0) { int, bit -> (int shl 1) or bit }

            acc.groupBy { it[i] }.let {
                val size0 = it.getValue(0).size
                val size1 = it.getValue(1).size
                when (size0.compareTo(size1).sign) {
                    -1 -> when (criteria) {
                        MOST_COMMON -> it.getValue(1)
                        LEAST_COMMON -> it.getValue(0)
                    }
                    0 -> when (criteria) {
                        MOST_COMMON -> it.getValue(1)
                        LEAST_COMMON -> it.getValue(0)
                    }
                    1 -> when (criteria) {
                        MOST_COMMON -> it.getValue(0)
                        LEAST_COMMON -> it.getValue(1)
                    }
                    else -> error("Unexpected sign")
                }
            }
        }
        throw IllegalStateException()
    }

    private enum class Criteria {
        MOST_COMMON,
        LEAST_COMMON
    }
}
