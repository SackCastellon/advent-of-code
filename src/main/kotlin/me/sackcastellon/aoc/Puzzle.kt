package me.sackcastellon.aoc

import java.nio.file.Path

interface Puzzle<R> {
    fun solve1(input: Path): R
    fun solve2(input: Path): R
}
