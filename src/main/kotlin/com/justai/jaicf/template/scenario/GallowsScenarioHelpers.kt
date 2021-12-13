package com.justai.jaicf.template.scenario



import com.justai.jaicf.reactions.Reactions

import com.justai.jaicf.template.data_base.WordBase
import com.justai.jaicf.template.model.Gallows

const val rules = "Правила игры: Я загадываю слово и говорю сколько в нем букв.\n" +
        "Ты должен по буквам угадать это слово. Если ты решишь угадать слово целиком, просто введи его. Ты можешь ошибиться максимум 6 раз.\n" +
        "Все слова на русском языке. Буквы не чувствительны к регистру\n" +
        "Если захочешь завершить игру, введи: /exit\n" +
        "После окончания игры введи: /start для старта новой игры\n" +
        "Удачи!"

const val maxErrorNumber = 6
val allWords = WordBase().readWords()
val game = Gallows()
var word = ""
var encodedWord = ""
var errorCount = 0
var currentError = 0
var guessedLettersNum = 0

fun makeWord(words: List<String>): String = words[words.indices.random()]

fun errorReaction(reactions: Reactions) {
    reactions.say("Ты ошибся :(")
    errorCount++
    currentError = maxErrorNumber - errorCount
    reactions.say("Осталось ошибок: $currentError")
    if (currentError == 0) {
        reactions.go("/loss")
    }
}
