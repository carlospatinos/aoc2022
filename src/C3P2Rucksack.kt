import java.io.File

//fun mapChars(str: String) {

//}
fun main() {
    var total = 0;
    // a through z have priorities 1 through 26
    // A through Z have priorities 27 through 52
    val priorityValueBasedOnCharPosition = ('a'..'z') + ('A'..'Z')
    val commonItemInGroup = StringBuilder("111")

    var mapOfCharsAcross3Lines = mutableMapOf<Char, StringBuilder>()
    var lineNumber :Int = 0
    var group = mutableListOf<String>()
    // c3input
    File("./c3input.txt").forEachLine {


        if (lineNumber != 0 && lineNumber % 3 == 0) {

            for (elfItems in group.indices) {
                for (itemIndex in group[elfItems].indices) {
                    val currentChar = group[elfItems][itemIndex];
                    var existingValue = mapOfCharsAcross3Lines.get(currentChar)
                    if (existingValue != null) {
                        existingValue.setCharAt(elfItems, '1')
                        mapOfCharsAcross3Lines.put(currentChar, existingValue)
                    } else {
                        var default = StringBuilder("000");
                        default.setCharAt(elfItems, '1');
                        mapOfCharsAcross3Lines.put(currentChar, default)

                    }
                }
            }


            mapOfCharsAcross3Lines.forEach{ (key, value) ->
                if (value.compareTo(commonItemInGroup) == 0) {
//                    println("Common letter is $key")
                    total += priorityValueBasedOnCharPosition.indexOf(key) + 1
                    return@forEach
                }
            }

            mapOfCharsAcross3Lines.clear();
            group.clear()

        }
        group.add(it)

        lineNumber++;


    }
    println(total)


}