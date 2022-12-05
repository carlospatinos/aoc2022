import java.io.File

// 72070
fun main () {
    var totalFullyOverlapAreas = 0

    fun containsSubgroup(minContains: Int, maxContains:Int, minContained:Int, maxContained:Int): Boolean {
        return (minContains <= minContained && maxContains >= maxContained)
    }

    File("./c4input.txt").forEachLine {
        val pairOfElves = it.split( ",")
        val minAndMaxElfOne = pairOfElves.get(0).split("-")
        val minAndMaxElfTwo = pairOfElves.get(1).split("-")

        val minElf1 = minAndMaxElfOne.get(0)
        val maxElf1 = minAndMaxElfOne.get(1)
        val minElf2 = minAndMaxElfTwo.get(0)
        val maxElf2 = minAndMaxElfTwo.get(1)

        if (containsSubgroup(minElf1.toInt(), maxElf1.toInt(), minElf2.toInt(), maxElf2.toInt())) {
            println("Elf1 [$minElf1, $maxElf1] contains elf2 [$minElf2, $maxElf2]")
            totalFullyOverlapAreas++
        } else if (containsSubgroup(minElf2.toInt(), maxElf2.toInt(), minElf1.toInt(), maxElf1.toInt())) {
            println("Elf2 [$minElf2, $maxElf2] contains elf1 [$minElf1, $maxElf1]")
            totalFullyOverlapAreas++
        }
    }
    println("Total number is: $totalFullyOverlapAreas")
}

