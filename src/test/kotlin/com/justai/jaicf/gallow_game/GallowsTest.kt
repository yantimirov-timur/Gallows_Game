package com.justai.jaicf.gallow_game

import com.justai.jaicf.template.model.Gallows
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GallowsTest {
    private val testingWord = "булка"
    private val testingWord1 = "ау"
    private val gallowsGame = Gallows()

    @Test
    fun encodeWord() {
        val encodedWord1 = gallowsGame.encodeWord(testingWord)
        val expectedWord1 = "_____"
        val encodedWord2 = gallowsGame.encodeWord(testingWord1)
        val expectedWord2 = "__"

        assertEquals(expectedWord1, encodedWord1)
        assertEquals(expectedWord2, encodedWord2)
    }

    @Test
    fun checkLetter() {
        val expectedList = listOf(1)
        val indexOfLettersInWord1 = gallowsGame.checkLetter('у', testingWord)
        val indexOfLettersInWord2 = gallowsGame.checkLetter('й', testingWord)

        assertEquals(expectedList, indexOfLettersInWord1)
        assertTrue(indexOfLettersInWord2.isEmpty())
    }

    @Test
    fun fillEncodedWord() {
        var encodedWord = "_____"
        val correctLetters = listOf(1)
        val expectedWord = "_у___"
        encodedWord = gallowsGame.fillEncodedWord(encodedWord, correctLetters, testingWord)
        assertEquals(expectedWord, encodedWord)
    }
}