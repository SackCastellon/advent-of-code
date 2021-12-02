package me.sackcastellon.aoc

import java.nio.file.Path

interface Puzzle<out R> {
    fun solve1(input: Path): R
    fun solve2(input: Path): R
}
