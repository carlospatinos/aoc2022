import java.io.File

fun main() {
    var total = 0;
    // a through z have priorities 1 through 26
    // A through Z have priorities 27 through 52
    val priorityValueBasedOnCharPosition = ('a'..'z') + ('A'..'Z')

    File("./c3input.txt").forEachLine {
        var mapOfChars = mutableMapOf<Char, Int>()
        val mid = it.length / 2;

        for (i in it.indices) {
            val currentChar = it[i];
            if (i < mid) {
                mapOfChars.put(currentChar, 1);
            } else {
                if (mapOfChars.get(currentChar) == 1) {
                    total += priorityValueBasedOnCharPosition.indexOf(currentChar) + 1
                    break;
                }
            }

        }
    }
    println(total)
}