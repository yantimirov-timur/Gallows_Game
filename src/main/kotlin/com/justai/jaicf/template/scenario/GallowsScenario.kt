package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram

val gameScenario = Scenario {
    state("start") {
        activators {
            regex("/start")
        }
        action {
            reactions.run {
                word = makeWord(allWords)
                //word = "ааббаа"
                encodedWord = game.encodeWord(word)
                say("Давай сыграем в игру Виселица!\nПравила игры: /help\n")
                say("Я загадал слово: ${encodedWord}\nКоличество букв: ${encodedWord.length}\n")
                say("Введи букву или полное слово")
                go("game_cycle")
            }
        }

        state("game_cycle") {
            activators {
                catchAll()
            }
            action {
                val guessedWord = request.telegram?.input
                //Try to guess letter in word
                if (guessedWord != null) {
                    if (guessedWord.length == 1) {
                        val correctLetterList = game.checkLetter(guessedWord[0], word)
                        if (correctLetterList.isNotEmpty()) {
                            if (!encodedWord.contains(guessedWord)) {
                                guessedLettersNum += correctLetterList.size
                            }
                            reactions.say("Ты угадал часть слова!")
                            encodedWord = game.fillEncodedWord(encodedWord, correctLetterList, word)
                            reactions.say(
                                "Угаданная часть слова: $encodedWord\n" +
                                        "Осталось угадать букв: ${encodedWord.length - guessedLettersNum}"
                            )
                            if (guessedLettersNum == encodedWord.length) {
                                reactions.go("/win")
                            }
                        } else {
                            errorReaction(reactions)
                        }
                    }
                    //Try to guess full word
                    else if (guessedWord[0] != '/' && guessedWord.length > 1) {
                        if (guessedWord == word) {
                            reactions.go("/win")
                        } else {
                            errorReaction(reactions)
                        }
                    }
                }
            }
        }
        state("/loss") {
            activators {
                regex("/loss")
            }
            action {
                reactions.say("Ты проиграл :(\nЗагаданное слово: $word")
                reactions.go("/exit")
            }
        }
        state("/win") {
            activators {
                regex("/win")
            }
            action {
                reactions.say("Ты выиграл! :)")
                reactions.go("/exit")
            }
        }
        state("/help") {
            activators {
                regex("/help")
            }

            action {
                reactions.say(rules)
                reactions.go("/start/game_cycle")
            }
        }
        state("/exit") {
            activators {
                regex("/exit")
            }
            action {
                errorCount = 0
                currentError = 0
                guessedLettersNum = 0
                reactions.say("Игра окончена.\nДля старта новой игры введи: /start")
            }
        }
    }
}
