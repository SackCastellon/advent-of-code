package me.sackcastellon.aoc.y2021

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.abs

object Day07 : Puzzle<Int> {

    override fun solve1(input: Path): Int {
        val map = input.readLines()
            .single()
            .split(",")
            .map { it.toInt() }
            .sorted()
            .let {
                buildMap {
                    (it.first()..it.last()).associateWithTo(this) { 0 }
                    it.groupingBy { it }.eachCountTo(this)
                }
            }

        val cumulative = map.values.runningReduce(Int::plus)
        val threshold = (cumulative.last() + 1) / 2
        val median = cumulative.indexOfFirst { it > threshold } + map.keys.first()
        return map.filterValues { it > 0 }.map { (pos, count) -> abs(pos - median) * count }.sum()
    }

    override fun solve2(input: Path): Int = TODO()
}
