import java.io.File

// My values:    rock = X; paper = Y; scissors = Z;
// Other player: rock = A; paper = B; scissors = C;
val valueOfMyShape = mapOf<String, Int>("X" to 1, "Y" to 2, "Z" to 3)

val whatShouldIdo = mapOf<String, Int>("X" to 0, "Y" to 3, "Z" to 6)

// Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock.
// If both players choose the same shape, the round instead ends in a draw.

// 6 for win, 3 for tie and 0 if lose
val scoreOfGame = mapOf("A Y" to 6, "B Z" to 6, "C X" to 6,
                        "A X" to 3, "B Y" to 3, "C Z" to 3,
                        "A Z" to 0, "B X" to 0, "C Y" to 0)


val matchGuide = mapOf("A X" to "A Z", "B X" to "B X", "C X" to "C Y", // Lose
    "A Y" to "A X", "B Y" to "B Y", "C Y" to "C Z", // Draw
    "A Z" to "A Y", "B Z" to "B Z", "C Z" to "C X") // Win


fun main(){
    var points = 0;
    File("./c2input.txt").forEachLine {
        points += valueOfMyShape.getValue(matchGuide.getValue(it.toString()).last().toString())
        points += whatShouldIdo.getValue(it.last().toString())
    }
    println(points)
}