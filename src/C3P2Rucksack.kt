import java.io.File

//fun mapChars(str: String) {

//}
fun main() {
    var total = 0;
    // a through z have priorities 1 through 26
    // A through Z have priorities 27 through 52
    val priorityValueBasedOnCharPosition = ('a'..'z') + ('A'..'Z')

    var mapOfCharsAcross3Lines = mutableMapOf<Char, Double>()
    var lineNumber :Int = 0
    var group = mutableListOf<String>()
    File("./c3input.txt").forEachLine {


        if (lineNumber != 0 && lineNumber % 3 == 0) {

            for (elfItems in group) {
                for (i in elfItems.indices) {
                    val currentChar = elfItems[i];
                    var existingValue = mapOfCharsAcross3Lines.get(currentChar)
                    if (existingValue != null) {
                        mapOfCharsAcross3Lines.put(currentChar, existingValue)
                    } else {
                        mapOfCharsAcross3Lines.put(currentChar, Math.pow(1.0, 1.0*i))
                    }

                    total += priorityValueBasedOnCharPosition.indexOf(currentChar) + 1
                    break;
                }
            }

            println("--------- new group")
            group.clear()
        }
        group.add(it)

        lineNumber++;


    }
    println(total)


}