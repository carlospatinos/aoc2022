import java.io.File
import kotlin.math.abs

fun main() {
    val fileToExplore = "./tmp.txt"

    val MAX_LENGTH_LINE_COL = 20 //5-99
    val MAX_NUMBER_OF_LINE_ROW = 20 //5-99
    val T = 9
    val H = 9

    var mapOfTrees = Array(MAX_NUMBER_OF_LINE_ROW) { IntArray(MAX_LENGTH_LINE_COL) { -1 } }
    var visitedRowColumnPair = mutableSetOf<String>() // String is 0-0, 0-1, etc row-column

    val initialRow = MAX_NUMBER_OF_LINE_ROW/2
    val initialColumn = MAX_LENGTH_LINE_COL/2

    mapOfTrees[initialRow][initialColumn] = H


    var headRow = initialRow
    var headColumn = initialColumn

    var tailRow = initialRow
    var tailColumn = initialColumn

    visitedRowColumnPair.add("$tailRow-$tailColumn")

    File(fileToExplore).forEachLine {
        // moving head
        var move = it.split(" ")
        val direction = move[0].get(0)
        val amountToMove = move[1].toInt()

        for (i in 0 until amountToMove) {
            mapOfTrees[headRow][headColumn] = -1
            when (direction) {
                'R' -> headColumn += 1
                'D' -> headRow += 1
                'L' -> headColumn -= 1
                'U' -> headRow -= 1
            }
            mapOfTrees[headRow][headColumn] = H
            println("Head at position $headRow , $headColumn")

            // check where head is and move tails
            mapOfTrees[tailRow][tailColumn] = -1
            if (tailRow - headRow >= 2) {
                // move up subtract
                tailRow -= 1
            } else if (headRow - tailRow >= 2) {
                // move down add
                tailRow += 1
            } else if (tailColumn - headColumn >= 2) {
                // move left subtract
                tailColumn -= 1
            } else if (headColumn - tailColumn >= 2) {
                // move right add
                tailColumn += 1
            }

            mapOfTrees[tailRow][tailColumn] = T
            visitedRowColumnPair.add("$tailRow-$tailColumn")
            println("Tail at position $tailRow , $tailColumn")

        }
    }
    println("Visited positions $visitedRowColumnPair")
}


