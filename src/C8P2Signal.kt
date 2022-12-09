import java.io.File

fun main() {
    val MAX_LENGTH_LINE = 99 //5-99
    val MAX_NUMBER_OF_LINE = 99 //5-99
    //
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



    var maxScenicScore = 0
    for(rowLine in 0 until mapOfTrees.size){
        for(column in 0 until mapOfTrees[rowLine].size) {
            var currentTree = mapOfTrees[rowLine][column];
            // first and last rowLine are visible
            if ((rowLine == 0 || rowLine == mapOfTrees.size - 1) && currentTree != UNINITIALIZED_VAL) {
                visibleTrees += 1
            } else if((column == 0 || column == mapOfTrees[rowLine].size - 1) && currentTree != UNINITIALIZED_VAL) {
                visibleTrees += 1
            } else {
                var maxNumberOfShorterTreesTillEdgeInColumn = IntArray(2) //Before and After
                var maxNumberOfShorterTreesTillEdgeInRow = IntArray(2) //Before and After
                var beforeAfter = 0 //1 is after
                // if it is the shortest across rows and columns
                for (tmpCol in 0 until mapOfTrees[rowLine].size) {
                    if (tmpCol == column) {
                        beforeAfter++
                        continue
                    }
                    if (beforeAfter == 0) {
                        if (mapOfTrees[rowLine][tmpCol] < currentTree) {
                            maxNumberOfShorterTreesTillEdgeInColumn[beforeAfter] =  (maxNumberOfShorterTreesTillEdgeInColumn[beforeAfter])+1
                        } else {
                            maxNumberOfShorterTreesTillEdgeInColumn[beforeAfter] = 1
                        }
                    } else {
                        if (mapOfTrees[rowLine][tmpCol] < currentTree) {
                            maxNumberOfShorterTreesTillEdgeInColumn[beforeAfter] =  (maxNumberOfShorterTreesTillEdgeInColumn[beforeAfter])+1
                        } else {
                            maxNumberOfShorterTreesTillEdgeInColumn[beforeAfter] =  (maxNumberOfShorterTreesTillEdgeInColumn[beforeAfter])+1
                            break
                        }
                    }
                }
                beforeAfter = 0
                for (tmpRow in 0 until mapOfTrees.size) {
                    if (tmpRow == rowLine) {
                        beforeAfter++
                        continue
                    }
                    if (beforeAfter == 0) {
                        if (mapOfTrees[tmpRow][column] < currentTree) {
                            maxNumberOfShorterTreesTillEdgeInRow[beforeAfter] =  (maxNumberOfShorterTreesTillEdgeInRow[beforeAfter])+1
                        } else {
                            maxNumberOfShorterTreesTillEdgeInRow[beforeAfter] = 1
                        }
                    } else {
                        if (mapOfTrees[tmpRow][column] < currentTree) {
                            maxNumberOfShorterTreesTillEdgeInRow[beforeAfter] =  (maxNumberOfShorterTreesTillEdgeInRow[beforeAfter])+1
                        } else {
                            maxNumberOfShorterTreesTillEdgeInRow[beforeAfter] =  (maxNumberOfShorterTreesTillEdgeInRow[beforeAfter])+1
                            break
                        }
                    }
                }

                var tmpScenicScore = maxNumberOfShorterTreesTillEdgeInRow[0] * maxNumberOfShorterTreesTillEdgeInRow[1] * maxNumberOfShorterTreesTillEdgeInColumn[0] * maxNumberOfShorterTreesTillEdgeInColumn[1]

                if(maxScenicScore < tmpScenicScore) {
                    maxScenicScore = tmpScenicScore
                }
            }
        }
    }

    println("Max Scenic Score is $maxScenicScore")
}


