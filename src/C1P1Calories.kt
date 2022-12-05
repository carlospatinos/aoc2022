import java.io.File

// 72070
fun main () {
    var maxCalories = 0;
    var accumulated = 0;
    File("./c1input.txt").forEachLine {
        if(it.equals("")) {
            if (accumulated > maxCalories) {
                maxCalories = accumulated;
            }
            accumulated = 0;
        } else {
            accumulated += it.toInt();
        }
    }
    println("Max Value is $maxCalories");
}