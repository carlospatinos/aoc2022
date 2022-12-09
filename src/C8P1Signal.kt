import java.io.File

fun main() {
    val MAX_LENGTH_LINE = 99 //5-99
    val MAX_NUMBER_OF_LINE = 99 //5-99
    // tmp
    val fileToExplore = "./c8input.txt"
    val UNINITIALIZED_VAL = -1
    var mapOfTrees = Array(MAX_NUMBER_OF_LINE) { IntArray(MAX_LENGTH_LINE) { -1 } }


    var visibleTrees = 0
    var rowLineInFile = 0

    File(fileToExplore).forEachLine {
        for(treeColumnCharInLine in 0 until it.length) {
            mapOfTrees[rowLineInFile][treeColumnCharInLine] = Character.getNumericValue(it[treeColumnCharInLine])
        }
        rowLineInFile++
    }




    for(rowLine in 0 until mapOfTrees.size){
        for(column in 0 until mapOfTrees[rowLine].size) {
            var currentTree = mapOfTrees[rowLine][column];
            // first and last rowLine are visible
            if ((rowLine == 0 || rowLine == mapOfTrees.size - 1) && currentTree != UNINITIALIZED_VAL) {
                visibleTrees += 1
            } else if((column == 0 || column == mapOfTrees[rowLine].size - 1) && currentTree != UNINITIALIZED_VAL) {
                visibleTrees += 1
            } else {
                var maxValInColumn = IntArray(2) //Before and After
                var maxValInRow = IntArray(2) //Before and After
                var beforeAfter = 0 //1 is after
                // if it is the shortest across rows and columns
                for (tmpCol in 0 until mapOfTrees[rowLine].size) {
                    if (tmpCol == column) {
                        beforeAfter++
                        continue
                    }
                    if (mapOfTrees[rowLine][tmpCol] > maxValInColumn[beforeAfter]) {
                        maxValInColumn[beforeAfter] = mapOfTrees[rowLine][tmpCol]
                    }
                }
                beforeAfter = 0
                for (tmpRow in 0 until mapOfTrees.size) {
                    if (tmpRow == rowLine) {
                        beforeAfter++
                        continue
                    }
                    if (mapOfTrees[tmpRow][column] > maxValInRow[beforeAfter]) {
                        maxValInRow[beforeAfter] = mapOfTrees[tmpRow][column]
                    }
                }

                if (maxValInColumn[0] < currentTree ||
                    maxValInColumn[1] < currentTree ||
                    maxValInRow[0] < currentTree ||
                    maxValInRow[1] < currentTree){
                    visibleTrees += 1
                }
            }
        }
    }

    println("Visible trees are $visibleTrees")
}


