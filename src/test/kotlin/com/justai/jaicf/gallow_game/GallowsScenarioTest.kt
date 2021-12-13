package com.justai.jaicf.gallow_game



import com.justai.jaicf.template.scenario.gameScenario
import com.justai.jaicf.test.ScenarioTest
import org.junit.jupiter.api.Test

class GallowsScenarioTest : ScenarioTest(gameScenario) {

    @Test
    fun beginGame() {
        query("/start") endsWithState ("/start/game_cycle")
    }

    @Test
    fun quitGame() {
        query("/start")
        query("/exit") endsWithState ("/exit")
    }

    @Test
    fun winGame() {
        query("/start")
        query("/win") endsWithState ("/exit")
    }

    @Test
    fun lossGame() {
        query("/start")
        query("/loss") endsWithState ("/exit")
    }

    @Test
    fun askHelp() {
        query("/start")
        query("/help") endsWithState ("/start/game_cycle")
    }


}