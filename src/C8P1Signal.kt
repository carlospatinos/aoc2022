import java.io.File

fun main() {
    val MAX_LENGTH_LINE = 99 //5-99
    val MAX_NUMBER_OF_LINE = 99 //5-99
    // tmp
    val fileToExplore = "./c8input.txt"
    val UNINITIALIZED_VAL = -1
    var mapOfThrees = Array(MAX_NUMBER_OF_LINE) { IntArray(MAX_LENGTH_LINE) { -1 } }


    var visibleTrees = 0
    var rowLineInFile = 0

    File(fileToExplore).forEachLine {
        for(threeColumnCharInLine in 0 until it.length) {
            mapOfThrees[rowLineInFile][threeColumnCharInLine] = Character.getNumericValue(it[threeColumnCharInLine])
        }
        rowLineInFile++
    }




    for(rowLine in 0 until mapOfThrees.size){
        for(column in 0 until mapOfThrees[rowLine].size) {
            var currentThree = mapOfThrees[rowLine][column];
            // first and last rowLine are visible
            if ((rowLine == 0 || rowLine == mapOfThrees.size - 1) && currentThree != UNINITIALIZED_VAL) {
                visibleTrees += 1
            } else if((column == 0 || column == mapOfThrees[rowLine].size - 1) && currentThree != UNINITIALIZED_VAL) {
                visibleTrees += 1
            } else {
                var maxValInColumn = IntArray(2) //Before and After
                var maxValInRow = IntArray(2) //Before and After
                var beforeAfter = 0 //1 is after
                // if it is the shortest across rows and columns
                for (tmpCol in 0 until mapOfThrees[rowLine].size) {
                    if (tmpCol == column) {
                        beforeAfter++
                        continue
                    }
                    if (mapOfThrees[rowLine][tmpCol] > maxValInColumn[beforeAfter]) {
                        maxValInColumn[beforeAfter] = mapOfThrees[rowLine][tmpCol]
                    }
                }
                beforeAfter = 0
                for (tmpRow in 0 until mapOfThrees.size) {
                    if (tmpRow == rowLine) {
                        beforeAfter++
                        continue
                    }
                    if (mapOfThrees[tmpRow][column] > maxValInRow[beforeAfter]) {
                        maxValInRow[beforeAfter] = mapOfThrees[tmpRow][column]
                    }
                }

                if (maxValInColumn[0] < currentThree ||
                    maxValInColumn[1] < currentThree ||
                    maxValInRow[0] < currentThree ||
                    maxValInRow[1] < currentThree){
                    visibleTrees += 1
                }
            }
        }
    }

    println("Visible trees are $visibleTrees")
}


