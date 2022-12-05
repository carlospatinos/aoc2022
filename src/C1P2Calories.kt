import java.io.File

// c1p1 - 72070
// c1p2 - 211805
fun main () {
    var maxCaloriesArray = Array<Int>(3){ 0 };
    var accumulated = 0;
    File("./c1input.txt").forEachLine {
        if(it.equals("")) {
            if (accumulated > maxCaloriesArray[0]) {
                maxCaloriesArray[2] = maxCaloriesArray[1]
                maxCaloriesArray[1] = maxCaloriesArray[0];
                maxCaloriesArray[0] = accumulated;
            } else if (accumulated > maxCaloriesArray[1]) {
                maxCaloriesArray[2] = maxCaloriesArray[1]
                maxCaloriesArray[1] = accumulated;
            } else if (accumulated > maxCaloriesArray[2]) {
                maxCaloriesArray[2] = accumulated;
            }
            accumulated = 0;
        } else {
            accumulated += it.toInt();
        }
    }
    val sum = maxCaloriesArray.sum()
    println("Max Value is $sum");
}