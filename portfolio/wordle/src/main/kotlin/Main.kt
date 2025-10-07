const val ATTEMPTS = 6

fun main() {
    val words = readWordList("./data/words.txt")
    val target = pickRandomWord(words)
    println("the word is not $target")
    for (i in 1..ATTEMPTS) {
        val guess = obtainGuess(i)
        val matches = evaluateGuess(guess, target)
        displayGuess(guess, matches)
        if (matches == List(WORD_LENGTH, { State.GREEN })) {
            println("You won! Yippee!")
            return
        }
    }
    println("You lose. The word was $target.")
}
