import java.io.File
import java.util.*

// 806
fun main () {
    val TABLE_INDEX_POSITION = 9; // 9 on real, 3
    val INSTRUCTIONS_INDEX_POSITION = 5; // 10 on real, 5
    val CHAR_POS_INCREMENT = 4

    // LinkedList per column and Array per row
    var stacks = Array<LinkedList<Char>>(9) { LinkedList<Char>(listOf())}

    var lineCount = 0;
    //
    File("./c5input.txt").forEachLine {
        // mapping
        if (lineCount < TABLE_INDEX_POSITION) {
            var columnPosition = 0
            var charPosition = 1; // 1st character starts at 2
            for(i in charPosition until it.length step CHAR_POS_INCREMENT){
                val charAtPosition = it[i]
                if (charAtPosition != ' ') {
                    stacks[columnPosition].add(charAtPosition)
                }
                columnPosition++
            }

        } else if (lineCount >= INSTRUCTIONS_INDEX_POSITION && it != "" ) { // processing
            // Organize
            val match = Regex("move (\\d+) from (\\d+) to (\\d+)").find(it.toString())!! //  (\w+)
            val (move, from, to) = match.destructured

            var itemsToMove = mutableListOf<Char>()
            for(i in 0 until move.toInt()){
                itemsToMove.add(stacks[from.toInt() - 1].remove())
            }
//            val itemsToMove = stacks[from.toInt() - 1].take(move.toInt())
            println("moving item [$itemsToMove] from $from to $to, $move times")
            Collections.reverse(itemsToMove);
            for (i in itemsToMove) {
                stacks[to.toInt() - 1].add(0, i)
            }

        }
        lineCount++
    }
    println("Top letters are:")
    stacks.forEach {
        if (!it.isEmpty()) {
            println(it.remove())
        }
    }
}


