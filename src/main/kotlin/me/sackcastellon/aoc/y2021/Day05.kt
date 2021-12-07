package me.sackcastellon.aoc.y2021

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.max
import kotlin.math.min

object Day05 : Puzzle<Int> {

    override fun solve1(input: Path): Int {
        val map = hashMapOf<Pair<Int, Int>, Int>()

        input.readLines()
            .flatMap { line ->
                val (x1, y1, x2, y2) = line.split(" -> ", limit = 2)
                    .flatMap { it.split(",", limit = 2).map(String::toInt) }
                when {
                    x1 == x2 -> (min(y1, y2)..max(y1, y2)).map { x1 to it }
                    y1 == y2 -> (min(x1, x2)..max(x1, x2)).map { it to y1 }
                    else -> emptyList()
                }
            }
            .forEach { map.compute(it) { _, old -> (old ?: 0) + 1 } }

        return map.count { (_, count) -> count >= 2 }
    }

    override fun solve2(input: Path): Int {
        val map = hashMapOf<Pair<Int, Int>, Int>()

        input.readLines()
            .flatMap { line ->
                val (x1, y1, x2, y2) = line.split(" -> ", limit = 2)
                    .flatMap { it.split(",", limit = 2).map(String::toInt) }
                when {
                    x1 == x2 -> (min(y1, y2)..max(y1, y2)).map { x1 to it }
                    y1 == y2 -> (min(x1, x2)..max(x1, x2)).map { it to y1 }
                    else -> {
                        val xRange = if (x1 < x2) x1..x2 else x1 downTo x2
                        val yRange = if (y1 < y2) y1..y2 else y1 downTo y2
                        xRange zip yRange
                    }
                }
            }
            .forEach { map.compute(it) { _, old -> (old ?: 0) + 1 } }

        return map.count { (_, count) -> count >= 2 }
    }
}
