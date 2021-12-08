package me.sackcastellon.aoc.y2021

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.readLines

object Day06 : Puzzle<Long> {

    override fun solve1(input: Path): Long = solve(input, 80)

    override fun solve2(input: Path): Long = solve(input, 256)

    private fun solve(input: Path, days: Int): Long {
        val map = input
            .readLines()
            .single()
            .split(",")
            .map { it.toInt() }
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, count) -> count.toLong() }

        val finalMap = (0 until days).fold(map) { acc, _ ->
            buildMap {
                acc.forEach { (time, amount) ->
                    if (time == 0) {
                        add(6, amount)
                        add(8, amount)
                    } else {
                        add(time - 1, amount)
                    }
                }
            }
        }

        return finalMap.values.sum()
    }

    private fun MutableMap<Int, Long>.add(time: Int, amount: Long) {
        compute(time) { _, old -> (old ?: 0) + amount }
    }
}
