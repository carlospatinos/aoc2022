import java.io.File

// c1p1 - 72070
// c1p2 - 211805
fun main () {
    var maxCalariesArray = Array<Int>(3){ 0 };
    var accumulated = 0;
    File("./input.txt").forEachLine {
        if(it.equals("")) {
            if (accumulated > maxCalariesArray[0]) {
                maxCalariesArray[2] = maxCalariesArray[1]
                maxCalariesArray[1] = maxCalariesArray[0];
                maxCalariesArray[0] = accumulated;
            } else if (accumulated > maxCalariesArray[1]) {
                maxCalariesArray[2] = maxCalariesArray[1]
                maxCalariesArray[1] = accumulated;
            } else if (accumulated > maxCalariesArray[2]) {
                maxCalariesArray[2] = accumulated;
            }
            accumulated = 0;
        } else {
            accumulated += it.toInt();
        }
    }
    val sum = maxCalariesArray.sum()
    println("Max Value is $sum");
}