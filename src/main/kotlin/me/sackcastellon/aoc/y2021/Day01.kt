package me.sackcastellon.aoc.y2021

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.readLines

object Day01 : Puzzle<Int> {
    override fun solve1(input: Path): Int = input.readLines()
        .map(String::toInt)
        .zipWithNext()
        .count { (a, b) -> b > a }

    override fun solve2(input: Path): Int = input.readLines()
        .map(String::toInt)
        .windowed(size = 4)
        .count { (a, _, _, b) -> b > a }
}
