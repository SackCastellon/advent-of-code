package me.sackcastellon.aoc.y2020

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.useLines

object Day03 : Puzzle<Long> {
    override fun solve1(input: Path): Long =
        input.useLines { lines ->
            lines.foldIndexed(0) { y, count, line ->
                if (line[(y * 3) % line.length] == '#') count + 1 else count
            }
        }

    override fun solve2(input: Path): Long {
        class Toboggan {
            private val slopes = listOf(
                1 to 1,
                3 to 1,
                5 to 1,
                7 to 1,
                1 to 2
            )
            private val treeCount = slopes.associateWithTo(mutableMapOf()) { 0L }
            val product: Long get() = treeCount.values.reduce(Long::times)

            fun check(y: Int, line: String) {
                treeCount.replaceAll { (r, d), count ->
                    val i = ((y / d) * r) % line.length
                    if (((y % d) == 0) && (line[i] == '#')) count + 1 else count
                }
            }
        }
        return input.useLines { Toboggan().apply { it.forEachIndexed(::check) }.product }
    }
}
