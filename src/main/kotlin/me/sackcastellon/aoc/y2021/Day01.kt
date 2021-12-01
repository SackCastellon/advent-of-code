package me.sackcastellon.aoc.y2021

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.useLines

object Day01 : Puzzle<Int> {
    override fun solve1(input: Path): Int = input.useLines { lines ->
        lines.map { it.toInt() }
            .zipWithNext()
            .count { (a, b) -> b > a }
    }

    override fun solve2(input: Path): Int = input.useLines { lines ->
        lines.map { it.toInt() }
            .windowed(size = 3) { it.sum() }
            .zipWithNext()
            .count { (a, b) -> b > a }
    }
}
