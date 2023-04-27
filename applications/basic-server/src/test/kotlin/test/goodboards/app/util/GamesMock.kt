package test.goodboards.app.util

import com.goodboards.app.game.Game
import com.goodboards.app.game.GamesHelper
import io.mockk.every
import io.mockk.mockkObject

object GamesMock {
    fun makeGames(count: Int): List<Game> = DBMock.makeGameInfos(count).map {
        Game(it[0], it[1], it[2])
    }.toList()

    fun mockGames(count: Int) : List<Game> {
        val games = makeGames(count)
        mockkObject(GamesHelper)
        every { GamesHelper.getAllGames() }  returns games
        return games
    }
}