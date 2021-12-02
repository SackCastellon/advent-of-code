package me.sackcastellon.aoc.y2020

import me.sackcastellon.aoc.Puzzle
import java.nio.file.Path
import kotlin.io.path.useLines

object Day02 : Puzzle<Int> {
    private val regex = """(\d+)-(\d+) ([a-z]): ([a-z]+)""".toRegex()

    override fun solve1(input: Path): Int =
        input.useLines { lines ->
            lines.count { line ->
                val groups = regex.find(line)!!.groupValues
                val min = groups[1].toInt()
                val max = groups[2].toInt()
                val char = groups[3].single()
                val password = groups[4]
                password.count { it == char } in min..max
            }
        }

    override fun solve2(input: Path): Int =
        input.useLines { lines ->
            lines.count { line ->
                val groups = regex.find(line)!!.groupValues
                val posA = groups[1].toInt() - 1
                val posB = groups[2].toInt() - 1
                val char = groups[3].single()
                val password = groups[4]
                (password[posA] == char) xor (password[posB] == char)
            }
        }
}
