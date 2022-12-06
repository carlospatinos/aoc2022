import java.io.File
import java.util.*

// 806
fun main () {
    var uniqueCharsList = mutableListOf<Char>()
//
    File("./c6input.txt").forEachLine {
        val MIN_MARKER_POSITION = 13
        var marker = 4

        for(i in 0 until it.length) {
            val c = it[i]
            if (i < MIN_MARKER_POSITION){
                uniqueCharsList.add(c)
            } else {
                if(uniqueCharsList.contains(c)){
                    uniqueCharsList.removeAt(0)
                    uniqueCharsList.add(c)
                } else {
                    if (uniqueCharsList.distinct().size == uniqueCharsList.size) {
                        marker = i
                        break;
                    } else {
                        uniqueCharsList.removeAt(0)
                        uniqueCharsList.add(c)
                        continue;
                    }

                }
            }
        }
        uniqueCharsList.clear()
//        println("Marker for $it at ${marker+1}")
        println("Marker at ${marker+1}")
    }

}


