// Implement the six required functions here
import java.io.File
import kotlin.random.Random

enum class State {
    GREEN,
    YELLOW,
    NONE,
}

const val WORD_LENGTH = 5

fun isValid(word: String): Boolean = (word.length == WORD_LENGTH) && word.all { it.isLetter() }

fun readWordList(filename: String): MutableList<String> = File(filename).readLines().toMutableList()

fun pickRandomWord(words: MutableList<String>): String {
    val pos = Random.nextInt(words.size)
    val result = words[pos]
    words.removeAt(pos)
    return result
}

fun obtainGuess(attempt: Int): String {
    while (true) {
        print("Attempt $attempt: ")
        val input = readln().uppercase()
        if (isValid(input)) {
            return input
        }
        println("$input is not a valid word (must be WORD_LENGTH letters)")
    }
}

fun evaluateGuess(guess: String, target: String): List<State> {
    assert(guess.length == WORD_LENGTH)
    assert(target.length == WORD_LENGTH)

    val matches = MutableList<State>(WORD_LENGTH, { State.NONE })
    val targetArr = target.toCharArray()
    for (i in 0..WORD_LENGTH - 1) {
        if (targetArr[i] == guess[i]) {
            targetArr[i] = 'x'
            matches[i] = State.GREEN
        }
    }

    for (i in 0..WORD_LENGTH - 1) {
        val pos = targetArr.indexOf(guess[i])
        if (pos != -1) {
            targetArr[pos as Int] = 'x'
            matches[i] = State.YELLOW
        }
    }
    return matches
}

fun displayGuess(guess: String, matches: List<State>) {
    for ((index, value) in guess.withIndex()) {
        when (matches[index]) {
            State.NONE -> print("\u001B[1;39m$value")
            State.YELLOW -> print("\u001B[1;33m$value")
            State.GREEN -> print("\u001B[1;32m$value")
        }
        print("\u001B[0m")
    }
    print("\n")
}
