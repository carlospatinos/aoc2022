import java.io.File

fun main() {
    // My values:    rock = X; paper = Y; scissors = Z;
    // Other player: rock = A; paper = B; scissors = C;
    val valueOfMyShape = mapOf<String, Int>("X" to 1, "Y" to 2, "Z" to 3)

    // Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock.
    // If both players choose the same shape, the round instead ends in a draw.

    // 6 for win, 3 for tie and 0 if lose
    val scoreOfGame = mapOf(
        "A Y" to 6, "B Z" to 6, "C X" to 6,
        "A X" to 3, "B Y" to 3, "C Z" to 3,
        "A Z" to 0, "B X" to 0, "C Y" to 0
    )
    var points = 0;
    File("./c2input.txt").forEachLine {
        points += valueOfMyShape.getValue(it.last().toString())
        points += scoreOfGame.getValue(it.toString())
    }
    println(points)
}