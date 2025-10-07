import State.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class WordleTest : StringSpec({
    "readWordList can load data/words.txt" {
        val words = readWordList("./data/words.txt")
        (words.size > 100) shouldBe true
    }

    val words = readWordList("./data/words.txt")

    "isValid denies invalid length inputs" {
        isValid("") shouldBe false
        isValid("aaaa") shouldBe false
        isValid("aaaaaa") shouldBe false
    }

    "isValid denies non alpha inputs" {
        isValid("12345") shouldBe false
        isValid("-----") shouldBe false
        isValid("abc1e") shouldBe false
        isValid("abc-e") shouldBe false
    }

    "isValid allows valid inputs" {
        isValid("abcde") shouldBe true
    }

    "pickRandomWord removes words from list" {
        val initalSize = words.size
        val word = pickRandomWord(words)
        words.size shouldBe initalSize - 1
        words.contains(word) shouldBe false
    }

    "pickRandomWord selects a word from the list" {
        val initialWords = words.toList() // copy
        val word = pickRandomWord(words)
        initialWords.contains(word) shouldBe true
    }

    "pickRandomWord selects a unique word from the list" {
        val word1 = pickRandomWord(words)
        val word2 = pickRandomWord(words)
        (word1 == word2) shouldBe false
    }

    "evaluateGuess handles trival cases" {
        val target = "aaaaa"
        evaluateGuess("aaaaa", target) shouldBe listOf(GREEN, GREEN, GREEN, GREEN, GREEN)
        evaluateGuess("bbbbb", target) shouldBe listOf(NONE, NONE, NONE, NONE, NONE)
    }

    "evaluateGuess handles partial matches" {
        val target = "abcde"
        evaluateGuess("a    ", target) shouldBe listOf(GREEN, NONE, NONE, NONE, NONE)
        evaluateGuess("b    ", target) shouldBe listOf(YELLOW, NONE, NONE, NONE, NONE)
    }

    "evaluateGuess handles duplicate input yellows" {
        val target = "bbbba"
        evaluateGuess("a    ", target) shouldBe listOf(YELLOW, NONE, NONE, NONE, NONE)
        evaluateGuess("aa   ", target) shouldBe listOf(YELLOW, NONE, NONE, NONE, NONE)
    }

    "evaluateGuess handles duplicate source yellows" {
        val target = "bbaaa"
        evaluateGuess("a    ", target) shouldBe listOf(YELLOW, NONE, NONE, NONE, NONE)
        evaluateGuess("aa   ", target) shouldBe listOf(YELLOW, YELLOW, NONE, NONE, NONE)
    }
})
