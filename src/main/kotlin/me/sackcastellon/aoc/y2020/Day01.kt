package me.sackcastellon.aoc.y2020

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.useLines

object Day01 : Puzzle<Int> {
    override fun solve1(input: Path): Int {
        val set = input.useLines { it.map(String::toInt).toSortedSet() }

        val a = set.first { (2020 - it) in set }
        val b = 2020 - a

        return a * b
    }

    override fun solve2(input: Path): Int {
        val set = input.useLines { it.map(String::toInt).toSortedSet() }

        set.forEach { a ->
            val subSet = set.subSet(a, 2020 - a)
            subSet.forEach { b ->
                val c = 2020 - a - b
                if (c in subSet)
                    return a * b * c
            }
        }

        throw IllegalStateException()
    }
}
