package me.sackcastellon.aoc.y2021

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.readText

object Day04 : Puzzle<Int> {

    override fun solve1(input: Path): Int {
        val split = input.readText().split("\n\n")

        val boards = split.drop(1)
            .map { it.split(" ", "\n").mapNotNull(String::toIntOrNull).associateWithTo(linkedMapOf()) { false } }

        split.first().splitToSequence(",").map(String::toInt).forEach { n ->
            for (b in boards) {
                b.computeIfPresent(n) { _, _ -> true } ?: continue
                val values = b.values.toList()
                if (values.windowed(5, 5).any { row -> row.all { it } } ||
                    (0 until 5).any { col -> (0 until 5).all { row -> values[col + (row * 5)] } }) {
                    return b.filterValues { !it }.keys.sum() * n
                }
            }
        }
        throw IllegalStateException()
    }

    override fun solve2(input: Path): Int {
        val split = input.readText().split("\n\n")

        val boards = split.drop(1)
            .map { it.split(" ", "\n").mapNotNull(String::toIntOrNull).associateWithTo(linkedMapOf()) { false } }
            .toMutableList()

        split.first().splitToSequence(",").map(String::toInt).forEach { n ->
            val iter = boards.listIterator()
            while (iter.hasNext()) {
                val b = iter.next()
                b.computeIfPresent(n) { _, _ -> true } ?: continue
                val values = b.values.toList()
                if (values.windowed(5, 5).any { row -> row.all { it } } ||
                    (0 until 5).any { col -> (0 until 5).all { row -> values[col + (row * 5)] } }) {
                    if (boards.size == 1) return b.filterValues { !it }.keys.sum() * n
                    iter.remove()
                }

            }
        }
        throw IllegalStateException()
    }
}
