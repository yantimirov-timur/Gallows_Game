package com.justai.jaicf.template.model


/**
 * Class work with input letter
 */
class Gallows {
    fun encodeWord(word: String): String {
        val result = word.toMutableList()
        for (i in word.indices) {
            result[i] = '_'
        }
        return result.joinToString(separator = "")
    }

    fun checkLetter(letter: Char, word: String): MutableList<Int> {
        val list = mutableListOf<Int>()
        for (i in word.indices) {
            if (word[i].equals(letter, ignoreCase = true)) {
                list.add(i)
            }
        }
        return list
    }

    fun fillEncodedWord(encodedWord: String, correctLetters: List<Int>, correctWord: String): String {
        val encodedList = encodedWord.toMutableList()
        for (index in correctLetters) {
            encodedList[index] = correctWord.toList()[index]
        }
        return encodedList.joinToString(separator = "")
    }
}