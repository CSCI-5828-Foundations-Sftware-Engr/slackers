package test.goodboards.db

import com.goodboards.db.Game
import io.mockk.every
import io.mockk.mockk
import java.sql.PreparedStatement
import java.sql.ResultSet

class DBInterfaceTestHelpers() {

    val ids = mutableListOf<String>()
    val names = mutableListOf<String>()
    val descriptions = mutableListOf<String>()
    val iterations = mutableListOf<Boolean>()

    fun parseGames (games: List<Game>) {
        for(game in games) {
            ids.add(game.uuid)
            names.add(game.name)
            descriptions.add(game.description)
            iterations.add(true)
        }
        iterations.add(false)
    }

    fun getMockedResultSet() : ResultSet {
        val mockedResultSet: ResultSet = mockk(relaxed = true)
        every { mockedResultSet.getString("id") } returnsMany ids
        every { mockedResultSet.getString("name") } returnsMany names
        every { mockedResultSet.getString("description") } returnsMany descriptions
        every { mockedResultSet.next() } returnsMany iterations
        return mockedResultSet
    }

    fun getPreparedStatement(mockedResultSet: ResultSet) : PreparedStatement {
        val mockedStatement: PreparedStatement = mockk(relaxed = true)
        every { mockedStatement.executeQuery() } returns mockedResultSet
        return mockedStatement
    }

}