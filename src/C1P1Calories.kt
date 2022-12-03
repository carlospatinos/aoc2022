import java.io.File

// 72070
fun main () {
    var maxCalaries = 0;
    var accumulated = 0;
    File("./c1input.txt").forEachLine {
        if(it.equals("")) {
            if (accumulated > maxCalaries) {
                maxCalaries = accumulated;
            }
            accumulated = 0;
        } else {
            accumulated += it.toInt();
        }
    }
    println("Max Value is $maxCalaries");
}